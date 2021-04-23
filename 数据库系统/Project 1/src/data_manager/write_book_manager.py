# -*- coding: utf-8 -*-
"""WriteBookManager 类，
写作关系管理系统，负责管理 WriteBook 表。

-- ----------------------------
-- Table structure for WriteBook
-- ----------------------------
CREATE TABLE WriteBook (
  AuthorId INT NOT NULL,
  BookId INT NOT NULL,
  PRIMARY KEY (AuthorId, BookId),
  FOREIGN KEY (AuthorId) REFERENCES Author(Id),
  FOREIGN KEY (BookId) REFERENCES Book(Id)
);"""

from data_manager.mysql_connect import MySQLConnect


class WriteBookManager:
    """WriteBookManager 类，管理 WriteBook 表"""

    _SEARCH_RAW = """SELECT Author.Name, Book.Name FROM WriteBook
LEFT JOIN Author ON AuthorId = Author.Id
LEFT JOIN Book ON BookId = Book.Id"""

    def __init__(self, conn: MySQLConnect):
        self._conn = conn

    def init_table(self):
        """初始化数据表"""
        self._conn.create_table(
            "WriteBook", """AuthorId INT NOT NULL,
  BookId INT NOT NULL,
  PRIMARY KEY (AuthorId, BookId),
  FOREIGN KEY (AuthorId) REFERENCES Author(Id),
  FOREIGN KEY (BookId) REFERENCES Book(Id)""", True)

    def add_write_book(self, author_id: int, book_id: int):
        """添加写作关系"""
        if self._conn.query_one("SELECT Id FROM Author WHERE Id = {}".format(
                author_id)) is None:
            return False
        if self._conn.query_one(
                "SELECT Id FROM Book WHERE Id = {}".format(book_id)) is None:
            return False
        return self._conn.add_tuple("WriteBook", "AuthorId, BookId",
                                    ", ".join(map(repr, [author_id, book_id])))

    def list_write_book(self):
        """列出所有写作关系"""
        return self._conn.query_all(WriteBookManager._SEARCH_RAW + ";")

    def search_books_with__author_id(self, author_id: int):
        """使用作者 id 搜索书籍"""
        return self._conn.query_all(
            """SELECT Book.Id, Book.ISBN, Book.Name, Book.Category, Publisher.Name
FROM WriteBook
LEFT JOIN Author ON AuthorId = Author.Id
LEFT JOIN Book ON BookId = Book.Id
LEFT JOIN Publisher ON Book.PublisherId = Publisher.Id
 WHERE Author.Id = {};""".format(author_id))
