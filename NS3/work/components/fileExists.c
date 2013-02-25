#include<sys/stat.h>
#include<stdio.h>
#include <unistd.h>

int main(){

char filename[]="index.html";
int result=access(filename, R_OK);
if(result==0){
	printf("File exists! Handle File!");
}else{
	printf("File does not exist! Send 404!");
}
printf("\n");

return 0;
}
