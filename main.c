#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifdef _WIN32
#include <Windows.h>
#else
#include <unistd.h>
#endif

#include "logic.h"

#define _COL_SIZE 30
#define _ROW_SIZE 30

void clearScr() {
    printf("\033[H\033[2J");
    fflush(stdout);
}

void printGrid(int **univ) {
    for (int i = 0; i < _COL_SIZE; i++) {
        for (int j = 0; j < _ROW_SIZE; j++) {
            printf(univ[i][j] == 1 ? "▣ " : "  ");
        }
        printf("\n");
    }
    printf("\n");
}

void mainLoop(int **univ, int **copy) {
    for (;;) {
        clearScr();
        printGrid(univ);
        nextGen(univ, copy, _COL_SIZE, _ROW_SIZE);

#ifdef _WIN32
        Sleep(500);  
#else
        usleep(500000);  
#endif
    }
}

int main() {
    int **univ = (int **)malloc(sizeof(int *) * _COL_SIZE);
    int **copy = (int **)malloc(sizeof(int *) * _COL_SIZE);

    for (int i = 0; i < _COL_SIZE; i++) {
        univ[i] = (int *)malloc(sizeof(int) * _ROW_SIZE);
        memset(univ[i], 0, sizeof(int) * _ROW_SIZE);

        copy[i] = (int *)malloc(sizeof(int) * _ROW_SIZE);
        memset(copy[i], 0, sizeof(int) * _ROW_SIZE);
    }

    fillboard(univ, _COL_SIZE, _ROW_SIZE);
    mainLoop(univ, copy);

    for (int i = 0; i < _COL_SIZE; i++) {
        free(univ[i]);
        free(copy[i]);
    }
    free(univ);
    free(copy);

    return 0;
}
