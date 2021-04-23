# -*- coding: utf-8 -*-
"""PublisherManager 类，
出版社管理系统，负责管理 Publisher 表。

-- ----------------------------
-- Table structure for Publisher
-- ----------------------------
CREATE TABLE Publisher (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(256) NOT NULL
);"""

from data_manager.mysql_connect import MySQLConnect


class PublisherManager:
    """PublisherManager 类，管理 Publisher 表"""

    _SEARCH_RAW = """SELECT Id, Name FROM Publisher"""

    def __init__(self, conn: MySQLConnect):
        self._conn = conn

    def init_table(self):
        """初始化数据表"""
        self._conn.create_table(
            "Publisher", """Id INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(256) NOT NULL""", True)

    def add_publisher(self, name: str):
        """添加出版社"""
        if name == '':
            return False
        return self._conn.add_tuple("Publisher", "Name", repr(name))

    def list_publisher(self):
        """列出所有出版社"""
        return self._conn.query_all(PublisherManager._SEARCH_RAW + ";")

    def search_publisher_with_id(self, publisher_id: int):
        """使用 id 搜索出版社"""
        return self._conn.query_one(PublisherManager._SEARCH_RAW +
                                    " WHERE Id = {};".format(publisher_id))

    def search_publisher_with_keyword(self, keyword: str):
        """使用关键字搜索出版社"""
        return self._conn.query_all(PublisherManager._SEARCH_RAW +
                                    " WHERE Name LIKE '%{}%';".format(keyword))
