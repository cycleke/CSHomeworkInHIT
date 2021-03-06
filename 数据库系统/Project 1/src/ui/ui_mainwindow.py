# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'mainwindow.ui'
#
# Created by: PyQt5 UI code generator 5.15.3
#
# WARNING: Any manual changes made to this file will be lost when pyuic5 is
# run again.  Do not edit this file unless you know what you are doing.


from PyQt5 import QtCore, QtGui, QtWidgets


class Ui_mainwindow(object):
    def setupUi(self, mainwindow):
        mainwindow.setObjectName("mainwindow")
        mainwindow.resize(773, 382)
        font = QtGui.QFont()
        font.setFamily(".SF UI Display Condensed")
        font.setBold(False)
        font.setWeight(50)
        mainwindow.setFont(font)
        self.central_widget = QtWidgets.QWidget(mainwindow)
        self.central_widget.setObjectName("central_widget")
        self.central_layout = QtWidgets.QVBoxLayout(self.central_widget)
        self.central_layout.setObjectName("central_layout")
        self.tab_widget = QtWidgets.QTabWidget(self.central_widget)
        self.tab_widget.setObjectName("tab_widget")
        self.publisher_tab = QtWidgets.QWidget()
        self.publisher_tab.setObjectName("publisher_tab")
        self.publisher_layout = QtWidgets.QVBoxLayout(self.publisher_tab)
        self.publisher_layout.setObjectName("publisher_layout")
        self.publisher_input_layout = QtWidgets.QHBoxLayout()
        self.publisher_input_layout.setObjectName("publisher_input_layout")
        self.publisher_add_button = QtWidgets.QPushButton(self.publisher_tab)
        self.publisher_add_button.setObjectName("publisher_add_button")
        self.publisher_input_layout.addWidget(self.publisher_add_button)
        self.publisher_search_id_button = QtWidgets.QPushButton(self.publisher_tab)
        self.publisher_search_id_button.setObjectName("publisher_search_id_button")
        self.publisher_input_layout.addWidget(self.publisher_search_id_button)
        self.publisher_search_keyword_button = QtWidgets.QPushButton(self.publisher_tab)
        self.publisher_search_keyword_button.setObjectName("publisher_search_keyword_button")
        self.publisher_input_layout.addWidget(self.publisher_search_keyword_button)
        self.publisher_list_button = QtWidgets.QPushButton(self.publisher_tab)
        self.publisher_list_button.setObjectName("publisher_list_button")
        self.publisher_input_layout.addWidget(self.publisher_list_button)
        self.publisher_layout.addLayout(self.publisher_input_layout)
        self.publisher_table = QtWidgets.QTableView(self.publisher_tab)
        self.publisher_table.setObjectName("publisher_table")
        self.publisher_layout.addWidget(self.publisher_table)
        self.tab_widget.addTab(self.publisher_tab, "")
        self.author_tab = QtWidgets.QWidget()
        self.author_tab.setObjectName("author_tab")
        self.author_layout = QtWidgets.QVBoxLayout(self.author_tab)
        self.author_layout.setObjectName("author_layout")
        self.author_input_layout = QtWidgets.QHBoxLayout()
        self.author_input_layout.setObjectName("author_input_layout")
        self.author_add_button = QtWidgets.QPushButton(self.author_tab)
        self.author_add_button.setObjectName("author_add_button")
        self.author_input_layout.addWidget(self.author_add_button)
        self.author_search_id_button = QtWidgets.QPushButton(self.author_tab)
        self.author_search_id_button.setObjectName("author_search_id_button")
        self.author_input_layout.addWidget(self.author_search_id_button)
        self.author_search_keyword_button = QtWidgets.QPushButton(self.author_tab)
        self.author_search_keyword_button.setObjectName("author_search_keyword_button")
        self.author_input_layout.addWidget(self.author_search_keyword_button)
        self.author_search_books_button = QtWidgets.QPushButton(self.author_tab)
        self.author_search_books_button.setObjectName("author_search_books_button")
        self.author_input_layout.addWidget(self.author_search_books_button)
        self.author_list_button = QtWidgets.QPushButton(self.author_tab)
        self.author_list_button.setObjectName("author_list_button")
        self.author_input_layout.addWidget(self.author_list_button)
        self.author_layout.addLayout(self.author_input_layout)
        self.author_table = QtWidgets.QTableView(self.author_tab)
        self.author_table.setObjectName("author_table")
        self.author_layout.addWidget(self.author_table)
        self.tab_widget.addTab(self.author_tab, "")
        self.book_tab = QtWidgets.QWidget()
        self.book_tab.setObjectName("book_tab")
        self.book_layout = QtWidgets.QVBoxLayout(self.book_tab)
        self.book_layout.setObjectName("book_layout")
        self.book_input_layout = QtWidgets.QHBoxLayout()
        self.book_input_layout.setObjectName("book_input_layout")
        self.book_add_button = QtWidgets.QPushButton(self.book_tab)
        self.book_add_button.setObjectName("book_add_button")
        self.book_input_layout.addWidget(self.book_add_button)
        self.book_search_id_button = QtWidgets.QPushButton(self.book_tab)
        self.book_search_id_button.setObjectName("book_search_id_button")
        self.book_input_layout.addWidget(self.book_search_id_button)
        self.book_search_isbn_button = QtWidgets.QPushButton(self.book_tab)
        self.book_search_isbn_button.setObjectName("book_search_isbn_button")
        self.book_input_layout.addWidget(self.book_search_isbn_button)
        self.book_search_keyword_button = QtWidgets.QPushButton(self.book_tab)
        self.book_search_keyword_button.setObjectName("book_search_keyword_button")
        self.book_input_layout.addWidget(self.book_search_keyword_button)
        self.book_list_button = QtWidgets.QPushButton(self.book_tab)
        self.book_list_button.setObjectName("book_list_button")
        self.book_input_layout.addWidget(self.book_list_button)
        self.book_layout.addLayout(self.book_input_layout)
        self.book_table = QtWidgets.QTableView(self.book_tab)
        self.book_table.setObjectName("book_table")
        self.book_layout.addWidget(self.book_table)
        self.tab_widget.addTab(self.book_tab, "")
        self.reader_tab = QtWidgets.QWidget()
        self.reader_tab.setObjectName("reader_tab")
        self.reader_layout = QtWidgets.QVBoxLayout(self.reader_tab)
        self.reader_layout.setObjectName("reader_layout")
        self.reader_input_layout = QtWidgets.QHBoxLayout()
        self.reader_input_layout.setObjectName("reader_input_layout")
        self.reader_add_button = QtWidgets.QPushButton(self.reader_tab)
        self.reader_add_button.setObjectName("reader_add_button")
        self.reader_input_layout.addWidget(self.reader_add_button)
        self.reader_search_id_button = QtWidgets.QPushButton(self.reader_tab)
        self.reader_search_id_button.setObjectName("reader_search_id_button")
        self.reader_input_layout.addWidget(self.reader_search_id_button)
        self.reader_search_keyword_button = QtWidgets.QPushButton(self.reader_tab)
        self.reader_search_keyword_button.setObjectName("reader_search_keyword_button")
        self.reader_input_layout.addWidget(self.reader_search_keyword_button)
        self.reader_list_button = QtWidgets.QPushButton(self.reader_tab)
        self.reader_list_button.setObjectName("reader_list_button")
        self.reader_input_layout.addWidget(self.reader_list_button)
        self.reader_layout.addLayout(self.reader_input_layout)
        self.reader_table = QtWidgets.QTableView(self.reader_tab)
        self.reader_table.setObjectName("reader_table")
        self.reader_layout.addWidget(self.reader_table)
        self.tab_widget.addTab(self.reader_tab, "")
        self.comment_tab = QtWidgets.QWidget()
        self.comment_tab.setObjectName("comment_tab")
        self.comment_layout = QtWidgets.QVBoxLayout(self.comment_tab)
        self.comment_layout.setObjectName("comment_layout")
        self.comment_input_layout = QtWidgets.QHBoxLayout()
        self.comment_input_layout.setObjectName("comment_input_layout")
        self.comment_add_button = QtWidgets.QPushButton(self.comment_tab)
        self.comment_add_button.setObjectName("comment_add_button")
        self.comment_input_layout.addWidget(self.comment_add_button)
        self.comment_search_id_button = QtWidgets.QPushButton(self.comment_tab)
        self.comment_search_id_button.setObjectName("comment_search_id_button")
        self.comment_input_layout.addWidget(self.comment_search_id_button)
        self.comment_search_reader_button = QtWidgets.QPushButton(self.comment_tab)
        self.comment_search_reader_button.setObjectName("comment_search_reader_button")
        self.comment_input_layout.addWidget(self.comment_search_reader_button)
        self.comment_search_book_button = QtWidgets.QPushButton(self.comment_tab)
        self.comment_search_book_button.setObjectName("comment_search_book_button")
        self.comment_input_layout.addWidget(self.comment_search_book_button)
        self.comment_search_keyword_button = QtWidgets.QPushButton(self.comment_tab)
        self.comment_search_keyword_button.setObjectName("comment_search_keyword_button")
        self.comment_input_layout.addWidget(self.comment_search_keyword_button)
        self.comment_list_button = QtWidgets.QPushButton(self.comment_tab)
        self.comment_list_button.setObjectName("comment_list_button")
        self.comment_input_layout.addWidget(self.comment_list_button)
        self.comment_layout.addLayout(self.comment_input_layout)
        self.comment_table = QtWidgets.QTableView(self.comment_tab)
        self.comment_table.setObjectName("comment_table")
        self.comment_layout.addWidget(self.comment_table)
        self.tab_widget.addTab(self.comment_tab, "")
        self.central_layout.addWidget(self.tab_widget)
        mainwindow.setCentralWidget(self.central_widget)

        self.retranslateUi(mainwindow)
        self.tab_widget.setCurrentIndex(4)
        QtCore.QMetaObject.connectSlotsByName(mainwindow)

    def retranslateUi(self, mainwindow):
        _translate = QtCore.QCoreApplication.translate
        mainwindow.setWindowTitle(_translate("mainwindow", "书评系统"))
        self.publisher_add_button.setText(_translate("mainwindow", "添加出版社"))
        self.publisher_search_id_button.setText(_translate("mainwindow", "按 ID 搜索出版社"))
        self.publisher_search_keyword_button.setText(_translate("mainwindow", "按关键字搜索出版社"))
        self.publisher_list_button.setText(_translate("mainwindow", "列出所有"))
        self.tab_widget.setTabText(self.tab_widget.indexOf(self.publisher_tab), _translate("mainwindow", "出版社"))
        self.author_add_button.setText(_translate("mainwindow", "添加作者"))
        self.author_search_id_button.setText(_translate("mainwindow", "按 ID 搜索作者"))
        self.author_search_keyword_button.setText(_translate("mainwindow", "按关键字搜索作者"))
        self.author_search_books_button.setText(_translate("mainwindow", "搜索作者书籍"))
        self.author_list_button.setText(_translate("mainwindow", "列出所有"))
        self.tab_widget.setTabText(self.tab_widget.indexOf(self.author_tab), _translate("mainwindow", "作者"))
        self.book_add_button.setText(_translate("mainwindow", "添加书籍"))
        self.book_search_id_button.setText(_translate("mainwindow", "按 ID 搜索书籍"))
        self.book_search_isbn_button.setText(_translate("mainwindow", "按 ISBN 搜索书籍"))
        self.book_search_keyword_button.setText(_translate("mainwindow", "按关键字搜索书籍"))
        self.book_list_button.setText(_translate("mainwindow", "列出所有"))
        self.tab_widget.setTabText(self.tab_widget.indexOf(self.book_tab), _translate("mainwindow", "书籍"))
        self.reader_add_button.setText(_translate("mainwindow", "添加读者"))
        self.reader_search_id_button.setText(_translate("mainwindow", "按 ID 搜索读者"))
        self.reader_search_keyword_button.setText(_translate("mainwindow", "按关键字搜索读者"))
        self.reader_list_button.setText(_translate("mainwindow", "列出所有"))
        self.tab_widget.setTabText(self.tab_widget.indexOf(self.reader_tab), _translate("mainwindow", "读者"))
        self.comment_add_button.setText(_translate("mainwindow", "添加评论"))
        self.comment_search_id_button.setText(_translate("mainwindow", "按 ID 搜索评论"))
        self.comment_search_reader_button.setText(_translate("mainwindow", "按读者搜索评论"))
        self.comment_search_book_button.setText(_translate("mainwindow", "按书籍搜索评论"))
        self.comment_search_keyword_button.setText(_translate("mainwindow", "按关键字搜索评论"))
        self.comment_list_button.setText(_translate("mainwindow", "列出所有"))
        self.tab_widget.setTabText(self.tab_widget.indexOf(self.comment_tab), _translate("mainwindow", "评论"))
