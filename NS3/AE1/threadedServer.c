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
  struct sockaddr_in cliaddr; 
  socklen_t   cliaddrlen = sizeof(cliaddr);

  while(1){
    int connfd=accept(fd, (struct sockaddr *) &cliaddr, &cliaddrlen);
    
    if (connfd == -1) {
      printf("unable to accept\n");
    }

    pthread_t thread1;
    int  thr1;
    thr1 = pthread_create( &thread1, NULL, acceptConnection, (void*) &connfd);
    pthread_join( thread1, NULL);
    printf("Thread 1 returns: %d\n",thr1);
  }
  return 0;
}

void *acceptConnection(void *ptr){
#define BUFLEN 1500
  ssize_t rcount;
  ssize_t i;
  char buf[BUFLEN];
  int connfd;
  int *connpt=(int *) ptr;
  connfd=(int) *connpt;
  while(1){
    rcount = read(connfd, buf, BUFLEN);
    if (rcount == 0) {
      printf("Connection was closed by client.\n");
      break;
    }
    if (rcount < 0) {
      perror("Unable to read");
      exit(1);
    }
    
    if(buf){ 
      printf("%s", buf);
    }
    char request[5];
    //get the first word of the request
    strcpy (request, strtok (buf, "/"));
    char filename[30];
    strcpy (filename, strtok (NULL, " "));
    strtok (NULL, " ");		//go to next line
    char host[30];
   char data[1000];
	 strcpy (host, strtok (NULL, "\r\n"));
    printf ("Request: %s\n", request);
    printf ("Filename: %s\n", filename);
    printf ("Host: %s\n", host);
    if (strncmp (request, "GET", 3) == 0){
      int fileExists = access (filename, R_OK);
      if (fileExists == 0){
	//File Exists
	char *buffer = 0;
	int length;
	FILE *f = fopen (filename, "rb");
	
	if (f){
	  fseek (f, 0, SEEK_END);
	  length = ftell (f);
	  fseek (f, 0, SEEK_SET);
	  buffer = malloc (length);
	  if (buffer){
	    fread (buffer, 1, length, f);
	  }
	  fclose (f);
	}
	
	
	char responseHead[]="HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nConnection: close\r\n\r\n";


//	printf ("Size of page is %d\n",strlen(page));
	if (buffer){
	  strcat(data,responseHead);
	  strcat (data, buffer);
	  free (buffer); 
	}else{
	  printf ("File does not exist! Send 404!");
	}
      }
    }else{
      printf ("404 not a GET request!\n");
    }      
    int datalen=strlen(data) ;

    if (write(connfd,data, datalen) == -1){
      printf("Unable to write %s\n", strerror(errno));
      printf("\n");
      
    }
  }    
  close(connfd);
  printf("Connection Closed\n");
  
  
  
  return 0;
}

