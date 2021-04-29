# Cpu-Scheduling-
A cpu scheduling simulation for FCFS and Round Robin

# How to use it

Run the command **javac** to compile the code and then  java CPUScheduler **filepath** **time quantum** <br/>
  
**filepath**: the .txt file containing the information of all your process ID, arrivial time, and burst time <br/>
**time quantum**: the time quantum used for round robin <br/>
  
# Format of the .txt file  
The format for the .txt file should be PX Y Z (the 'P' is also part of the formating) <br/>
X: the ID number of the process <br/>
Y: the process arrival time <br/>
Z: the process brust time <br/>

Example of .txt file <br/>
P0 0 3 <br/>
P1 1 6 <br/>
P2 5 4<br/>
P3 7 3  <br/>
