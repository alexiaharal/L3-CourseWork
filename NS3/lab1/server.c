#include <arpa/inet.h>
#include<stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main()
{
    #define BUFLEN 1500
  int fd;
  ssize_t i;
  ssize_t rcount;
  char buf[BUFLEN];

    
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
  char data[]="Data received successfully!\n";
  int datalen=strlen(data);

  while(1){
    connfd = accept(fd, (struct sockaddr *) &cliaddr, &cliaddrlen);
    if (connfd == -1) {
      printf("unable to accept\n");
    }

    rcount = read(connfd, buf, BUFLEN);
    if (rcount == -1) {
      printf("Error has occurred\n");
      break;
    }
    for (i = 0; i < rcount; i++) {
      // if(buf[i]=='\0'){
      //     printf("ITS NULL\n");
      // } 
      printf("%c", buf[i]);
                
    }
    printf("\n");
    if (write(connfd,data, datalen) == -1){
      printf("Unable to write %s\n", strerror(errno));
      printf("\n");
      //return -1;
      break;
    }
    close(connfd);
    printf("Connection Closed\n");

  }


  return 0;}
