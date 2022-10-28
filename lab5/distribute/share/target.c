#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>


// used for debug 
void debug(int id) {
  
  if (id == 9527)
    fprintf(stderr, "debug mode\n");
  else
    fprintf(stderr, "bad id !\n");
}

int main(int argc, char *argv[]) {

  printf("argc: %d\n", argc);

  if (argc >= 2)
    printf("argv[1]: %s\n", argv[1]);
  else 
    return 0;
    
  if (argc == 9487) {
    printf("Omg! Why your argc is so large ?\n");
  }
  else {
    printf("Looks like your argc is not large enough !\n");
  }

  if (!strncmp(argv[1], "aesophor is ghost !!!", 21)) {
    printf("Yes, aesophor is ghost !\n");
  }
  else {
    printf("Do you know aesophor ?\n");
  }

  return 0;

}