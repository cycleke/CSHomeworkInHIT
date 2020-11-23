#include <cassert>
#include <fstream>
#include <iostream>
#include <regex>

#include "bayesian_network.h"

int main() {
  std::ios::sync_with_stdio(false);

  std::ifstream fin("burglarnetwork.txt");
  assert(fin.is_open());
  BayesianNetwork net(fin);
  fin.close();

  fin.open("burglarqueries.txt");
  assert(fin.is_open());

  std::regex split("[P()|,\\s]+");
  std::regex assign("=");

  std::vector<int> vars(net.getCPTSize());
  for (std::string line; std::getline(fin, line);) {
    if (line.empty()) continue;
    std::vector<std::string> tokens(
        std::sregex_token_iterator(line.begin(), line.end(), split, -1),
        std::sregex_token_iterator());
    fill(vars.begin(), vars.end(), 3);
    for (auto &var : tokens) {
      if (var.empty()) continue;
      std::vector<std::string> tokens_(
          std::sregex_token_iterator(var.begin(), var.end(), assign, -1),
          std::sregex_token_iterator());
      if (tokens_.size() == 1) {
        vars[net.getVariableId(var)] = 2;
      } else {
        vars[net.getVariableId(tokens_[0])] = tokens_[1] == "true";
      }
    }

    auto result = net.calculateProbability(vars);
    std::cout << result[1] << " " << result[0] << std::endl;
  }

  return 0;
}
