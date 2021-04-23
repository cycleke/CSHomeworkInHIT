# -*- coding: utf-8 -*-
"""AuthorManager 类，
作者管理系统，负责管理 Publisher 表。

-- ----------------------------
-- Table structure for Author
-- ----------------------------
CREATE TABLE Author (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(256) NOT NULL,
  Sex ENUM('男','女') NOT NULL
);"""

from data_manager.mysql_connect import MySQLConnect


class AuthorManager:
    """AuthorManager 类，管理 Author 表"""

    _SEARCH_RAW = """SELECT Id, Name, Sex FROM Author"""

    def __init__(self, conn: MySQLConnect):
        self._conn = conn

    def init_table(self):
        """初始化数据表"""
        self._conn.create_table(
            "Author", """Id INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(256) NOT NULL,
  Sex ENUM('男','女') NOT NULL""", True)

    def add_author(self, name: str, sex: str):
        """添加作者"""
        if name == '' or not sex in ("男", "女"):
            return False
        return self._conn.add_tuple("Author", "Name, Sex",
                                    ", ".join(map(repr, [name, sex])))

    def list_authors(self):
        """列出所有作者"""
        return self._conn.query_all(AuthorManager._SEARCH_RAW + ";")

    def search_author_with_id(self, author_id: int):
        """使用 id 搜索作者"""
        return self._conn.query_one(AuthorManager._SEARCH_RAW +
                                    " WHERE Id = {};".format(author_id))

    def search_author_with_keyword(self, keyword: str):
        """使用关键字搜索作者"""
        return self._conn.query_all(AuthorManager._SEARCH_RAW +
                                    " WHERE Name LIKE '%{}%';".format(keyword))
