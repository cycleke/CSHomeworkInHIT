/**
 * @author Zhaonian Zou <znzou@hit.edu.cn>,
 * School of Computer Science and Technology,
 * Harbin Institute of Technology, China
 */

#include <math.h>
#include <stdlib.h>

#include <cstring>
#include <iostream>
#include <memory>
#include <sstream>
#include <vector>

#include "buffer.h"
#include "exceptions/buffer_exceeded_exception.h"
#include "exceptions/file_not_found_exception.h"
#include "exceptions/invalid_page_exception.h"
#include "exceptions/page_not_pinned_exception.h"
#include "exceptions/page_pinned_exception.h"
#include "executor.h"
#include "file_iterator.h"
#include "page.h"
#include "page_iterator.h"
#include "storage.h"

using namespace badgerdb;

void createDatabase(BufMgr* bufMgr, Catalog* catalog) {
  // Create table schemas
  TableSchema leftTableSchema = TableSchema::fromSQLStatement(
      "CREATE TABLE r (a CHAR(8) UNIQUE NOT NULL, b INT);");
  TableSchema rightTableSchema = TableSchema::fromSQLStatement(
      "CREATE TABLE s (b INT UNIQUE NOT NULL, c VARCHAR(8));");

  // Create table files
  string leftTableFilename = "r.tbl";
  string rightTableFilename = "s.tbl";
  File leftTableFile = File::create(leftTableFilename);
  File rightTableFile = File::create(rightTableFilename);

  // Add table schemas and filenames to catalog
  catalog->addTableSchema(leftTableSchema, leftTableFilename);
  catalog->addTableSchema(rightTableSchema, rightTableFilename);

  // Insert tuples
  int leftTableRows = 500;
  int rightTableRows = 100;

  for (int i = 0; i < leftTableRows; i++) {
    stringstream ss;
    ss << "INSERT INTO r VALUES ('r" << i << "', " << (i % rightTableRows)
       << ");";
    string tuple =
        HeapFileManager::createTupleFromSQLStatement(ss.str(), catalog);
    HeapFileManager::insertTuple(tuple, leftTableFile, bufMgr);
  }

  for (int i = 0; i < rightTableRows; i++) {
    stringstream ss;
    ss << "INSERT INTO s VALUES (" << i << ", 's" << i << "');";
    string tuple =
        HeapFileManager::createTupleFromSQLStatement(ss.str(), catalog);
    HeapFileManager::insertTuple(tuple, rightTableFile, bufMgr);
  }

  // Print all tuples in tables
  TableScanner leftTableScanner(leftTableFile, leftTableSchema, bufMgr);
  leftTableScanner.print();
  TableScanner rightTableScanner(rightTableFile, rightTableSchema, bufMgr);
  rightTableScanner.print();
}

void testOnePassJoin(BufMgr* bufMgr, Catalog* catalog) {
  TableId leftTableId = catalog->getTableId("r");
  TableId rightTableId = catalog->getTableId("s");
  TableSchema leftTableSchema = catalog->getTableSchema(leftTableId);
  TableSchema rightTableSchema = catalog->getTableSchema(rightTableId);

  // Create one-pass join operator
  File leftTableFile = File::open(catalog->getTableFilename(leftTableId));
  File rightTableFile = File::open(catalog->getTableFilename(rightTableId));
  OnePassJoinOperator joinOperator(
      leftTableFile, rightTableFile, leftTableSchema,
      rightTableSchema, catalog, bufMgr);
  TableSchema resultSchema = joinOperator.getResultTableSchema();

  // Join two tables using one-pass join
  string filename = leftTableSchema.getTableName() + "_OPJ_" +
                    rightTableSchema.getTableName() + ".tbl";
  File resultFile = File::create(filename);
  joinOperator.execute(100, resultFile);

  // Print running statistics
  joinOperator.printRunningStats();

  // Print all tuples in result
  TableScanner scanner(resultFile, resultSchema, bufMgr);
  scanner.print();
}

void testNestedLoopJoin(BufMgr* bufMgr, Catalog* catalog) {
  TableId leftTableId = catalog->getTableId("r");
  TableId rightTableId = catalog->getTableId("s");
  TableSchema leftTableSchema = catalog->getTableSchema(leftTableId);
  TableSchema rightTableSchema = catalog->getTableSchema(rightTableId);

  // Create one-pass join operator
  File leftTableFile = File::open(catalog->getTableFilename(leftTableId));
  File rightTableFile = File::open(catalog->getTableFilename(rightTableId));
  NestedLoopJoinOperator joinOperator(
      leftTableFile, rightTableFile, leftTableSchema,
      rightTableSchema, catalog, bufMgr);
  TableSchema resultSchema = joinOperator.getResultTableSchema();

  // Join two tables using one-pass join
  string filename = leftTableSchema.getTableName() + "_NLJ_" +
                    rightTableSchema.getTableName() + ".tbl";
  File resultFile = File::create(filename);
  joinOperator.execute(10, resultFile);

  // Print running statistics
  joinOperator.printRunningStats();

  // Print all tuples in result
  TableScanner scanner(resultFile, resultSchema, bufMgr);
  scanner.print();
}

void testGraceHashJoin(BufMgr* bufMgr, Catalog* catalog) {
  TableId leftTableId = catalog->getTableId("r");
  TableId rightTableId = catalog->getTableId("s");
  TableSchema leftTableSchema = catalog->getTableSchema(leftTableId);
  TableSchema rightTableSchema = catalog->getTableSchema(rightTableId);
  rightTableSchema.print();

  // Create one-pass join operator
  File leftTableFile = File::open(catalog->getTableFilename(leftTableId));
  File rightTableFile = File::open(catalog->getTableFilename(rightTableId));
  GraceHashJoinOperator joinOperator(
      leftTableFile, rightTableFile, leftTableSchema,
      rightTableSchema, catalog, bufMgr);
  TableSchema resultSchema = joinOperator.getResultTableSchema();

  // Join two tables using one-pass join
  string filename = leftTableSchema.getTableName() + "_GHJ_" +
                    rightTableSchema.getTableName() + ".tbl";
  File resultFile = File::create(filename);
  cout << resultFile.filename() << endl;
  joinOperator.execute(50, resultFile);

  // Print running statistics
  joinOperator.printRunningStats();

  // Print all tuples in result
  TableScanner scanner(resultFile, resultSchema, bufMgr);
  scanner.print();
}

int main() {
  // Create buffer pool
  int availableBufPages = 256;
  BufMgr* bufMgr = new BufMgr(availableBufPages);

  // Create system catalog
  Catalog* catalog = new Catalog("lab3");

  // Create tables
  createDatabase(bufMgr, catalog);

  // Test one-pass join operator
  // cout << "Test One-Pass Join ..." << endl;
  // testOnePassJoin(bufMgr, catalog);

  // Test nested-loop join operator
  cout << "Test Nested-Loop Join ..." << endl;
  testNestedLoopJoin(bufMgr, catalog);

  // Test grace-loop join operator
  // cout << "Test Grace Hash Join ..." << endl;
  // testGraceHashJoin(bufMgr, catalog);

  // Destroy objects
  delete bufMgr;
  delete catalog;

  cout << "Test Completed" << endl;

  return 0;
}