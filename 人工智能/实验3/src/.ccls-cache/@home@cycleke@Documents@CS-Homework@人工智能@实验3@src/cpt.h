#include <string>
#include <vector>

struct CPT {
  int id;
  std::vector<int> parents;
  std::vector<std::vector<double>> probabilities;
};
