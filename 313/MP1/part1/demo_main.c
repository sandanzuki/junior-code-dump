/*
Jessica Fang
Colin Banigan
*/

#include <ctype.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <getopt.h>
#include "linked_list.h"

int main(int argc, char** argv){
    const int M_DEFAULT = 512000;
	const int B_DEFAULT = 128;
	extern char *optarg;
	extern int optind;
	int debug, c, err = 0;
	int b_flag = 0;
	int s_flag = 0;
	int s_arg, b_arg;
	static char usage[] = "usage: %s [-b <blocksize>] [-s <memsize>]\n";

	while((c = getopt(argc, argv, "b:s:")) != -1)
		switch(c){
			case 'b':
				if(b_flag == 1){
					err = 999;
					break;
				}
				b_flag = 1;
				b_arg = atoi(optarg);
				break;
				
			case 's':
				if(s_flag == 1){
					err = 666;
					break;
				}
				s_flag = 1;
				s_arg = atoi(optarg);
				break;
				
			case '?':
				err = 1;
				break;
		}
	
	if (err > 0) {
		printf(usage, argv[0]);
		exit(1);
	}
	
	printf("b_flag = %d\n", b_flag);
	printf("s_flag = %d\n", s_flag);
	printf("b_arg = %d\n", b_arg);
	printf("s_arg = %d\n", s_arg);

	if(b_flag == 1 && s_flag == 1){
		printf("created a linked list with memory size %d and %d bytes per node.\n", s_arg, b_arg);
		Init(s_arg, b_arg);
	}else if(b_flag == 1){
		printf("created a linked list with memory size %d and %d bytes per node.\n", M_DEFAULT, b_arg);
		Init(M_DEFAULT, b_arg);
	}else if(s_flag == 1){
		printf("created a linked list with memory size %d and %d bytes per node.\n", s_arg, B_DEFAULT);
		Init(s_arg, B_DEFAULT);
	}else{
		printf("created a linked list with memory size %d and %d bytes per node.\n", M_DEFAULT, B_DEFAULT);
		Init(M_DEFAULT,B_DEFAULT);
	}
    
	//int b = 128;

	//int M = b * 12;  // so we have space for 12 items

	// their getopt() code, keep intact

	char buf [1024];

	memset (buf, 1, 1024); // set each byte to 1

	char * msg = "a sample message";

	//Init (M,b); // initialize

	
	// test operations

	int testnums [] = {100, 5, 200, 7, 39, 25, 400, 50, 200, 300};

	int i = 0;

	// some sample insertions

	for (i=0; i< 10; i ++)

	{ 

	Insert (testnums [i], buf, 50);   // insert 50 bytes from the buffer as value for each of the insertions

	}

	PrintList ();

	Insert (150, buf, 200); // this Insert should fail

	Delete (200);

	Delete (7);

	PrintList ();

	Insert (13, msg, strlen(msg)+1); // insertion of strings, copies the null byte at the end

	Delete (100);

	Delete (300);

	PrintList ();

	Insert (500, msg, strlen(msg)+1);

	Insert (600, msg, strlen(msg)+1);

	PrintList ();

	// a sample lookup operations that should return null, because it is looking up a non-existent number

	int _key = 3;

	char* kv = Lookup (_key);

	if (kv) 

	printf ("Key = %d, Value Len = %d\n", *(int *) kv, *(int *) (kv+4));

	else 

	printf ("Key = %d could not be found\n", _key);

	// this look up  should succeed and print the string "a sample message"

	_key = 13;

	kv = Lookup (_key);

	if (kv) 

	printf ("Key = %d, Value Len = %d, Value = %s\n", *(int *) kv, *(int *) (kv+4), kv + 8);

	else 

	printf ("Key = %d could not be found\n", _key);

	// end test operations 

	Destroy ();
}