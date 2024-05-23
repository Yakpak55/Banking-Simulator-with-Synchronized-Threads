/* Name: Christopher Deluigi
Course: CNT 4714 Spring 2024
Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
Due Date: February 11, 2024
*/
package developmentalVersion;

import java.util.Random;

public class InternalAuditor implements Runnable{

	private static Random sleepTimeStop = new Random();
	private static final int MAXSLEEPTIME_STOP = 1500;
	private TheBankAccount jointAccount;
	private int transaction_Num_Last = 0;
	
	public InternalAuditor(TheBankAccount joint)
	{
		this.jointAccount = joint;
	}
	
	public void run()
	{
		while(true)
		{
			System.out.println();
			System.out.println("**************************************************************************************************************************************************");
			System.out.println();
			System.out.println("\t\tINTERNAL BANK AUDITOR FIND CURRENT ACCOUNT BALANCE TO BE: $" + jointAccount.getBal() + "\t\t Number of transactions since last Internal audit is: " + (jointAccount.getTransNum() - transaction_Num_Last));
			System.out.println();
			System.out.println("***************************************************************************************************************************************************");
			System.out.println();
			
			transaction_Num_Last = jointAccount.getTransNum();
			
			try {
				Thread.sleep(sleepTimeStop.nextInt(MAXSLEEPTIME_STOP)+1+1+1);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
