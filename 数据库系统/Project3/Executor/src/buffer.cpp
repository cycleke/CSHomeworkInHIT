/**
 * @author See Contributors.txt for code contributors and overview of BadgerDB.
 *
 * @section LICENSE
 * Copyright (c) 2012 Database Group, Computer Sciences Department, University
 * of Wisconsin-Madison.
 */

#include <cassert>
#include <iostream>
#include <memory>

#include "exceptions/bad_buffer_exception.h"
#include "exceptions/buffer_exceeded_exception.h"
#include "exceptions/hash_not_found_exception.h"
#include "exceptions/page_not_pinned_exception.h"
#include "exceptions/page_pinned_exception.h"

#include "buffer.h"

namespace badgerdb {

BufMgr::BufMgr(std::uint32_t bufs) : numBufs(bufs) {
  bufDescTable = new BufDesc[bufs];

  for (FrameId i = 0; i < bufs; i++) {
    bufDescTable[i].frameNo = i;
    bufDescTable[i].valid = false;
  }

  bufPool = new Page[bufs];

  int htsize = ((((int)(bufs * 1.2)) * 2) / 2) + 1;
  hashTable = new BufHashTbl(htsize); // allocate the buffer hash table

  clockHand = bufs - 1;
}

BufMgr::~BufMgr() {

  for (unsigned int i = 0; i < numBufs; ++i) {
    auto &bufDesc = bufDescTable[i];
    if (!bufDesc.valid) { continue; }
    if (bufDesc.dirty) { bufDesc.file->writePage(bufPool[i]); }
  }

  delete hashTable;
  delete[] bufPool;
  delete[] bufDescTable;
}

void BufMgr::advanceClock() {
  ++clockHand;
  if (clockHand == numBufs) { clockHand = 0; }
}

void BufMgr::allocBuf(FrameId &frame) {
  for (unsigned int pinned_cnt = 0; pinned_cnt < numBufs;) {
    advanceClock();
    auto &bufDesc = bufDescTable[clockHand];
    if (!bufDesc.valid) {
      frame = bufDesc.frameNo;
      return;
    }
    if (bufDesc.refbit) {
      bufDesc.refbit = false;
      continue;
    }
    if (bufDesc.pinCnt > 0) {
      ++pinned_cnt;
      continue;
    }
    if (bufDesc.dirty) {
      bufDesc.file->writePage(bufPool[clockHand]);
      bufDesc.dirty = false;
    }
    frame = bufDesc.frameNo;
    try {
      hashTable->remove(bufDesc.file, bufDesc.pageNo);
    } catch (HashNotFoundException &e) {
      // std::cerr << "Can't find the bufDesc\n" << e.message() << std::endl;
    }
    return;
  }
  throw BufferExceededException();
}

void BufMgr::readPage(File *file, const PageId pageNo, Page *&page) {
  FrameId frame;
  try {
    hashTable->lookup(file, pageNo, frame);
    bufDescTable[frame].refbit = true;
    ++bufDescTable[frame].pinCnt;
  } catch (HashNotFoundException &e) {
    allocBuf(frame);
    bufPool[frame] = file->readPage(pageNo);
    hashTable->insert(file, pageNo, frame);
    bufDescTable[frame].Set(file, pageNo);
  }
  page = bufPool + frame;
}

void BufMgr::unPinPage(File *file, const PageId pageNo, const bool dirty) {
  try {
    FrameId frame;
    hashTable->lookup(file, pageNo, frame);
    auto &bufDesc = bufDescTable[frame];
    if (bufDesc.pinCnt <= 0) {
      throw PageNotPinnedException(bufDesc.file->filename(), pageNo, frame);
    }
    --bufDesc.pinCnt;
    if (dirty) { bufDesc.dirty = dirty; }
  } catch (HashNotFoundException &e) {
    // do nothing
  }
}

void BufMgr::flushFile(const File *file) {
  for (FrameId i = 0; i < numBufs; ++i) {
    auto &bufDesc = bufDescTable[i];
    if (bufDesc.file != file) { continue; }
    if (!bufDesc.valid) {
      throw BadBufferException(i, bufDesc.dirty, false, bufDesc.refbit);
    }
    if (bufDesc.pinCnt > 0) {
      throw PagePinnedException(file->filename(), bufDesc.pageNo, i);
    }
    if (bufDesc.dirty) {
      bufDesc.file->writePage(bufPool[i]);
      bufDesc.dirty = false;
    }
    hashTable->remove(file, bufDesc.pageNo);
    bufDesc.Clear();
  }
}

void BufMgr::allocPage(File *file, PageId &pageNo, Page *&page) {
  auto p = file->allocatePage();
  FrameId frame;
  allocBuf(frame);
  bufPool[frame] = p;
  pageNo = p.page_number();
  page = bufPool + frame;

  hashTable->insert(file, pageNo, frame);
  bufDescTable[frame].Set(file, pageNo);
}

void BufMgr::disposePage(File *file, const PageId PageNo) {
  try {
    FrameId frame;
    hashTable->lookup(file, PageNo, frame);
    hashTable->remove(file, PageNo);
    bufDescTable[frame].Clear();
  } catch (HashNotFoundException &e) {
    // do nothing
  }
  file->deletePage(PageNo);
}

void BufMgr::printSelf(void) {
  BufDesc *tmpbuf;
  int validFrames = 0;

  for (std::uint32_t i = 0; i < numBufs; i++) {
    tmpbuf = &(bufDescTable[i]);
    std::cout << "FrameNo:" << i << " ";
    tmpbuf->Print();

    if (tmpbuf->valid == true) validFrames++;
  }

  std::cout << "Total Number of Valid Frames:" << validFrames << "\n";
}

} // namespace badgerdb
