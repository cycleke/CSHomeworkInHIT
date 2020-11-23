#include <iostream>
using namespace std;

#define INTEGER int
#define FLOAT float
#define GET_VALUE(a) ((a).is_integer_ ? (a).value_.intval : (a).value_.floatval)

class Number {
protected:
  bool is_integer_;
  union {
    INTEGER intval;
    FLOAT floatval;
  } value_;

public:
  Number();
  Number(const string &);
  ~Number();

  Number pow(const Number &) const;
  friend Number operator+(const Number&, const Number&);
  friend Number operator-(const Number &, const Number &);
  friend Number operator*(const Number &, const Number &);
  friend Number operator/(const Number &, const Number &);
  friend Number operator%(const Number &, const Number &);
  friend std::ostream &operator<<(std::ostream &, const Number &);
};
