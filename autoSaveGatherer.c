#include<stdio.h>  //Needed for file I/O
#include<stdlib.h> 
#include<string.h> //Needed for strncmp, used in filtering
#include<unistd.h> //Necessary for delay function

  int main(){
  
    char *fileName = (char *)(malloc(1000*sizeof(char)));
    char *buff = (char *)(malloc(1000*sizeof(char)));

    int n = 0;
    int j = 0;

    FILE *readGPS = fopen("/dev/ttyO1","r");
    FILE *readOBD = fopen("/dev/pcanusb0","r");
    if(readGPS == NULL || readOBD == NULL) perror("Failed to find read files");

    FILE *write;

    int hazflg = 0;
    int gasflg = 0;
    int litflg = 0;
    int brkflg = 0;
    int whlflg = 0;
    int velflg = 0;

    while(1){
      sprintf(fileName,"/home/ubuntu/BowserProject/test1/datadump%d.txt",n);
      write = fopen(fileName,"a");
      while(strncmp(buff,"$GPRMC",6)){
        fscanf(readGPS,"%s",buff);
      }
      while(j<60){
        if(!(strncmp(buff,"$GPRMC",6))){
	  fprintf(write,"%s\n",buff);
	  fflush(write);
          hazflg = 0;
          gasflg = 0;
	  litflg = 0;
	  brkflg = 0;
	  whlflg = 0;
	  velflg = 0;
	  j++;
	} else {
	  fscanf(readOBD,"%s",buff);
          if(!(strncmp(buff,"m s 0x0000057F",14)) && !(hazflg)){
	    fprintf(write,"%s\n",buff);
	    fflush(write);
	    hazflg++;
	  }
	  else if (!(strncmp(buff,"m s 0x000002C6",14)) && !(gasflg)){
	    fprintf(write,"%s\n",buff);
	    fflush(write);
	    gasflg++;
	  }
	  else if (!(strncmp(buff,"m s 0x0000058D",14)) && !(litflg)){
	    fprintf(write,"%s\n",buff);
	    fflush(write);
	    litflg++;
	  }
	  else if (!(strncmp(buff,"m s 0x000005D4",14)) && !(brkflg)){
	    fprintf(write,"%s\n",buff);
	    fflush(write);
	    brkflg++;
	  }
	  else if (!(strncmp(buff,"m s 0x00000025",14)) && !(whlflg)){
	    fprintf(write,"%s\n",buff);
	    fflush(write);
	    whlflg++;
	  }
	  else if (!(strncmp(buff,"m s 0x000005C5",14)) && !(velflg)){
	    fprintf(write,"%s\n",buff);
	    fflush(write);
	    velflg++;
	  }
	}
      }
      
      n++;
      j = 0;
      fclose(write);
    }
    fclose(readOBD);
    fclose(readGPS);
  }
