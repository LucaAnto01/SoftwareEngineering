import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Luca Antognarelli
 *
 */
public class Main 
{
	/*----- ATTRIBUTES -----*/
	public static Scanner sc = new Scanner(System.in).useLocale(Locale.US);
	public static List<Client> clientList = new ArrayList<Client>();
	public static int purchaseId = 0;
	private static Employee employee = new Employee("Employee pippo", "Pippo", "Pluto");
	private static Administrator administrator = new Administrator("Employee pippo", "Pippo", "Pluto");
	
	/**
	 * Method to execute the management action, in accord with selected mode
	 */
	public static void management(char inMode)
	{
		int enMode = -1;
		boolean terminate = false;
		
		while(!terminate)
		{
			if((inMode == 'E') || (inMode == 'e'))
				System.out.println(employee.getFunctionalities());
			
			else if((inMode == 'A') || (inMode == 'a'))
				System.out.println(administrator.getFunctionalities());
			
			try
			{
				System.out.print("Select action: ");
				enMode = sc.nextInt();
			}
			
			catch (Exception e)
	        {
				System.out.println("ERROR: input char");
				e.printStackTrace();
	        }
			
			if(enMode == 0)
				terminate = true;
			
			else if((inMode == 'E') || (inMode == 'e'))
				employee.doFunctionality(enMode);
		
			else if((inMode == 'A') || (inMode == 'a'))
				administrator.doFunctionality(enMode);
		}
	}
	
	public static void administratorManagement()
	{
		
	}

	public static void main(String[] args) 
	{
		char enMode = 'X';
		boolean terminate = false;
		
		/*Test attributes*/
		Client client1 = new Client("Pippo", "surnamepippo", "123pipposur", "Mountain street 11");
		Purchase purchase1 = new Purchase(purchaseId, "Key", "Product key", 11.11, LocalDateTime.now());
		purchaseId++;
		Purchase purchase2 = new Purchase(purchaseId, "Car", "Ferrari", 500000.11, LocalDateTime.now());
		purchaseId++;
		client1.insertNewPurchase(purchase1);
		client1.insertNewPurchase(purchase2);
		Client client2 = new Client("Pluto", "surnamepluto", "456plutosur", "Sea street 11");
		clientList.add(client1);
		clientList.add(client2);
		
		System.out.println("SIMPLE SALES MANAGEMENT");
		
		while(!terminate)
		{		
			try
			{
				System.out.println("Insert X to terminate the program");
				System.out.print("Choose management mode: E for employee, A for administrator: ");
				enMode = sc.next().charAt(0);	
			}
			
			catch (Exception e)
	        {
				System.out.println("ERROR: input char");
				e.printStackTrace();
	        }
			
			//Terminate the program
			if((enMode == 'X') || (enMode == 'x'))
				terminate = true;				
				
			//Employee is selected
			else if((enMode == 'E') || (enMode == 'e') || (enMode == 'A') || (enMode == 'a'))
				management(enMode);
			
			else
				System.out.println("Please, enter a valid execution parameter");
					
		}
		
		sc.close();		
		System.out.println("End of programm");
		System.exit(0);
	}

}
