# -*- coding: utf-8 -*-
"""MySQLConnect 类，
负责管理数据库连接。
"""

import pymysql


class MySQLConnect:
    """MySQLConnect 类，负责管理数据库连接。"""
    def __init__(self,
                 host: str = "localhost",
                 user: str = "root",
                 password: str = "12345",
                 database: str = "DBMS_PROJECT1"):
        self._db = pymysql.connect(host=host,
                                   user=user,
                                   password=password,
                                   database=database)

    def __del__(self):
        self._db.close()

    def clear_table(self, table: str):
        """清空表的内容"""
        try:
            with self._db.cursor() as cursor:
                cursor.execute("TRUNCATE TABLE {};".format(table))
            self._db.commit()
            return True
        except:
            self._db.rollback()
            return False

    def exist_table(self, table: str):
        """查看一个表是否存在"""
        try:
            with self._db.cursor() as cursor:
                cursor.execute("SHOW TABLES LIKE '{}'".format(table))
                return cursor.rowcount > 0
        except:
            return False

    def create_table(self, table: str, attribute: str, force=False):
        """创建新表"""
        try:
            with self._db.cursor() as cursor:
                if force:
                    cursor.execute("DROP TABLE IF EXISTS {};".format(table))
                cursor.execute("CREATE TABLE {} ({});".format(
                    table, attribute))
            self._db.commit()
            return True
        except:
            self._db.rollback()
            return False

    def add_tuple(self, table: str, keys: str, values: str):
        """添加元组"""
        try:
            with self._db.cursor() as cursor:
                cursor.execute("INSERT INTO {} ({}) VALUES ({});".format(
                    table, keys, values))
            self._db.commit()
            return True
        except:
            self._db.rollback()
            return False

    def query_one(self, sql: str):
        """查询单个元组"""
        try:
            with self._db.cursor() as cursor:
                cursor.execute(sql)
                self._db.commit()
                return cursor.fetchone()
        except:
            return None

    def query_all(self, sql: str):
        """查询所有元组"""
        try:
            with self._db.cursor() as cursor:
                cursor.execute(sql)
                self._db.commit()
                return cursor.fetchall()
        except:
            return None

    def query_count(self, sql: str):
        """查询元组个数"""
        try:
            with self._db.cursor() as cursor:
                cursor.execute(sql)
                self._db.commit()
                return cursor.rowcount
        except:
            return 0
