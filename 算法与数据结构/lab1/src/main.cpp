#include <cstring>
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>

#include "lib/number.h"

namespace MyStack {

#define DISPLAY_STACK

template <class T> class Stack {
public:
  Stack();
  ~Stack();

  void push(T);
  void pop();
  T &top();
  bool is_empty() const;
  size_t get_size() const;
  void display() const;

protected:
  struct Node {
    T value;
    Node *next;
  };
  Node *head_;
  size_t size_;
};

template <class T> Stack<T>::Stack() {
  // construct a stack
  size_ = 0;
  head_ = nullptr;
}
template <class T> Stack<T>::~Stack() {
  // distruct a stack
  while (!is_empty()) pop();
}

template <class T> void Stack<T>::push(T element) {
  ++size_;
  // generate a new node
  Node *new_node = new Node;
  new_node->value = element, new_node->next = head_;
  head_ = new_node;
}
template <class T> void Stack<T>::pop() {
  // determine whether the stack is empty
  if (size_ == 0) {
    std::cerr << "The Stack has nothing!!!" << std::endl;
    exit(-1);
  }
  --size_;
  Node *iter = head_->next;
  delete head_;
  head_ = iter;
}
template <class T> T &Stack<T>::top() {
  // determine whether the stack is empty
  if (size_ == 0) {
    std::cerr << "The Stack has nothing!!!" << std::endl;
    exit(-1);
  }
  return head_->value;
}
template <class T> bool Stack<T>::is_empty() const { return size_ == 0; }
template <class T> size_t Stack<T>::get_size() const { return size_; }
template <class T> void Stack<T>::display() const {
  vector<T> array(size_);
  Node *iter = head_;
  // reverse the element in stack
  for (int i = size_ - 1; ~i; --i) array[i] = iter->value, iter = iter->next;
  for (T element : array) cout << element << ' ';
  std::cout << std::endl;
}
} // namespace MyStack

typedef enum {
  VARIABLE,
  NUMBER,
  ASSIGNMENT,
  LPAR,
  RPAR,
  POW,
  MUL,
  DIV,
  MOD,
  PLUS,
  MINUS
} TokenType;

class Token {
public:
  TokenType type;
  Number value;
  std::string var;

  Token();
  Token(TokenType);
  Token(Number);
  Token(std::string);
};

Token::Token() {}
Token::Token(TokenType t) : type(t) {} // generate a Token with certain type
Token::Token(Number n) : type(NUMBER), value(n) {} // generate a Number Token
Token::Token(std::string var)
    : type(VARIABLE), var(var) {} // generate a Varible Token

std::ostream &operator<<(std::ostream &os, const Token &a) {
  if (a.type == NUMBER) {
    os << a.value;
  } else {
    switch (a.type) {
    case LPAR: os << '('; break;
    case RPAR: os << ')'; break;
    case POW: os << "**"; break;
    case MUL: os << '*'; break;
    case DIV: os << '/'; break;
    case MOD: os << '%'; break;
    case PLUS: os << '+'; break;
    case MINUS: os << '-'; break;
    case ASSIGNMENT: os << '='; break;
    case VARIABLE: os << a.var; break;
    default: break;
    }
  }
  return os;
}

void ParseTokenfromInfix(const std::string &ts, std::vector<Token> &tokens) {
  /*
    Parse the infix token array.
    parameter:
      ts: the infix expression string
      tokens: return the infix tokens parsed from ts
   */

  int length = ts.length();
  tokens.clear();

  bool flag = false;
  for (int i = 0; i < length; ++i) {
    if (isspace(ts[i])) continue;

    // the bound symbol
    if (ts[i] == '#') {
      if (flag) break;
      flag = true;
      continue;
    }
    // no bound symbol before
    if (!flag) continue;

    bool def = false;
    switch (ts[i]) {
    case '+': tokens.emplace_back(PLUS); break;
    case '-':
      if (tokens.empty() ||
          (tokens.back().type != NUMBER && tokens.back().type != RPAR)) {
        int j = i;
        while (j + 1 < length && (isdigit(ts[j + 1]) || ts[j + 1] == '.')) ++j;
        Number n(ts.substr(i, j - i + 1));
        i = j;
        tokens.emplace_back(n);
      } else {
        tokens.emplace_back(MINUS);
      }
      break;
    case '*':
      if (i + 1 < length && ts[i + 1] == '*') {
        ++i;
        tokens.emplace_back(POW);
      } else {
        tokens.emplace_back(MUL);
      }
      break;
    case '/': tokens.emplace_back(DIV); break;
    case '%': tokens.emplace_back(MOD); break;
    case '(': tokens.emplace_back(LPAR); break;
    case ')': tokens.emplace_back(RPAR); break;
    case '=': tokens.emplace_back(ASSIGNMENT); break;
    default: def = true; break;
    }
    if (!def) continue;
    if (isdigit(ts[i]) || ts[i] == '.') {
      // Parse number
      int j = i;
      while (j + 1 < length && (isdigit(ts[j + 1]) || ts[j + 1] == '.')) ++j;
      tokens.emplace_back(Number(ts.substr(i, j - i + 1)));
      i = j;
    } else {
      // Parse variable
      static auto valid = [](const char &c) {
        return isalpha(c) || c == '$' || c == '_';
      };

      int j = i;
      while (j + 1 < length && (valid(ts[j + 1]))) ++j;
      tokens.emplace_back(ts.substr(i, j - i + 1));
      i = j;
    }
  }
}

int GetPrecedence(const TokenType &t) {
  /*
    the precedence less one works first
    parameter:
      t: the token type
    return:
      the precedence of the token type
   */

  switch (t) {
  case NUMBER: return 0;
  case VARIABLE: return 0;
  case POW: return 1;
  case DIV: return 2;
  case MOD: return 2;
  case MUL: return 2;
  case PLUS: return 3;
  case MINUS: return 3;
  case LPAR: return 4;
  case RPAR: return 4;
  case ASSIGNMENT: return 5;
  default: return 10;
  }
}

std::vector<Token> Infix2Postfix(const std::vector<Token> &infix) {
  /*
    convert infix expression to postfix expression
    parameter:
      infix: the infix tokens
    return:
      the postfix tokens
   */

  int top = 0;
  std::vector<Token> postfix;
  MyStack::Stack<Token> stack;
  for (auto token : infix) {
    if (token.type == NUMBER || token.type == VARIABLE) {
      stack.push(token);
    } else if (token.type == LPAR) {
      stack.push(token);
    } else if (token.type == RPAR) {
      while (!stack.is_empty() && stack.top().type != LPAR) {
        postfix.push_back(stack.top());
        stack.pop();
      }
      // delete the LPAR
      if (!stack.is_empty()) stack.pop();
    } else {
      while (!stack.is_empty() &&
             GetPrecedence(stack.top().type) <= GetPrecedence(token.type)) {
        postfix.push_back(stack.top());
        stack.pop();
      }
      stack.push(token);
    }

#ifdef DISPLAY_STACK
    std::cout << "Stack: ";
    stack.display();
#endif
  }

  // store the rest tokens in stack
  while (!stack.is_empty()) {
    postfix.push_back(stack.top());
    stack.pop();
  }
  return postfix;
}

Number DoOperator(TokenType oper, Number a, Number b) {
  /*
    return the value of expression "a oper b"
    parameter:
      oper: the operator
      a, b: the operand number
    return:
      the value of "a oper b"
   */

  switch (oper) {
  case POW: return a.pow(b);
  case DIV: return a / b;
  case MOD: return a % b;
  case MUL: return a * b;
  case PLUS: return a + b;
  case MINUS: return a - b;
  default: return Number();
  }
}

Number EvalPostfix(const std::vector<Token> &postfix,
                   std::unordered_map<string, Number> table) {
  /*
    calculate the value of the postfix expression
    parameter:
      postfix: the postfix tokens
      table: the map which stores the value of variable
    return:
      the the value of the postfix expression
   */

  MyStack::Stack<Number> stack;
  if (postfix.empty()) {
    std::cerr << "The postfix expression is none!" << std::endl;
    exit(1);
  }
  for (auto token : postfix) {
    if (token.type == NUMBER) {
      stack.push(token.value);
    } else if (token.type == VARIABLE) {
      auto iter = table.find(token.var);
      if (iter == table.end()) {
        // variable unassigned appear
        cerr << "Error: Unassigned Variable " << token.var << endl;
        exit(2);
      }
      stack.push(iter->second);
    } else {
      auto n2 = stack.top();
      stack.pop();
      auto n1 = stack.top();
      stack.pop();
      stack.push(DoOperator(token.type, n1, n2));
    }

#ifdef DISPLAY_STACK
    std::cout << "Stack: ";
    stack.display();
#endif
  }
  return stack.top();
}

int main(int argc, char *argv[]) {
  if (argc == 2) freopen(argv[1], "r", stdin);

  std::string ts;
  std::unordered_map<std::string, Number> table;
  while (std::getline(std::cin, ts)) {
    std::cout << "####################" << std::endl;
    std::vector<Token> infix;
    ParseTokenfromInfix(ts, infix);

    std::cout << "\nPrint normalized expression.\nInfix Expression: ";
    for (auto token : infix) std::cout << token << ' ';
    std::cout << std::endl;

    // generate the postfix expression
    auto postfix = Infix2Postfix(infix);
    std::cout << "\nPrint the postfix expression.\nPostfix Expression: ";
    for (auto token : postfix) std::cout << token << ' ';
    std::cout << std::endl;

    if (postfix.size() > 2 && postfix.back().type == ASSIGNMENT &&
        postfix.front().type == VARIABLE) {
      // assign value to a variable
      std::string var = postfix.front().var;
      std::cout << "Assign value to variable.\n" << var << ": ";
      postfix.pop_back();
      for (size_t i = 1; i < postfix.size(); ++i) postfix[i - 1] = postfix[i];
      postfix.pop_back();
      table[var] = EvalPostfix(postfix, table);
      std::cout << table[var] << std::endl;
      continue;
    }

    // evaluate the value of postfix
    Number value = EvalPostfix(postfix, table);

    for (auto token : postfix) std::cout << token << ' ';
    std::cout << "= " << value << std::endl;
  }
  return 0;
}
