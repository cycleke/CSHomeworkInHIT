# -*- coding: utf-8 -*-
"""CommentManager 类，
评论管理系统，负责管理 Comment 表。

-- ----------------------------
-- Table structure for Comment
-- ----------------------------
CREATE TABLE Comment (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  ReaderId INT NOT NULL,
  BookId INT NOT NULL,
  Content VARCHAR(256) NOT NULL,
  FOREIGN KEY (ReaderId) REFERENCES Reader(Id),
  FOREIGN KEY (BookId) REFERENCES Book(Id)
);"""

from data_manager.mysql_connect import MySQLConnect


class CommentManager:
    """CommentManager 类，管理 Comment 表"""

    _SEARCH_RAW = """SELECT Comment.Id, Reader.Name, Book.Name, Content FROM Comment
LEFT JOIN Reader ON Comment.ReaderId = Reader.Id
LEFT JOIN Book ON Comment.BookId = Book.Id"""

    def __init__(self, conn: MySQLConnect):
        self._conn = conn

    def init_table(self):
        """初始化数据表"""
        self._conn.create_table(
            "Comment", """Id INT AUTO_INCREMENT PRIMARY KEY,
  ReaderId INT NOT NULL,
  BookId INT NOT NULL,
  Content VARCHAR(256) NOT NULL,
  FOREIGN KEY (ReaderId) REFERENCES Reader(Id),
  FOREIGN KEY (BookId) REFERENCES Book(Id)""", True)

    def add_comment(self, reader_id: int, book_id: int, content: str):
        """添加评论"""
        if content == '':
            return False
        if self._conn.query_one("SELECT Id FROM Reader WHERE Id = {}".format(
                reader_id)) is None:
            return False
        if self._conn.query_one(
                "SELECT Id FROM Book WHERE Id = {}".format(book_id)) is None:
            return False
        return self._conn.add_tuple(
            "Comment", "ReaderId, BookId, Content",
            ", ".join(map(repr, [reader_id, book_id, content])))

    def list_comments(self):
        """列出所有评论"""
        return self._conn.query_all(CommentManager._SEARCH_RAW + ";")

    def search_comment_with_id(self, comment_id: int):
        """使用 id 搜索评论"""
        return self._conn.query_one(
            CommentManager._SEARCH_RAW +
            " WHERE Comment.Id = {};".format(comment_id))

    def search_comment_with_reader(self, reader: str):
        """使用读者搜索评论"""
        return self._conn.query_all(
            CommentManager._SEARCH_RAW +
            " WHERE Reader.Name LIKE '%{}%';".format(reader))

    def search_comment_with_book(self, book: str):
        """使用书籍搜索评论"""
        return self._conn.query_all(
            CommentManager._SEARCH_RAW +
            " WHERE Book.Name LIKE '%{}%';".format(book))

    def search_comment_with_keyword(self, keyword: str):
        """使用关键字搜索评论"""
        return self._conn.query_all(
            CommentManager._SEARCH_RAW +
            " WHERE Content LIKE '%{}%';".format(keyword))
