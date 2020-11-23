#include <algorithm>
#include <cassert>
#include <chrono>
#include <climits>
#include <iostream>
#include <random>
using namespace std;

#define DISPLAY

const int MAX_ARRAY_SIZE = (int)1e6;

void InsertionSort(int a[], const int n) {
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
  cout << "Time taken by insertion sort: " << duration.count()
       << " microseconds." << endl;
}

void BinaryInsertionSort(int a[], const int n) {
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
  cout << "Time taken by binary insertion sort: " << duration.count()
       << " microseconds." << endl;
}

void BubbleSort(int a[], const int n) {
  auto start = chrono::high_resolution_clock::now();

  for (int i = 0, end_i = n - 1; i < end_i; ++i)
    for (int j = n - 1, end_j = i + 1; j >= end_j; --j)
      if (a[j] < a[j - 1]) swap(a[j], a[j - 1]);

  auto stop = chrono::high_resolution_clock::now();
  auto duration = chrono::duration_cast<chrono::microseconds>(stop - start);
  cout << "Time taken by bubble sort: " << duration.count() << " microseconds."
       << endl;
}

void SelectionSort(int a[], const int n) {
  auto start = chrono::high_resolution_clock::now();

  for (int i = 0; i < n; ++i) {
    int min_pos = i;
    for (int j = i + 1; j < n; ++j)
      if (a[j] < a[min_pos]) min_pos = j;
    if (min_pos != i) swap(a[min_pos], a[i]);
  }

  auto stop = chrono::high_resolution_clock::now();
  auto duration = chrono::duration_cast<chrono::microseconds>(stop - start);
  cout << "Time taken by selection sort: " << duration.count()
       << " microseconds." << endl;
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

void QuickSort(int a[], const int n) {
  auto start = chrono::high_resolution_clock::now();

  QuickSort(a, a + n - 1);

  auto stop = chrono::high_resolution_clock::now();
  auto duration = chrono::duration_cast<chrono::microseconds>(stop - start);
  cout << "Time taken by quick sort: " << duration.count() << " microseconds."
       << endl;
}

void HeapSort(int a[], const int n) {
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
  cout << "Time taken by heap sort: " << duration.count() << " microseconds."
       << endl;
}

void RadixSort(int a[], const int n) {
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
  cout << "Time taken by radix sort: " << duration.count() << " microseconds."
       << endl;
}

int InputArraySize() {
  int n;
  cout << "Input the size of the array to be sorted(-1 to exit):";
  cout.flush();
  if (!(cin >> n)) n = -1;
  return n;
}

int InputSortAlgorithm() {
  int type;
  for (;;) {
    cout << "Choose the sort algorithm:\n"
         << "1. Insertion Sort.\n"
         << "2. Binary Insertion Sort.\n"
         << "3. Bubble Sort.\n"
         << "4. Quick Sort.\n"
         << "5. Selection Sort.\n"
         << "6. Heap Sort.\n"
         << "7. Radix Sort.\n"
         << "Input:";
    cout.flush();
    if (!(cin >> type)) {
      cout << "Error: Got the EOF character!" << endl;
      exit(1);
    }
    if (type < 1 || type > 7) {
      cout << "Wrong Input!";
      continue;
    }
    return type;
  }
}

int main(int argc, char *argv[]) {
  // Will be used to obtain a seed for the random number engine
  random_device rd;
  std::mt19937 gen(rd());  // Standard mersenne_twister_engine seeded with rd()
  int *array = new int[MAX_ARRAY_SIZE];

  for (;;) {
    int n = InputArraySize();
    if (n > MAX_ARRAY_SIZE) {
      cout << "The size of array is too large!" << endl;
      continue;
    }
    if (n == -1) break;
    if (n < 0) {
      cout << "The size can't be negative!" << endl;
      continue;
    }
    int algor = InputSortAlgorithm();

    // the max number for radix sort is less than 1000
    int gen_max = algor == 7 ? 999 : INT_MAX;

    // Generate the array
    std::uniform_int_distribution<int> dis(0, gen_max);
    for (int i = 0; i < n; ++i) array[i] = dis(gen);

#ifdef DISPLAY
    for (int i = 0; i < n; ++i) cout << array[i];
    cout << endl;
#endif

    switch (algor) {
      case 1:
        InsertionSort(array, n);
        break;
      case 2:
        BinaryInsertionSort(array, n);
        break;
      case 3:
        BubbleSort(array, n);
        break;
      case 4:
        QuickSort(array, n);
        break;
      case 5:
        SelectionSort(array, n);
        break;
      case 6:
        HeapSort(array, n);
        break;
      case 7:
        RadixSort(array, n);
        break;
      default:
        assert(0);
        break;
    }

#ifdef DISPLAY
    for (int i = 0; i < n; ++i) cout << array[i];
    cout << endl;
#endif
  }

  delete[] array;
  return 0;
}
