package pers.cycleke.compiler;

import pers.cycleke.compiler.Grammar.SymbolKind;

import java.util.ArrayList;
import java.util.List;

public class Node {
  public SymbolKind getSymbolKind() {
		return symbolKind;
	}

  private final Object value;
  private final Position position;
  private final SymbolKind symbolKind;

  private final List<Node> children;

  public Node(SymbolKind symbolKind, Position position, Object object) {
    this.symbolKind = symbolKind;
    this.position = position;
    this.value = object;
    this.children = new ArrayList<>();
  }

  public void setChildren(List<Node> children) {
    assert this.children.isEmpty();
    this.children.addAll(children);
  }

  public List<Node> getChildren() {
    return children;
  }

  public Position getPosition() {
    return position;
  }

  @Override
  public String toString() {
    switch (symbolKind) {
    case S_CONST_FLOAT:
    case S_CONST_INTEGER:
    case S_CONST_STRING:
    case S_CONST_CHARACTER:
    case S_IDN:
      return symbolKind + " " + value + " " + position;
    default:
      return symbolKind + " " + position;
    }
  }

}
