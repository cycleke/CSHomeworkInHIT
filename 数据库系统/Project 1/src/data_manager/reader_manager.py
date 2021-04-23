# -*- coding: utf-8 -*-
"""ReaderManager 类，
读者管理系统，负责管理 Reader 表。

-- ----------------------------
-- Table structure for Reader
-- ----------------------------
CREATE TABLE Reader (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(256) NOT NULL
);"""

from data_manager.mysql_connect import MySQLConnect


class ReaderManager:
    """ReaderManager 类，管理 Reader 表"""

    _SEARCH_RAW = """SELECT Id, Name FROM Reader"""

    def __init__(self, conn: MySQLConnect):
        self._conn = conn

    def init_table(self):
        """初始化数据表"""
        self._conn.create_table(
            "Reader", """Id INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(256) NOT NULL""", True)

    def add_reader(self, name: str):
        """添加读者"""
        if name == '':
            return False
        return self._conn.add_tuple("Reader", "Name",
                                    ", ".join(map(repr, [name])))

    def list_readers(self):
        """列出所有读者"""
        return self._conn.query_all(ReaderManager._SEARCH_RAW + ";")

    def search_reader_with_id(self, reader_id: int):
        """使用 id 搜索读者"""
        return self._conn.query_one(ReaderManager._SEARCH_RAW +
                                    " WHERE Id = {};".format(reader_id))

    def search_reader_with_keyword(self, keyword: str):
        """使用关键字搜索读者"""
        return self._conn.query_all(ReaderManager._SEARCH_RAW +
                                    " WHERE Name LIKE '%{}%';".format(keyword))
