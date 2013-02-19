#include <sys/types.h>
#include <sys/socket.h>
#include<stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <netdb.h>

int main(int argc, char *argv[]){
    #define BUFLEN 1500
   	if (argc!=2){
		printf("Usage: %s host\n",argv[0]);
		return -1;
	}
    int fd;
    ssize_t i;
    ssize_t rcount;
    char buf[BUFLEN];

	char data[]="DATE\r\n";
	int datalen = strlen(data);
	struct addrinfo hints, *ai, *ai0;	
	

	memset(&hints, 0, sizeof(hints));
	hints.ai_family	= PF_UNSPEC;
	hints.ai_socktype = SOCK_STREAM;
	if ((i = getaddrinfo(argv[1], "8000", &hints, &ai0))!=0){
		printf("Unable to look up IP address\n");
	}

	for (ai=ai0; ai != NULL; ai= ai->ai_next){
		fd=socket(ai->ai_family, ai->ai_socktype, ai->ai_protocol);
   	 if (fd == -1){
    	     perror("Unable to create socket!\n");
		continue;
   	 }

	if (connect(fd, ai->ai_addr, ai->ai_addrlen) == -1){
		perror("Unable to connect to \n");
		close(fd);
		continue;
	}

	}//for

	if (write(fd,data, datalen) == -1){
		printf("Unable to write %s\n", strerror(errno));
		return -1;
    }

   rcount = read(fd, buf, BUFLEN);
    if (rcount == -1) {
	printf("Unable to read\n");
	return -1;
    }
    for (i = 0; i < rcount; i++) { 
        printf("%c", buf[i]);
    }
	close(fd);
	printf("Function ended\n");

    return 0;}





