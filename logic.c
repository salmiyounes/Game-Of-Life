#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>
#include "logic.h"


void fillboard(int** board, int row, int col) {
    srand(time(NULL));  
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            board[i][j] = rand() % 2;
        }
    }
}

int calcAliveNie(int **state, int i, int j, int row, int col) {
    int countNie = 0, left = j - 1, right = j + 1, up = i - 1, down = i + 1;

    if (right < col) {
        countNie += state[i][right];
        if (down < row) countNie += state[down][right];
        if (up >= 0)    countNie += state[up][right];
    }
    if (left >= 0) {
        countNie += state[i][left];
        if (down < row) countNie += state[down][left];
        if (up >= 0)    countNie += state[up][left];
    }
    if (up >= 0) countNie += state[up][j];
    if (down < row) countNie += state[down][j];

    return countNie;
}

bool isAlive(int **state, int i, int j, int row, int col) {
    int result = calcAliveNie(state, i, j, row, col);
    return (result == 2 || result == 3);
}

bool isDead(int **state, int i, int j, int row, int col) {
    int result = calcAliveNie(state, i, j, row, col);
    return (result < 2);
}

bool overPop(int **state, int i, int j, int row, int col) {
    int result = calcAliveNie(state, i, j, row, col);
    return (result > 3);
}

void copyUniv(int **state, int **copy, int row, int col) {
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            copy[i][j] = state[i][j];
        }
    }
}

void nextGen(int **state, int **copy, int row, int col) {
    copyUniv(state, copy, row, col);

    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (overPop(copy, i, j, row, col)) state[i][j] = DEAD;
            else if (copy[i][j]) {
                if (!isAlive(copy, i, j, row, col)) state[i][j] = DEAD;
            }
            else if (calcAliveNie(copy, i, j, row, col) == 3) state[i][j] = ALIVE;
        }
    }
}







