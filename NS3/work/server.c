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
			rcount = read(connfd, buf, BUFLEN);
			buf[rcount] = '\0';
			if (rcount == 0) {
				printf("Connection was closed by client.\n");
				break;
			}
			if (rcount < 0) {
				perror("Unable to read");
				exit(1);
			}
			
			for (i = 0; i < rcount; i++) {
				if(buf[i]=='\0'){
					printf("ITS NULL\n");
				} 
				printf("%c", buf[i]);
                
			}
			printf("\n");
			
			char data[1000];
			char * pch;
			printf("Splitting string\"%s\" into tokens\n",buf);
			pch = strtok(buf, " ");
			if (strncmp(pch, "GET", 3)==0){
				printf("GET command detected\n");
			}
			pch = strtok(NULL, " ");
			char filename[] = pch; 
			if (FILE * file = fopen(filename, "r"))
				{
        			fclose(file);
        			return true;
    			}
			else{
				FILE * file = fopen("./404.html","r")
					while(1){
						fgets(temp, 1000, file);
						if(feof(file)){
							printf("Reached end of file!\n");
							break;
						}
						strncat(data,temp, sizeof(data)+sizeof(temp));
						else if(strncmp(buf, "TIME\r\n", 6)==0){
							getTime(data);
						}else{
							strcpy(data,"HTTP/1.1 404 Not Found\nContent-Type: text/html\n Connection: close\r\n");
						}
						int datalen=strlen(data);
						if (write(connfd,data, datalen) == -1){
							perror("unable to write");
							printf("\n");
							
						}
					}
				close(connfd);
				printf("Connection Closed\n");
				
			}
			
			
			return 0;
		}
		
