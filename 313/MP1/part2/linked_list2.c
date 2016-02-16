/*
Jessica Fang 
Colin Banigan
*/

#include <stdio.h>
#include <stdlib.h>
#include <cstring>
#include <iostream>
#include <vector>
#include "linked_list2.h"

using namespace std;

const int NUMBER_SPACE = 2147483647;

int bytes_per;
int num_tiers;
int tier_size;
int tier_range;
vector<int> bytes_remaining; //bytes remaining at each tier
vector<node*> head; //head at each tier
vector<char*> freeptr; //free pointer at each tier
vector<void*> tier; //memory at each tier

void Init(int M, int b, int t) {
    bytes_per = b; //number of bytes per node
    num_tiers = t;
    tier_size = M/num_tiers;
    tier_range = NUMBER_SPACE/num_tiers;
    
    for(int i = 0; i < num_tiers; i++){
        tier.push_back(malloc(tier_size)); //allocate M/t bytes of memory per tier
        freeptr.push_back((char*)tier.at(i));
        head.push_back((node*) freeptr.at(i));
        bytes_remaining.push_back(tier_size); //number of bytes remaining in memory     
    }
}

void Destroy() {
    for(int i = 0; i < num_tiers;i++){
        free(tier.at(i));
    }
}

int Insert(int key, char* value_ptr, int value_len) {
    if(value_len > bytes_per - (8 + 4 + 4)){ //lol magic numbers
        cerr << "value too large\n";
        return -69;
    }else{
        node insert;
        insert.key = key;
        insert.value_length = value_len;
        insert.next = NULL;
        
        for(int i = 0; i < num_tiers; i++){
            if(bytes_per > bytes_remaining.at(i)){
                cerr << "not enough memory\n";
                return -666;
            }else if((key >= i*tier_range) && (key < (i+1)*tier_range)){
                
            struct node* tmp = (node*) freeptr.at(i);
            *tmp = insert;
            freeptr.at(i) += sizeof(node);
            memcpy(freeptr.at(i), value_ptr, value_len);
        
            freeptr.at(i) += bytes_per - sizeof(node); // move the free pointer back
            tmp->next = (node*) freeptr.at(i);
            bytes_remaining.at(i) -= bytes_per;
            return 0;
            }
        }
        return 0;
    }
}

int Delete(int key) {
    if(Lookup(key) == NULL){
        cerr << key <<" doesn't exist\n";
        return -350;
    }else{
        for(int i = 0; i < num_tiers; i++){
            if((key >= i*tier_range) && (key < (i+1)*tier_range)){
                struct node* iter = head.at(i);
                if(iter->key == key){
                    iter->key = -1;
                    head.at(i) = iter->next;
                    iter->next = NULL;
                    return 0;
                }
                while(iter->next != NULL){
                    if(iter->next->key == key){
                        iter->next->key = -1;
                        iter->next = iter->next->next;
                        return 0;
                    }
                    iter = iter->next;
                }
            }
        }
        return -1;
    }
}

char* Lookup(int key) {
    if (key < 0){
        cerr << key << " is a negative number\n";
        return NULL;
    }else{
        for(int i = 0; i < num_tiers; i++){
            if((bytes_remaining[i] != tier_size) && (key >= i*tier_range) && (key < (i+1)*tier_range)){
                struct node* tmp = head.at(i);
                while(tmp != NULL){
                    if(tmp->key == key){
                        return (char*)&(tmp->key);
                    }
                    tmp = tmp->next;
                }
            }
        }
        return NULL;
    }
}

void PrintList() {
    for(int i = 0; i < num_tiers; i++){
        struct node* tmp = head.at(i);
        cout << i << ":";
        while(tmp != NULL && tmp->value_length > 0){
            cout << "(" << tmp->key << ", " << tmp->value_length << ")";
            tmp = tmp->next;
        }
        cout << endl;
    }
}