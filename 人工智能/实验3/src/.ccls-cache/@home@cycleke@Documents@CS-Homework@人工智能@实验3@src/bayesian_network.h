#include <cassert>
#include <iostream>
#include <istream>
#include <map>
#include <vector>

#include "cpt.h"

class BayesianNetwork {
public:
  BayesianNetwork(std::istream &in) {
    cpts.clear(), var2id.clear();

    in >> n;
    cpts.resize(n);
    for (int i = 0; i < n; ++i) {
      std::string var;
      in >> var;
      var2id[var] = i;
    }

    for (int i = 0; i < n; ++i) {
      for (int j = 0, temp; j < n; ++j) {
        in >> temp;
        if (temp) cpts[j].parents.push_back(i);
      }
    }

    for (int i = 0; i < n; ++i) {
      int size = 1 << cpts[i].parents.size();
      auto &array = cpts[i].probabilities;
      array.resize(size);
      for (int j = 0; j < size; ++j) {
        array[j].resize(2);
        in >> array[j][1] >> array[j][0];
      }
    }

    calculateTotalProbability();
  }

  void calculateTotalProbability() {
    int bound = 1 << n;
    total_probability.resize(bound, 1);
    for (int s = 0; s < bound; ++s) {
      double &poss = total_probability[s];
      for (int i = 0; i < n; ++i) {
        int index = 0;
        static auto get_bit = [&](int x) { return s >> (n - 1 - x) & 1; };

        for (int x : cpts[i].parents) index = index << 1 | get_bit(x);
        poss *= cpts[i].probabilities[index][get_bit(i)];
      }
    }
  }

  std::vector<double> calculateProbability(const std::vector<int> &vars) const {
    // var : 0: 假, 1: 真, 2: 求解, 3: 待消去
    assert(static_cast<int>(vars.size()) == n);

    int to_solve = 0, to_eliminate = 0;
    for (int x : vars) {
      to_solve += x == 2;
      to_eliminate += x == 3;
    }
    assert(to_solve == 1);

    int solve_case = 1 << to_solve;
    int eliminate_case = 1 << to_eliminate;

    std::vector<double> probability(solve_case, 0);
    for (int solve = 0; solve < solve_case; ++solve)
      for (int eliminate = 0; eliminate < eliminate_case; ++eliminate) {
        auto values = vars;
        int c_solve = to_solve, c_eliminate = to_eliminate, index = 0;
        for (int &x : values) {
          if (x == 2) x = solve >> (--c_solve) & 1;
          if (x == 3) x = eliminate >> (--c_eliminate) & 1;
          index = index << 1 | x;
        }
        probability[solve] += total_probability[index];
      }

    double sum = 0;
    for (auto &x : probability) sum += x;
    for (auto &x : probability) x /= sum;
    return probability;
  }

  int getVariableId(const std::string &var) const {
    auto it = var2id.find(var);
    return it == var2id.end() ? -1 : it->second;
  }

  int getCPTSize() const { return n; }

private:
  int n;
  std::vector<CPT> cpts;
  std::map<std::string, int> var2id;
  std::vector<double> total_probability;
};
