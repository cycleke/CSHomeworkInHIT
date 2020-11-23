#include <algorithm>
#include <cassert>
#include <cstring>
#include <fstream>
#include <iostream>
#include <string>
using namespace std;

const int MAX_VERTEX = 100;
const int MAX_EDGE = 1000 * 2;

class AdjArray {
public:
  AdjArray(int n = 0) { set_vertex_number(n); }

  void set_vertex_number(int n) {
    assert(n < MAX_VERTEX);
    _n = n;
    for (int i = 1; i <= n; ++i) memset(_a[i] + 1, 0, sizeof(bool) * n);
  }
  int get_vertex_number() const { return _n; }
  void add_edge(int u, int v) {
    assert(u > 0 && u <= _n && v > 0 && v <= _n);
    _a[u][v] = true;
  }
  bool connected(int u, int v) const {
    assert(u > 0 && u <= _n && v > 0 && v <= _n);
    return _a[u][v];
  }
  void display() const {
    cout << "Display AdjArray: n = " << _n << endl;
    for (int i = 1; i <= _n; ++i) {
      for (int j = 1; j <= _n; ++j) cout << _a[i][j] << ' ';
      cout << endl;
    }
  }

protected:
  int _n;
  bool _a[MAX_VERTEX][MAX_VERTEX];
};

class AdjList {
public:
  AdjList(int n = 0) { set_vertex_number(n); }

  void set_vertex_number(int n) {
    assert(n < MAX_VERTEX);
    _n = n, _tote = 0;
    memset(_head + 1, -1, sizeof(int) * n);
  }
  int get_vertex_number() const { return _n; }
  void add_edge(int u, int v) {
    assert(u > 0 && u <= _n && v > 0 && v <= _n);
    assert(_tote < MAX_EDGE);
    _next[_tote] = _head[u], _to[_tote] = v;
    _head[u] = _tote++;
  }
  int get_begin(int u) const {
    assert(u > 0 && u <= _n);
    return _head[u];
  }
  int get_vertex(int iter) const {
    assert(iter >= 0 && iter < _tote);
    return _to[iter];
  }
  int get_next(int iter) const {
    assert(iter >= 0 && iter < _tote);
    return _next[iter];
  }
  void display() const {
    cout << "Display AdjList: n = " << _n << endl;
    for (int i = 1; i <= _n; ++i) {
      for (int j = _head[i]; ~j; j = _next[j]) cout << _to[j] << ' ';
      cout << endl;
    }
  }

protected:
  int _n, _tote, _head[MAX_VERTEX];
  int _next[MAX_EDGE], _to[MAX_EDGE];
};

int PrintMenu() {
  int a;
  cout << "Input the instruction:\n"
       << "1. Print DFS spanning tree.\n"
       << "2. Print BFS spanning tree.\n"
       << "3. Convert adjacency list to array.\n"
       << "4. Convert adjacency array to list.\n"
       << "-1. Exit." << endl;
  cin >> a;
  if (a == 1) {
    cout << "Recursive algorithm?(0/1)" << endl;
    cin >> a;
  }
  return a;
}

void BuildGraph(const string &file, AdjArray *array, AdjList *list) {
  ifstream in;
  in.open(file);

  int n, m, flag;
  in >> n >> m >> flag;
  array->set_vertex_number(n);
  list->set_vertex_number(n);

  for (int u, v; m; --m) {
    in >> u >> v;
    array->add_edge(u, v);
    list->add_edge(u, v);
    if (flag) {
      array->add_edge(v, u);
      list->add_edge(v, u);
    }
  }
}

bool visited[MAX_VERTEX];

void RecursiveDFS(int u, const int n, AdjArray *array, AdjList *list) {
  cout << u << ' ';
  visited[u] = true;
  if (array != nullptr) {
    for (int i = 1; i <= n; ++i)
      if (!visited[i] && array->connected(u, i))
        RecursiveDFS(i, n, array, nullptr);
  } else {
    for (int it = list->get_begin(u); ~it; it = list->get_next(it)) {
      int v = list->get_vertex(it);
      if (!visited[v]) RecursiveDFS(v, n, array, list);
    }
  }
}
void RecursiveDFS(AdjArray *array, AdjList *list) {
  cout << "\nDFS Sequence: ";
  const int n = (array != nullptr) ? array->get_vertex_number()
                                   : list->get_vertex_number();
  memset(visited + 1, 0, sizeof(bool) * n);
  for (int i = 1; i <= n; ++i)
    if (!visited[i]) RecursiveDFS(i, n, array, list);
  cout << endl;
}
void NoRecursiveDFS(int root, const int n, AdjArray *array, AdjList *list) {
  static int vertex[MAX_VERTEX], last[MAX_VERTEX];
  int top = 1;
  vertex[0] = root;
  last[0] = (array != nullptr) ? 1 : list->get_begin(root);
  visited[root] = true;
  cout << root << ' ';
  while (top) {
    --top;
    if (array != nullptr) {
      int u = vertex[top], i = last[top];
      for (; i <= n; ++i)
        if (!visited[i] && array->connected(u, i)) {
          last[top++] = i + 1;

          vertex[top] = i;
          last[top] = 1;
          visited[i] = true;
          cout << i << ' ';
          ++top;
          break;
        }
    } else {
      int it = last[top];
      for (; ~it; it = list->get_next(it)) {
        int v = list->get_vertex(it);
        if (visited[v]) continue;
        last[top++] = list->get_next(it);

        vertex[top] = v;
        last[top] = list->get_begin(v);
        visited[v] = true;
        cout << v << ' ';
        ++top;
        break;
      }
    }
  }
}
void NoRecursiveDFS(AdjArray *array, AdjList *list) {
  cout << "\nDFS Sequence: ";
  const int n = (array != nullptr) ? array->get_vertex_number()
                                   : list->get_vertex_number();
  memset(visited + 1, 0, sizeof(bool) * n);
  for (int i = 1; i <= n; ++i)
    if (!visited[i]) NoRecursiveDFS(i, n, array, list);
  cout << endl;
}
void BFS(int root, const int n, AdjArray *array, AdjList *list) {
  static int queue[MAX_VERTEX];
  int head = 0, tail = 0;
  queue[tail++] = root;
  visited[root] = true;
  while (head < tail) {
    int u = queue[head++];
    cout << u << ' ';
    if (array != nullptr) {
      for (int i = 1; i <= n; ++i)
        if (!visited[i] && array->connected(u, i)) {
          visited[i] = true;
          queue[tail++] = i;
        }
    } else {
      for (int it = list->get_begin(u); ~it; it = list->get_next(it)) {
        int v = list->get_vertex(it);
        if (visited[v]) continue;
        visited[v] = true;
        queue[tail++] = v;
      }
    }
  }
}
void BFS(AdjArray *array, AdjList *list) {
  cout << "\nBFS Sequence: ";
  const int n = (array != nullptr) ? array->get_vertex_number()
                                   : list->get_vertex_number();
  memset(visited + 1, 0, sizeof(bool) * n);
  for (int i = 1; i <= n; ++i)
    if (!visited[i]) BFS(i, n, array, list);
  cout << endl;
}
void ConvertList2Array(AdjArray *dest, const AdjList *source) {
  int n = source->get_vertex_number();
  dest->set_vertex_number(n);
  for (int i = 1; i <= n; ++i)
    for (int it = source->get_begin(i); ~it; it = source->get_next(it))
      dest->add_edge(i, source->get_vertex(it));
}
void ConvertArray2List(AdjList *dest, const AdjArray *source) {
  int n = source->get_vertex_number();
  dest->set_vertex_number(n);
  for (int i = 1; i <= n; ++i)
    for (int j = 1; j <= n; ++j)
      if (source->connected(i, j)) dest->add_edge(i, j);
}

int main() {

  string file, type;
  cout << "Input the graph file(default: input.txt):";
  getline(cin, file);
  if (file.empty()) file = "input.txt";

  AdjArray *array_graph = new AdjArray;
  AdjList *list_graph = new AdjList;
  BuildGraph(file, array_graph, list_graph);

  for (;;) {
    int op = PrintMenu();
    if (op < -1 || op > 4) {
      cout << "Wrong instruction!!!" << endl;
      continue;
    }
    if (op == -1) break;
    if (op < 3) {
      getline(cin, type);
      for (;;) {
        cout << "Input the graph storage(list/array, default: list):";
        getline(cin, type);
        if (type.empty()) type = "list";
        if (type != "list" && type != "array") continue;
        break;
      }
    }

    AdjArray *array = type == "array" ? array_graph : nullptr;
    AdjList *list = type == "list" ? list_graph : nullptr;

    switch (op) {
    case 0: {
      NoRecursiveDFS(array, list);
      break;
    }
    case 1: {
      RecursiveDFS(array, list);
      break;
    }
    case 2: {
      BFS(array, list);
      break;
    }
    case 3: {
      ConvertList2Array(array_graph, list_graph);
      array_graph->display();
      break;
    }
    case 4: {
      ConvertArray2List(list_graph, array_graph);
      list_graph->display();
      break;
    }
    default: break;
    }
  }

  delete array_graph;
  delete list_graph;

  return 0;
}
