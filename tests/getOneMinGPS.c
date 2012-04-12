#include<stdio.h> // Needed to deal with file I/O
#include<stdlib.h>
#include<string.h> //Needed for strncmp, used in parsing
#include<unistd.h> //Necessary for the delay function below

//This function takes care of the process of writing new NMEA sentences
//to the datadump text file. While it may be more costly to reopen and
//reclose a new output stream with each new string, this is the only
//way to ensure that, should the program crash or the Beagle Bone lose
//power, all previously written lines of text will be preserved. That is,
//this method ensures that no sentences but the current one can be lost
//in the event of a crash.

void writeLoop(char *buff){
  FILE *fp = fopen("/home/ubuntu/tests/datadump.txt","a");
  if(fp == NULL) perror("Failed to find write file");
  fprintf(fp,"%s\n",buff);
  fclose(fp);
}

int main(){

  //Here file I/O is established with pointer to GPS device.
  //If an error occurs, programs fails; else, it reads and writes all
  //NMEA sentences beginning with the desired "GPGLL" header

  FILE *readStream;
  readStream = fopen("/dev/ttyO1","r");
  if (readStream == NULL) {
    perror("Failed to read from pin");
    return EXIT_FAILURE;
  }


  //Below, it's important to note that empty (useless) GPS entries
  //will always have a length of 27 characters, while useful ones have
  //a length of 50 characters, enabling us to use the strlen function
  //to screen for entries of some worth.
    
  char *buff = (char *)(malloc(1000*sizeof(char)));

  int j = 0;
  while(j < 60){
    fscanf(readStream,"%s",buff);
    if (strncmp(buff,"$GPGLL",6) == 0){
      if (strlen(buff) == 50) writeLoop(buff);
      printf("Input: %s\n",buff);
      j++;
      usleep(50000);
    }
  }
  
  fclose(readStream);
  return EXIT_SUCCESS;
}
