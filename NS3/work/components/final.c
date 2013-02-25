#include <arpa/inet.h>
#include<stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>

int
main ()
{
	char header[] = "GET /index.html HTTP/1.1\r\nHost: 127.0.0.1";
	char request[5];
	char data[1000];
	//get the first word of the request
	strcpy (request, strtok (header, "/"));
	char filename[30];
	strcpy (filename, strtok (NULL, " "));
	strtok (NULL, " ");		//go to next line
	char host[30];
	strcpy (host, strtok (NULL, "\r\n"));
	printf ("%s\n", request);
	printf ("%s\n", filename);
	printf ("%s\n", host);
	if (strncmp (request, "GET", 3) == 0)
		{
			int fileExists = access (filename, R_OK);
			if (fileExists == 0)
				{
					//File Exists
					char *buffer = 0;
					long length;
					FILE *f = fopen (filename, "rb");

					if (f)
						{
							fseek (f, 0, SEEK_END);
							length = ftell (f);
							fseek (f, 0, SEEK_SET);
							buffer = malloc (length);
							if (buffer)
								{
									fread (buffer, 1, length, f);
								}
							fclose (f);
						}


					strcat(data,"HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nConnection: close\r\n\r\n");

					if (buffer)
						{
							strcat (data, buffer);
							free (buffer);

						}
					else
						{
							printf ("File does not exist! Send 404!");
						}
				}
			else
				{
					printf ("404 not a GET request!\n");
				}


		}
	printf("Data to be send is: %s\n",data);
	return 0;

}
