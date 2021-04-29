
//Truman Nguyen
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class CPUScheduler {
	public static void main(String[] args) 
	{	
		//getting the data from the command prompt
		//this is for the file name
		ArrayList<String> processor = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> arriveAndBrust = new ArrayList<>();
		try {
		      File myObj = new File(args[0]);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		    	processor.add(myReader.next()); 
		    	int arivial = Integer.parseInt(myReader.next());
		    	int burst = Integer.parseInt(myReader.next());
		    	ArrayList<Integer> list1=new ArrayList();
		    	list1.add(arivial);
		    	list1.add(burst);
		    	arriveAndBrust.add(list1);
		    	
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		//this is for the quantum
	    int quantum = Integer.parseInt(args[1]);  
	    
	    //printing
	    printer();
		 


		//bubble sort to sort the list by arrival time, Just in case it isn't sort by arrival time 
        for (int i = 0; i < arriveAndBrust.size()-1; i++) 
        {
        	 for (int j = 0; j < arriveAndBrust.size()-i-1; j++) 
        	 {
            	 if (arriveAndBrust.get(j).get(0)> arriveAndBrust.get(j+1).get(0) || (arriveAndBrust.get(j).get(0) == arriveAndBrust.get(j+1).get(0) && arriveAndBrust.get(j).get(1) > arriveAndBrust.get(j+1).get(1)))
                 {
                     
            		 ArrayList<Integer> temp = arriveAndBrust.get(j);
                     String tempString = processor.get(j);
                     arriveAndBrust.set(j, arriveAndBrust.get(j+1));
                     arriveAndBrust.set(j+1, temp);
                     processor.set(j, processor.get(j+1));
                     processor.set(j+1, tempString);
                     
                 }
            }
        }
        
        //First Come First Serve
        FCFS(arriveAndBrust,processor);
        System.out.println("-------------------------------------------------");
		System.out.println("                    Round Robin                 ");
		System.out.println("-------------------------------------------------");
        roundRobin(arriveAndBrust,processor,quantum);
        System.out.println("-------------------------------------------------");
        System.out.println("Project done by Truman Nguyen CMP 426");
        System.out.println("-------------------------------------------------");
        
			
		}
		

	
	public static void FCFS(ArrayList<ArrayList<Integer>> arriveAndBrust, ArrayList<String> processor) 
	{
		int[]waitTimeList = new int[arriveAndBrust.size()];
		int[]turnaroundTimeList = new int[arriveAndBrust.size()];;
		int[]responseTimeList = new int[arriveAndBrust.size()];;
		int counter = 0; //to keep track of the timer
		boolean idle = false; //to handle idles
		int holder =0; //to handle wait, turnaround, and responds time when there is an idle
		
				//loop for each processor
				for(int i= 0; i< arriveAndBrust.size(); i++) 
				{
					int waitTime = counter - arriveAndBrust.get(i).get(0) ;
					waitTimeList[i] = waitTime;
					int respondTime = counter - arriveAndBrust.get(i).get(0) ;
					responseTimeList[i] = respondTime;
					
					//loop from arrival time to burst time
					for(int j =0; j< arriveAndBrust.get(i).get(1);j++) 
					{
							//if arrival time doesn't match with current time (idling)
							while(arriveAndBrust.get(i).get(0) > counter) 
							{
								System.out.println("Time " + counter + " is idling");	
								counter++;
								idle = true;
							}
							//set idle mode for caculating 
							if(idle == true)
							{
								holder = counter-1; //since in the while loop we are getting the counter up to the arrival time
							}
						//FCFSString.add("Time " + counter + " process " + processor[i]+ " is running");
						System.out.println("Time " + counter + " Process " + processor.get(i)+ " is running");	
						counter++;
					}
					//if there was an idle before the arrive 
					if(idle == true) 
					{
						 waitTime = holder - arriveAndBrust.get(i).get(0);
						waitTimeList[i] = waitTime;
						 respondTime = holder - arriveAndBrust.get(i).get(0);
						responseTimeList[i] = respondTime;
						idle = false;
					}
					int turnAroundTime = waitTime + arriveAndBrust.get(i).get(1);
					turnaroundTimeList[i] = turnAroundTime;
				}
			average(processor, turnaroundTimeList,  waitTimeList, responseTimeList);	
				

		}
	
		public static void roundRobin(ArrayList<ArrayList<Integer>> arriveAndBrust, ArrayList<String> processor, int quantum) 
		{
			int[]waitTimeList = new int[arriveAndBrust.size()];
			int[]turnaroundTimeList = new int[arriveAndBrust.size()];;
			int[]responseTimeList = new int[arriveAndBrust.size()];;
			int counter = 0; //to keep track of the timer
			boolean idle = false; //to handle idles
			int holder =0; //to handle wait, turnaround, and responds time when there is an idle
			int whileLoopCounter =0; //our counter for the while loop and the timer
			int processCounter =0; //to keep track when we have to add the process
			int rotation =0; //to keep track what processor are we doing
			int quantumCounter = 0; //to run the quantum 
			int quantumLimit = 0; //to set the another of time we have to run the quantum depending on the cases
			boolean watcher = true; //to keep track when we have to add the process
			int amountOfRotationFinished =0;
			//to keep track of our rotations of processor
			LinkedList<Integer> scheduledBrust = new LinkedList<Integer>();
			LinkedList<Integer> scheduledArival = new LinkedList<Integer>();
			LinkedList<String> processorName = new LinkedList<String>();
			//for the response and wait time of each processor
			int responseTimeCounter =0;
			int[] waitTimeHolder = new int[arriveAndBrust.size()];
			int waitTimeCounter = 0;
			
			
			//loop for each processor to find the total length including idles
			for(int i= 0; i< arriveAndBrust.size(); i++) 
			{	
				//loop from arrival time to burst time
				for(int j =0; j< arriveAndBrust.get(i).get(1);j++) 
				{
						//if arrival time doesn't match with current time (idling)
						while(arriveAndBrust.get(i).get(0) > counter) 
						{	
							counter++;
							idle = true;
						}
					counter++;
				}
			}
			//now that counter has the total length we can do a for loop for the entire length of scheduler.
			while(whileLoopCounter < counter) 
			{
				//to deal with idle time
				if(arriveAndBrust.get(amountOfRotationFinished).get(0) > whileLoopCounter) {
					while(arriveAndBrust.get(amountOfRotationFinished).get(0) != whileLoopCounter) 
					{
						System.out.println("Time " + whileLoopCounter + " is idling");
						whileLoopCounter++;
					}
				}
				//if the the counter is higher or equal to the arrival time, add to be schedule
				if(processCounter != arriveAndBrust.size()) 
				{
					//we use while loop because what if we have multiple process to schedule
					while(watcher == true) 
					{
						if(processCounter != arriveAndBrust.size()) 
						{
							if(arriveAndBrust.get(processCounter).get(0)  <= whileLoopCounter) 
							{
								scheduledBrust.add(arriveAndBrust.get(processCounter).get(1));
								scheduledArival.add(arriveAndBrust.get(processCounter).get(0));
								processorName.add(processor.get(processCounter));
								processCounter++;
								watcher = true;
							}
							else 
							{
								watcher = false;
							}
						}else 
						{
							watcher = false;
						} 
					}
					watcher = true;
				}
				
				//to get the responseTime, we get the the current time - arrivial time (only the 1st time each processor runs)
				//also get the 1st wait time since the 1st respondsTime is also part of the wait time
				if(responseTimeCounter != arriveAndBrust.size()) 
				{
					if(processor.get(responseTimeCounter) == processorName.get(rotation)) 
					{
						responseTimeList[responseTimeCounter] = whileLoopCounter - scheduledArival.get(rotation);
						waitTimeList[responseTimeCounter] = whileLoopCounter - scheduledArival.get(rotation);
						responseTimeCounter++;
					}
				}
				//to get wait time
				if(processor.get(waitTimeCounter) == processorName.get(rotation)) 
				{
					//to prevent the 1st wait time since the responseTime is the 1st  wait time
					if(waitTimeHolder[waitTimeCounter] != 0) {
//						System.out.println("HIII");
//						System.out.println(processor.get(waitTimeCounter));
//						System.out.println(waitTimeList[waitTimeCounter]);
//						System.out.println(whileLoopCounter);
//						System.out.println(waitTimeHolder[waitTimeCounter]);
						
						waitTimeList[waitTimeCounter] = waitTimeList[waitTimeCounter] + whileLoopCounter - waitTimeHolder[waitTimeCounter];
						waitTimeCounter++;
					}
				}
					//set up the quantumLimit
					if(quantumCounter == 0) 
					{
						if(scheduledBrust.get(rotation) > quantum || scheduledBrust.get(rotation) == quantum)
						{
							quantumLimit = quantum;
						}
						else 
						{
							quantumLimit = scheduledBrust.get(rotation);
						}
					}
					
					//printing and updating the count for quantumCounter and the process burst time
					System.out.println("Time " + whileLoopCounter + " Process " + processorName.get(rotation)+ " is running");	
					quantumCounter++;
					scheduledBrust.set(rotation, scheduledBrust.get(rotation)-1);
					whileLoopCounter++;
					//when it has went through the quantum, it will rotate and/or delete the current rotation if it's emepty 
					if(quantumCounter == quantumLimit && scheduledBrust.get(rotation) == 0) 
					{	
						//delete the rotation
						//System.out.println("CASE 1" + " rotation " + rotation);
						turnaroundTimeList[amountOfRotationFinished] = whileLoopCounter - scheduledArival.get(rotation);
						scheduledBrust.remove(rotation);
						processorName.remove(rotation);
						scheduledArival.remove(rotation);
						quantumCounter = 0;
						quantumLimit = 0;
						amountOfRotationFinished++;
						//if this was at the end we rest
						if(rotation == scheduledBrust.size()) {
							waitTimeCounter =0;
							rotation= 0;
						}else if ( rotation >0) 
						{   waitTimeCounter--;
							rotation--;
						}	
					}
					//when it has went through the quantum and has to restart the rotation
					else if(quantumCounter == quantumLimit && rotation == scheduledBrust.size()-1)
					{
						//restart the rotation
						//System.out.println("CASE 2"+ " rotation " + rotation);
						for(int i =0; i < waitTimeHolder.length; i++) 
						{
							if(processor.get(i).equals(processorName.get(rotation))) 
							{
								waitTimeHolder[i] = whileLoopCounter;
							}
						}
						waitTimeCounter = 0;
						rotation = 0;
						quantumCounter =0;
						quantumLimit =0;
					}
					//when it has went through the quantum and has to restart the rotation
					else if(quantumCounter == quantumLimit) 
					{
						//go to the next rotation
						//System.out.println("CASE 3"+ " rotation " + rotation);
						for(int i =0; i < waitTimeHolder.length; i++) 
						{
							if(processor.get(i).equals(processorName.get(rotation))  ) 
							{
								waitTimeHolder[i] = whileLoopCounter;
							}
						}
						quantumCounter =0;
						quantumLimit =0;
						rotation++;
					}
			}
			
			average(processor, turnaroundTimeList,  waitTimeList, responseTimeList);
		}
		
		//print out the turnaround,wait, and respond time
		public static void average(ArrayList<String> processor, int[] turnaroundTimeList, int[] waitTimeList, int [] responseTimeList) 
		{
			double WTAverageFCFS =0;
			double TAAverageFCFS =0;
			double RTAverageFCFS =0;
			//printing the turnaround time for each processor 
			System.out.println("Turnaround time:");
			for(int i =0; i< processor.size(); i++) {
			System.out.println("    "+ processor.get(i)+ " = " + turnaroundTimeList[i]);
			}
			//printing the wait time for each processor
			System.out.println("Wait time:");
			for(int i =0; i< processor.size(); i++) {
				System.out.println("    "+ processor.get(i)+ " = " + waitTimeList[i]);
			}
			//print the responds time for each processor
			System.out.println("Responds time:");
			for(int i =0; i< processor.size(); i++) {
				System.out.println("    "+ processor.get(i)+ " = " + responseTimeList[i]);
			}
			//getting the average
			for(int i =0; i < waitTimeList.length;i++) 
			{
				WTAverageFCFS = WTAverageFCFS + waitTimeList[i];
				TAAverageFCFS =TAAverageFCFS + turnaroundTimeList[i];
				RTAverageFCFS = RTAverageFCFS + responseTimeList[i];
			}
			System.out.println("Average turnaround time: " + TAAverageFCFS /  turnaroundTimeList.length);
			System.out.println("Average wait time: " + WTAverageFCFS /  waitTimeList.length);
			System.out.println("Average respond time: " + RTAverageFCFS /  responseTimeList.length);
		}
		
		public static void printer() 
		{
			System.out.println("-------------------------------------------------");
			System.out.println("            CPU Scheduling Simulation            ");
			System.out.println("-------------------------------------------------");
			System.out.println("               By Truman Nguyen                  ");
			for(int i = 0; i < 3; i++) 
			{
				System.out.println();
			}
			System.out.println("-------------------------------------------------");
			System.out.println("            First Come First Serve Simulation    ");
			System.out.println("-------------------------------------------------");
		}
	}
	
	


	
	

