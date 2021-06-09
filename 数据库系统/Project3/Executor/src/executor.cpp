/**
 * @author Zhaonian Zou <znzou@hit.edu.cn>,
 * School of Computer Science and Technology,
 * Harbin Institute of Technology, China
 */

#include <cmath>
#include <ctime>
#include <functional>
#include <iostream>
#include <string>
#include <utility>

#include "exceptions/buffer_exceeded_exception.h"
#include "file_iterator.h"
#include "page_iterator.h"
#include "storage.h"

#include "executor.h"

using namespace std;

namespace badgerdb {

void TableScanner::print() const {
  badgerdb::File file = badgerdb::File::open(tableFile.filename());
  for (badgerdb::FileIterator iter = file.begin(); iter != file.end(); ++iter) {
    badgerdb::Page page = *iter;
    badgerdb::Page *buffered_page;
    bufMgr->readPage(&file, page.page_number(), buffered_page);

    for (badgerdb::PageIterator page_iter = buffered_page->begin();
         page_iter != buffered_page->end(); ++page_iter) {
      string key = *page_iter;
      string print_key = "(";
      int current_index = 0;
      for (int i = 0; i < tableSchema.getAttrCount(); ++i) {
        switch (tableSchema.getAttrType(i)) {
        case INT: {
          int true_value = 0;
          for (int j = 0; j < 4; ++j) {
            if (std::string(key, current_index + j, 1)[0] == '\0') {
              continue; // \0 is actually representing 0
            }
            true_value +=
                (std::string(key, current_index + j, 1))[0] * pow(128, 3 - j);
          }
          print_key += to_string(true_value);
          current_index += 4;
          break;
        }
        case CHAR: {
          int max_len = tableSchema.getAttrMaxSize(i);
          print_key += std::string(key, current_index, max_len);
          current_index += max_len;
          current_index +=
              (4 - (max_len % 4)) % 4; // align to the multiple of 4
          break;
        }
        case VARCHAR: {
          int actual_len = key[current_index];
          current_index++;
          print_key += std::string(key, current_index, actual_len);
          current_index += actual_len;
          current_index +=
              (4 - ((actual_len + 1) % 4)) % 4; // align to the multiple of 4
          break;
        }
        }
        print_key += ",";
      }
      print_key[print_key.size() - 1] = ')'; // change the last ',' to ')'
      cout << print_key << endl;
    }
    bufMgr->unPinPage(&file, page.page_number(), false);
  }
  bufMgr->flushFile(&file);
}

JoinOperator::JoinOperator(File &leftTableFile, File &rightTableFile,
                           const TableSchema &leftTableSchema,
                           const TableSchema &rightTableSchema,
                           const Catalog *catalog, BufMgr *bufMgr)
    : leftTableFile(leftTableFile), rightTableFile(rightTableFile),
      leftTableSchema(leftTableSchema), rightTableSchema(rightTableSchema),
      resultTableSchema(
          createResultTableSchema(leftTableSchema, rightTableSchema)),
      catalog(catalog), bufMgr(bufMgr), isComplete(false) {
  // nothing
}

TableSchema
JoinOperator::createResultTableSchema(const TableSchema &leftTableSchema,
                                      const TableSchema &rightTableSchema) {
  vector<Attribute> attrs;

  // first add all the left table attrs to the result table
  for (int k = 0; k < leftTableSchema.getAttrCount(); ++k) {
    Attribute new_attr = Attribute(
        leftTableSchema.getAttrName(k), leftTableSchema.getAttrType(k),
        leftTableSchema.getAttrMaxSize(k), leftTableSchema.isAttrNotNull(k),
        leftTableSchema.isAttrUnique(k));
    attrs.push_back(new_attr);
  }

  // test every right table attrs, if it doesn't have the same attr(name and
  // type) in the left table, then add it to the result table
  for (int i = 0; i < rightTableSchema.getAttrCount(); ++i) {
    bool has_same = false;
    for (int j = 0; j < leftTableSchema.getAttrCount(); ++j) {
      if ((leftTableSchema.getAttrType(j) == rightTableSchema.getAttrType(i)) &&
          (leftTableSchema.getAttrName(j) == rightTableSchema.getAttrName(i))) {
        has_same = true;
      }
    }
    if (!has_same) {
      Attribute new_attr = Attribute(
          rightTableSchema.getAttrName(i), rightTableSchema.getAttrType(i),
          rightTableSchema.getAttrMaxSize(i), rightTableSchema.isAttrNotNull(i),
          rightTableSchema.isAttrUnique(i));
      attrs.push_back(new_attr);
    }
  }
  return TableSchema("TEMP_TABLE", attrs, true);
}

void JoinOperator::printRunningStats() const {
  cout << "# Result Tuples: " << numResultTuples << endl;
  cout << "# Used Buffer Pages: " << numUsedBufPages << endl;
  cout << "# I/Os: " << numIOs << endl;
}

vector<Attribute>
JoinOperator::getCommonAttributes(const TableSchema &leftTableSchema,
                                  const TableSchema &rightTableSchema) const {
  vector<Attribute> common_attrs;
  for (int i = 0; i < rightTableSchema.getAttrCount(); ++i) {
    for (int j = 0; j < leftTableSchema.getAttrCount(); ++j) {
      if ((leftTableSchema.getAttrType(j) == rightTableSchema.getAttrType(i)) &&
          (leftTableSchema.getAttrName(j) == rightTableSchema.getAttrName(i))) {
        Attribute new_attr = Attribute(rightTableSchema.getAttrName(i),
                                       rightTableSchema.getAttrType(i),
                                       rightTableSchema.getAttrMaxSize(i),
                                       rightTableSchema.isAttrNotNull(i),
                                       rightTableSchema.isAttrUnique(i));
        common_attrs.push_back(new_attr);
      }
    }
  }
  return common_attrs;
}

/**
 * use the original key to generate the search key
 * @param key
 * @param common_attrs
 * @param TableSchema
 * @return
 */
string construct_search_key(string key, vector<Attribute> &common_attrs,
                            const TableSchema &TableSchema) {
  string search_key;
  int current_index = 0;
  int current_attr_index = 0;
  for (int i = 0; i < TableSchema.getAttrCount(); ++i) {
    switch (TableSchema.getAttrType(i)) {
    case INT: {
      if (TableSchema.getAttrName(i) ==
              common_attrs[current_attr_index].attrName &&
          TableSchema.getAttrType(i) ==
              common_attrs[current_attr_index].attrType) {
        search_key += std::string(key, current_index, 4);
        current_attr_index++;
      }
      current_index += 4;
      break;
    }
    case CHAR: {
      int max_len = TableSchema.getAttrMaxSize(i);
      if (TableSchema.getAttrName(i) ==
              common_attrs[current_attr_index].attrName &&
          TableSchema.getAttrType(i) ==
              common_attrs[current_attr_index].attrType) {
        search_key += std::string(key, current_index, max_len);
        current_attr_index++;
      }
      current_index += max_len;
      current_index += (4 - (max_len % 4)) % 4;
      ; // align to the multiple of 4
      break;
    }
    case VARCHAR: {
      int actual_len = key[current_index];
      current_index++;
      if (TableSchema.getAttrName(i) ==
              common_attrs[current_attr_index].attrName &&
          TableSchema.getAttrType(i) ==
              common_attrs[current_attr_index].attrType) {
        search_key += std::string(key, current_index, actual_len);
        current_attr_index++;
      }
      current_index += actual_len;
      current_index +=
          (4 - ((actual_len + 1) % 4)) % 4; // align to the multiple of 4
      break;
    }
    }
    if (current_attr_index >= common_attrs.size()) break;
  }
  return search_key;
}

string JoinOperator::joinTuples(string leftTuple, string rightTuple,
                                const TableSchema &leftTableSchema,
                                const TableSchema &rightTableSchema) const {
  int cur_right_index = 0; // current substring index in the right table key
  string result_tuple = leftTuple;

  for (int i = 0; i < rightTableSchema.getAttrCount(); ++i) {
    bool has_same = false;
    for (int j = 0; j < leftTableSchema.getAttrCount(); ++j) {
      if ((leftTableSchema.getAttrType(j) == rightTableSchema.getAttrType(i)) &&
          (leftTableSchema.getAttrName(j) == rightTableSchema.getAttrName(i))) {
        has_same = true;
      }
    }
    // if the key is only owned by right table, add it to the result tuple
    switch (rightTableSchema.getAttrType(i)) {
    case INT: {
      if (!has_same) {
        result_tuple += std::string(rightTuple, cur_right_index, 4);
      }
      cur_right_index += 4;
      break;
    }
    case CHAR: {
      int max_len = rightTableSchema.getAttrMaxSize(i);
      if (!has_same) {
        result_tuple += std::string(rightTuple, cur_right_index, max_len);
      }
      cur_right_index += max_len;
      unsigned align_ = (4 - (max_len % 4)) % 4; // align to the multiple of
                                                 // 4
      for (int k = 0; k < align_; ++k) {
        result_tuple += "0";
        cur_right_index++;
      }
      break;
    }
    case VARCHAR: {
      int actual_len = rightTuple[cur_right_index];
      result_tuple += std::string(rightTuple, cur_right_index, 1);
      cur_right_index++;
      if (!has_same) {
        result_tuple += std::string(rightTuple, cur_right_index, actual_len);
      }
      cur_right_index += actual_len;
      unsigned align_ =
          (4 - ((actual_len + 1) % 4)) % 4; // align to the multiple of 4
      for (int k = 0; k < align_; ++k) {
        result_tuple += "0";
        cur_right_index++;
      }
      break;
    }
    }
  }
  return result_tuple;
}

bool OnePassJoinOperator::execute(int numAvailableBufPages, File &resultFile) {
  if (isComplete) return true;

  numResultTuples = 0;
  numUsedBufPages = 0;
  numIOs = 0;

  // TODO: Execute the join algorithm

  isComplete = true;
  return true;
}

static size_t tableSize(const File &tableFile) {
  size_t size = 0;
  auto file = tableFile;
  for (auto iter = file.begin(); iter != file.end(); ++iter) { ++size; }
  return size;
}

static void parseTuple(const TableSchema &tableSchema, const string &key,
                       vector<string> &tuple) {
  tuple.clear();
  tuple.reserve(tableSchema.getAttrCount());
  for (int i = 0, current_index = 0; i < tableSchema.getAttrCount(); ++i) {
    switch (tableSchema.getAttrType(i)) {
    case INT: {
      tuple.push_back(string(key, current_index, 4));
      current_index += 4;
      break;
    }
    case CHAR: {
      int max_len = tableSchema.getAttrMaxSize(i), last = current_index;
      current_index += max_len;
      current_index += (4 - (max_len % 4)) % 4; // align to the multiple of 4
      tuple.push_back(string(key, last, current_index - last));
      break;
    }
    case VARCHAR: {
      int actual_len = key[current_index], last = current_index;
      current_index++;
      current_index += actual_len;
      current_index +=
          (4 - ((actual_len + 1) % 4)) % 4; // align to the multiple of 4
      tuple.push_back(string(key, last, current_index - last));
      break;
    }
    }
  }
}

bool NestedLoopJoinOperator::execute(int numAvailableBufPages,
                                     File &resultFile) {
  if (isComplete) return true;

  numResultTuples = 0;
  numUsedBufPages = 0;
  numIOs = 0;

  // 使用小的部分当作 S

  // File s_file = File::open("s.tbl");
  // File r_file = File::open("r.tbl");
  // File result_file = File::open(resultFile.filename());
  File s_file = leftTableFile;
  File r_file = rightTableFile;
  TableSchema s_schema = leftTableSchema;
  TableSchema r_schema = rightTableSchema;

  if (tableSize(leftTableFile) > tableSize(rightTableFile)) {
    s_file = rightTableFile, s_schema = rightTableSchema;
    r_file = leftTableFile, r_schema = leftTableSchema;
  }

  int buffer_cap = numAvailableBufPages - 1, buffer_size;
  Page *pages[buffer_cap], *r_page;

  auto s_it = s_file.begin();
  while (s_it != s_file.end()) {
    for (buffer_size = 0; buffer_size < buffer_cap && s_it != s_file.end();
         ++s_it, ++buffer_size) {
      ++numIOs, ++numUsedBufPages;
      bufMgr->readPage(&s_file, (*s_it).page_number(), pages[buffer_size]);
    }

    vector<string> s_tuple, r_tuple;
    string raw_tuple;

    for (auto r_it = r_file.begin(); r_it != r_file.end(); ++r_it) {
      ++numIOs;
      bufMgr->readPage(&r_file, (*r_it).page_number(), r_page);

      for (auto r_tuple_iter = r_page->begin(); r_tuple_iter != r_page->end();
           ++r_tuple_iter) {
        parseTuple(r_schema, *r_tuple_iter, r_tuple);
        for (int i = 0; i < buffer_size; ++i) {
          for (auto s_tuple_iter = pages[i]->begin();
               s_tuple_iter != pages[i]->end(); ++s_tuple_iter) {
            parseTuple(s_schema, *s_tuple_iter, s_tuple);
            bool succeess = true;
            raw_tuple.clear();
            for (int i = 0; i < resultTableSchema.getAttrCount(); ++i) {
              auto attr = resultTableSchema.getAttrName(i);
              auto s_have_attr = s_schema.hasAttr(attr);
              auto r_have_attr = r_schema.hasAttr(attr);
              if (s_have_attr && r_have_attr) {
                auto s_raw = s_tuple[s_schema.getAttrNum(attr)];
                auto r_raw = r_tuple[r_schema.getAttrNum(attr)];
                if (s_raw != r_raw) {
                  succeess = false;
                  break;
                }
                raw_tuple += s_raw;
              } else if (s_have_attr) {
                auto s_raw = s_tuple[s_schema.getAttrNum(attr)];
                raw_tuple += s_raw;
              } else {
                assert(r_have_attr);
                auto r_raw = r_tuple[r_schema.getAttrNum(attr)];
                raw_tuple += r_raw;
              }
            }
            if (succeess) {
              ++numResultTuples;
              HeapFileManager::insertTuple(raw_tuple, resultFile, bufMgr);
            }
          }
        }
      }
      bufMgr->unPinPage(&r_file, (*r_it).page_number(), false);
    }
    for (int i = 0; i < buffer_size; ++i) {
      bufMgr->unPinPage(&s_file, (*pages[i]).page_number(), false);
    }
  }

  ++numUsedBufPages;

  isComplete = true;
  return true;
}

BucketId GraceHashJoinOperator::hash(const string &key) const {
  std::hash<string> strHash;
  return strHash(key) % numBuckets;
}

bool GraceHashJoinOperator::execute(int numAvailableBufPages,
                                    File &resultFile) {
  if (isComplete) return true;

  numResultTuples = 0;
  numUsedBufPages = 0;
  numIOs = 0;

  // TODO: Execute the join algorithm

  isComplete = true;
  return true;
}

} // namespace badgerdb
