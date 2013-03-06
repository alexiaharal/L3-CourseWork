#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <stdlib.h> 
#include <string.h>
#define PORT 5010
#define IP "224.0.0.22"

int main(int argc, char** argv)
{
    int fd, slen;
    char buffer[512];
    int buflen = sizeof(buffer);
 	struct sockaddr_in serv_addr;
 	slen=sizeof(serv_addr);
 	
    if(argc < 2)
		{
			printf("Usage : %s <message>\n",argv[0]);
			exit(0);
		}
	
    if ((fd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP))==-1){
        printf("Cannot create socket!\n");
		exit(1);
	}

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);
    if (inet_aton(IP, &serv_addr.sin_addr)==0)
		{
			fprintf(stderr, "inet_aton() failed\n");
			exit(1);
		}
	
	
	char message[1000];//maximum number of characters for each message
	char *user;
	user=getlogin();
	sprintf(message, "FROM %s\n%s\n",user,argv[1]);
	
	if (sendto(fd, message, buflen, 0, (struct sockaddr*)&serv_addr, slen)==-1){
		printf("Error in sending message to multicast network!\n");
	}
	
    close(fd);
    return 0;
}
