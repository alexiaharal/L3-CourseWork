#include<stdio.h>
#include<string.h>
#include <ctype.h>
 
/*Functions Prototypes */
char* check_str(char *);
 
 
/*The main program*/
 
int main()
{
  char string1[40]="strin1";
  char answer[40]="test";        
  strcpy(answer,check_str(string1));
  printf("answer = %s",answer);
  return 0;
}  /* ERRORS OUT HERE*/
 
 
/*Function To Concatonate String Passed*/
char *check_str(char *string2)
{
  char string3[]="abc";
  /*Concatenate string3 to string2 and return string2. */
 
  strcpy(string2,string3);
  return string2;
}
