package pers.cycleke.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import pers.cycleke.compiler.Grammar.SymbolKind;

public class Lexer implements pers.cycleke.compiler.Grammar.Lexer {

  private Object yylval;
  private Position yylpos;

  private final Scanner scanner;
  private final Stack<Node> stack;
  private Node root = null, lastTerminalNode = null;

  public Lexer(InputStream inputStream) {
    stack = new Stack<>();
    scanner = new Scanner(inputStream);
  }

  @Override
  public Object getLVal() {
    return yylval;
  }

  public Position getLPosition() {
    return yylpos;
  }

  @Override
  public int yylex() throws IOException {
    for (;;) {
      if (!scanner.hasNext()) {
        return YYEOF;
      }
      var line1 = scanner.nextLine();

      if (!scanner.hasNext()) {
        return YYerror;
      }
      var line2 = scanner.nextLine();

      var objs = parseToken(line1);
      String type = (String) objs[0];
      if (type == "COMMENT") {
        continue;
      }
      yylval = objs[1];
      yylpos = parsePosition(line2);
      return LexerId.getLexerId(type);
    }
  }

  private static Object[] parseToken(String line) {
    var tokens = line.split(",", 2);
    assert tokens.length == 2;
    switch (tokens[0]) {
    case "CONST_FLOAT":
      return new Object[] { tokens[0], Double.parseDouble(tokens[1].substring(1)) };
    case "CONST_INTEGER":
      return new Object[] { tokens[0], Integer.parseInt(tokens[1].substring(1)) };
    case "CONST_STRING":
    case "IDN":
      return new Object[] { tokens[0], tokens[1].substring(2, tokens[1].length() - 1) };
    case "CONST_CHARACTER":
      return new Object[] { tokens[0], tokens[1].charAt(1) };
    }
    return new Object[] { tokens[0], null };
  }

  private static Position parsePosition(String line) {
    var tokens = line.substring(1, line.length() - 1).split(",");
    assert tokens.length == 2;
    return new Position(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1].substring(1)));
  }

  @Override
  public void yyerror(String msg) {
    System.err.println("[ERROR] " + lastTerminalNode.getPosition() + " " + msg);
  }

  public void addTerminalNode(SymbolKind symbolKind, Position position, Object value) {
    switch (symbolKind) {
    case S_CONST_FLOAT:
    case S_CONST_INTEGER:
    case S_CONST_STRING:
    case S_IDN:
    case S_CONST_CHARACTER:
      assert value != null;
    default:
      assert value == null;
    }
    var node = new Node(symbolKind, position, value);
    lastTerminalNode = node;
    stack.push(lastTerminalNode);
  }

  public void addNonTerminalNode(SymbolKind symbolKind, int reduceNum) {
    List<Node> children = new ArrayList<>();
    var node = new Node(symbolKind, lastTerminalNode.getPosition(), null);
    for (int i = 0; i < reduceNum; ++i) {
      children.add(stack.pop());
    }
    Collections.reverse(children);
    node.setChildren(children);
    stack.push(node);
    if (node.getSymbolKind() == SymbolKind.S_PROGRAM) {
      root = node;
    }
  }

  public void printASTree() {
    assert root != null;
    dfs(root, 0);
  }

  public static void dfs(Node node, int depth) {
    for (int i = 0; i < depth; ++i) {
      System.out.print("  ");
    }
    System.out.println(node);
    for (var child : node.getChildren()) {
      dfs(child, depth + 1);
    }
  }

}
