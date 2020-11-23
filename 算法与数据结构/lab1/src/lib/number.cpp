#include <cmath>
#include <iostream>

#include "number.h"

Number::Number() {
  is_integer_ = true;
  value_.intval = 0;
}
Number::Number(const string &s) {
  // Parse the number from a string
  int length = s.length();
  if (s.find('.') == string::npos) {
    is_integer_ = true;
    INTEGER &x = value_.intval;
    x = 0;
    int i = 0;
    bool negative = false;
    if (s[i] == '-') negative = true, ++i;
    for (; i < length; ++i) x = x * 10 + s[i] - '0';
    if (negative) x = -x;
  } else {
    is_integer_ = false;
    FLOAT &x = value_.floatval;
    x = 0;
    bool negative = false;
    int pos = s.find('.'), i = 0;
    if (s[i] == '-') negative = true, ++i;
    for (; i < pos; ++i) x = x * 10 + s[i] - '0';

    FLOAT p10 = 0.1;
    for (++i; i < length; ++i, p10 *= 0.1) x += (s[i] - '0') * p10;
    if (negative) x = -x;
  }
}
Number::~Number() {}

Number Number::pow(const Number &a) const {
  // calculate the a power of this
  Number c;
  if ((c.is_integer_ = is_integer_ && a.is_integer_)) {
    if (a.value_.intval < 0) {
      c.is_integer_ = false;
      c.value_.floatval = std::pow(value_.intval, a.value_.intval);
      return c;
    }
    int x = value_.intval, y = a.value_.intval, &z = c.value_.intval;
    for (z = 1; y; y >>= 1, x *= x)
      if (y & 1) z *= x;
  } else {
    c.value_.floatval = std::pow(GET_VALUE(*this), GET_VALUE(a));
  }
  return c;
}

Number operator+(const Number &a, const Number &b) {
  Number c;
  if ((c.is_integer_ = a.is_integer_ && b.is_integer_)) {
    c.value_.intval = a.value_.intval + b.value_.intval;
  } else {
    c.value_.floatval = GET_VALUE(a) + GET_VALUE(b);
  }
  return c;
}
Number operator-(const Number &a, const Number &b) {
  Number c;
  if ((c.is_integer_ = a.is_integer_ && b.is_integer_)) {
    c.value_.intval = a.value_.intval - b.value_.intval;
  } else {
    c.value_.floatval = GET_VALUE(a) - GET_VALUE(b);
  }
  return c;
}
Number operator*(const Number &a, const Number &b) {
  Number c;
  if ((c.is_integer_ = a.is_integer_ && b.is_integer_)) {
    c.value_.intval = a.value_.intval * b.value_.intval;
  } else {
    c.value_.floatval = GET_VALUE(a) * GET_VALUE(b);
  }
  return c;
}
Number operator/(const Number &a, const Number &b) {
  Number c;
  if ((c.is_integer_ = a.is_integer_ && b.is_integer_)) {
    c.value_.intval = a.value_.intval / b.value_.intval;
  } else {
    c.value_.floatval = GET_VALUE(a) / GET_VALUE(b);
  }
  return c;
}
Number operator%(const Number &a, const Number &b) {
  Number c;
  if ((c.is_integer_ = a.is_integer_ && b.is_integer_)) {
    c.value_.intval = a.value_.intval % b.value_.intval;
  } else {
    c.value_.floatval = fmod(GET_VALUE(a), GET_VALUE(b));
  }
  return c;
}
std::ostream &operator<<(std::ostream &os, const Number &a) {
  if (a.is_integer_) {
    os << a.value_.intval;
  } else {
    os << a.value_.floatval;
  }
  return os;
}
