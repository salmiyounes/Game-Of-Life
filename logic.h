#ifndef LOGIC_H
#define LOGIC_H

#include <stdbool.h>

#define DEAD  0

#define ALIVE 1

#define _COL_SIZE 60

#define _ROW_SIZE 60

#define _BCK_COLOR "\033[0;107m"

#define _NON_EMPTY "\033[1;30m■"

#define _EMPTY " "

static const int grid[][5] = {
	{ 0, 0, 0, 0, 0},
	{ 0, 0, 0, 1, 0},
	{ 0, 1, 0, 1, 0},
	{ 0, 0, 1, 1, 0},
	{ 0, 0, 0, 0, 0}
};

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
