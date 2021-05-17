int fib(int n) {
  if (n <= 0 || n > 100) {
    return 1;
  } else {
    return fib(n - 1) + fib(n - 2);
  }
}

void copy(int a[10][], int b[10][10]) {
  int i = 0;
  while (i < 10) {
    a[i][0] = b[i][0];
    i = i + 1;
  }
}

int print(struct Iter *left, struct Iter *right) {
  while (left < right) {
    printf("%d %lf\n", a, b);
    left = left + 1;
  }
}

struct Iter {
  int a;
  double b;
};

int global_init = 1, global_non_init;

int main() {

  struct Node {
    int type;
    void *value;
    struct Node *children[20];
  };

  struct Node nodes[1 + 2];
  int x = fib(nodes[0].type);

  struct Node node;
  print("%p\n", &node);

  struct Edge *edges_iter[3][3 * 3][2 + 0xff % 077];
  edges[0][1][2] = &edges[3];

  edge[99].from_node[1].weight = 2.123e7;
  int *ptr = NULL;

  return 0;
}
