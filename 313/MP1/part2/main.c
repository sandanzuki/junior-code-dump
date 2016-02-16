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
#include "linked_list2.h"

using namespace std;

//TO GET testlist or testlist2
//when compiling the files with g++, tack a -o _________ at the end with the name of the a.out desired

int main(int argc, char** argv) 
{
	const int M_DEFAULT = 512000;
	const int B_DEFAULT = 128;
	const int T_DEFAULT = 4;
	extern char *optarg;
	extern int optind;
	int debug, c, err = 0;
	int b_flag = 0;
	int s_flag = 0;
	int t_flag = 0;
	int s_arg, b_arg, t_arg;
	static char usage[] = "usage: %s [-b <blocksize>] [-s <memsize>] [-t <tiers>]\n";

	while((c = getopt(argc, argv, "b:s:t:")) != -1)
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
				
			case 't':
				if(t_flag == 1){
					err = 444;
					break;
				}
				t_flag = 1;
				t_arg = atoi(optarg);
				break;
				
			case '?':
				err = 1;
				break;
		}
	
	if (err > 0) {
		printf(usage, argv[0]);
		exit(1);
	}

	if(b_flag == 1 && s_flag == 1 && t_flag == 1){
		printf("created a linked list with memory size %d, %d bytes per node, and %d tiers.\n", s_arg, b_arg, t_arg);
		Init(s_arg, b_arg, t_arg);
	}else if(b_flag == 1 && s_flag == 1){
		printf("created a linked list with memory size %d, %d bytes per node, and %d tiers.\n", s_arg, b_arg, T_DEFAULT);
		Init(s_arg, b_arg, T_DEFAULT);
	}else if(b_flag == 1 && t_flag == 1){
		printf("created a linked list with memory size %d, %d bytes per node, and %d tiers.\n", M_DEFAULT, b_arg, t_arg);
		Init(M_DEFAULT, b_arg, t_arg);
	}else if(s_flag == 1 && t_flag == 1){
		printf("created a linked list with memory size %d, %d bytes per node, and %d tiers.\n", s_arg, B_DEFAULT, t_arg);
		Init(s_arg, B_DEFAULT, t_arg);
	}else if(b_flag == 1){
		printf("created a linked list with memory size %d, %d bytes per node, and %d tiers.\n", M_DEFAULT, b_arg, T_DEFAULT);
		Init(M_DEFAULT, b_arg, T_DEFAULT);
	}else if(s_flag == 1){
		printf("created a linked list with memory size %d, %d bytes per node, and %d tiers.\n", s_arg, B_DEFAULT, T_DEFAULT);
		Init(s_arg, B_DEFAULT, T_DEFAULT);
	}else if(t_flag == 1){
		printf("created a linked list with memory size %d, %d bytes per node, and %d tiers.\n", M_DEFAULT, B_DEFAULT, t_arg);
		Init(M_DEFAULT, B_DEFAULT, t_arg);
	}else{
		printf("created a linked list with memory size %d, %d bytes per node, and %d tiers.\n", M_DEFAULT, B_DEFAULT, T_DEFAULT);
		Init(M_DEFAULT,B_DEFAULT, T_DEFAULT);
	}
	
	//int b = 128;
	//int M = b * 16;  	// so we have space for 16 items in the whole list
	//int t = 4;		// 4 tiers and 4 items per tier
	
	//DEFAULT VALUES:
	//M = 512000 aka 512kb
	//b = 128
	//t = 4
	
	char buf [1024];
	memset (buf, 1, 1024);     // set each byte to 1
	
	char * msg = "a sample message";
	
	//Init (M, b, t); // initialize
	// test operations
	int testnums [] = {0, 1<<29 , (1<<29) + 5 , 50, (1<<30) + 5, (1<<30) - 500};
	int i = 0;
	// some sample insertions
	for (i=0; i< 6; i ++){
		//cout << testnums[i] << " " << endl;
		Insert (testnums [i], buf, 50);   // insert 50 bytes from the buffer as value for each of the insertions
	}
	PrintList ();
	// end test operations	
	Destroy ();
}