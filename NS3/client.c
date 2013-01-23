
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
    int fd;
    ssize_t i;
    ssize_t rcount;
    char buf[BUFLEN];

	


	struct addrinfo {
		int ai_flags;
		int ai_family;
		int ai_socktype;
		int ai_protocol;
		socklen_t ai_addrlen;
		struct sockaddr *ai_addr;
		char *ai_canonname;
		struct addrinfo *ai_next;
	};

	char data[]="hello from client!";
	int datalen = strlen(data);
	struct addrinfo hints, *ai, *ai0;	
	

	memset(&hints, 0, sizeof(hints));
	hints.ai_family	= PF_UNSPEC;
	hints.ai_socktype = SOCK_STREAM;
	if ((i = getaddrinfo("localhost", "8000", &hints, &ai0))!=0){
		printf("UNable to look up IP address\n");
	}

	for (ai=ai0; ai != NULL; ai= ai->ai_next){
		fd=socket(ai->ai_family, ai->ai_socktype, ai->ai_protocol);
   	 if (fd == -1){
    	     perror("Unable to create socket!");
		continue;
   	 }

	if (connect(fd, ai->ai_addr, ai->ai_addrlen) == -1){
		perror("Unable to connect");
		close(fd);
		continue;
	}

}
	if (write(fd,data, datalen) == -1){
		printf("Unable to write %s\n", strerror(errno));
    }

   rcount = read(fd, buf, BUFLEN);
    if (rcount == -1) {
	printf("Error has occurred");
	return -1;
    }
    for (i = 0; i < rcount; i++) { 
        printf("%c", buf[i]);
    }
	close(fd);
	printf("Function ended");

    return 0;}





