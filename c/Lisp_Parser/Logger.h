#include <stdio.h>

#ifndef Lisp_Parser_Logger_h
#define Lisp_Parser_Logger_h

typedef enum {
    LOG_FINEST = 1,
    LOG_INFO = 3,
    LOG_WARN = 6,
    LOG_ERROR = 9
} LOG_LEVEL;

LOG_LEVEL _CURLEVEL_ = LOG_INFO;

#define LOG( level, line, ...) \
    if ( level >= _CURLEVEL_ ) { \
        fprintf(stderr, "%s[%d]: " line "\n", \
                __FILE__, __LINE__, ## __VA_ARGS__); \
        fflush(stderr); \
    }

#endif