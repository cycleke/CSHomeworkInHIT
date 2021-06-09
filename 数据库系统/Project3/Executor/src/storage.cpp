/**
 * @author Zhaonian Zou <znzou@hit.edu.cn>,
 * School of Computer Science and Technology,
 * Harbin Institute of Technology, China
 */

#include "storage.h"
#include <regex>
#include "exceptions/invalid_record_exception.h"
#include "file_iterator.h"
#include "page_iterator.h"

using namespace std;

namespace badgerdb {

RecordId HeapFileManager::insertTuple(const string& tuple,
                                      File& file,
                                      BufMgr* bufMgr) {
  badgerdb::Page* buffered_page = nullptr;
  RecordId recordId = {};
  // iterate all the pages in the file
  for (badgerdb::FileIterator iter = file.begin(); iter != file.end(); ++iter) {
    badgerdb::Page page = *iter;
    // find a page in the certain file that has enough space for the tuple
    if (page.hasSpaceForRecord(tuple)) {
      bufMgr->readPage(&file, page.page_number(), buffered_page);
      recordId = buffered_page->insertRecord(tuple);
      // unpin the page after we finished inserting the tuple
      bufMgr->unPinPage(&file, buffered_page->page_number(), true);
      // write the change back to the file
      bufMgr->flushFile(&file);
      return recordId;
    }
  }
  // no available page found in the file
  // then allocate a new page
  badgerdb::Page new_page = file.allocatePage();
  bufMgr->readPage(&file, new_page.page_number(), buffered_page);
  recordId = buffered_page->insertRecord(tuple);
  // unpin the page after we finished inserting the tuple
  bufMgr->unPinPage(&file, buffered_page->page_number(), true);
  // write the change back to the file
  bufMgr->flushFile(&file);
  return recordId;
}

void HeapFileManager::deleteTuple(const RecordId& rid,
                                  File& file,
                                  BufMgr* bufMgr) {
  // iterate all the pages in the file
  for (auto page : file) {
    badgerdb::Page* page_i;
    bufMgr->readPage(&file, page.page_number(), page_i);
    try {
      page_i->deleteRecord(rid);
    } catch (InvalidRecordException& e) {
      // did not find the correspond rid in this page
      bufMgr->unPinPage(&file, page_i->page_number(), false);
      continue;  // try to find the record in the next page
    };
    // has deleted the record
    bufMgr->unPinPage(&file, page_i->page_number(), true);
    break;
  }
  // write the change back to the file
  bufMgr->flushFile(&file);
}

string HeapFileManager::createTupleFromSQLStatement(const string& sql,
                                                    const Catalog* catalog) {
  smatch result;
  regex pattern("INSERT INTO (.*?) VALUES \\((.*?)\\);");
  regex_match(sql, result, pattern);
  string table_name = result[1];
  TableId tableId = catalog->getTableId(table_name);
  const TableSchema& tableSchema = catalog->getTableSchema(tableId);

  string attr_content = result[2];
  regex sep1(",");

  string tuple_layout;
  int now_insert_i = 0;  // layout.size() % 4 == 0 otherwise add 0 to the layout

  sregex_token_iterator attr_tokens(attr_content.cbegin(), attr_content.cend(),
                                    sep1, -1);
  std::sregex_token_iterator attr_end;
  for (int i = 0; attr_tokens != attr_end; ++attr_tokens) {
    // TODO how to represent tuple
    // TODO \0 int 0000 will be regarded as end in the c++ string

    DataType dataType = tableSchema.getAttrType(i);

    string token = *attr_tokens;

    // delete the white spaces
    int space_index = token.find(' ');
    while (space_index != std::string::npos) {
      token.replace(space_index, 1, "");
      space_index = token.find(' ');
    }

    // delete the single quotes spaces
    int quo_index = token.find('\'');
    while (quo_index != std::string::npos) {
      token.replace(quo_index, 1, "");
      quo_index = token.find('\'');
    }

    switch (dataType) {  // (int) 56 (0011 1000) -> (char) '\0''\0''\0''8'
      case INT: {        // convert int value into 4 byte representation
        int value = atoi(token.c_str());
        for (int j = 0; j < 4; ++j) {
          char c = value;
          tuple_layout.insert(now_insert_i, 1, c);
          value = value >> 8;
        }
        now_insert_i += 4;
        break;
      }
      case CHAR: {  // (char(5) ) 'abc' -> 'abc00'
        int max_len = tableSchema.getAttrMaxSize(i);
        tuple_layout += token;
        now_insert_i += token.size();
        for (int j = 0; j < (max_len - token.size()); ++j) {
          tuple_layout.insert(now_insert_i, 1, '0');
          now_insert_i++;
        }
        // align length to the multiple of 4
        unsigned align_ = (4 - (max_len % 4)) % 4;
        for (int k = 0; k < align_; ++k) {
          tuple_layout.insert(now_insert_i, 1, '0');
          now_insert_i++;
        }
        break;
      }
      case VARCHAR: {  // (varchar(8) ) 'abc' -> '3''abc'  (3 refer to the ascii
                       // code number correspond alpha)
        unsigned true_len = token.size();  // assume -> 0< length < 128
        char len_2_char = true_len;
        tuple_layout.insert(now_insert_i, 1, len_2_char);
        now_insert_i++;
        tuple_layout += token;
        now_insert_i += token.size();
        // align length to the multiple of 4
        unsigned align_ = (4 - ((true_len + 1) % 4)) % 4;
        for (int k = 0; k < align_; ++k) {
          tuple_layout.insert(now_insert_i, 1, '0');
          now_insert_i++;
        }
        break;
      }
    }
    i++;
  }
  return tuple_layout;
}
}  // namespace badgerdb