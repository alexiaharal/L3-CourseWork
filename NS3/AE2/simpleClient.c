#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <stdlib.h> 
#include <string.h>
#define PORT 8000
 
void err(char *s)
{
    perror(s);
    exit(1);
}
 
int main(int argc, char** argv)
{
    int fd, i, slen;
    char buffer[512];
    int buflen = sizeof(buffer);
 	struct sockaddr_in serv_addr;
 	slen=sizeof(serv_addr);
 	
    if(argc != 2)
    {
      printf("Usage : %s <Server-IP>\n",argv[0]);
      exit(0);
    }
 
    if ((fd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP))==-1)
        err("socket");
 
    bzero(&serv_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);
    if (inet_aton(argv[1], &serv_addr.sin_addr)==0)
    {
        fprintf(stderr, "inet_aton() failed\n");
        exit(1);
    }
 
    while(1)
    {
        printf("\nEnter data to send(Type exit and press enter to exit) : ");
        scanf("%[^\n]",buffer);
        getchar();
        if(strcmp(buffer,"exit") == 0)
          exit(0);
 
        if (sendto(fd, buffer, buflen, 0, (struct sockaddr*)&serv_addr, slen)==-1)
            err("sendto()");
    }
 
    close(fd);
    return 0;
}
