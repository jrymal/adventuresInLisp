//
//  FunctionTree.c
//  Lisp Parser
//
//  Created by Jason Rymal on 3/19/13.
//  Copyright (c) 2013 Jason Rymal. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "CoreDataStructures.h"
#include "FunctionTree.h"
#include "MathFunctions.h"

Boolean isCharLeaf(void* criteria, void* checkValue) {
    if (((Function*)checkValue)->character == (char)criteria) {
        return TRUE;
    }
    return FALSE;
}

Tree* findLeaf(Tree *parent, char curChar) {
    Tree* retValue = findNode(parent, curChar, &isCharLeaf);
    
    if (retValue == NULL) {
        Function* newFunction = calloc(FUNCTION_SIZE, 1);
        newFunction->character = curChar;
        return addNodeToTree(parent, newFunction);
    }
    
    return retValue;
}

Tree* getLeaf(Tree *ft, char *name, size_t nameLen) {
    size_t i;
    Tree *currentTree = ft;
    
    for(i = 0; i < nameLen; i++) {
        currentTree = findLeaf(currentTree, name[i]);
    }
    
    return currentTree;
}

Status addCoreFunction(Tree *ft, char *name, size_t nameLen, char* (*function)(List*)) {
    Tree* leaf = getLeaf(ft, name, nameLen);
    
    Function* coreFunc = calloc(FUNCTION_SIZE, 1);
    coreFunc->type = CORE;
    coreFunc->version.core.function = function;
    
    leaf->data = function;
    
    return OK;
}

Status addScriptFunction(Tree *ft, char *name, size_t nameLen, char *function, size_t functionLen) {
    Tree *leaf = getLeaf(ft, name, nameLen);

    Function* scriptFunc = calloc(FUNCTION_SIZE, 1);
    scriptFunc->type = SCRIPT;
    scriptFunc->version.script.script = function;
    scriptFunc->version.script.len = functionLen;

    leaf->data = scriptFunc;
    
    return OK;
}

Function* getFunction(Tree *ft, char *name, size_t nameLen) {
    Tree *leaf = getLeaf(ft, name, nameLen);
    if (leaf != NULL) {
        return leaf->data;
    }
    return NULL;
}

Tree* buildStdFunctionTree() {
    Tree* ft = calloc(TREE_SIZE, 1);
    
    addCoreFunction(ft, strdup("+"), 1, &plusHandle);
    //addCoreFunction(ft, "-", 1, &minusHandle);
    //addCoreFunction(ft, "*", 1, &multiplyHandle);
    //addCoreFunction(ft, "/", 1, &divisionHandle);
    
    return ft;
}

