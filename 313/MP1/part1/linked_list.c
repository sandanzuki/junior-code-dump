/*
Jessica Fang 
Colin Banigan
*/

#include <stdio.h>
#include <stdlib.h>
#include <cstring>
#include <iostream>
#include "linked_list.h"

using namespace std;

int bytes_per;
int bytes_remaining;
struct node* head;
void* memory;
char* freeptr;

void Init(int M, int b) {   //given both arguments
    memory = malloc(M); //allocate M bytes of memory
    freeptr = (char*)memory; //who memory is currently free
    head = (node*) freeptr; //head points to front
    bytes_per = b; //number of bytes per node
    bytes_remaining = M; //number of bytes remaining in memory
}

void Destroy() {
    free(memory); 
}

int Insert(int key, char* value_ptr, int value_len) {
    if(bytes_per > bytes_remaining){
        cerr << "not enough memory\n";
        return -666;
    }else if(value_len > bytes_per - (8 + 4 + 4)){ //lol magic numbers
        cerr << "value too large\n";
        return -69;
    }else{
        node insert;
        insert.key = key;
        //insert.data = *value_ptr;
        insert.value_length = value_len;
        insert.next = NULL;
        struct node* tmp = (node*) freeptr;
        *tmp = insert;
        
        freeptr += sizeof(node);
        memcpy(freeptr, value_ptr, value_len);
        
        
        //tmp[0] = insert;
        //memcpy(freeptr, &insert, bytes_per);
        freeptr += bytes_per - sizeof(node); // move the free pointer back
        tmp->next = (node*) freeptr;
        bytes_remaining -= bytes_per;
        return 0;
    }
}

int Delete(int key) {
    if(Lookup(key) == NULL){
        cerr << key <<" doesn't exist\n";
        return -350;
    }else{
        struct node* iter = head;
        if(iter->key == key){
            iter->key = -1;
            head = iter->next;
            iter->next = NULL;
            //freeptr -= bytes_per; // move free ptr forward
            //bytes_remaining += bytes_per;
            return 0;
        }
        while(iter->next != NULL){
            if(iter->next->key == key){
                iter->next->key = -1;
                iter->next = iter->next->next;
                //freeptr -= bytes_per; // move free ptr forward
                //bytes_remaining += bytes_per;
                return 0;
            }
            iter = iter->next;
        }
    }
}

char* Lookup(int key) {
    struct node* tmp = head;
    while(tmp != NULL){
        if(tmp->key == key) return (char*)&(tmp->key);
        tmp = tmp->next;
    }
    return NULL;
}

void PrintList() {
    struct node* tmp = head;
    while(tmp != NULL && tmp->key != -1 && tmp->value_length > 0){
        cout << "(" << tmp->key << ", " << tmp->value_length << ")";
        tmp = tmp->next;
    }
    cout << endl;
}