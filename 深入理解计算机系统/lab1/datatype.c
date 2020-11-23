#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LEN 128

int stu_id, *p, a[MAX_LEN];
float var_f;
double var_d;
char name[MAX_LEN], ch;

struct sample_struct {
  char a;
  int b;
} struct_sample;

union sample_union {
  int a;
  long long b;
} union_sample;

enum sample_enum {
  ONE = 1, TWO, THREE
} enum_sample;

int main();
void show_byte(char *, int);
void print_int(int, char *, void *);
void print_float(float, char *, void *);
void print_double(double, char *, void *);
void print_char(char, char *, void *);
void print_string(char *, char *, void *);
void print_struct(struct sample_struct, char *, void *);
void print_union(union sample_union, char *, void *);
void print_enum(enum sample_enum, char *, void *);
void print_main(int (*)(void), char *, void *);
void print_printf(int (*)(const char * restrict), char *, void *);

int main() {
  stu_id = 9999999999;
  print_int(stu_id, "stu_id", &stu_id);
  var_f = 998244353.0;
  print_float(var_f, "var_f", &var_f);
  var_d = 19260817.0;
  print_double(var_d, "var_d", &var_d);
  ch = '&';
  print_char(ch, "ch", &ch);
  strcpy(name, "cycleke");
  print_string(name, "name", &name);
  struct_sample.a = '@', struct_sample.b = 2;
  print_struct(struct_sample, "struct_sample", &struct_sample);
  union_sample.a = 1, union_sample.b = 2;
  print_union(union_sample, "union_sample", &union_sample);
  enum_sample = TWO;
  print_enum(enum_sample, "enum_sample", &enum_sample);
  print_main(main, "main", &main);
  print_printf(printf, "printf", &printf);
  return 0;
}

void show_byte(char *start, int len) {
  int i;
  for (i = 0; i < len; ++i)
    printf("%.2X ", start[i]);
  puts("");
}

void print_int(int a, char *name, void *addr) {
  puts("###########################");
  puts("Print int data");
  printf("var name:\t%s\n", name);
  printf("var value:\t%d\n", a);
  printf("var addr:\t%p\n", addr);
  printf("var byte:\t");
  show_byte((char*)addr, sizeof(a));
  puts("###########################\n");
}

void print_float(float x, char *name, void *addr) {
  puts("###########################");
  puts("Print float data");
  printf("var name:\t%s\n", name);
  printf("var value:\t%f\n", x);
  printf("var addr:\t%p\n", addr);
  printf("var byte:\t");
  show_byte((char*)addr, sizeof(x));
  puts("###########################\n");
}

void print_double(double x, char *name, void *addr) {
  puts("###########################");
  puts("Print double data");
  printf("var name:\t%s\n", name);
  printf("var value:\t%lf\n", x);
  printf("var addr:\t%p\n", addr);
  printf("var byte:\t");
  show_byte((char*)addr, sizeof(x));
  puts("###########################\n");
}

void print_char(char ch, char *name, void *addr) {
  puts("###########################");
  puts("Print char data");
  printf("var name:\t%s\n", name);
  printf("var value:\t%c\n", ch);
  printf("var addr:\t%p\n", addr);
  printf("var byte:\t");
  show_byte((char*)addr, sizeof(ch));
  puts("###########################\n");
}

void print_string(char *s, char *name, void *addr) {
  puts("###########################");
  puts("Print char* data");
  printf("var name:\t%s\n", name);
  printf("var value:\t%s\n", s);
  printf("var addr:\t%p\n", addr);
  printf("var byte:\t");
  show_byte((char*)addr, sizeof(s));
  puts("###########################\n");
}

void print_struct(struct sample_struct a, char *name, void *addr) {
  puts("###########################");
  puts("Print struct data");
  printf("var name:\t%s\n", name);
  printf("var value:\ta=%c b=%d\n", a.a, a.b);
  printf("var addr:\t%p\n", addr);
  printf("var byte:\t");
  show_byte((char*)addr, sizeof(a));
  puts("###########################\n");
}

void print_union(union sample_union a, char *name, void *addr) {
  puts("###########################");
  puts("Print union data");
  printf("var name:\t%s\n", name);
  printf("var value:\ta=%d b=%lld\n", a.a, a.b);
  printf("var addr:\t%p\n", addr);
  printf("var byte:\t");
  show_byte((char*)addr, sizeof(a));
  puts("###########################\n");
}

void print_enum(enum sample_enum a, char *name, void *addr) {
  puts("###########################");
  puts("Print enum data");
  printf("var name:\t%s\n", name);
  printf("var value:\t%d\n", a);
  printf("var addr:\t%p\n", addr);
  printf("var byte:\t");
  show_byte((char*)addr, sizeof(a));
  puts("###########################\n");
}

void print_main(int (*foo)(void), char *name, void *addr) {
  puts("###########################");
  puts("Print function main data");
  printf("var name:\t%s\n", name);
  printf("var value:\t%p\n", foo);
  printf("var addr:\t%p\n", addr);
  printf("var byte:\t");
  show_byte((char*)addr, sizeof(foo));
  puts("###########################\n");
}

void print_printf(int (*foo)(const char * restrict), char *name, void *addr) {
  puts("###########################");
  puts("Print function printf data");
  printf("var name:\t%s\n", name);
  printf("var value:\t%p\n", foo);
  printf("var addr:\t%p\n", addr);
  printf("var byte:\t");
  show_byte((char*)addr, sizeof(foo));
  puts("###########################\n");
}
