# -*- coding: utf-8 -*-
"""
App 类，
负责处理用户界面和业务逻辑。
"""

import sys

from data_manager.comment_manager import CommentManager
from data_manager.reader_manager import ReaderManager
from data_manager.write_book_manager import WriteBookManager
from data_manager.book_manager import BookManager, BookCategory, STR2BOOK_CATEGORY
from data_manager.publisher_manager import PublisherManager
from data_manager.mysql_connect import MySQLConnect
from data_manager.author_manager import AuthorManager

from PyQt5.QtGui import QStandardItem, QStandardItemModel
from PyQt5.QtWidgets import QApplication, QHeaderView, QInputDialog, QMainWindow, QMessageBox, QTableView
from ui.ui_mainwindow import Ui_mainwindow


class App(QMainWindow, Ui_mainwindow):

    _PUBLISHER_HEADER = ["ID", "名称"]
    _AUTHOR_HEADER = ["ID", "姓名", "性别"]
    _BOOK_HEADER = ["ID", "ISBN", "书名", "领域", "出版社"]
    _READER_HEADER = ["ID", "姓名"]
    _COMMENT_HEADER = ["ID", "读者", "书籍", "评论"]

    def __init__(self, parent=None):
        super(App, self).__init__(parent)
        self.init_ui()
        self.init_action()
        self.init_manager()

    def init_ui(self):
        self.ui = Ui_mainwindow()
        self.ui.setupUi(self)
        self.ui.tab_widget.setCurrentIndex(0)
        self.ui.tab_widget.setTabsClosable(False)

        self.ui.publisher_table.setEditTriggers(QTableView.NoEditTriggers)
        self.ui.author_table.setEditTriggers(QTableView.NoEditTriggers)
        self.ui.book_table.setEditTriggers(QTableView.NoEditTriggers)
        self.ui.reader_table.setEditTriggers(QTableView.NoEditTriggers)
        self.ui.comment_table.setEditTriggers(QTableView.NoEditTriggers)

        model = QStandardItemModel()
        model.setHorizontalHeaderLabels(App._PUBLISHER_HEADER)
        self.ui.publisher_table.setModel(model)
        self.ui.publisher_table.horizontalHeader().setSectionResizeMode(
            QHeaderView.ResizeToContents)

        model = QStandardItemModel()
        model.setHorizontalHeaderLabels(App._AUTHOR_HEADER)
        self.ui.author_table.setModel(model)
        self.ui.author_table.horizontalHeader().setSectionResizeMode(
            QHeaderView.ResizeToContents)

        model = QStandardItemModel()
        model.setHorizontalHeaderLabels(App._BOOK_HEADER)
        self.ui.book_table.setModel(model)
        self.ui.book_table.horizontalHeader().setSectionResizeMode(
            QHeaderView.ResizeToContents)

        model = QStandardItemModel()
        model.setHorizontalHeaderLabels(App._READER_HEADER)
        self.ui.reader_table.setModel(model)
        self.ui.reader_table.horizontalHeader().setSectionResizeMode(
            QHeaderView.ResizeToContents)

        model = QStandardItemModel()
        model.setHorizontalHeaderLabels(App._COMMENT_HEADER)
        self.ui.comment_table.setModel(model)
        self.ui.comment_table.horizontalHeader().setSectionResizeMode(
            QHeaderView.ResizeToContents)

    def init_action(self):
        self.ui.publisher_add_button.clicked.connect(self.add_publisher)
        self.ui.publisher_search_id_button.clicked.connect(
            self.search_publisher_by_id)
        self.ui.publisher_search_keyword_button.clicked.connect(
            self.search_publisher_by_keyword)
        self.ui.publisher_list_button.clicked.connect(self.list_publisher)

        self.ui.author_add_button.clicked.connect(self.add_author)
        self.ui.author_search_id_button.clicked.connect(
            self.search_author_by_id)
        self.ui.author_search_keyword_button.clicked.connect(
            self.search_author_by_keyword)
        self.ui.author_search_books_button.clicked.connect(
            self.search_author_books)
        self.ui.author_list_button.clicked.connect(self.list_author)

        self.ui.book_add_button.clicked.connect(self.add_book)
        self.ui.book_search_id_button.clicked.connect(self.search_book_by_id)
        self.ui.book_search_isbn_button.clicked.connect(
            self.search_book_by_isbn)
        self.ui.book_search_keyword_button.clicked.connect(
            self.search_book_by_keyword)
        self.ui.book_list_button.clicked.connect(self.list_book)

        self.ui.reader_add_button.clicked.connect(self.add_reader)
        self.ui.reader_search_id_button.clicked.connect(
            self.search_reader_by_id)
        self.ui.reader_search_keyword_button.clicked.connect(
            self.search_reader_by_keyword)
        self.ui.reader_list_button.clicked.connect(self.list_reader)

        self.ui.comment_add_button.clicked.connect(self.add_comment)
        self.ui.comment_search_id_button.clicked.connect(
            self.search_comment_by_id)
        self.ui.comment_search_reader_button.clicked.connect(
            self.search_comment_by_reader)
        self.ui.comment_search_book_button.clicked.connect(
            self.search_comment_by_book)
        self.ui.comment_search_keyword_button.clicked.connect(
            self.search_comment_by_keyword)
        self.ui.comment_list_button.clicked.connect(self.list_comment)

    def init_manager(self):
        self.mysql_connect = MySQLConnect()
        self.publisher_manager = PublisherManager(self.mysql_connect)
        self.author_manager = AuthorManager(self.mysql_connect)
        self.book_manager = BookManager(self.mysql_connect)
        self.reader_manager = ReaderManager(self.mysql_connect)
        self.comment_manager = CommentManager(self.mysql_connect)
        self.write_book_manager = WriteBookManager(self.mysql_connect)

    def add_comment(self):
        reader_id, ok = QInputDialog.getInt(self, "输入读者 ID", "输入读者 ID")
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入内容")
            return
        if self.reader_manager.search_reader_with_id(reader_id) is None:
            QMessageBox.critical(self, "错误", "无对应读者")
            return

        book_id, ok = QInputDialog.getInt(self, "输入书籍 ID", "输入书籍 ID")
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入内容")
            return
        if self.book_manager.search_book_with_id(book_id) is None:
            QMessageBox.critical(self, "错误", "无对应读者")
            return

        content, ok = QInputDialog.getText(self, "输入评论", "输入评论（不超过 256 字）")
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入内容")
            return
        content = content.strip()
        if len(content) == 0:
            QMessageBox.critical(self, "错误", "评论不能为空")
            return
        if len(content) > 256:
            QMessageBox.critical(self, "错误", "评论不能超过 256 字")
            return

        self.comment_manager.add_comment(reader_id, book_id, content)

    def search_comment_by_id(self):
        comment_id, ok = QInputDialog.getInt(self, "输入注释 ID", "输入注释 ID")
        if ok:
            item = self.comment_manager.search_comment_with_id(comment_id)
            if item:
                item = [item]
            self.list_comment(item)
        else:
            QMessageBox.critical(self, "错误", "无法获取输入 ID")

    def search_comment_by_reader(self):
        reader, ok = QInputDialog.getText(self, "输入读者姓名", "输入读者姓名")
        if ok:
            item = self.comment_manager.search_comment_with_reader(reader)
            self.list_comment(item)
        else:
            QMessageBox.critical(self, "错误", "无法获取输入")

    def search_comment_by_book(self):
        book, ok = QInputDialog.getText(self, "输入书籍姓名", "输入书籍姓名")
        if ok:
            item = self.comment_manager.search_comment_with_book(book)
            self.list_comment(item)
        else:
            QMessageBox.critical(self, "错误", "无法获取输入")

    def search_comment_by_keyword(self):
        keyword, ok = QInputDialog.getText(self, "输入关键字", "输入关键字")
        if ok:
            items = self.comment_manager.search_comment_with_keyword(keyword)
            self.list_comment(items)
        else:
            QMessageBox.critical(self, "错误", "无法获取输入内容")

    def list_comment(self, items=0):
        if items == 0:
            items = self.comment_manager.list_comments()
        self.ui.comment_table.model().clear()
        self.ui.comment_table.model().setHorizontalHeaderLabels(
            App._COMMENT_HEADER)
        if items:
            for comment in items:
                self.ui.comment_table.model().appendRow(
                    list(map(lambda x: QStandardItem(str(x)), comment)))

    def add_reader(self):
        name, ok = QInputDialog.getText(self, "输入读者姓名", "输入读者姓名")
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入内容")
            return
        name = name.strip()
        if len(name) == 0:
            QMessageBox.critical(self, "错误", "输入不能为空")
            return

        if self.reader_manager.add_reader(name):
            self.list_reader()
        else:
            QMessageBox.critical(self, "错误", "无法添加")

    def search_reader_by_id(self):
        reader_id, ok = QInputDialog.getInt(self, "输入读者 ID", "输入读者 ID")
        if ok:
            item = self.reader_manager.search_reader_with_id(reader_id)
            if item:
                item = [item]
            self.list_reader(item)
        else:
            QMessageBox.critical(self, "错误", "无法获取输入 ID")

    def search_reader_by_keyword(self):
        keyword, ok = QInputDialog.getText(self, "输入关键字", "输入关键字")
        if ok:
            items = self.reader_manager.search_reader_with_keyword(keyword)
            self.list_reader(items)
        else:
            QMessageBox.critical(self, "错误", "无法获取输入内容")

    def list_reader(self, items=0):
        if items == 0:
            items = self.reader_manager.list_readers()
        self.ui.reader_table.model().clear()
        self.ui.reader_table.model().setHorizontalHeaderLabels(
            App._READER_HEADER)
        if items:
            for reader in items:
                self.ui.reader_table.model().appendRow(
                    list(map(lambda x: QStandardItem(str(x)), reader)))

    def add_book(self):
        isbn, ok = QInputDialog.getText(self, "输入书籍 ISBN", "输入书籍 ISBN（不包含横线）")
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入内容")
            return
        isbn = isbn.strip()
        if len(isbn) == 0 or (len(isbn) != 10 and len(isbn) != 13):
            QMessageBox.critical(self, "错误", "输入格式错误")
            return

        name, ok = QInputDialog.getText(self, "输入书名", "输入书名")
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入内容")
            return
        name = name.strip()
        if len(name) == 0:
            QMessageBox.critical(self, "错误", "输入不能为空")
            return

        category, ok = QInputDialog.getItem(
            self, "选择书籍类型", "选择书籍类型", map(lambda x: x.value, BookCategory))
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入内容")
            return

        publisher_id, ok = QInputDialog.getInt(self, "输入出版社 ID", "输入出版社 ID")
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入内容")
            return
        if self.publisher_manager.search_publisher_with_id(
                publisher_id) is None:
            QMessageBox.critical(self, "错误", "无法找到该出版社 ID")
            return

        authors, ok = QInputDialog.getText(self, "输入作者 ID",
                                           "输出作者 ID（多个使用空格隔开）")
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入内容")
            return
        authors_id = list(map(int, authors.split()))
        for author_id in authors_id:
            if self.author_manager.search_author_with_id(author_id) is None:
                QMessageBox.critical(self, "错误", "无法找到该作者 ID")
                return

        if not self.book_manager.add_book(
                isbn, name, STR2BOOK_CATEGORY[category], publisher_id):
            QMessageBox.critical(self, "错误", "无法添加")
            return

        book_id = self.book_manager.search_book_with_isbn(isbn)
        assert isinstance(book_id, (int, str, str, str, str))
        book_id = book_id[0]
        for author_id in authors_id:
            self.write_book_manager.add_write_book(author_id, book_id)
        self.list_book()

    def search_book_by_id(self):
        book_id, ok = QInputDialog.getInt(self, "输入书籍 ID", "输入书籍 ID")
        if ok:
            item = self.book_manager.search_book_with_id(book_id)
            if item:
                item = [item]
            self.list_book(item)
        else:
            QMessageBox.critical(self, "错误", "无法获取输入 ID")

    def search_book_by_isbn(self):
        isbn, ok = QInputDialog.getText(self, "输入书籍 ISBN", "输入书籍 ISBN（不包含横线）")
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入内容")
            return
        isbn = isbn.strip()
        if len(isbn) == 0 or (len(isbn) != 10 and len(isbn) != 13):
            QMessageBox.critical(self, "错误", "输入格式错误")
            return
        item = self.book_manager.search_book_with_isbn(isbn)
        if item:
            item = [item]
        self.list_book(item)

    def search_book_by_keyword(self):
        keyword, ok = QInputDialog.getText(self, "输入关键字", "输入关键字")
        if ok:
            items = self.book_manager.search_book_with_keyword(keyword)
            self.list_book(items)
        else:
            QMessageBox.critical(self, "错误", "无法获取输入内容")

    def list_book(self, items=0):
        if items == 0:
            items = self.book_manager.list_books()
        self.ui.book_table.model().clear()
        self.ui.book_table.model().setHorizontalHeaderLabels(App._BOOK_HEADER)
        if items:
            for book in items:
                self.ui.book_table.model().appendRow(
                    list(map(lambda x: QStandardItem(str(x)), book)))

    def add_author(self):
        name, ok = QInputDialog.getText(self, "输入作者姓名", "输入作者姓名")
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入内容")
            return
        name = name.strip()
        if len(name) == 0:
            QMessageBox.critical(self, "错误", "输入不能为空")
            return

        sex, ok = QInputDialog.getItem(self, "选择作者性别", "输入作者性别", ["男", "女"])
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入内容")
            return

        if self.author_manager.add_author(name, sex):
            self.list_author()
        else:
            QMessageBox.critical(self, "错误", "无法添加")

    def search_author_by_id(self):
        author_id, ok = QInputDialog.getInt(self, "输入作者 ID", "输入作者 ID")
        if ok:
            item = self.author_manager.search_author_with_id(author_id)
            if item:
                item = [item]
            self.list_author(item)
        else:
            QMessageBox.critical(self, "错误", "无法获取输入 ID")

    def search_author_by_keyword(self):
        keyword, ok = QInputDialog.getText(self, "输入关键字", "输入关键字")
        if ok:
            items = self.author_manager.search_author_with_keyword(keyword)
            self.list_author(items)
        else:
            QMessageBox.critical(self, "错误", "无法获取输入内容")

    def search_author_books(self):
        author_id, ok = QInputDialog.getInt(self, "输入作者 ID", "输入作者 ID")
        if not ok:
            QMessageBox.critical(self, "错误", "无法获取输入 ID")
            return
        items = self.write_book_manager.search_books_with__author_id(author_id)
        self.list_book(items)
        self.ui.tab_widget.setCurrentWidget(self.ui.book_tab)

    def list_author(self, items=0):
        if items == 0:
            items = self.author_manager.list_authors()
        self.ui.author_table.model().clear()
        self.ui.author_table.model().setHorizontalHeaderLabels(
            App._AUTHOR_HEADER)
        if items:
            for author in items:
                self.ui.author_table.model().appendRow(
                    list(map(lambda x: QStandardItem(str(x)), author)))

    def add_publisher(self):
        name, ok = QInputDialog.getText(self, "输入出版社名称", "输入出版社名称")
        if ok:
            name = name.strip()
            if len(name) == 0:
                QMessageBox.critical(self, "错误", "输入不能为空")
            else:
                if self.publisher_manager.add_publisher(name):
                    self.list_publisher()
                else:
                    QMessageBox.critical(self, "错误", "无法添加")

        else:
            QMessageBox.critical(self, "错误", "无法获取输入内容")

    def search_publisher_by_id(self):
        publisher_id, ok = QInputDialog.getInt(self, "输入出版社 ID", "输入出版社 ID")
        if ok:
            item = self.publisher_manager.search_publisher_with_id(
                publisher_id)
            if item:
                item = [item]
            self.list_publisher(item)
        else:
            QMessageBox.critical(self, "错误", "无法获取输入 ID")

    def search_publisher_by_keyword(self):
        keyword, ok = QInputDialog.getText(self, "输入关键字", "输入关键字")
        if ok:
            items = self.publisher_manager.search_publisher_with_keyword(
                keyword)
            self.list_publisher(items)
        else:
            QMessageBox.critical(self, "错误", "无法获取输入内容")

    def list_publisher(self, items=0):
        if items == 0:
            items = self.publisher_manager.list_publisher()
        self.ui.publisher_table.model().clear()
        self.ui.publisher_table.model().setHorizontalHeaderLabels(
            App._PUBLISHER_HEADER)
        if items:
            for publisher in items:
                self.ui.publisher_table.model().appendRow(
                    list(map(lambda x: QStandardItem(str(x)), publisher)))


if __name__ == '__main__':
    env = QApplication(sys.argv)
    app = App()
    app.show()
    sys.exit(env.exec_())
