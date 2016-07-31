//
//  main.c
//  Lisp Parser
//
//  Created by Jason Rymal on 1/20/13.
//  Copyright (c) 2013 Jason Rymal. All rights reserved.
//
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>

#include "CoreDataStructures.h"
#include "FunctionTree.h"
#include "Logger.h"

Status executeString(Tree* functionTree, const char *line);

Status executeParseTree(Tree *parseTree, Tree* functionTree);

char* execute(Tree *element, Tree* functionTree);

Status buildParseTree(Tree *element, const char * line, const long lineLen);

Boolean isFirstChar(const char *line, const long lineLen, const char searchFor);

char* buildSubString(const char *line, const char * searchFor);

char* executeList( List *list );


int main(int argc, const char * argv[]) {

    // Need this outside so we can add functions to it
    Tree *ft = buildStdFunctionTree();
    
    // set up to parse file or use a command line
    
    /* Good parsing tests */

//    executeString("1");
//    executeString("stuff");
    executeString(ft, "(+ 1 1)");
    executeString(ft, "(+ 1 1 1)");
    executeString(ft, "(+ (+ 1 1) 1)");
    executeString(ft, "(+ (+ 1 1) (+ 1 1)");
//    executeString("(let ((F1 (fibonacci (- N 1))) (F2 (fibonacci (- N 2)))) (+ F1 F2))");
//    executeString("(defun fibonacci (N) \"Compute the N'th Fibonacci number.\" (if (or (zerop N) (= N 1)) 1 (let ((F1 (fibonacci (- N 1))) (F2 (fibonacci (- N 2)))) (+ F1 F2))))");
//    executeString("(defun fibonacci (N) \"Compute the N'th Fibonacci number.\" ");
    
    freeTree(ft);
    
    return 0;
}

Status executeString(Tree* functionTree, const char *line) {
    long lineLen = strlen(line);
    
    // test for comment line
    if (isFirstChar(line, lineLen, ';') == TRUE) {
        return OK;
    }
    
    Tree root;
    memset(&root, 0, TREE_SIZE);

    LOG(LOG_INFO, "Building parse tree");    
    buildParseTree(&root, line, lineLen);
    
    LOG(LOG_INFO, "Executing parse tree");    
    Status stat = executeParseTree(&root, functionTree);
    LOG(LOG_INFO, "Executed parse tree with status: %d", stat);    
    
    LOG(LOG_INFO, "Freeing parse tree");    
    freeTree(root.elements);
    root.elements = NULL;
    free(root.data);
    root.data = NULL;
    
    return OK;
}

Status executeParseTree(Tree *parseTree, Tree* functionTree) {

    char *final = execute(parseTree, functionTree);
    if (final != NULL) {
        LOG(LOG_INFO, "Final: %s", final);
        free(final);
        return OK;
    }   
    
    return ERROR;
}

char* execute(Tree *element, Tree* functionTree) {
    List *list = NULL;
    int i;
    
    LOG(LOG_INFO, "About to enter reverse handle");
    for(i = element->elementCount-1; i >= 0; i--) {
        LOG(LOG_INFO, "adding #%d", i);
        addToList(&list, execute(&element->elements[i], functionTree));
    }
    
    LOG(LOG_INFO, "Reverse handle done");

    if (element == NULL) {
        return NULL;
    } else if (list == NULL) {
        return element->data;
    } else { 
        return executeList(list);
    }
}

char* executeList( List *list ) {
    LOG(LOG_INFO, "About to print");
    printList(list);

    return NULL;
}

Status buildParseTree( Tree *root,
        const char *line, const long lineLen) {
    Status parseResult = OK;
    Tree *element = root;
    
    Stack stack;
    memset(&stack, 0, sizeof(Stack));
    
    LOG(LOG_INFO, "Parsing '%s'", line);
    
    const char *iterator;
    int index;
    Boolean quitLoop = FALSE;
    
    for (index = 0; quitLoop == FALSE && index < lineLen; index++ ) {
        
        iterator = &line[index];

        switch (*iterator) {
            case '(':
                element = addNodeToTree(element, NULL);
                push(&stack, element);
                break;
            case ')':
                element = pop(&stack);
                break;
            case ' ':
                // ignore
                break;
            case ';':
                if (stack.size != 1) {
                    LOG(LOG_INFO, "Error in statement at character %d", index);
                    parseResult = ERROR;
                }
                quitLoop = TRUE;
                break;
            case '"':
            {
                // this is a literal string
                
                char *token = buildSubString(&iterator[1], "\"");
                if (token != NULL) {
                    index += strlen(token) + 1;
                    
                    LOG(LOG_INFO, "%d %s", stack.size , token);

                    addNodeToTree(element, token);
                }
                break;
            }
            default:
            {
                char *token = buildSubString(iterator, "() \"\n");
                if (token != NULL) {
                    // we want to parse the character that stopped parsing
                    index += strlen(token) - 1;
                    
                    LOG(LOG_INFO, "%d %s", stack.size , token);
                    
                    addNodeToTree(element, token);
                }
            }
        }
    }
    
    free(stack.value);
    
    return parseResult;
}

Boolean isFirstChar(const char * line, long lineLen, const char searchFor) {
    long i;
    for ( i = 0; i < lineLen; i++) {
        if (!isspace(line[i])) {
            if (line[i] == searchFor) {
                return TRUE;
            }
            return FALSE;
        }
    }
    return FALSE;
}

char *buildSubString(const char *line, const char *searchFor) {
    int index;
    long maxSearchLen = strlen(searchFor);
    const char *stringEnd = strchr(line, '\0');
    
    for ( index = 0; index < maxSearchLen; index++) {
        const char *curSearch = &searchFor[index];
        const char *testStringEnd = line;

        testStringEnd = strchr(line, *curSearch);

        if (testStringEnd != NULL && stringEnd > testStringEnd) {
            stringEnd = testStringEnd;
        }
    }
    
    if (stringEnd != NULL && stringEnd != line) {
        return strndup(line, stringEnd - line);
    }
    return NULL;
}
