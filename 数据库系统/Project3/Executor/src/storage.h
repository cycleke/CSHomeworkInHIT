/**
 * @author Zhaonian Zou <znzou@hit.edu.cn>,
 * School of Computer Science and Technology,
 * Harbin Institute of Technology, China
 */

#pragma once

#include "buffer.h"
#include "catalog.h"
#include "file.h"
#include "types.h"

using namespace std;

namespace badgerdb {

/**
 * Heap file manager for inserting and deleting tuples
 */
class HeapFileManager {
 public:
  /**
   * Insert a tuple to a table
   */
  static RecordId insertTuple(const string& tuple, File& file, BufMgr* bufMgr);

  /**
   * Delete a tuple from a table
   */
  static void deleteTuple(const RecordId& rid, File& file, BufMgr* bufMgr);

  /**
   * Create a tuple from an SQL statement
   */
  static string createTupleFromSQLStatement(const string& sql,
                                            const Catalog* catalog);
};
}  // namespace badgerdb