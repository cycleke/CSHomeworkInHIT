#include <algorithm>
#include <cassert>
#include <chrono>
#include <climits>
#include <iostream>
#include <random>
using namespace std;

const int MAX_ARRAY_SIZE = (int)1e6;

long InsertionSort(int a[], const int n) {
  auto start = chrono::high_resolution_clock::now();

  for (int i = 1; i < n; ++i) {
    int key = a[i], j = i - 1;
    while (j >= 0 && key < a[j]) {
      a[j + 1] = a[j];
      --j;
    }
    a[j + 1] = key;
  }

  auto stop = chrono::high_resolution_clock::now();
  auto duration = chrono::duration_cast<chrono::microseconds>(stop - start);
  return duration.count();
}

long BinaryInsertionSort(int a[], const int n) {
  auto start = chrono::high_resolution_clock::now();

  for (int i = 1; i < n; ++i) {
    int key = a[i], l = 0, r = i - 1;
    while (l <= r) {
      int mid = (l + r) / 2;
      if (a[mid] > key) {
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    for (int j = i; j > l; --j) a[j] = a[j - 1];
    a[l] = key;
  }

  auto stop = chrono::high_resolution_clock::now();
  auto duration = chrono::duration_cast<chrono::microseconds>(stop - start);
  return duration.count();
}

long BubbleSort(int a[], const int n) {
  auto start = chrono::high_resolution_clock::now();

  for (int i = 0, end_i = n - 1; i < end_i; ++i)
    for (int j = n - 1, end_j = i + 1; j >= end_j; --j)
      if (a[j] < a[j - 1]) swap(a[j], a[j - 1]);

  auto stop = chrono::high_resolution_clock::now();
  auto duration = chrono::duration_cast<chrono::microseconds>(stop - start);
  return duration.count();
}

long SelectionSort(int a[], const int n) {
  auto start = chrono::high_resolution_clock::now();

  for (int i = 0; i < n; ++i) {
    int min_pos = i;
    for (int j = i + 1; j < n; ++j)
      if (a[j] < a[min_pos]) min_pos = j;
    if (min_pos != i) swap(a[min_pos], a[i]);
  }

  auto stop = chrono::high_resolution_clock::now();
  auto duration = chrono::duration_cast<chrono::microseconds>(stop - start);
  return duration.count();
}

void QuickSort(int *begin, int *end) {
  if (begin >= end) return;
  int key = *end;
  int *i = begin - 1;
  for (int *j = begin; j + 1 <= end; ++j)
    if (*j < key) {
      ++i;
      swap(*i, *j);
    }
  swap(*(++i), *end);
  QuickSort(begin, i - 1);
  QuickSort(i + 1, end);
}

long QuickSort(int a[], const int n) {
  auto start = chrono::high_resolution_clock::now();

  QuickSort(a, a + n - 1);

  auto stop = chrono::high_resolution_clock::now();
  auto duration = chrono::duration_cast<chrono::microseconds>(stop - start);
  return duration.count();
}

long HeapSort(int a[], const int n) {
  auto start = chrono::high_resolution_clock::now();

  static auto MaxHeapify = [a](int p, const int n) -> void {
    int ch = 2 * p + 1;
    while (ch < n) {
      if (ch + 1 < n && a[ch + 1] > a[ch]) ++ch;
      if (a[p] >= a[ch]) break;
      swap(a[p], a[ch]);
      p = ch, ch = 2 * p + 1;
    }
  };

  for (int i = n / 2 - 1; i >= 0; --i) MaxHeapify(i, n);
  for (int i = n - 1; i >= 0; --i) {
    swap(a[i], a[0]);
    MaxHeapify(0, i);
  }

  auto stop = chrono::high_resolution_clock::now();
  auto duration = chrono::duration_cast<chrono::microseconds>(stop - start);
  return duration.count();
}

long RadixSort(int a[], const int n) {
  static const int BASE = 16;
  static int count[BASE];

  auto start = chrono::high_resolution_clock::now();

  int max_elem = a[0], step = 1, temp[n];
  for (int i = 1; i < n; ++i) max_elem = max(max_elem, a[i]);
  while (max_elem > BASE) {
    ++step;
    max_elem /= BASE;
  }

  int radix = 1;
  for (int i = 0; i < step; ++i, radix *= BASE) {
    for (int j = 0; j < BASE; ++j) count[j] = 0;
    for (int j = 0; j < n; ++j) ++count[(a[j] / radix) % BASE];
    for (int j = 1; j < BASE; ++j) count[j] += count[j - 1];
    for (int j = n - 1; j >= 0; --j) {
      int base = (a[j] / radix) % BASE;
      temp[--count[base]] = a[j];
    }
    for (int j = 0; j < n; ++j) a[j] = temp[j];
  }

  auto stop = chrono::high_resolution_clock::now();
  auto duration = chrono::duration_cast<chrono::microseconds>(stop - start);
  return duration.count();
}

int main(int argc, char *argv[]) {
  freopen("time.csv", "w", stdout);

  random_device rd;
  std::mt19937 gen(rd());
  int *array = new int[MAX_ARRAY_SIZE];

  printf("Algorithm");

  vector<int> ns({1});
  int step = 1000;
  for (int n = step; n <= 50000; n += step) ns.push_back(n);
  sort(ns.begin(), ns.end());
  ns.erase(unique(ns.begin(), ns.end()), ns.end());
  for (int n : ns) printf(",%d", n);
  puts("");

  fprintf(stderr, "p:");
  for (int i = 0; i < (int)ns.size(); ++i) fprintf(stderr, "#");
  fprintf(stderr, "\n");

  for (int algor = 1; algor < 8; ++algor) {
    long (*foo)(int[], int);
    switch (algor) {
      case 1:
        foo = InsertionSort;
        printf("Insertion Sort");
        break;
      case 2:
        foo = BinaryInsertionSort;
        printf("Binary Insertion Sort");
        break;
      case 3:
        foo = BubbleSort;
        printf("Bubble Sort");
        break;
      case 4:
        foo = QuickSort;
        printf("Quick Sort");
        break;
      case 5:
        foo = SelectionSort;
        printf("Selection Sort");
        break;
      case 6:
        foo = HeapSort;
        printf("Heap Sort");
        break;
      case 7:
        foo = RadixSort;
        printf("Radix Sort");
        break;
      default:
        assert(0);
        break;
    }

    int gen_max = algor == 7 ? 999 : INT_MAX;
    fprintf(stderr, "%d:", algor);
    std::uniform_int_distribution<int> dis(0, gen_max);
    for (int n : ns) {
      long sum = 0;
      for (int step = 0; step < 15; ++step) {
        for (int i = 0; i < n; ++i) array[i] = dis(gen);
        sum += foo(array, n);
      }
      printf(",%ld", sum / 15);
      fprintf(stderr, "#");
    }
    puts("");
    fprintf(stderr, "\n");
  }
  delete[] array;
  return 0;
}
