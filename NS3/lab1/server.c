#include <arpa/inet.h>
#include<stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main()
    {
    #define BUFLEN 80000
    int fd;
    ssize_t i;
    ssize_t rcount;
    char buf[BUFLEN];
	FILE *file;
	char temp[1000]="";
    
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
	file=fopen("./Leafos.jpg","r");
	fseek(file, 0, SEEK_END); // seek to end of file
	int size = ftell(file); // get current file pointer
	printf("SIZE IS: %d\n",size);
	fseek(file, 0, SEEK_SET);
	char data[6000];
	char temps[]="HTTP/1.1 2000 OK\nDate: Tue, 20 Jan 2009 10:31:56 GMT\nServer: Apache/2.0.46 (Scientific Linux)\nContent-Length:";
	sprintf(data,"%s%d",temps,size);
strncat(data,"\nConnection: close\nContent-Type: image/jpg\r\n",sizeof(data));

        
	//for loop to read in all three lines of an entry.
	while (1){
		fgets(temp,1000,file);//read next line of file into temp.
		if(feof(file)){//if file is and EOF
			printf("Reached end of file!\n");
			break;
		}
		strncat(data,temp,sizeof(data));//Input will hold all three lines of an entry.

	}
	strncat(data,"\n",sizeof(data));//Input will hold all three lines of an entry.
	fclose(file);
   int datalen=strlen(data);
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
