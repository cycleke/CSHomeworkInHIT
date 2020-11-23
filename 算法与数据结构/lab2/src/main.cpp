#include <iostream>
#include <vector>
using namespace std;

template <class T> class Stack {
public:
  Stack();
  ~Stack();

  void push(T);
  void pop();
  T &top();
  bool is_empty() const;
  size_t get_size() const;

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

template <class T> class Queue {
public:
  Queue();
  ~Queue();

  void push(T);
  void pop();
  T &front();
  bool is_empty() const;
  size_t get_size() const;

protected:
  struct Node {
    T value;
    Node *next;
  };
  Node *head_, *tail_;
  size_t size_;
};

template <class T> Queue<T>::Queue() {
  // construct a stack
  size_ = 0;
  head_ = tail_ = nullptr;
}
template <class T> Queue<T>::~Queue() {
  // distruct a stack
  while (!is_empty()) pop();
}

template <class T> void Queue<T>::push(T element) {
  ++size_;
  // generate a new node
  Node *new_node = new Node;
  new_node->value = element, new_node->next = nullptr;
  if (tail_ != nullptr) {
    tail_->next = new_node;
  } else {
    head_ = new_node;
  }
  tail_ = new_node;
}
template <class T> void Queue<T>::pop() {
  // determine whether the stack is empty
  if (size_ == 0) {
    std::cerr << "The Queue has nothing!!!" << std::endl;
    exit(-1);
  }
  --size_;
  Node *iter = head_->next;
  delete head_;
  head_ = iter;
  if (size_ == 0) tail_ = nullptr;
}
template <class T> T &Queue<T>::front() {
  // determine whether the stack is empty
  if (size_ == 0) {
    std::cerr << "The Queue has nothing!!!" << std::endl;
    exit(-1);
  }
  return head_->value;
}
template <class T> bool Queue<T>::is_empty() const { return size_ == 0; }
template <class T> size_t Queue<T>::get_size() const { return size_; }

struct BTREE {
  int dep, value;
  BTREE *lson, *rson, *fa;
};

BTREE *BuildTree(BTREE *fa = nullptr) {
  // build tree from pre-order traversal.
  int value;
  cin >> value;
  if (value == -1) return nullptr;
  BTREE *node = new BTREE;
  node->fa = fa;
  node->value = value;
  node->dep = fa == nullptr ? 0 : (fa->dep + 1);
  node->lson = BuildTree(node);
  node->rson = BuildTree(node);
  return node;
}
void DeleteTree(BTREE *u) {
  if (u == nullptr) return;
  DeleteTree(u->lson);
  DeleteTree(u->rson);
  delete u;
}

int PrintMenu() {
  char ch;
  static auto valid = [](const char &ch) { return ch >= '0' && ch <= '4'; };
  while (true) {
    cout << "Function Instruction:" << endl
         << "1: Preorder traversal." << endl
         << "2: Inorder traversal." << endl
         << "3: Postorder traversal." << endl
         << "4: Find LCA." << endl
         << "0: Exit Program." << endl;
    if (!(cin >> ch)) {
      cout << "Get EOF signal!" << endl;
      exit(-1);
    }
    if (!valid(ch)) {
      cout << "Wrong Instruction, please input again!" << endl;
    } else {
      return ch - '0';
    }
  }
}

void RecPreTraversal(BTREE *root) {
  if (root == nullptr) return;
  cout << root->value << ' ';
  RecPreTraversal(root->lson);
  RecPreTraversal(root->rson);
}
void NoRecPreTraversal(BTREE *root) {
  Stack<BTREE *> stk;
  BTREE *u = root;
  while (!stk.is_empty() || u != nullptr) {
    while (u != nullptr) {
      cout << u->value << ' ';
      stk.push(u);
      u = u->lson;
    }
    if (!stk.is_empty()) {
      u = stk.top();
      stk.pop();
      u = u->rson;
    }
  }
}
void RecInTraversal(BTREE *root) {
  if (root == nullptr) return;
  RecInTraversal(root->lson);
  cout << root->value << ' ';
  RecInTraversal(root->rson);
}
void NoRecInTraversal(BTREE *root) {
  Stack<BTREE *> stk;
  BTREE *u = root;
  while (!stk.is_empty() || u != nullptr) {
    while (u != nullptr) {
      stk.push(u);
      u = u->lson;
    }
    if (!stk.is_empty()) {
      u = stk.top();
      stk.pop();
      cout << u->value << ' ';
      u = u->rson;
    }
  }
}
void RecPostTraversal(BTREE *root) {
  if (root == nullptr) return;
  RecPostTraversal(root->lson);
  RecPostTraversal(root->rson);
  cout << root->value << ' ';
}
void NoRecPostTraversal(BTREE *root) {
  Stack<BTREE *> stk;
  BTREE *u = root;
  while (!stk.is_empty() || u != nullptr) {
    while (u != nullptr) {
      stk.push(u);
      BTREE *temp = u->rson;
      u = u->lson;
      if (u == nullptr) u = temp;
    }
    u = stk.top();
    stk.pop();
    cout << u->value << ' ';
    if (!stk.is_empty() && stk.top()->lson == u) {
      u = stk.top()->rson;
    } else {
      u = nullptr;
    }
  }
}

BTREE *FindNode(BTREE *root, int value) {
  Queue<BTREE *> que;
  que.push(root);
  while (!que.is_empty()) {
    BTREE *u = que.front();
    que.pop();
    if (u->value == value) return u;
    if (u->lson != nullptr) que.push(u->lson);
    if (u->rson != nullptr) que.push(u->rson);
  }
  return nullptr;
}
BTREE *FindLCA(BTREE *u, BTREE *v) {
  while (u != nullptr && v != nullptr && u != v) {
    if (u->dep < v->dep) {
      v = v->fa;
    } else if (u->dep > v->dep) {
      u = u->fa;
    } else {
      v = v->fa;
      u = u->fa;
    }
  }
  return u == v ? u : nullptr;
}

int main(int argc, char *argv[]) {
  if (argc == 2) freopen(argv[1], "r", stdin);

  cout << "build tree from pre-order traversal." << endl
       << "  integer for node, -1 for none." << endl;
  BTREE *root = BuildTree();
  while (true) {
    int oper = PrintMenu(), recursice;
    if (oper == 0) break;
    if (oper < 4) {
      cout << "Recursice?(0/1):";
      cin >> recursice;
    }
    switch (oper) {
    case 1:
      cout << "Preorder Traversal: ";
      if (recursice) {
        RecPreTraversal(root);
      } else {
        NoRecPreTraversal(root);
      }
      cout << endl;
      break;
    case 2:
      cout << "Inorder Traversal: ";
      if (recursice) {
        RecInTraversal(root);
      } else {
        NoRecInTraversal(root);
      }
      cout << endl;
      break;
    case 3:
      cout << "Postorder Traversal: ";
      if (recursice) {
        RecPostTraversal(root);
      } else {
        NoRecPostTraversal(root);
      }
      cout << endl;
      break;
    case 4: {
      int a, b;
      cin >> a >> b;
      BTREE *u = FindNode(root, a);
      BTREE *v = FindNode(root, b);
      if (u == nullptr || v == nullptr) {
        continue;
      } else {
        cout << "The LCA of a and b: " << FindLCA(u, v)->value;
      }
      cout << endl;
    } break;
    default: break;
    }
  }
  DeleteTree(root);
  return 0;
}
