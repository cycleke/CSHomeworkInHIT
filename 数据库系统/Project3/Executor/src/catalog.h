/**
 * @author Zhaonian Zou <znzou@hit.edu.cn>,
 * School of Computer Science and Technology,
 * Harbin Institute of Technology, China
 */

#pragma once

#include <map>
#include <string>
#include <utility>

#include "schema.h"

using namespace std;

namespace badgerdb {

/**
 * Table Id
 */
typedef std::uint32_t TableId;

/**
 * System catalog
 */
class Catalog {
 private:
  /**
   * Database name
   */
  string dbName;

  /**
   * Mapping table name to table Id
   */
  map<string, TableId> tableIds;

  /**
   * Mapping table Id to table schema
   */
  map<TableId, TableSchema> tableSchemas;

  /**
   * Mapping table id to table filename
   */
  map<TableId, string> tableFilenames;

  /**
   * Next available table Id
   */
  TableId nextTableId;

 public:
  /**
   * Constructor
   */
  Catalog(const string& dbName) : dbName(std::move(dbName)), nextTableId(0) {
    // nothing
  }

  /**
   * Destructor
   */
  ~Catalog() {
    // nothing
  }

  /**
   * Get database name
   */
  const string& getDatabaseName() const { return dbName; }

  /**
   * Get table Id
   */
  const TableId& getTableId(const string& tableName) const {
    return tableIds.at(tableName);
  }

  /**
   * Get table schema
   */
  const TableSchema& getTableSchema(const TableId& id) const {
    return tableSchemas.at(id);
  }

  /**
   * Get table file
   */
  const string& getTableFilename(const TableId& id) const {
    return tableFilenames.at(id);
  }

  /**
   * CREATE TABLE
   */
  TableId addTableSchema(const TableSchema& tableSchema,
                         const string& tableFilename) {
    tableIds.insert(
        pair<string, TableId>(tableSchema.getTableName(), nextTableId));
    tableSchemas.insert(pair<TableId, TableSchema>(nextTableId, tableSchema));
    tableFilenames.insert(pair<TableId, string>(nextTableId, tableFilename));
    return nextTableId++;
  }

  /**
   * DROP TABLE
   */
  void deleteTableSchema(const TableId& id) {
    tableIds.erase(getTableSchema(id).getTableName());
    tableSchemas.erase(id);
    tableFilenames.erase(id);
  }

  /**
   * ALTER TABLE
   */
  void setTableSchema(const TableId& id, const TableSchema& tableSchema) {
    tableSchemas.at(id) = tableSchema;
  }
};

}  // namespace badgerdb