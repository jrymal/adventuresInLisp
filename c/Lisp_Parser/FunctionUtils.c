//
//  FunctionUtils.c
//  Lisp Parser
//
//  Created by Jason Rymal on 3/23/13.
//  Copyright (c) 2013 Jason Rymal. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include "CoreDataStructures.h"

const char* const EMPTY_STRING = "";

long* convertListToLong(List* list, int len) {
    List* iter;
    int i;
    long* longList = calloc(sizeof(long), len);
    
    for (i=0, iter=list; iter != NULL; iter=iter->next, i++) {
        longList[i] = atol(iter->value);
    }
    
    return longList;
}

int calculateLongLength(long value) {
    int length = 0;
    long curVal = value;
    
    do{
        length++;
        curVal = curVal/10;
    }while (curVal > 0);
    
    return length;
}

char* convertLongToString(long value) {
    // need to hav the extra 1 as there needs to be a \n at the end
    int longLength = calculateLongLength(value) + 1;
    
    char* result = calloc(sizeof(char), longLength);
    
    snprintf(result, longLength, "%li", value);
    
    return result;
}
