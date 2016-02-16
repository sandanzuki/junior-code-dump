/*
//EXTRA CREDIT, REMOVE ALL EMPTY SPACE. we tried but it doesn't really work..
#include <stdio.h>
#include <stdlib.h>
#include <cstring>
#include <iostream>

using namespace std;

int bytes_per;
int bytes_remaining;
struct node* head;
void* memory;
void* freeptr;

void Init(int M, int b) {
    memory = malloc(M); //allocate M bytes of memory
    freeptr = memory; //who memory is currently free
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
    }else if(Lookup(-1) != NULL){
        node insert;
        insert.key = key;
        insert.data = value_ptr;
        insert.value_length = value_len;
        insert.next = (node*) freeptr;
        
        node* deleted;
        node* iter = head;
        node* iter_prev = head;
        if(iter->key == -1 && iter->next == NULL){ //deleted only item in list
            deleted->key = key;
            deleted->data = value_ptr;
            deleted->value_length = value_len;
            deleted->next = NULL;
            //memcpy(deleted, &insert, bytes_per); 
        }
        else if(iter->key == -1){ //first item in list was deleted
            deleted = head;
            head = iter->next;
            while(iter->next != NULL){
                iter = iter->next;
                cout << "fuck " << iter->key << endl;
            }
            //memcpy(deleted, &insert, bytes_per);
            iter->next = deleted;
            deleted->key = key;
            deleted->data = value_ptr;
            deleted->value_length = value_len;
            deleted->next = NULL;
        }else{ //there exists an item that was deleted
            while(iter != NULL){
                if(iter->key == -1){
                    deleted = iter;
                    iter_prev->next = iter->next;
                    //deleted->next = NULL;
                    break;
                }
                iter_prev = iter;
                iter = iter->next;
            }
            memcpy(deleted, &insert, bytes_per);
        }
        
        freeptr = freeptr + bytes_per; // move the free pointer back
        bytes_remaining -= bytes_per;
        cout << "disdum" << endl;
        return 0;
    }else if(value_len > bytes_per - (8 + 4 + 4)){ //lol magic numbers
        cerr << "value too large\n";
        return -69;
    }else{
        node insert;
        insert.key = key;
        insert.data = value_ptr;
        insert.value_length = value_len;
        insert.next = NULL;
        
        struct node* tmp = (node*) freeptr;
        tmp[0] = insert;
        memcpy(freeptr, &insert, bytes_per);
        freeptr = freeptr + bytes_per; // move the free pointer back
        tmp->next = (node*) freeptr;
        bytes_remaining -= bytes_per;
        
        return 0;
    }
}

int Delete(int key) {
    //if we delete the head, do we need to make the next node equal to the new head?
    if(Lookup(key) == NULL){
        cerr << key <<" doesn't exist\n";
        return -350;
    }else{
        struct node* iter = head;
        while(iter != NULL){
            if(iter->key == key){
                iter->key = -1;
                freeptr = freeptr - bytes_per;
                bytes_remaining += bytes_per;
                return 0;
            }
            iter = iter->next;
        }
    }
}

int* Lookup(int key) {
    struct node* tmp = head;
    while(tmp != NULL){
        if(tmp->key == key) return &(tmp->key);
        tmp = tmp->next;
    }
    cout << "end lookup" << endl;
    return NULL;
}

void PrintList() {
    struct node* tmp = head;
    while(tmp != NULL && tmp->data > 0){
        cout << "(" << tmp->key << ", " << tmp->data << ")";
        tmp = tmp->next;
    }
    cout << endl;
}
*/