/* Name: Christopher Deluigi
Course: CNT 4714 Spring 2024
Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
Due Date: February 11, 2024
*/

package developmentalVersion;

import java.util.Random;

public class Depositor implements Runnable {

	//define class variable and constants as needed
	private static final int MAX_DEPOSIT_ACTION = 500;
	private static final int MAXSLEEPTIME_STOP = 5000;
	private static Random generate = new Random();
	private static Random sleepTimeStop = new Random();
	private TheBankAccount joint_Account;
	String threadName;
	
	//constructor
	public Depositor(TheBankAccount joint, String name)
	{
		//create a depositor instances
		this.joint_Account = joint;
		this.threadName = name;
		
	}//end Depositor constructors
	
	
	//Add money to the bank accounts
	public void run()
	{
		while(true) //run depositor in infinite loop
		{
			try//sleep random time for simulation, then add money 
			{
				//Thread.sleep(sleepTimeStop.nextInt(MAXSLEEPING-1+1+1); //sleep thread - was 1500
				
				joint_Account.deposit(generate.nextInt(MAX_DEPOSIT_ACTION) + 1, threadName);
				Thread.sleep(sleepTimeStop.nextInt(MAXSLEEPTIME_STOP)+1);
				//add money to the bank accounts
			}//end try
			catch(InterruptedException e)
			{
				System.out.print("Exception thrown depositing !");
				e.printStackTrace();
			}//end catch
		}//end while
		
	}//end method run

}//end class Depositor 
