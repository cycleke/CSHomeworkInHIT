-- ----------------------------
-- Table structure for TestData
-- ----------------------------

DROP TABLE IF EXISTS WriteBook;
DROP TABLE IF EXISTS Comment;
DROP TABLE IF EXISTS Reader;
DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS Author;
DROP TABLE IF EXISTS Publisher;

CREATE TABLE Publisher (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(256) NOT NULL
);

INSERT INTO Publisher VALUES
(1, '人民文学出版社'),
(2, '中华书局'),
(3, '万卷出版公司'),
(4, '重庆出版社'),
(5, '译林出版社'),
(6, '作家出版社'),
(7, '北京十月文艺出版社'),
(8, '人民出版社');

CREATE TABLE Author (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(256) NOT NULL,
  Sex ENUM('男','女') NOT NULL
);

INSERT INTO Author VALUES
(1, '曹雪芹', '男'),
(2, '高鹗', '男'),
(3, '司马迁', '男'),
(4, '张守节', '男'),
(5, '乔治·奥威尔', '男'),
(6, '乔治.R.R.马丁', '男'),
(7, '玛格丽特·米切尔', '女'),
(8, '余华', '男'),
(9, '三毛', '女'),
(10, '毛泽东', '男');

CREATE TABLE Book (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  ISBN VARCHAR(17) NOT NULL UNIQUE,
  Name VARCHAR(20) NOT NULL,
  Category ENUM('社会科学', '自然科学', '文学', '技术', '军事', '宗教', '综合'),
  PublisherId INT NOT NULL,
  FOREIGN KEY (PublisherId) REFERENCES Publisher(Id),
  UNIQUE INDEX(ISBN)
);

INSERT INTO Book VALUES
(1, '9787020002207', '红楼梦', '文学', 1),
(2, '9787101003048', '史记（全十册）', '社会科学', 2),
(3, '9787547011607', '1984', '文学', 3),
(4, '9787536674257', '冰与火之歌（卷二）', '文学', 4),
(5, '9787806570920', '飘', '文学', 5),
(6, '9787506365437', '活着', '文学', 6),
(7, '9787530211113', '撒哈拉的故事', '文学', 7),
(8, '9787010009223', '毛泽东选集, 第 1 卷', '社会科学', 8);

CREATE TABLE Reader (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(256) NOT NULL
);

INSERT INTO Reader VALUES
(1, '二月鸟语'),
(2, 'Vamei'),
(3, '柴斯卡'),
(4, 'Chris 一切由无聊开始'),
(5, '东篱'),
(6, '小巩俐'),
(7, 'Mumu');

CREATE TABLE Comment (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  ReaderId INT NOT NULL,
  BookId INT NOT NULL,
  Content VARCHAR(256) NOT NULL,
  FOREIGN KEY (ReaderId) REFERENCES Reader(Id),
  FOREIGN KEY (BookId) REFERENCES Book(Id)
);

INSERT INTO Comment VALUES
(1, 1, 1, '永远只读到80回。。'),
(2, 2, 2, '荡气回肠的古籍。'),
(3, 3, 3, '重新看了一遍，感觉确实没有高中的时候那么神……'),
(4, 4, 4, '乔治那个马丁！长命百岁啊！一定要写完，但又不要太早结束！'),
(5, 5, 5, '“过去的已经过去了，死了的已经死了，活着的还要继续活着。”看完本书令我印象最深的一句话。'),
(6, 6, 6, '第一次见序这么多的一本书。（但是三星是更复杂的原因，短评里说不清的原因，不是因为序多……）'),
(7, 7, 7, '“生命的过程，无论是阳春白雪，青菜豆腐，我都得尝尝是什么滋味，才不枉来走这么一遭啊！”');

CREATE TABLE WriteBook (
  AuthorId INT NOT NULL,
  BookId INT NOT NULL,
  PRIMARY KEY (AuthorId, BookId),
  FOREIGN KEY (AuthorId) REFERENCES Author(Id),
  FOREIGN KEY (BookId) REFERENCES Book(Id)
);

INSERT INTO WriteBook VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3),
(6, 4),
(7, 5),
(8, 6),
(9, 7),
(10, 8);
