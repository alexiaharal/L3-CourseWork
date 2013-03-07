#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <stdlib.h> 
#include <string.h>
#include <ctype.h>
#include <time.h>

#define PORT 5010
#define IP "224.0.0.22"
void getDateTime(char *data){
  	time_t now = time(0);
    struct tm tstruct;
    tstruct = *localtime(&now);
    strftime(data, 23, "%d-%m-%Y - %X", &tstruct);
}


int main(void)
{
    struct sockaddr_in inaddr, cli_addr;
    int fd; 
    socklen_t slen=sizeof(cli_addr);
    char buffer[1500];
    int buflen = sizeof(buffer);
    struct ip_mreq imr;
	inet_pton(AF_INET, IP, &(imr.imr_multiaddr.s_addr));
	imr.imr_interface.s_addr = INADDR_ANY;
	
	fd = socket(AF_INET, SOCK_DGRAM, 0);
	bzero(&inaddr, sizeof(inaddr));
	inaddr.sin_family = AF_INET;
	inaddr.sin_port = htons(PORT);
	inaddr.sin_addr.s_addr = htonl(INADDR_ANY);
	
	if (bind(fd, (struct sockaddr* ) &inaddr, sizeof(inaddr))==-1){
		printf("Cannot bind!\n");
		exit(1);
	}
	
	
	
	if (setsockopt(fd, IPPROTO_IP, IP_ADD_MEMBERSHIP,&imr, sizeof(imr)) < 0){
		printf("Cannot join multicast group!");
		close(fd);
		exit(1);
	}
	
	while(1)
		{
			int len;
			
			if ((len=recvfrom(fd, buffer, buflen, 0, (struct sockaddr*)&cli_addr, &slen))==-1){
				printf("Could not read from group!\n");
				break;
			}
			
			buffer[len]='\0';
			char *s=buffer;
			//char date[15];
			char datetime[23];
			//getDate(date);
			getDateTime(datetime);
			printf("%s -", datetime);
			//Ignore the "FROM " part of the message
			while (*s != ' '&& *s!=0){
				s++;
			}
			
			//Read in the username of the sender and print it
			//The username is expected to end at the new line character
			//so we are reading in characters until the first new line character is detected.
			while (*s != '\n' && *s!=0){
				if (isprint(*s)||isspace(*s))
					{
						putchar(*s);
					}else{
					putchar('?');
				}
				s++;
			}printf(" - ");
			s++;//Ignore '\n'
			
			//Read in the rest of the message and print it out.
			while (*s != 0) {						
				if (isprint(*s)||isspace(*s))
					{
						putchar(*s);
					}else{
					putchar('?');
				}
				s++;
			}	
		}
	close(fd);
	return 0;
}
