void phase_2(char *input) {
  int i, a[5];
  read_six_numbers(input, a);
  if (a[0] || a[1] != 1) explode_bomb();
  for (i = 2; i <= 5; ++i) {
    if (a[i - 2] + a[i - 1] != a[i]) explode_bomb();
  }
}

void phase_3(char *input) {
  int idx, v1, v2;
  if (sscanf(input, "%d %d", &idx, &v1) <= 1) explode_bomb();

  switch (idx) {
  case 0:
    v2 = 108;
    break;
  case 1:
    v2 = 169;
    break;
  case 2:
    v2 = 197;
    break;
  case 3:
    v2 = 290;
    break;
  case 4:
    v2 = 938;
    break;
  case 5:
    v2 = 56;
    break;
  case 6:
    v2 = 645;
    break;
  case 7:
    v2 = 192;
    break;
  default:
    explode_bomb();
    return;
  }
  if (v1 != v2) explode_bomb();
}

void func4(int a, int b) {
  if (a <= 0) return 0;
  if (a == 1) return b;
  return func4(a - 1, b) + b + func4(a - 2, b);
}
void phase_4(char *input) {
  int val, b; //val:rsp+8(rbp-8), b:rsp+12(rbp-4)
  if (sscanf(input, "%d %d", &val, &b) != 2) explode_bomb();
  if (b <= 1) explode_bomb();
  if (b > 4) explode_bomb();
  if (func4(5, b) != val) explode_bomb();
}

void phase_5(char *input) {
  int val, i;
  if (string_length(input) != 6) explode_bomb();
  val = 0;
  for (i = 0; i <= 5; ++i) val += array_3511[input[i] & 0xf];
  // array_3511: 2 10 6 1 12 16
  // 43 = 2 + 2 + 10 + 1 + 12 + 16
  if (val != 43) explode_bomb();
}


void phase_6(char *input) {
  int a[6], i, j;
  Node *p[6], *p1, *p2, *p3;

  read_six_numbers(input, a);
  for (i = 0; i <= 5; ++i) {
    if ((unsigned int)(a[i] - 1) > 5) explode_bomb();
    for (j = i + 1; j <= 5; ++j)
      if (a[i] == a[j]) explode_bomb();
  }

  for (i = 0; i <= 5; ++i) {
    for (j = 1, p1 = &node1; a[i] > j; ++j)
      p1 = p1->next;
    p[i] = p1;
  }

  p1 = p[0];
  p2 = p[0];
  for (i = 1; i <= 5; ) {
    p3 = p[i];
    p2->next = p3;
    ++i;
    p2 = p3;
  }
  p2->next = 0;

  for (i = 0; i <= 4; ++i) {
    if (p1->val < p1->next->val) explode_bomb();
    p1 = p1->next;
  }
}

void phase_defused() {
  int a, b, c, d;
  char passphrase[80];

  if (num_input_strings == 6) {
    a = b;
    if (sscanf(inputs, "%d %d %s", &c, &d) == 3 && !strings_not_equal(passphrase, "DrEvil")) {
      puts("Curses, you've found the secret phase!");
      puts("But finding it and solving it are quite different...");
      secret_phase();
    }
    puts("Congratulations! You've defused the bomb!");
  }
}

void secret_phase() {
  char *s;
  int a;

  s = read_line();
  a = strtol(v0, 0LL, 10);
  if ((unsigned)(a - 1) > 1003) explode_bomb();
  if (fun7(&n1, a) != 3) explode_bomb();
  puts("Wow! You've defused the secret stage!");
  phase_defused();
}

int fun7(treeNode *node, int val) {
  if (node == 0) return -1;
  if (node->val > val) return 2 * fun7(node->left, val);
  if (node->val == val) return 0;
  return 2 * fun7(node->right, val) + 1;
}
