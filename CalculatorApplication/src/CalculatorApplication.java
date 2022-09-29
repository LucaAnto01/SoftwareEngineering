/**
 * @author Luca Antognarelli
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CalculatorApplication 
{

	/*Attributes*/
	public static Scanner sc= new Scanner(System.in).useLocale(Locale.US);
	
	/**
	 * Check if the value is valid --> > 0
	 * @param in
	 * @return
	 */
	public static boolean checkValue(double in)
	{
		if(in > 0)
			return true;
		
		return false;
	}
	
	/**
	 * Method to check file validity
	 * @param path
	 * @return
	 */
	public static boolean checkFile(String path)
	{
		File fileToCheck = new File(path);
		
		if(fileToCheck.exists() && fileToCheck.isFile())
			return true;
		
		return false;
	}
	
	/**
	 * Manual data input method
	 */
	public static void manualInputExecuteProgramm()
	{
		char operationSel = '+';
		double frstNumber = 0.0, scndNumber = 0.0, result = 0.0;
		boolean invalid = false; //Check the operation value
		
		System.out.println("Manual input mode");
		System.out.println("This is a simple calculator, you can do + - * / % operation between two numbers");
		System.out.println("Insert only positive value!");
		
		while(operationSel != 'X')
		{	
			invalid = false; 
			
			/*First number*/
			System.out.print("First number: ");
			
			try
			{
				frstNumber = sc.nextDouble();
				
				if(!checkValue(frstNumber))
				{
					System.out.println("Insert only positive value!");
					continue;
				}
					
			}
			
			catch (NumberFormatException e)
	        {
				System.out.println("ERROR: value " + frstNumber + " is not a double!");
	        }
			
			/*Second number*/
			System.out.print("Second number: ");
			
			try
			{
				scndNumber = sc.nextDouble();
				
				if(!checkValue(scndNumber))
				{
					System.out.println("Insert only positive value!");
					continue;
				}
			}
			
			catch (NumberFormatException e)
	        {
				System.out.println("ERROR: value " + scndNumber + " is not a double!");
	        }
			
			/*Operation*/
			System.out.println("INSERT X TO CLOSE THE PROGRAM");
			System.out.print("Operation: + - * / %: ");
			
			try
			{
				operationSel = sc.next().charAt(0);
			}
			
			catch (Exception e)
	        {
				System.out.println("ERROR: input char");
				e.printStackTrace();
	        }
			
			//Make calculation
			switch(operationSel)
			{
				case '+': result = frstNumber + scndNumber;
					break;
				case '-': result = frstNumber - scndNumber;
					break;
				case '*': result = frstNumber * scndNumber;
					break;
				case '/': result = frstNumber / scndNumber;
					break;
				case '%': result = frstNumber % scndNumber;
					break;
					
				default: invalid = true;
			}
			
			if((invalid) && (operationSel != 'X'))
				System.out.println("Not a defined operation");
			
			else if(operationSel != 'X')
				System.out.println("The result of the operation: " + frstNumber + operationSel + scndNumber + " = " + result);
				
		}
		
	}
	
	/**
	 * File data input method
	 */
	public static void fileInputExecuteProgramm()
	{
		String filePath = "./files/calculate.txt";
		double frstNumber = 0.0, scndNumber = 0.0;
		boolean invalid = false;

		System.out.println("File input mode");
		System.out.println("This is a simple calculator, you can do + - * / % operation between two numbers");
		System.out.println("Insert only positive value!");
		System.out.println("Each line represents a calculation. Use the following syntax: 10.33+10.11");
		
		while(!invalid)
		{
			try
			{
				System.out.print("Insert the path to the file containing the calculations to be performed (ex. ./files/calculate.txt): ");
				filePath = sc.next();
			}
			
			catch (Exception e)
	        {
				System.out.println("ERROR: input file path");
				e.printStackTrace();
	        }
			
			//File validity check
			if(checkFile(filePath))
				invalid = true;
			else
				System.out.println("Please, insert a existing file (not directory)");
		}
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
		{
		    String line;
		    
		    while ((line = br.readLine()) != null) 
		    {
		       System.out.print(line + "=");
		       
		       //Split reading line
		       String[] parts = line.split("[-+*/%]");
		       
		       
		       if(parts[1] != null && !parts[1].trim().isEmpty())//Check value
		       {
		    	   //Read numbers parse operation
		    	   frstNumber = Double.parseDouble(parts[0]);
		    	   scndNumber = Double.parseDouble(parts[1]);
		    	   
		    	   if(checkValue(frstNumber) && checkValue(scndNumber))
		    	   {
		    		   if(line.contains("+"))
				    	   System.out.print(frstNumber + scndNumber);
				       
				       
				       else if(line.contains("-"))
				    	   System.out.print(frstNumber - scndNumber);
				       
				       else if(line.contains("*"))
				    	   System.out.print(frstNumber * scndNumber);
				       
				       else if(line.contains("/"))
				    	   System.out.print(frstNumber / scndNumber);
				       
				       else if(line.contains("%"))
				    	   System.out.print(frstNumber % scndNumber);
		    		   
				       else
				    	   System.out.print("Please, insert only valid operation!"); 
		    	   }
		    	   
		    	   else
		    	   {
		    		   System.out.println("Please, insert valid values!");
		    	   }
		    	   		    	   			       			      
			       System.out.println();
		       }		       		      	
		       
		    }
		}
		
		catch (IOException e)
		{
			System.out.println("ERROR: read file");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		char enMode = 'X';
		boolean invalid = false;
		
		System.out.println("SIMPLE CALCULATOR");
		System.out.println("(Use . to separate integer and decimal parts)");		
		
		while(!invalid)
		{		
			try
			{
				System.out.print("Choose data entry mode: M for manual, F for file: ");
				enMode = sc.next().charAt(0);	
			}
			
			catch (Exception e)
	        {
				System.out.println("ERROR: input char");
				e.printStackTrace();
	        }
			
			//Choice of calculation mode
			if((enMode == 'M') || (enMode == 'm'))
			{
				invalid = true;
				manualInputExecuteProgramm();
			}
				
			
			else if((enMode == 'F') || (enMode == 'f'))
			{
				invalid = true;
				fileInputExecuteProgramm();
			}
				
			
			else
				System.out.println("Please, enter a valid execution parameter");
					
		}
		
		sc.close();
		
		System.out.println("End of programm");
		System.exit(0);
	}

}
