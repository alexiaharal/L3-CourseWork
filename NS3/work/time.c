#include <time.h>
#include <stdio.h>

int main() {
  time_t t;
  time(&t);
  printf("The current Date and Time is: %s\n", ctime(&t));
return 0;
}
