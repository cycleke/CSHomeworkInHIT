#include <algorithm>
#include <cassert>
#include <fstream>
#include <iostream>
using namespace std;

#define GRAPHVIZ_DOT // print tree in graphviz dot style

#ifdef GRAPHVIZ_DOT
int g_null_count;
#endif

class AVLTree {
public:
  AVLTree() { root_ = nullptr; }
  ~AVLTree() { clear(); }

  void clear() { clear(root_); }
  void insert(int value) { root_ = insert(root_, value); }
  void erase(int value) { root_ = erase(root_, value); }
  bool find(int value) const { return find(root_, value); }
  void PrintRelaship() const {
    cout << "Print Tree:\n";
#ifdef GRAPHVIZ_DOT
    g_null_count = 0;
    cout << "digraph {\n";
#endif
    PrintRelaship(root_);
#ifdef GRAPHVIZ_DOT
		cout << "}\n";
#endif
    cout << "############" << endl;
  }

protected:
  struct Node {
    int value;
    int height;
    Node *left, *right;

    Node(int value = 0) : value(value) {
      height = 1;
      left = right = nullptr;
    }
  };
  typedef Node *NodePointer;

  NodePointer root_;

  void clear(NodePointer node) {
    if (node == nullptr) return;
    clear(node->left), clear(node->right);
    delete node;
  }

  // return the height of node
  int getHeight(NodePointer node) {
    return (node == nullptr) ? 0 : node->height;
  }
  // return the balance factor
  int getBalanceFactor(NodePointer node) {
    return (node == nullptr) ? 0
                             : getHeight(node->left) - getHeight(node->right);
  }

  // left rotate subtree rooted with y
  NodePointer LeftRotate(NodePointer y) {
    NodePointer x = y->right, temp = x->left;
    x->left = y, y->right = temp;
    y->height = max(getHeight(y->left), getHeight(y->right)) + 1;
    x->height = max(getHeight(x->left), getHeight(x->right)) + 1;
    return x;
  }

  // right rotate subtree rooted with y
  NodePointer RightRotate(NodePointer y) {
    NodePointer x = y->left, temp = x->right;
    x->right = y, y->left = temp;
    y->height = max(getHeight(y->left), getHeight(y->right)) + 1;
    x->height = max(getHeight(x->left), getHeight(x->right)) + 1;
    return x;
  }

  // insert a value into the subtree rooted with node
  NodePointer insert(NodePointer node, int value) {
    if (node == nullptr) return new Node(value);

    if (value < node->value) {
      // less value -> left subtree
      node->left = insert(node->left, value);
    } else if (value > node->value) {
      // greater value -> right subtree
      node->right = insert(node->right, value);
    } else {
      // equal values are not allowed
      return node;
    }

    node->height = max(getHeight(node->left), getHeight(node->right)) + 1;
    int bf = getBalanceFactor(node);
    if (bf > 1) {
      // LR case, an additional rotation is needed
      if (value > node->left->value) node->left = LeftRotate(node->left);
      return RightRotate(node);
    } else if (bf < -1) {
      // RL case, an additional rotation is needed
      if (value < node->right->value) node->right = RightRotate(node->right);
      return LeftRotate(node);
    }
    return node;
  }

  // erase the value from the subtree rooted with node
  NodePointer erase(NodePointer node, int value) {
    if (node == nullptr) return node;

    if (value < node->value) {
      node->left = erase(node->left, value);
    } else if (value > node->value) {
      node->right = erase(node->right, value);
    } else {
      if (node->left == nullptr || node->right == nullptr) {
        NodePointer temp = node->left == nullptr ? node->right : node->left;
        if (temp == nullptr) {
          temp = node;
          node = nullptr;
        } else {
          *node = *temp;
        }
        delete temp;
      } else {
        NodePointer temp = node->left;
        while (temp->right != nullptr) temp = temp->right;
        node->value = temp->value;
        node->left = erase(node->left, temp->value);
      }
    }
    if (node == nullptr) return node;

    node->height = max(getHeight(node->left), getHeight(node->right)) + 1;
    int bf = getBalanceFactor(node);

    if (bf > 1) {
      // LR case, an additional rotation is needed
      if (getBalanceFactor(node->left) < 0) node->left = LeftRotate(node->left);
      return RightRotate(node);
    } else if (bf < -1) {
      // RL case, an additional rotation is needed
      if (getBalanceFactor(node->right) > 0)
        node->right = RightRotate(node->right);
      return LeftRotate(node);
    }

    return node;
  }

  bool find(NodePointer node, int value) const {
    if (node == nullptr) return false;
    if (value < node->value) return find(node->left, value);
    if (value > node->value) return find(node->right, value);
    return true;
  }

  void PrintRelaship(NodePointer node) const {
    if (node == nullptr) return;
    if (node->left != nullptr) {
      cout << node->value << " -> " << node->left->value << ";" << endl;
      PrintRelaship(node->left);
    }
#ifdef GRAPHVIZ_DOT
    else {
      cout << "null" << g_null_count << "[label=\"none\"];\n";
      cout << node->value << "-> null" << g_null_count++ << endl;
    }
#endif
    if (node->right != nullptr) {
      cout << node->value << " -> " << node->right->value << ";" << endl;
      PrintRelaship(node->right);
    }
#ifdef GRAPHVIZ_DOT
    else {
      cout << "null" << g_null_count << "[label=\"none\"];\n";
      cout << node->value << "-> null" << g_null_count++ << endl;
    }
#endif
  }
};

int PrintMenu() {
  int a;
  cout << "Input the instruction:\n"
       << "1. Add a new value.\n"
       << "2. Remove a value.\n"
       << "3. Find whether a value exist.\n"
       << "4. Print relaship of AVLTree.\n"
       << "-1. Exit." << endl;

  if (!(cin >> a)) return -1;
  return a;
}

int main() {
  AVLTree tree;
  while (true) {
    int op = PrintMenu();
    if (op < -1 || op > 4) {
      cout << "Wrong instruction!!!" << endl;
      continue;
    }
    if (op == -1) break;
    int x;
    if (op < 4) cin >> x;
    switch (op) {
    case 1: {
      tree.insert(x);
      break;
    }
    case 2: {
      tree.erase(x);
      break;
    }
    case 3: {
      cout << tree.find(x) << endl;
      break;
    }
    case 4: {
      tree.PrintRelaship();
      break;
    }
    default: assert(0);
    }
  }
  return 0;
}
