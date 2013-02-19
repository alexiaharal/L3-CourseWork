#include<stdio.h>

void swapping(int *ptr_c, int *ptr_d) {
  char tmp[];

  tmp = *ptr_c;
  *ptr_c = *ptr_d;
  *ptr_d = tmp;
  printf("In function: %d %d\n", *ptr_c , *ptr_d);
}

int main(void) {
  char date[100];
  printf("input: %d %d\n", a, b);
  swapping(&a,&b);
  printf("output: %d %d\n", a, b);
  return 0;
}
