#ifndef LOGIC_H
#define LOGIC_H

#include <stdbool.h>

#define DEAD  0
#define ALIVE 1

void fillboard(int** board, int row, int col);
bool isAlive(int **state, int i, int j, int row, int col);
bool isDead(int **state, int i, int j, int row, int col);
bool overPop(int **state, int i, int j, int row, int col);
void nextGen(int **state, int **copy, int row, int col);
int scanPos(int **state, int i, int j);
bool rePro(int **state, int i, int j, int row, int col);
int calcAliveNie(int **state, int i, int j, int row, int col);
void copyUniv(int **state, int **copy, int row, int col);


#endif
