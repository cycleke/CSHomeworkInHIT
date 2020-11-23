long switch_prob(long x, long n) {
  long result = x;
  switch (n) {
  case 60:
  case 62:
    result = x * 8;
    break;
  case 63:
    result = x / 8;
    break;
  case 64:
    result = x * 15;
    x = result;
  case 65:
    x *= x;
  default:
    result = x + 75;
  }
  return result;
}
