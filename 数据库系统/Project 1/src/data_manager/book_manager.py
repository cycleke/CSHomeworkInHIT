# -*- coding: utf-8 -*-
"""BookManager 类，
书籍管理系统，负责管理 Book 表。

-- ----------------------------
-- Table structure for Book
-- ----------------------------
CREATE TABLE Book (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  ISBN VARCHAR(17) UNIQUE,
  Name VARCHAR(20) NOT NULL,
  Category ENUM(""),
  PublisherId INT NOT NULL,
  FOREIGN KEY (PublisherId) REFERENCES Publisher(Id)
);"""

import enum
from data_manager.mysql_connect import MySQLConnect


class BookCategory(enum.Enum):
    """书籍类型"""

    SOCIAL_SCIENCES = "社会科学"
    NATURAL_SCIENCE = "自然科学"
    LITERATURE = "文学"
    TECHNOLOGY = "技术"
    MILITARY = "军事"
    RELIGION = "宗教"
    GENERAL = "综合"
    OTHERS = "其它"


STR2BOOK_CATEGORY = dict(map(lambda x: (x.value, x), BookCategory))


class BookManager:
    """BookManager 类，管理 Book 表"""

    _SEARCH_RAW = """SELECT Book.Id, Book.ISBN, Book.Name, Book.Category, Publisher.Name
FROM Book LEFT JOIN Publisher ON Book.PublisherId = Publisher.Id"""

    def __init__(self, conn: MySQLConnect):
        self._conn = conn

    def init_table(self):
        """初始化数据表"""
        self._conn.create_table(
            "Book", """Id INT AUTO_INCREMENT PRIMARY KEY,
  ISBN VARCHAR(17) NOT NULL UNIQUE,
  Name VARCHAR(20) NOT NULL,
  Category ENUM({}),
  PublisherId INT NOT NULL,
  FOREIGN KEY (PublisherId) REFERENCES Publisher(Id),
  UNIQUE INDEX(ISBN)""".format(', '.join(
                list(map(lambda x: repr(x.value), BookCategory)))), True)

    def add_book(self, isbn: str, name: str, category: BookCategory,
                 publisher_id: int):
        """添加书籍"""
        if isbn == '' or name == '':
            return False
        return self._conn.add_tuple(
            "Book", "ISBN, Name, Category, PublisherId",
            ", ".join(map(repr, [isbn, name, category.value, publisher_id])))

    def list_books(self):
        """列出所有书籍"""
        return self._conn.query_all(BookManager._SEARCH_RAW + ";")

    def search_book_with_id(self, book_id: int):
        """使用 id 搜索书籍"""
        return self._conn.query_one(BookManager._SEARCH_RAW +
                                    " WHERE Book.Id = {};".format(book_id))

    def search_book_with_isbn(self, isbn: str):
        """使用 ISBN 搜索书籍"""
        return self._conn.query_one(
            BookManager._SEARCH_RAW +
            " WHERE Book.ISBN = {};".format(repr(isbn)))

    def search_book_with_keyword(self, keyword: str):
        """使用关键字搜索书籍"""
        return self._conn.query_all(
            BookManager._SEARCH_RAW +
            " WHERE CONCAT(Book.ISBN, Book.Name, Publisher.Name, Book.Category) LIKE '%{}%';"
            .format(keyword))
