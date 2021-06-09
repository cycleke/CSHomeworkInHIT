/**
 * @author Zhaonian Zou <znzou@hit.edu.cn>,
 * School of Computer Science and Technology,
 * Harbin Institute of Technology, China
 */

#include "schema.h"

#include <algorithm>
#include <iomanip>
#include <iostream>
#include <regex>
#include <string>

using namespace std;

namespace badgerdb {

TableSchema TableSchema::fromSQLStatement(const string& sql) {
  string tableName;
  vector<Attribute> attrs;
  bool isTemp = false;
  regex pattern("CREATE TABLE (.*?) \\((.*?)\\);");
  smatch result;
  regex_match(sql, result, pattern);
  tableName = result[1];
  string attr_content = result[2];
  regex sep1(",");  // divide attributes
  regex sep2(" ");  // divide items in the attribute
  sregex_token_iterator attr_tokens(attr_content.cbegin(), attr_content.cend(),
                                    sep1, -1);
  std::sregex_token_iterator attr_end;
  for (; attr_tokens != attr_end; ++attr_tokens) {
    string this_token = *attr_tokens;
    sregex_token_iterator item_tokens(this_token.cbegin(), this_token.cend(),
                                      sep2, -1);
    std::sregex_token_iterator item_end;
    // attribute items
    int max_length = -1;
    bool not_null = false;
    bool is_unique = false;
    string attr_name;
    string attr_type;
    for (int i = 0; item_tokens != item_end; ++item_tokens) {
      if (item_tokens->length() == 0) {
        continue;
      }
      if (i == 0) {
        attr_name = *item_tokens;
      } else if (i == 1) {
        attr_type = *item_tokens;
        // parse the max length of the type (CHAR VARCHAR)
        regex length_pattern("(.*?)\\(([0-9]*)\\)");
        smatch length_result;
        bool len_matched =
            regex_match(attr_type, length_result, length_pattern);
        if (len_matched) {
          max_length = stoi(length_result[2]);
          attr_type = length_result[1];
        }
      } else {
        string other = *item_tokens;
        if (other == "NOT") {
          not_null = true;
        }
        if (other == "UNIQUE") {
          is_unique = true;
        }
      }
      i++;
    }
    // convert to the enum type
    DataType enum_attr_type;
    switch (attr_type.length()) {
      case 3:
        enum_attr_type = INT;
        max_length = 4;
        break;
      case 4:
        enum_attr_type = CHAR;
        break;
      case 7:
        enum_attr_type = VARCHAR;
        break;
    }
    Attribute new_attr(attr_name, enum_attr_type, max_length, not_null,
                       is_unique);
    attrs.push_back(new_attr);  // add the new attribute to the attrs vector
  }
  return TableSchema(tableName, attrs, isTemp);
}

void TableSchema::print() const {
  cout << setiosflags(ios::fixed)
       << setiosflags(ios::left);  // set the output form
  cout << setw(15) << "Field" << setw(15) << "Type" << setw(15) << "Null"
       << setw(15) << "Unique" << endl;
  for (const auto& attr : attrs) {
    string null = "NO";
    string is_unique = "NO";
    if (!attr.isNotNull)
      null = "YES";
    if (attr.isUnique)
      is_unique = "YES";

    // convert enum type to string type
    string attr_type;
    switch (attr.attrType) {
      case INT:
        attr_type = "INT";
        break;
      case CHAR:
        attr_type = "CHAR";
        break;
      case VARCHAR:
        attr_type = "VARCHAR";
        break;
    }
    string type = attr_type + "(" + to_string(attr.maxSize) + ")";
    cout << setw(15) << attr.attrName << setw(15) << type << setw(15) << null
         << setw(15) << is_unique << endl;
  }
}

}  // namespace badgerdb