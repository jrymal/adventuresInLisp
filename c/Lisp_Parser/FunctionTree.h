//
//  FunctionTree.h
//  Lisp Parser
//
//  Created by Jason Rymal on 3/19/13.
//  Copyright (c) 2013 Jason Rymal. All rights reserved.
//

#ifndef Lisp_Parser_FunctionTree_h
#define Lisp_Parser_FunctionTree_h

typedef enum {
    NONE,
    CORE,
    SCRIPT
} FunctionType;

typedef struct CoreFunction_t {
    char* (*function)(List*);
} CoreFunction;

typedef struct ScriptFunction_t {
    char *script;
    size_t len;
} ScriptFunction;

typedef union {
    ScriptFunction script;
    CoreFunction core;
} FunctionVersion;

typedef struct Function_t {
    FunctionType type;
    FunctionVersion version;
    char character;
} Function;

#define FUNCTION_SIZE sizeof(Function)

Status addCoreFunction(Tree *ft, char *name, size_t nameLen, char* (*function)(List*));

Status addScriptFunction(Tree *ft, char *name, size_t nameLen, char *function, size_t functionLen);

Function* getFunction(Tree *ft, char *name, size_t nameLen);

void freeFunctionTree(Tree *ft);

Tree* buildStdFunctionTree();

#endif
