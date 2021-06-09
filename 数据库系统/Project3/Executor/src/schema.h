/**
 * @author Zhaonian Zou <znzou@hit.edu.cn>,
 * School of Computer Science and Technology,
 * Harbin Institute of Technology, China
 */

#pragma once

#include <string>
#include <utility>
#include <vector>

using namespace std;

namespace badgerdb {

/**
 * Data type definitions: INT, CHAR(n), VARCHAR(n)
 */
enum DataType { INT, CHAR, VARCHAR };

/**
 * Attribute definition
 */
class Attribute {
 public:
  /**
   * Attribute name
   */
  string attrName;

  /**
   * Attribute type
   */
  DataType attrType;

  /**
   * The max size of the attribute
   * If the attribute is CHAR(5), maxSize = 5
   */
  int maxSize;

  /**
   * Is the attribute not allowed to be null?
   */
  bool isNotNull;

  /**
   * Is the attribute required to be unique?
   */
  bool isUnique;

  /**
   * Constructor
   */
  Attribute(const string& attrName,
            const DataType& attrType,
            int maxSize,
            bool isNotNull = false,
            bool isUnique = false)
      : attrName(attrName),
        attrType(attrType),
        maxSize(maxSize),
        isNotNull(false),
        isUnique(false) {
    // nothing
  }

  /**
   * Destructor
   */
  ~Attribute() {
    // nothing
  }
};

/**
 * Schema of Tables
 */
class TableSchema {
 private:
  /**
   * Table name
   */
  string tableName;

  /**
   * Attribute list
   */
  vector<Attribute> attrs;

  /**
   * Is temporary table?
   */
  bool isTemp;

 public:
  /**
   * Constructor
   */
  TableSchema(const string& tableName, bool isTemp = false)
      : tableName(tableName), isTemp(isTemp) {
    // nothing
  }

  /**
   * Constructor
   */
  TableSchema(const string& tableName,
              const vector<Attribute>& attrs,
              bool isTemp = false)
      : tableName(tableName), attrs(attrs), isTemp(isTemp) {
    // nothing
  }

  /**
   * Copy constructor
   */
  TableSchema(const TableSchema& tableSchema)
      : tableName(tableSchema.tableName),
        attrs(tableSchema.attrs),
        isTemp(tableSchema.isTemp) {
    // nothing
  }

  /**
   * Destructor
   */
  ~TableSchema() {
    // nothing
  }

  /**
   * Create table schema from an SQL statement
   */
  static TableSchema fromSQLStatement(const string& sql);

  /**
   * Is the table temporary?
   */
  bool isTempTable() const { return isTemp; }

  /**
   * Get table name
   */
  const string& getTableName() const { return tableName; }

  /**
   * Get the number of attributes
   */
  int getAttrCount() const { return attrs.size(); }

  /**
   * Get the name of the num-th attribute
   */
  const string& getAttrName(int num) const { return attrs[num].attrName; }

  /**
   * Get the type of the num-th attribute
   */
  const DataType& getAttrType(int num) const { return attrs[num].attrType; }

  /**
   * Get the max size of the num-th attribute
   */
  int getAttrMaxSize(int num) const { return attrs[num].maxSize; }

  /**
   * Is the num-th attribute not allowed to be null?
   */
  bool isAttrNotNull(int num) const { return attrs[num].isNotNull; }

  /**
   * Is the num-th attribute required to be unique?
   */
  bool isAttrUnique(int num) const { return attrs[num].isUnique; }

  /**
   * Set the type of the num-th attribute
   */
  void setAttrType(int num, const DataType& type) {
    attrs[num].attrType = type;
  }

  /**
   * Get the number of attribute by its name
   */
  int getAttrNum(const string& attrName) const {
    for (int i = 0; i < getAttrCount(); i++) {
      if (attrs[i].attrName == attrName)
        return i;
    }
    return -1;
  }

  /**
   * Does the table contains the attribute?
   */
  bool hasAttr(const string& attrName) const {
    for (auto it = attrs.begin(); it != attrs.end(); ++it) {
      if (it->attrName == attrName)
        return true;
    }
    return false;
  }

  /**
   * Add an attribute to the table
   */
  void addAttr(const Attribute& attr) { attrs.push_back(attr); }

  /**
   * Delete the num-th attribute
   */
  void deleteAttr(int num) { attrs.erase(attrs.begin() + num); }

  /**
   * Print the schema
   */
  void print() const;
};

}  // namespace badgerdb