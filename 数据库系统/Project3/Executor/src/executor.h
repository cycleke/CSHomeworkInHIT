/**
 * @author Zhaonian Zou <znzou@hit.edu.cn>,
 * School of Computer Science and Technology,
 * Harbin Institute of Technology, China
 */

#pragma once

#include "buffer.h"
#include "catalog.h"
#include "file.h"
#include "schema.h"
#include "storage.h"

using namespace std;

namespace badgerdb {

/**
 * Table scanner
 */
class TableScanner {
 private:
  /**
   * Table filename
   */
  const File& tableFile;

  /**
   * Table schema
   */
  const TableSchema& tableSchema;

  /**
   * Buffer pool manager
   */
  BufMgr* bufMgr;

 public:
  TableScanner(const File& tableFile,
               const TableSchema& tableSchema,
               BufMgr* bufMgr)
      : tableFile(tableFile), tableSchema(tableSchema), bufMgr(bufMgr) {
    // nothing
  }

  ~TableScanner() {
    // nothing
  }

  /**
   * Print tuples in the table
   */
  void print() const;
};

/**
 * Join Operator
 */
class JoinOperator {
 protected:
  /**
   * Data file of the left table
   */
  File& leftTableFile;

  /**
   * Data file of the right table
   */
  File& rightTableFile;

  /**
   * Schema of the left table
   */
  const TableSchema& leftTableSchema;

  /**
   * Schema of the right table
   */
  const TableSchema& rightTableSchema;

  /**
   * Schema of the result table
   */
  TableSchema resultTableSchema;

  /**
   * System catalog
   */
  const Catalog* catalog;

  /**
   * Buffer pool manager
   */
  BufMgr* bufMgr;

  /**
   * Is the executor completed
   */
  bool isComplete;

  /**
   * Number of result tuples
   */
  int numResultTuples;

  /**
   * Number of buffer pages actually used by the executor
   */
  int numUsedBufPages;

  /**
   * Number of I/Os carried out by the executor
   */
  int numIOs;

 public:
  /**
   * Constructor
   */
  JoinOperator(File& leftTableFile,
               File& rightTableFile,
               const TableSchema& leftTableSchema,
               const TableSchema& rightTableSchema,
               const Catalog* catalog,
               BufMgr* bufMgr);

  /**
   * Destructor
   */
  ~JoinOperator() {
    // nothing
  }

  /**
   * Is the algorithm complete?
   */
  bool isCompleted() const { return isComplete; }

  /**
   * Get the operator's name
   */
  virtual string getOperatorName() const { return "JOIN"; }

  /**
   * Print the running statistics of the executor
   */
  virtual void printRunningStats() const;

  /**
   * Execute the join algorithm
   * @return If succeeded, return true
   */
  virtual bool execute(int numAvailableBufPages, File& resultFile) = 0;

  /**
   * Get the schema of the result table
   */
  const TableSchema& getResultTableSchema() const { return resultTableSchema; }

  /**
   * Get number of result tuples
   */
  int getNumResultTuples() const { return numResultTuples; }

  /**
   * Get number of buffer pages used by the executor
   */
  int getNumUsedBufPages() const { return numUsedBufPages; }

  /**
   * Get number of I/Os carried out by the executor
   */
  int getNumIOs() const { return numIOs; }

  /**
   * Create the result schema using the input schemas
   */
  static TableSchema createResultTableSchema(
      const TableSchema& leftTableSchema,
      const TableSchema& rightTableSchema);

 protected:
  /**
   * Get common attributes in all input tables
   */
  vector<Attribute> getCommonAttributes(
      const TableSchema& leftTableSchema,
      const TableSchema& rightTableSchema) const;

  /**
   * Join two tuples
   */
  string joinTuples(string leftTuple,
                    string rightTuple,
                    const TableSchema& leftTableSchema,
                    const TableSchema& rightTableSchema) const;
};

class OnePassJoinOperator : public JoinOperator {
 public:
  /**
   * Constructor
   */
  OnePassJoinOperator(File& leftTableFile,
                      File& rightTableFile,
                      const TableSchema& leftTableSchema,
                      const TableSchema& rightTableSchema,
                      const Catalog* catalog,
                      BufMgr* bufMgr)
      : JoinOperator(leftTableFile,
                     rightTableFile,
                     leftTableSchema,
                     rightTableSchema,
                     catalog,
                     bufMgr) {
    // nothing
  }

  /**
   * Destructor
   */
  ~OnePassJoinOperator() {
    // nothing
  }

  /**
   * Get oprator's name (overrided)
   */
  string getOperatorName() const { return "ONE_PASS_JOIN"; }

  bool execute(int numAvailableBufPages, File& resultFile);
};

class NestedLoopJoinOperator : public JoinOperator {
 public:
  /**
   * Constructor
   */
  NestedLoopJoinOperator(File& leftTableFile,
                         File& rightTableFile,
                         const TableSchema& leftTableSchema,
                         const TableSchema& rightTableSchema,
                         const Catalog* catalog,
                         BufMgr* bufMgr)
      : JoinOperator(leftTableFile,
                     rightTableFile,
                     leftTableSchema,
                     rightTableSchema,
                     catalog,
                     bufMgr) {
    // nothing
  }

  /**
   * Destructor
   */
  ~NestedLoopJoinOperator() {
    // nothing
  }

  /**
   * Get oprator's name (overrided)
   */
  string getOperatorName() const { return "NESTED_LOOP_JOIN"; }

  bool execute(int numAvailableBufPages, File& resultFile);
};

/**
 * Bucket Id type
 */
typedef std::uint32_t BucketId;

class GraceHashJoinOperator : public JoinOperator {
 private:
  /**
   * Number of buckets
   */
  int numBuckets;

  /**
   * Hash function from key to bucket Id
   */
  BucketId hash(const string& key) const;

 public:
  /**
   * Constructor
   */
  GraceHashJoinOperator(File& leftTableFile,
                        File& rightTableFile,
                        const TableSchema& leftTableSchema,
                        const TableSchema& rightTableSchema,
                        const Catalog* catalog,
                        BufMgr* bufMgr)
      : JoinOperator(leftTableFile,
                     rightTableFile,
                     leftTableSchema,
                     rightTableSchema,
                     catalog,
                     bufMgr) {
    // nothing
  }

  /**
   * Destructor
   */
  ~GraceHashJoinOperator() {
    // nothing
  }

  /**
   * Get oprator's name (overrided)
   */
  string getOperatorName() const { return "GRACE_HASH_JOIN"; }

  /**
   * Print running statistics (overrided)
   */
  void printRunningStats() const {
    JoinOperator::printRunningStats();
    cout << "# Buckets: " << numBuckets << endl;
  }

  /**
   * Get number of buckets
   */
  int getNumBuckets() const { return numBuckets; }

  bool execute(int numAvailableBufPages, File& resultFile);
};

}  // namespace badgerdb