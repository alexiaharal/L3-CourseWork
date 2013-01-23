#include <arpa/inet.h>
#include<stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main(int argc, char *argv[])
    {
    #define BUFLEN 1500
    int fd;
    ssize_t i;
    ssize_t rcount;
    char buf[BUFLEN];

    printf("Server is now running on port 8000\n");
    fd = socket (AF_INET,SOCK_STREAM,0);
    if (fd == -1){
         printf("Cannot create socket! %s\n", strerror(errno));
    }

    struct sockaddr_in addr;

    addr.sin_addr.s_addr = INADDR_ANY;
    addr.sin_family = AF_INET;
    addr.sin_port = htons(8000);

    if (bind(fd, (struct sockaddr *)&addr, sizeof(addr)) == -1) {
        printf("cannot bind socket");
    }

    if (listen(fd, 20) == -1) {
       printf("Unable to listen");
    }

    int connfd; 
    struct sockaddr_in cliaddr; 
    socklen_t   cliaddrlen = sizeof(cliaddr);


    connfd = accept(fd, (struct sockaddr *) &cliaddr, &cliaddrlen);
    if (connfd == -1) {
        printf("unable to accept");
    }




    rcount = read(connfd, buf, BUFLEN);
    if (rcount == -1) {
	printf("Error has occurred");
	return -1;
    }
    for (i = 0; i < rcount; i++) { 
        printf("%c", buf[i]);
    }
	close(connfd);
	printf("Function ended");


    return 0;}
