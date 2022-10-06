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
	 * @param name
	 * @param surname
	 * @param registeredClients
	 * @param registeredPayments
	 * @param cancelledPurchases
	 */
	public Administrator(String username, String name, String surname, int registeredClients, int registeredPayments, int cancelledPurchases) 
	{
		super(username, name, surname, registeredClients, registeredPayments);
		this.cancelledPurchases = cancelledPurchases;
	}

	/**
	 * Constructor method
	 * @param username
	 * @param name
	 * @param surname
	 */
	public Administrator(String username, String name, String surname) 
	{
		super(username, name, surname);
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
		functionalities += "6: cancel purchase\n";
		
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
		if(fnc <= 5) //Do employee functionality
			super.doFunctionality(fnc);
		
		else if(fnc == 6)
			purchaseCancellation();
			
		else
			System.out.println("Insert only valid value!"); 
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
}
