# 编译系统实验

## 源代码文件

```
src
`-- main
    |-- bison
    |   |-- grammar.y
    |   |-- impl.y
    |   |-- tinyc.y
    |   `-- tokens.txt
    |-- java
    |   `-- pers
    |       `-- cycleke
    |           `-- compiler
    |               |-- Grammar.java
    |               |-- LexerId.java
    |               |-- Lexer.java
    |               |-- Node.java
    |               `-- Position.java
    `-- resources
        |-- test.c
        |-- test.lex
        `-- test.output
```

其中 bison 目录存储用于 Bison 生成自动机的语法文件。
在终端中输入如下指令可以用 `.y` 文件生成 Grammar

```bash
cat impl.y tinyc.y > grammer.y
bison grammar.y --output=../java/pers/cycleke/compiler/Grammar.java -Wall
```

## 程序使用

```bash
mvn exec:java -Dexec.mainClass="pers.cycleke.compiler.Grammar" [filename]
```

如果没有带有 filename 这个参数，
那么实验代码会默认设置 filename 为 `src/resources/test.lex`。
