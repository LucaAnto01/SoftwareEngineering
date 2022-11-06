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
	public static List<Worker> workerList = new ArrayList<Worker>();
	public static List<Product> productList = new ArrayList<Product>();
	public static int purchaseId = 0;
	private static Worker loggedInWorker;
	
	/**
	 * Method to execute the management action, in accord with selected mode
	 */
	public static void management()
	{
		int enMode = -1;
		boolean terminate = false;
		
		try
		{
			while(!terminate)
			{
				System.out.println(loggedInWorker.getFunctionalities());
				
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
				
				else
					loggedInWorker.doFunctionality(enMode);
			}
		}
				
		catch (Exception e)
        {
			System.out.println("ERROR: input char");
			e.printStackTrace();
        }
	}
	
	/**
	 * Method to execute the login
	 */
	public static void doLogin()
	{
		String username = "";
		String password = "";
		boolean success = false;
		
		try
		{
			while(!success)
			{
				//Main.sc.nextLine();
				System.out.print("Insert your username: ");
				username = Main.sc.next();
				Main.sc.nextLine();
				System.out.print("Insert password for " + username + ": ");
				password = Main.sc.nextLine();
				
				if(checkWorker(username, password))
				{
					success = true;
					System.out.println("Login successfully! Welcome " + loggedInWorker.getName());
				}
				
				else
					System.out.println("Invalid username or password, please, retry.");
			}	
		}
				
		catch (Exception e)
        {
			System.out.println("ERROR: string input");
			e.printStackTrace();
        }
	}
	
	/**
	 * Method to check the value of username and password for login
	 * @param username
	 * @param password
	 * @return
	 */
	private static boolean checkWorker(String username, String password)
	{
		boolean exist = false;
		
		try
		{
			for (Worker currentWorker : workerList)
			{
				if((currentWorker.getUsername().equals(username)) && (currentWorker.getPassword().equals(password)))
				{
					exist = true;
					loggedInWorker = currentWorker;
					break;
				}
			}
		}
		
		catch (Exception e)
        {
			System.out.println("ERROR: check worker");
			e.printStackTrace();
        }
		
		return exist;
	}

	public static void main(String[] args) 
	{
		char enMode = 'X';
		boolean terminate = false;
		
		/*Test attributes*/
		Product product1 = new Product("Pen", "Stabilo", 0.99);
		Product product2 = new Product("Screen", "LG", 150.99);
		productList.add(product1);
		productList.add(product2);
		Client client1 = new Client("Pippo", "surnamepippo", "123pipposur", "Mountain street 11");
		Purchase purchase1 = new Purchase(purchaseId, productList.get(0), 11.11, LocalDateTime.now());
		purchaseId++;
		Purchase purchase2 = new Purchase(purchaseId, productList.get(1), 500000.11, LocalDateTime.now());
		purchaseId++;
		client1.insertNewPurchase(purchase1);
		client1.insertNewPurchase(purchase2);
		Client client2 = new Client("Pluto", "surnamepluto", "456plutosur", "Sea street 11");
		clientList.add(client1);
		clientList.add(client2);
		
		Employee employee = new Employee("emp_paperino", "paperino", "Paperino", "Dei Paperi");
		Administrator administrator = new Administrator("admin_paperone", "apaperone", "Paperone", "De Paperoni");
		workerList.add(employee);
		workerList.add(administrator);
		
		
		System.out.println("SIMPLE SALES MANAGEMENT");
		
		while(!terminate)
		{		
			try
			{
				doLogin();				
				System.out.print("Insert X to terminate the program or insert S to see your command palettes: ");
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
			
			else if((enMode == 'S') || (enMode == 's'))
				management();
			
			else
				System.out.println("Please, enter a valid execution parameter");
					
		}
		
		sc.close();		
		System.out.println("End of programm");
		System.exit(0);
	}

}
