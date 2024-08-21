#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifdef _WIN32
#include <Windows.h>
#else
#include <unistd.h>
#endif

#include "logic.h"

void clearScr() {
    printf("\033[H\033[2J");
    fflush(stdout);
}
void memoClear(int **univ, int **copy) {
    for (int i = 0; i < _COL_SIZE; i++) {
        free(univ[i]);
        free(copy[i]);
    }
    free(univ);
    free(copy);
}
void printGrid(int **univ) {
    for (int i = 0; i < _COL_SIZE; i++) {
	printf ("%s", _BCK_COLOR);
        for (int j = 0; j < _ROW_SIZE; j++) {
            printf(univ[i][j] == ALIVE ? _NON_EMPTY : _EMPTY);
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
        Sleep(200);  
#else
        usleep(200000);  
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

#ifdef GLIDER
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            univ[i][j] = grid[i][j];
        }
    }

    mainLoop(univ, copy);
    memoClear(univ, copy);
#else
    fillboard(univ, _COL_SIZE, _ROW_SIZE);
    mainLoop(univ, copy);
    memoClear(univ, copy);

#endif

    return 0;
}
