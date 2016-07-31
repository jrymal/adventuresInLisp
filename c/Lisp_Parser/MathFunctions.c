//
//  MathFunctions.c
//  Lisp Parser
//
//  Created by Jason Rymal on 3/23/13.
//  Copyright (c) 2013 Jason Rymal. All rights reserved.
//

#include <stdlib.h>
#include <stdio.h>
#include "FunctionUtils.h"
#include "CoreDataStructures.h"

char* plusHandle(List* list) {
    
    int len = getListSize(list);
    
    if (len > 0) {
        long result = 0;
        long* asLong = convertListToLong(list, len);
        int i;
        for (i = 0; i < len - 1; i++) {
            result += asLong[i];
        }
        free(asLong);
        
        return convertLongToString(result);
    }

    return EMPTY_STRING;
}