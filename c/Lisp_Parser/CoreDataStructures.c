//
//  CoreDataStructures.c
//  Lisp Parser
//
//  Created by Jason Rymal on 1/24/13.
//  Copyright (c) 2013 Jason Rymal. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "CoreDataStructures.h"

void push(Stack *stack, void *value) {
    (stack->size)++;
    stack->value = realloc(stack->value, stack->size * sizeof(void*));
    stack->value[stack->size - 1] = value;
}

void *pop(Stack *stack) {
    void *value = stack->value[stack->size - 1];
    (stack->size)--;
    return value;
}

Tree* addNodeToTree(Tree* nodeToAddTo, void* object) {
    nodeToAddTo->elementCount += 1;
    nodeToAddTo->elements = realloc(nodeToAddTo->elements,
                                  TREE_SIZE * nodeToAddTo->elementCount);
    
    Tree *returnValue = &(nodeToAddTo->elements[nodeToAddTo->elementCount - 1]);
    memset(returnValue,0,TREE_SIZE);
    
    returnValue->data = object;
    
    return returnValue;
}

Tree* findNode(Tree* tree, void* criteria, Boolean (*isSearchedFor)(void* criteria, void* current)) {
    
    
    if (tree == NULL) {
        return NULL;
    } else if (tree->data != NULL && (*isSearchedFor)(criteria, tree->data)) {
        return tree;
    }
    
    int i;
    for(i = tree->elementCount - 1; i >= 0; i--) {
        Tree* node = findNode(&(tree->elements[i]), criteria, isSearchedFor);
        if (node != NULL) {
            return node;
        }
    }
    
    return NULL;
}

void freeTree(Tree *root) {
   
    int i; 
    for(i = root->elementCount - 1; i >= 0; i--) {
        freeTree(&root->elements[i]);
    }
    
    free(root->elements);
    root->elements = NULL;
    if (root->data != NULL) {
        free(root->data);
        root->data = NULL;
    }
}

void addToList(List **list, char *value) {

    if (value != NULL) {
        List *iter;

        if (list == NULL || *list == NULL) {
            iter = *list = calloc( sizeof(List), 1 );
        } else {
            iter = *list; 
            while (iter->next != NULL) {
                iter = iter->next;
            }

            iter->next = calloc( sizeof(List), 1 );
            iter = iter->next;
        }
    
        iter->value = value;
        iter->next = NULL;
    }
}

int getListSize(List* list) {
    List* iter = list;
    int listSize = 0;
    while (iter->next != NULL) {
        iter = iter->next;
        list++;
    }
    
    return listSize;
}


void printList(List *list) {
    List *iter = list;
    while (iter != NULL) {
        if (iter->value == NULL) {
            printf("SHIT ");
        } else {
            printf("%s ", (char*) iter->value);
        }
        iter = iter->next;
    }
    printf("\n");
}
