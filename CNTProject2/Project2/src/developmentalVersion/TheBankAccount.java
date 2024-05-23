/* Name: Christopher Deluigi
Course: CNT 4714 Spring 2024
Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
Due Date: February 11, 2024
*/

//BankAccount synchronizes access to a single bank account entirely
package developmentalVersion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TheBankAccount implements TheBank
{

	//defines the lock to control mutually exclusive access to the bank accounts
	private int balance = 0;
	private Lock accLock = new ReentrantLock();
	
	//defines any condition variables for this lock
	private Condition sufficentFCondition = accLock.newCondition();
	
	//defines the variables and constants for the bank accounts
	private int transaction_Number = 0;
	
	
	
	//method used to log flagged transactions made against the bank accounts
	public void flagged_transaction(int transAmount, String threadName, String threadType)
	{
		double extra_Amount = 0.0;
		
		//generates a Date object for timestamping 
		LocalDateTime now = LocalDateTime.now();		
		ZoneId zoneID = ZoneId.systemDefault();
		String sZone = zoneID.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		
		//sets the Date object format
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		String formatDate = now.format(format) + " " + sZone;
		
		
		//creates an output transaction file
		File file = new File("transactions.csv");
		
		
			if(threadType.equals("Deposit"))
			{
				extra_Amount = 350.00;
			}
			else //transactino type is a withdrawal
			{
				extra_Amount = 75.00;
			}
			
			System.out.println("* * * Flagged Transaction - " + threadType + " " + threadName + " Made a " + threadType + " In Excess of $" + extra_Amount + " USD - See Flagged Transaction Log" + "\n");
			
			//write the string to the transaction file
			try (FileWriter write = new FileWriter(file, true))
			{
				if(threadType.equals("Deposit"))
				{
				write.write(threadType + " " + threadName + " issued a " + threadType + " of $" + transAmount + " at " + formatDate + " Transaction Number : " + transaction_Number + System.lineSeparator()+"\n");
				}
				else
				{
				write.write("\t" + threadType + " " + threadName + " issued a " + threadType + " of $" + transAmount + " at " + formatDate + " Transaction Number : " + transaction_Number + System.lineSeparator()+"\n");
				}
			} catch (IOException e) {
				System.out.println("Error writing to the file ");
			}
			
		}//ends the method flagged_transaction
	
	
	//methods used to make a deposit into the bank account
	
	public void deposit(int depposit_Amount, String threadName)
	{
		//locks the bank accounts
		accLock.lock();
		//waits for auditor to complete
		//output thread information, deposit amount and account balance then signal any waiting withdrawal threads
		try
		{
			//make deposit
			balance += depposit_Amount;
			
			//handle transaction logging for flagged transaction
			transaction_Number += 1;
			System.out.println(threadName + " deposits $" + depposit_Amount + "\t\t\t\t\t\t" + "(+) Balance is $" + balance + "\t\t\t\t" + transaction_Number);
			
			if(depposit_Amount > 350)
			{
				System.out.println();
				flagged_transaction(depposit_Amount, threadName, "Deposit");
			}
			
			//signals all waiting withdrawal threads waiting to make a withdrawal 
			sufficentFCondition.signalAll();
			
		}//end try block
		catch(Exception e)
		{
			System.out.print("Exception thrown depositing");
		}//end catch block
		finally
		{
			accLock.unlock();//unlock the bank account
		}//end finally block
	}//end method deposit
	
	
	
	//method used to make a withdrawal from the bank account
	public void withdrawal(int with_$_Amount, String threadName)
	{
		//locks the bank account
		accLock.lock();
		//waits for auditor to complete
		//outputs the thread information, withdrawal amount, and account balance. Block on insufficient funds
		try
		{
			//checks balance
			//if balance is too low
			//it will block for deposit to occur
			if(balance < with_$_Amount)
			{
				System.out.println("\t\t\t\t" + threadName + " withdraws $" + with_$_Amount + "\t\t(******) WITHDRAWAL BLOCKED - INSUFFICENT FUNDS!!!");
				sufficentFCondition.await();
			}
			//else
			//make withdrawal
			//handle transaction logging for flagged transaction
			else
			{
				balance -= with_$_Amount;
				transaction_Number += 1;
				System.out.println("\t\t\t\t" + threadName + " withdraws $" + with_$_Amount + "\t\t(-) Balance is $" + balance + "\t\t\t\t" + transaction_Number);
				if(with_$_Amount> 75)
				{
					System.out.println();
					flagged_transaction(with_$_Amount, threadName, "Withdrawal");
				}
			}

		} //end try block
		catch(Exception e)
			{System.out.println("An Exception was thrown getting the withdrawal.");}
		finally
		{
			accLock.unlock();//unlock the bank account
		}//end finally block
		}//end method internalAudit
	
	public int getBal()
	{
		return balance;
	}
	
	public int getTransNum()
	{
		return transaction_Number;
	}


	@Override
	public void internalAudit() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void treasuryAudit() {
		// TODO Auto-generated method stub
		
	}
	

	
	
}//end class TheBankAccount
	

