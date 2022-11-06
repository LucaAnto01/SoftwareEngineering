import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Luca Antognarelli
 *
 */
public class Employee extends Worker 
{

	/*----- ATTRIBUTES -----*/
	private int registeredClients;
	private int registeredPayments;

	/**
	 * @return the registeredPayments
	 */
	public int getRegisteredPayments() {
		return registeredPayments;
	}

	/**
	 * @param registeredPayments the registeredPayments to set
	 */
	public void setRegisteredPayments(int registeredPayments) {
		this.registeredPayments = registeredPayments;
	}

	/*----- CONSTRUCTOR METHODS -----*/
	/**
	 * Constructor method
	 * @param username
	 * @param password
	 * @param name
	 * @param surname
	 * @param registeredClients
	 * @param registeredPayments
	 */
	public Employee(String username, String password, String name, String surname, int registeredClients, int registeredPayments) 
	{
		super(username, password, name, surname);
		this.registeredClients = registeredClients;
		this.registeredPayments = registeredPayments;
	}
	
	/**
	 * Constructor method
	 * @param username
	 * @param password
	 * @param name
	 * @param surname
	 */
	public Employee(String username, String password, String name, String surname) 
	{
		super(username, password, name, surname);
		this.registeredClients = 0;
		this.registeredPayments = 0;
	}
	
	/**
	 * Empty constructor method
	 */
	public Employee() {}	
	
	/*----- GETTERS & SETTERS -----*/
	/**
	 * @return the registeredClients
	 */
	public int getRegisteredClients() 
	{
		return registeredClients;
	}

	/**
	 * @param registeredClients the registeredClients to set
	 */
	public void setRegisteredClients(int registeredClients) 
	{
		this.registeredClients = registeredClients;
	}

	/*----- METHODS -----*/
	/**
	 *  Method to get the list of functions that Employee can do
	 * @return
	 */
	@Override
	public String getFunctionalities() 
	{
		String functionalities = "Please, select what you want to do:\n";

		functionalities += "0: logout\n";
		functionalities += "1: edit password\n";
		functionalities += "2: add new product\n";
		functionalities += "3: show product list\n";
		functionalities += "4: add new client\n";
		functionalities += "5: edit client\n";
		functionalities += "6: show client purchases\n";
		functionalities += "7: add new client purchase and payment\n";
		functionalities += "8: show clients list\n";
		
		return functionalities;
	}
	
	/**
	 * Method to get a specific Client based on taxCode
	 * @param taxCode
	 * @return
	 */
	protected Client getSelectedClient(String taxCode)
	{
		for (Client currentClient : Main.clientList) //Select client
			if(taxCode.equals(currentClient.getTaxCode())) //Get selected client
				return currentClient;
				
		return null;	
	}

	/**
	 * Method that to execute a given functionality
	 * --> based on id fnc
	 * --> default false, logout
	 * @param fnc
	 */
	@Override
	public void doFunctionality(int fnc) 
	{
		switch(fnc)
		{
			//Manage case 0 from client to terminate while
			/*case 0: {
				System.out.println("Logout effettuato con successo!");
				try 
				{
					Thread.sleep(1500);
				} 
				catch (InterruptedException e) 
				{
					System.out.println("ERROR: sleep shell");
					e.printStackTrace();
				}
				
				System.exit(0); //Close application
			}
				break;*/
			
			case 1: {
				editPassword();
			}
				break;
			
			case 2: {
				if(addNewProduct())
				{
					System.out.println("Product successfully added!"); 
					registeredClients++;
				}
					
				else
					System.out.println("Error during insertion!");
			}
				break;
				
			case 3: {
				showProductList();
			}
				break;
			
			case 4: {
				if(addNewClient())
				{
					System.out.println("Client successfully added!"); 
					registeredClients++;
				}
					
				else
					System.out.println("Error during insertion!"); 
			}
				break;
				
			case 5: {
				if(editClient())
					System.out.println("Client successfully modified!"); 
				else
					System.out.println("Error during editing, select valid client tax code!"); 
			}
				break;
				
			case 6: {
				showClientPurchases();
			}
				break;
				
			case 7: {
				addPurchaseAndPayment();
			}
				break;
				
			case 8: {
				showClientsList();
			}			
				break;
			
			default: System.out.println("Insert only valid value!"); 
		}

	}
	
	/**
	 * Method to edit worker password
	 */
	protected void editPassword()
	{
		String newPassword = "";
		
		try
		{
			Main.sc.nextLine();
			System.out.print("Insert new password: "); 
			newPassword = Main.sc.nextLine();
			setPassword(newPassword);
			
			System.out.println("Password successluy updated");
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR: update password");
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to add a new product
	 * @return
	 */
	protected boolean addNewProduct()
	{
		try
		{
			String tmpIn = "";
			Product newProd = new Product();
			double cost = -1.1;
			
			//Insert name
			System.out.print("Insert product name: "); 
			tmpIn = Main.sc.next();
			newProd.setName(tmpIn);
			
			//Insert manufacturer
			System.out.print("Insert product manufacturer: "); 
			tmpIn = Main.sc.next();
			newProd.setManufacturer(tmpIn);
			
			//Insert cost
			System.out.print("Insert product cost: "); 
			cost = Main.sc.nextDouble();
			newProd.setCost(cost);
			
			Main.productList.add(newProd);
			
			return true;
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR: add client");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to print the products' list
	 */
	protected void showProductList()
	{
		if(!Main.productList.isEmpty())
		{
			System.out.println("List of products:");
			for (Product currentProduct : Main.productList)
				System.out.println("\t" + currentProduct.toString());				
		}
		
		else
			System.out.println("Product list is empty");
	}
	
	/**
	 * Method to add a new client
	 * @return
	 */
	protected boolean addNewClient()
	{
		try
		{
			String tmpIn = "";
			Client newClient = new Client();
			
			//Insert name
			System.out.print("Insert client name: "); 
			tmpIn = Main.sc.next();
			newClient.setName(tmpIn);
			
			//Insert surname
			System.out.print("Insert client surname: "); 
			tmpIn = Main.sc.next();
			newClient.setSurname(tmpIn);
			
			//Insert taxCode
			System.out.print("Insert client tax code: "); 
			tmpIn = Main.sc.next();
			newClient.setTaxCode(tmpIn);
						
			//Insert residence
			Main.sc.nextLine();
			System.out.print("Insert clientresidence: "); 
			tmpIn = Main.sc.nextLine();
			newClient.setResidence(tmpIn);
			
			newClient.setPurchasesList(new ArrayList<Purchase>());
			Main.clientList.add(newClient);
			
			return true;
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR: add client");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to edit a specific Client
	 * @return
	 */
	protected boolean editClient()
	{
		try
		{
			String taxCode = "";
			System.out.print("Insert client taxCode: "); 
			taxCode = Main.sc.next();
			
			Client selectedClient = getSelectedClient(taxCode);
			
			if(selectedClient != null)
			{
				int action = -1;
				
				System.out.println("Choose what to edit:"); 
				System.out.println("1: name"); 
				System.out.println("2: surname");
				System.out.println("3: taxCode");
				System.out.println("4: residence");
				
				System.out.print("Please, select what you want to do: "); 
				action = Main.sc.nextInt();
				
				String tmp = "";
				
				switch(action)
				{
					case 1: {
						System.out.print("Insert new name for client: "); 
						tmp = Main.sc.next();
						getSelectedClient(taxCode).setName(tmp);
						return true;
					}
					
					case 2: {
						System.out.print("Insert new surname for client: "); 
						tmp = Main.sc.next();
						getSelectedClient(taxCode).setSurname(tmp);
						return true;
					}
					
					case 3: {
						System.out.print("Insert new taxCode for client: "); 
						tmp = Main.sc.next();
						getSelectedClient(taxCode).setTaxCode(tmp);
						return true;
					}
					
					case 4: {
						System.out.print("Insert new residence for client: "); 
						tmp = Main.sc.next();
						getSelectedClient(taxCode).setResidence(tmp);
						return true;
					}
					
					default: {
						System.out.println("Invalid value!"); 
						return false;
					}
				}
			}
			
			else
				return false;
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR: edit client");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to show purchases of a specific Client
	 */
	protected void showClientPurchases()
	{
		try
		{
			String taxCode = "";
			System.out.print("Insert client taxCode: "); 
			taxCode = Main.sc.next();
			
			Client selectedClient = getSelectedClient(taxCode);
			
			if(selectedClient != null)
				selectedClient.printPurchases();
			
			else
				System.out.println("Error during editing, select valid client tax code!"); 
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR: show client purchases");
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to add purchase and payment for specific Client
	 */
	protected void addPurchaseAndPayment()
	{
		try
		{
			String taxCode = "";			
			System.out.print("Insert client taxCode: "); 
			taxCode = Main.sc.next();
			
			Client selectedClient = getSelectedClient(taxCode);
			
			if(selectedClient != null)
			{
				Product newProd = new Product();
				String tmpIn = "";
				double amount = -1.1;
				double cost = -1.1;
				
				Main.sc.nextLine();
				System.out.print("Insert product name: "); 
				tmpIn = Main.sc.nextLine();
				newProd.setName(tmpIn);
				
				System.out.print("Insert product manufacturer: "); 
				tmpIn = Main.sc.nextLine();
				newProd.setManufacturer(taxCode);
				
				System.out.print("Insert product cost: "); 
				cost = Main.sc.nextDouble();
				newProd.setCost(cost);
				
				System.out.print("Insert total amount: "); 
				amount = Main.sc.nextDouble();
				
				Purchase newPurchase = new Purchase(Main.purchaseId, newProd, amount, LocalDateTime.now());
				Main.purchaseId++; //Increment id
				
				getSelectedClient(taxCode).insertNewPurchase(newPurchase);
			}
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR: edit client");
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to show Clients list
	 */
	protected void showClientsList()
	{
		for(Client currentClient : Main.clientList)
		{
			System.out.print(currentClient.toString());
			currentClient.printPurchases();
		}
	}

}
