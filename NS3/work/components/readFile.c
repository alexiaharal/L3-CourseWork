#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main(){
char filename[]="index.html";
char * buffer = 0;
long length;
FILE * f = fopen (filename, "rb");

if (f)
{
  fseek (f, 0, SEEK_END);
  length = ftell (f);
  fseek (f, 0, SEEK_SET);
  buffer = malloc (length);
  if (buffer)
  {
    fread (buffer, 1, length, f);
  }
  fclose (f);
}

char data[1000]="HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nConnection: close\r\n";

if (buffer)
{
	strcat(data,buffer);	
	printf("%s\n",data);
  // start to process your data / extract strings here...
  //
}
free(buffer);

return 0; }
