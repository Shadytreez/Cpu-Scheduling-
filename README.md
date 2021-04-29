# Cpu-Scheduling-
A cpu scheduling simulation for FCFS and Round Robin

# How to use it

Run the command javac to compile the code and then java CPUScheduler <filepath> <time quantum>
  
<filepath> : the .txt file containing the information of all your process ID, arrivial time, and burst time
<time quantum> : the time quantum used for round robin
  
The format for the .txt file should be PX Y Z (the 'P' is also part of the formating) 
X: the ID number of the process
Y: the process arrival time
Z: the process brust time 

Example of .txt file
P0 0 3
P1 1 6
P2 5 4
P3 7 3  
