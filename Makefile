CC = gcc

CFLAGS = -Wall -Wextra -pedantic

TARGET = game_of_life

SRCS = main.c logic.c

OBJS = $(SRCS:.c=.o)

DEPS = logic.h

all: $(TARGET)

$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) -o $(TARGET) $(OBJS)

%.o: %.c $(DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

clean:
	rm -f $(OBJS) $(TARGET)

run: $(TARGET)
	./$(TARGET)

.PHONY: all clean run

