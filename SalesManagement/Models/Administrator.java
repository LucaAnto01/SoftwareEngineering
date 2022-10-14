/**
 * 
 */

/**
 * @author Luca Antognarelli
 *
 */
public class Administrator extends Employee 
{
	/*----- ATTRIBUTES -----*/
	private int cancelledPurchases;
	
	/**
	 * Constructor method
	 * @param username
	 * @param password
	 * @param name
	 * @param surname
	 * @param registeredClients
	 * @param registeredPayments
	 * @param cancelledPurchases
	 */
	public Administrator(String username, String password, String name, String surname, int registeredClients, int registeredPayments, int cancelledPurchases) 
	{
		super(username, password, name, surname, registeredClients, registeredPayments);
		this.cancelledPurchases = cancelledPurchases;
	}

	/**
	 * Constructor method
	 * @param username
	 * @param username
	 * @param password
	 * @param name
	 * @param surname
	 */
	public Administrator(String username, String password, String name, String surname) 
	{
		super(username, password, name, surname);
		this.cancelledPurchases = 0;
	}

	/**
	 * Empty constructor method
	 */
	public Administrator() {}

	/**
	 * @return the cancelledPurchases
	 */
	public int getCancelledPurchases() {
		return cancelledPurchases;
	}	

	/*----- GETTERS & SETTERS -----*/
	/**
	 * @param cancelledPurchases the cancelledPurchases to set
	 */
	public void setCancelledPurchases(int cancelledPurchases) 
	{
		this.cancelledPurchases = cancelledPurchases;
	}
	
	/*----- METHODS -----*/
	/**
	 *  Method to get the list of functions that Administrator can do
	 * @return
	 */
	@Override
	public String getFunctionalities() 
	{
		String functionalities = super.getFunctionalities();
		functionalities += "7: cancel purchase\n";
		functionalities += "8: create new Employee\n";
		functionalities += "9: edit Employee username or password\n";
		
		return functionalities;
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
		if(fnc <= 6) //Do employee functionality
			super.doFunctionality(fnc);
		
		else
		{
			switch(fnc)
			{
				case 7: {
					purchaseCancellation();
				}
					break;
				
				case 8: {
					ceateEmployee();
				}
				
				default: System.out.println("Insert only valid value!"); 
			}
		}			
	}
	
	/**
	 * Method to delete a determinate purchase of client
	 */
	private void purchaseCancellation()
	{
		try
		{
			/*Select client*/
			String taxCode = "";
			System.out.print("Insert client taxCode: "); 
			taxCode = Main.sc.next();
			
			Client selectedClient = super.getSelectedClient(taxCode);
			
			if(selectedClient != null)
			{
				selectedClient.printPurchases();
				
				if(!selectedClient.getPurchasesList().isEmpty())
				{
					/*Select client purchase*/
					int idPurchase = -1;							
					
					System.out.print("Select purchase id to remove: ");
					idPurchase = Main.sc.nextInt();
					
					Purchase selectedPurchase = selectedClient.getSelectedPurchase(idPurchase);
					
					if(selectedPurchase != null)
					{
						if(selectedClient.removePurchase(selectedPurchase))
						{
							System.out.println("Purchase: " + selectedPurchase.toString() + " successfully removed!");
							cancelledPurchases++; //Increment the number of cancelled purchases
							makeRepayment(); //Execute repayments
						}
						
						else
							System.out.println("Impossible to remove!");
					}
									
					else
						System.out.println("Invalid purchase id!");
				}
				
			}
			
			else
				System.out.println("Invalid client tax code!");
		}
				
		catch(Exception e)
		{
			System.out.println("ERROR: edit client");
			e.printStackTrace();
		}
	}

	/**
	 * Method to do a repayments
	 */
	private void makeRepayment()
	{
		System.out.println("Repayment succefully execute!");
	}
	
	/**
	 * Method to create a new Employee
	 * @return
	 */
	private boolean ceateEmployee()
	{
		String tmpIn = "";
		
		try
		{
			Employee newEmployee = new Employee();
			
			//Insert username
			System.out.print("Insert employee username: "); 
			tmpIn = Main.sc.next();
			newEmployee.setUsername(tmpIn);
			
			//Insert password
			System.out.print("Insert employee password: "); 
			tmpIn = Main.sc.next();
			newEmployee.setPassword(tmpIn);
			
			//Insert name
			System.out.print("Insert employee name: "); 
			tmpIn = Main.sc.next();
			newEmployee.setName(tmpIn);
			
			//Insert surname
			System.out.print("Insert employee surname: "); 
			tmpIn = Main.sc.next();
			newEmployee.setSurname(tmpIn);
			
			//Adding new Employee to the worker list
			Main.workerList.add(newEmployee);
			
			return true;
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR: create Employee");
			e.printStackTrace();
		}
		
		return false;
	}
}
