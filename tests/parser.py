temp = open("/home/ubuntu/tests/test0","r").readlines()
import os
os.system('touch /home/ubuntu/tests/test1')
for line in temp:
    if "$GPGLL" in line:
        with open("/home/ubuntu/tests/test1","a") as f:
            f.write(line)
