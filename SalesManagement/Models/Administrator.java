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
		functionalities += "9: cancel purchase\n";
		functionalities += "10: create new Employee\n";
		functionalities += "11: edit Employee username or password\n";
		functionalities += "12: delete Employee\n";
		
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
		if(fnc <= 8) //Do employee functionality
			super.doFunctionality(fnc);
		
		else
		{
			switch(fnc)
			{
				case 9: {
					purchaseCancellation();
				}
					break;
				
				case 10: {
					if(ceateEmployee())
						System.out.println("Employee successfully created!");
					else
						System.out.println("Error during creation!"); 
				}
					break;
				
				case 11: {
					if(editEmployee())
						System.out.println("Employee successfully edit!");
					else
						System.out.println("Error during edit!"); 
				}
					break;
					
				case 12: {
					if(deleteEmployee())
						System.out.println("Employee successfully deleted!");
					else
						System.out.println("Error during delete!"); 
				}
					break;
				
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
	
	/**
	 * Method to edit username or password of a specific Employee
	 * @return
	 */
	private boolean editEmployee()
	{
		char enMode = 'X';
		String usernameIn = "";
		
		try
		{
			System.out.print("Insert the username of the Employee to edit: ");
			usernameIn =  Main.sc.next();
			
			if(checkSelectedEmployee(usernameIn))
			{
				String newValue = "";
				
				System.out.print("Insert U to edit Employee username, P to edit Employee password or R to reset password to CHANGE value: ");
				enMode = Main.sc.next().charAt(0);
				
				for (Worker currentEmployee : Main.workerList)
				{
					if(currentEmployee instanceof Employee)
					{
						if(currentEmployee.getUsername().equals(usernameIn))
						{
							if((enMode == 'U') || (enMode == 'u'))
							{
								Main.sc.nextLine();
								System.out.print("Insert new username value: ");
								newValue =  Main.sc.nextLine();
								currentEmployee.setUsername(newValue);
								
								return true;
							}
							
							else if((enMode == 'P') || (enMode == 'p'))
							{
								Main.sc.nextLine();
								System.out.print("Insert new passowrd value: ");
								newValue =  Main.sc.nextLine();
								currentEmployee.setPassword(newValue);
								
								return true;
							}
							
							else if((enMode == 'R') || (enMode == 'r'))
							{
								currentEmployee.setPassword("CHANGE");								
								return true;
							}
						}								
					}
				}
			}
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR: edit Employee username or password");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to delete a specific Employee
	 * @return
	 */
	private boolean deleteEmployee()
	{
		String usernameIn = "";
		Employee employeeToDelete = new Employee();
		
		try
		{
			System.out.print("Insert the username of the Employee to edit: ");
			usernameIn =  Main.sc.next();
			
			if(checkSelectedEmployee(usernameIn))
			{
				
				for (Worker currentEmployee : Main.workerList) //Find Worker to delete
					if(currentEmployee instanceof Employee)
						if(currentEmployee.getUsername().equals(usernameIn))
							employeeToDelete = (Employee) currentEmployee;		
				
				if(!employeeToDelete.getUsername().equals("")) //Check if object has value
				{
					Main.workerList.remove(employeeToDelete);
					return true;
				}									
			}
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR: edit Employee username or password");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to check the existence a specific Employee search for username
	 * @param username
	 * @return
	 */
	private boolean checkSelectedEmployee(String username)
	{
		boolean exist = false;
		
		for (Worker currentEmployee : Main.workerList)
		{
			if(currentEmployee instanceof Employee) //Check if the Worker is a Employee
			{
				if(currentEmployee.getUsername().equals(username)) //Check username
				{
					exist = true;
					break;
				}								
			}
		}
		
		return exist;
	}
}
