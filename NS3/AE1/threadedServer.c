#include <arpa/inet.h>
#include<stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>
#include <pthread.h>
void *acceptConnection(void *ptr);
void getContentType(char*, char[4]);
void getResponseHead(char*, char[], char[], int);
void getContentType(char *contentType, char filetype[]){
  if (strcmp (filetype, "html") == 0){
    strcpy(contentType, "text/html");
  }else if (strcmp(filetype, "htm")==0){
    strcpy(contentType, "text/html");
  }else if (strcmp(filetype, "gif")==0){
    strcpy(contentType, "image/gif");
  }else if (strcmp(filetype, "jpg")==0){
    strcpy(contentType, "image/jpeg");
  }else if (strcmp(filetype, "jpeg")==0){
    strcpy(contentType, "image/jpeg");
  }else if (strcmp(filetype, "txt")==0){
    strcpy(contentType, "text/plain");
  }else if (strcmp(filetype, "gif")==0){
    strcpy(contentType, "image/gif");
  }else{
    strcpy(contentType, "application/octet-stream");
  }
}


void getResponseHead(char *head, char responseStatus[],char type[], int length){
  sprintf(head,"%s\r\nContent-Type: %s\r\nContent-Length: %d\r\nConnection: close\r\n\r\n",responseStatus, type, length);
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
  
  if (listen(fd, 50) == -1) {
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
#define BUFLEN 30000
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
	char filetype[4];
    strcpy (filename, strtok (NULL, "."));
	strcpy(filetype, strtok(NULL, " "));
	sprintf(filename, "%s.%s",filename,filetype); 
   strtok (NULL, " ");		//go to next line
    char host[30];
    char *data=0;
    data=malloc(BUFLEN);
    strcpy (host, strtok (NULL, "\r\n"));
 
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
	
	char *contentType;
	char *responseHead;
	contentType=malloc(25);
	responseHead=malloc(300);
	getContentType(contentType, filetype);
	getResponseHead(responseHead,"HTTP/1.1 200 OK",contentType, length);
	free(contentType);	
	
	//	printf ("Size of page is %d\n",strlen(page));
	if (buffer){
	  strcat(data,responseHead);
	  strcat (data, buffer);
	  free (buffer); 
		free(responseHead);
	}
      }else{
	//File does NOT exist
	char *buffer = 0;
	int length;
	FILE *f = fopen ("404.html", "rb");
	
	if (f){
	  fseek (f, 0, SEEK_END);
	  length = ftell (f);
	  fseek (f, 0, SEEK_SET);
	  buffer = malloc (length);
	  if (buffer){
	    int reads=fread (buffer, 1, length, f);
		printf("size read is %d\n",reads);	  
	}
	  fclose (f);
	}
	char responseHead[100];
	sprintf(responseHead,"HTTP/1.1 404 Not Found\r\nContent-Type: text/h\
tml\r\nContent-Length: %d\r\nConnection: close\r\n\r\n",length);
	
	       
	if (buffer){
	  strcat(data,responseHead);
	  strcat (data, buffer);
	  free (buffer);
	}
      }
    }else{
      	char *buffer = 0;
	int length;
	FILE *f = fopen ("400.html", "rb");
	
	if (f){
	  fseek (f, 0, SEEK_END);
	  length = ftell (f);
	  fseek (f, 0, SEEK_SET);
	  buffer = malloc (length);
	  if (buffer){
	    fread (buffer, 1, length, f);
	  }
	  buffer[length]='\0';
	  fclose (f);
	}
	char responseHead[100];
	sprintf(responseHead,"HTTP/1.1 400 Bad Request\r\nContent-Type: text/h\
tml\r\nContent-Length: %d\r\nConnection: close\r\n\r\n",length);
	
	
	if (buffer){
	  strcat(data,responseHead);
	  strcat (data, buffer);
	  free (buffer);
	}
    }
    
    int datalen=strlen(data) ;
	data[datalen]='\0';
    
    if (write(connfd,data, datalen) == -1){
      printf("Unable to write %s\n", strerror(errno));
      printf("\n");
      
    }
    printf("finished writing\n"); 
    free(data);
  }
  
  close(connfd);
  printf("Connection Closed\n");
  
  
  
  return 0;
}

  
