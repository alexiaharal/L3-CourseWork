#include <arpa/inet.h>
#include<stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>
//author: 1000007

void getDate(char *data){
	time_t now;
	struct tm *datedata;
	char date[15];
	time(&now);
	datedata=localtime(&now);
	strftime(date, 15, "%d/%m/%Y\r\n", datedata);
	strcpy(data,date);
}

void getTime(char *data){
	time_t current_time;
	struct tm * timedata;
	char timeString[6];
	
	time(&current_time);
	timedata = localtime(&current_time);
	strftime(timeString, 8, "%H:%M\r\n", timedata);
	strcpy(data,timeString);
	
}

int main()
{
#define BUFLEN 1500
	int fd;
	ssize_t i;
	ssize_t rcount;
	char buf[BUFLEN+1];
	
	
	fd = socket (AF_INET,SOCK_STREAM,0);
	if (fd == -1){
		printf("Cannot create socket! %s\n", strerror(errno));
	}
	
	struct sockaddr_in addr;
	
	addr.sin_addr.s_addr = INADDR_ANY;
	addr.sin_family = AF_INET;
	addr.sin_port = htons(8000);
	
	if (bind(fd, (struct sockaddr *)&addr, sizeof(addr)) == -1) {
		printf("cannot bind socket\n");
	}
	
	if (listen(fd, 20) == -1) {
		printf("Unable to listen\n");
	}
	printf("Server is now running on port 8000\n");
	int connfd; 
	struct sockaddr_in cliaddr; 
	socklen_t   cliaddrlen = sizeof(cliaddr);
	
	while(1){
		printf("Waiting for a new connection...\n");
		connfd = accept(fd, (struct sockaddr *) &cliaddr, &cliaddrlen);
		if (connfd == -1) {
			printf("unable to accept\n");
		}
		
		while(1){
			printf("read\n");
			rcount = read(connfd, buf, BUFLEN);
			buf[rcount] = '\0';
			printf("if to rcount\n");
			if (rcount == 0) {
				printf("Connection was closed by client.\n");
				break;
			}
			if (rcount < 0) {
				perror("Unable to read");
				exit(1);
			}
			printf("for loop\n");
			for (i = 0; i < rcount; i++) {
				if(buf[i]=='\0'){
					printf("ITS NULL\n");
				} 
				printf("%c", buf[i]);
                
			}
			printf("\n");
			printf("comparing\n");
			char data[30];
			if (strncmp(buf, "DATE\r\n", 6)==0){
				printf("getting data\n");
				getDate(data);
			}
			else if(strncmp(buf, "TIME\r\n", 6)==0){
				printf("getting time \n");
				getTime(data);
			}else{
				printf("printing invalid copmmand\n");
				strcpy(data,"Invalid command\r\n");
			}
			printf("write\n");
			int datalen=strlen(data);
			printf("datalen set\n");
			printf("data=[%s]\n",data);
			printf("datalen=%d\n",datalen);
			if (write(connfd,data, datalen) == -1){
				perror("unable to write");
				printf("\n");
				
			}
			printf("after write\n");
		}
		close(connfd);
		printf("Connection Closed\n");
		
	}
	

	return 0;
}
