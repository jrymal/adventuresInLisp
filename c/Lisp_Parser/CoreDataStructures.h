//
//  CoreDataStructures.h
//  Lisp Parser
//
//  Created by Jason Rymal on 1/24/13.
//  Copyright (c) 2013 Jason Rymal. All rights reserved.
//

#ifndef Lisp_Parser_CoreDataStructures_h
#define Lisp_Parser_CoreDataStructures_h

typedef enum {
    OK,
    ERROR
} Status;

typedef enum {
    TRUE = 1,
    FALSE = 0
} Boolean;

typedef struct Tree_t {
    struct Tree_t *elements;
    int elementCount;
    void *data;
} Tree;

#define TREE_SIZE sizeof(Tree)

typedef struct Stack_t {
    void **value;
    int size;
} Stack;

typedef struct List_t{
    struct List_t *next;
    void *value;
} List;



void *pop(Stack *stack);
void push(Stack *stack, void *value);

void addToList(List **list, char *value);
int getListSize(List* list);
void printList(List *list);

Tree* addNodeToTree(Tree* nodeToAddTo, void* object);
Tree* findNode(Tree* tree, void* criteria, Boolean (*isSearchedFor)(void* criteria, void* current));
void freeTree(Tree *root);

#endif
