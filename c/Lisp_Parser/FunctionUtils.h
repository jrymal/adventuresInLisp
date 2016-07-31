//
//  FunctionUtil.h
//  Lisp Parser
//
//  Created by Jason Rymal on 3/23/13.
//  Copyright (c) 2013 Jason Rymal. All rights reserved.
//

#ifndef Lisp_Parser_FunctionUtil_h
#define Lisp_Parser_FunctionUtil_h

#include "CoreDataStructures.h"

extern char* EMPTY_STRING;

long* convertListToLong(List* list, int len);

char* convertLongToString(long result);

#endif
