#include <arpa/inet.h>
#include<stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>

int main(){
	char header[]="GET /index.html HTTP/1.1\r\nHost: 127.0.0.1";
	char request[5];
	//get the first word of the request
	strcpy(request,strtok(header,"/"));
	char filename[30];
	strcpy(filename, strtok(NULL, " "));
	strtok(NULL," ");//go to next line
	char host[30];
	strcpy(host, strtok(NULL,"\r\n"));
	printf("%s\n", request);
	printf("%s\n", filename);
	printf("%s\n", host);
	if(strncmp(request, "GET", 3)==0){
		printf("Handle Request\n");
	}else{
		printf("404 not a GET request!\n");
	}
	


	return 0;

}
