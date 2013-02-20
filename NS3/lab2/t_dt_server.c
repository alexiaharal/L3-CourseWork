#include <arpa/inet.h>
#include<stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>
#include <pthread.h>
void *acceptConnection(void *ptr);
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

int main(){
  int fd;

  
  printf("creating socket\n");
  fd = socket (AF_INET,SOCK_STREAM,0);
  if (fd == -1){
    printf("Cannot create socket! %s\n", strerror(errno));
  }
  printf("socket created\n");
  struct sockaddr_in addr;
  
  addr.sin_addr.s_addr = INADDR_ANY;
  addr.sin_family = AF_INET;
  addr.sin_port = htons(8000);
  printf("now binding\n");
  if (bind(fd, (struct sockaddr *)&addr, sizeof(addr)) == -1) {
    printf("cannot bind socket\n");
  }

  if (listen(fd, 20) == -1) {
    printf("Unable to listen\n");
  }
  printf("Server is now running on port 8000\n"); 
  struct sockaddr_in cliaddr; 
  socklen_t   cliaddrlen = sizeof(cliaddr);
  printf("now entering while loop\n");
  while(1){
    
    printf("i have said connfd to 0\n");
    int connfd=accept(fd, (struct sockaddr *) &cliaddr, &cliaddrlen);

    printf("this the value of connfd %d \n",connfd);
    printf("connfd location of connfd%d \n", &connfd);
    if (connfd == -1) {
      printf("unable to accept\n");
    }
    pthread_t thread1;
    int  thr1;
    printf("now here\n");
    thr1 = pthread_create( &thread1, NULL, acceptConnection, (void*) &connfd);
    pthread_join( thread1, NULL);
    printf("Thread 1 returns: %d\n",thr1);

    return 0;
  }
}
void *acceptConnection(void *ptr){
#define BUFLEN 1500
  ssize_t rcount;
  ssize_t i;
  char buf[BUFLEN];
  printf("now in thread \n");
  int connfd;
  int *connpt=(int *) ptr;
  connfd=(int) *connpt;
  while(1){
    rcount = read(connfd, buf, BUFLEN);
    if (rcount == -1) {
      printf("Connection was closed by client.\n");
      break;
    }
    for (i = 0; i < rcount; i++) {
      if(buf[i]=='\0'){
	printf("ITS NULL\n");
      } 
      printf("%c", buf[i]);
      
    }
    char data[15]="Null";
    if ((strncmp(buf, "DATE\r\n", strlen(buf)))==0){
      getDate(data);
    }
    else if(strncmp(buf, "TIME\r\n", strlen(buf))==0){
      getTime(data);
    }else{
      strcpy(data,"Invalid command\r\n");
    }
    
    int datalen=strlen(data);
    if (write(connfd,data, datalen) == -1){
      printf("Unable to write %s\n", strerror(errno));
      printf("\n");
      break;
    }}
  close(connfd);
  printf("Connection Closed\n");
}
