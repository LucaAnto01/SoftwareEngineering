import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luca Antognarelli
 *
 */
public class Client extends User
{
	/*----- ATTRIBUTES -----*/
	private String taxCode;
	private String residence;
	private List<Purchase> purchasesList;  	

	/*----- CONSTRUCTOR METHODS -----*/
	/**
	 * Constructor method
	 * @param username
	 * @param password
	 * @param name
	 * @param surname
	 * @param taxCode
	 * @param residence
	 * @param purchasesList
	 */
	public Client(String username, String password, String name, String surname, String taxCode, String residence, List<Purchase> purchasesList) 
	{
		super(username, password, name, surname);
		this.taxCode = taxCode;
		this.residence = residence;
		this.purchasesList = purchasesList;
	}

	/**
	 * Constructor method
	 * @param username
	 * @param password
	 * @param name
	 * @param surname
	 * @param taxCode
	 * @param residence
	 */
	public Client(String username, String password, String name, String surname, String taxCode, String residence) 
	{
		super(username, password, name, surname);
		this.taxCode = taxCode;
		this.residence = residence;
		this.purchasesList = new ArrayList<Purchase>();
	}
	
	/**
	 * Empty constructor method
	 */
	public Client() {}


	/*----- GETTERS & SETTERS -----*/
	/**
	 * @return the taxCode
	 */
	public String getTaxCode() 
	{
		return taxCode;
	}

	/**
	 * @param taxCode the taxCode to set
	 */
	public void setTaxCode(String taxCode) 
	{
		this.taxCode = taxCode;
	}

	/**
	 * @return the residence
	 */
	public String getResidence() 
	{
		return residence;
	}

	/**
	 * @param residence the residence to set
	 */
	public void setResidence(String residence) 
	{
		this.residence = residence;
	}


	/**
	 * @return the purchaseList
	 */
	public List<Purchase> getPurchasesList()
	{
		return purchasesList;
	}


	/**
	 * @param purchaseList the purchaseList to set
	 */
	public void setPurchasesList(List<Purchase> purchasesList) 
	{
		this.purchasesList = purchasesList;
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
		functionalities += "1: show products\n";
		functionalities += "2: buy product\n";
		
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
		switch(fnc)
		{
			//Manage case 0 from client to terminate while
			
			case 1: 
				showProducts();	
				break;
			
			case 2: makePurchase();
				break;
			
			default: System.out.println("Insert only valid value!"); 
		}

	}
	
	/**
	 * Method to get a specific Product based on id
	 * @param id
	 * @return
	 */
	protected Product getSelectedProduct(int id)
	{
		if(!Main.productList.isEmpty())
		{
			for(Product currentProduct : Main.productList)
			{
				if(currentProduct.getId() == id)
					return currentProduct;
			}
		}
		
		return null;
	}
	
	/**
	 * Method to show products
	 */
	protected void showProducts()
	{
		if(!Main.productList.isEmpty())
		{
			for(Product currentProduct: Main.productList)
				System.out.println(currentProduct.toString());
		}
		
		else
			System.out.println("No products available");
	}
	
	/**
	 * Method to make a purchase
	 */
	protected void makePurchase()
	{
		int id = -1;
		System.out.print("Insert product id: "); 
		id = Main.sc.nextInt();
						
		Product selectedProduct = getSelectedProduct(id);
		
		if(selectedProduct != null)
		{
			double amount = -1.1;

			Main.sc.nextLine();			
			System.out.print("Insert total amount: "); 
			amount = Main.sc.nextDouble();
			
			Purchase newPurchase = new Purchase(Main.purchaseId, selectedProduct, amount, LocalDateTime.now());
			Main.purchaseId++; //Increment id
			
			insertNewPurchase(newPurchase);
		}
		
		else
			System.out.println("Invalid product id!");
	}
	
	/**
	 * Method to get a specific Purchase in accord with a specific id
	 * @param id
	 * @return
	 */
	public Purchase getSelectedPurchase(int id)
	{
		if(!getPurchasesList().isEmpty())
		{
			for (Purchase currentPurchase : getPurchasesList()) 
			{
				if(currentPurchase.id() == id)
					return currentPurchase;
			}
		}
		
		return null;			
	}
	
	/**
	 * Method to add a new Purchase to purchasesList
	 * @param purchase
	 */
	public void insertNewPurchase(Purchase purchase)
	{
		try
		{
			if(this.purchasesList == null) //Check the list
				this.purchasesList = new ArrayList<Purchase>();
				
			this.purchasesList.add(purchase);
			System.out.println("New purchase: " + purchase.toString() + " successfully added!");
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR: insert new purchase");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to remove Purchase from purchasesList
	 * @param purchase
	 * @return
	 */
	public boolean removePurchase(Purchase purchase)
	{
		try
		{
			if(!this.purchasesList.isEmpty()) //Check the list
			{
				if(this.purchasesList.remove(purchase))
					return true;
				
				else
					return false;
			}
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR: remove purchase");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Method to print all purchases of client
	 */
	public void printPurchases()
	{
		if(!getPurchasesList().isEmpty())
		{
			System.out.println("List of purchases for client " + getTaxCode() + ":");
			for (Purchase currentPurchase : getPurchasesList()) 
				System.out.println("\t" + currentPurchase.toString());
				
		}
		
		else
			System.out.println("Client with " + getTaxCode() + " tax code didn't make any purchases");
	}


	/**
	 * ToString method
	 */
	@Override
	public String toString() 
	{
		return "Client: " + super.toString() + ", taxCode=" + taxCode + ", residence=" + residence + "\n";
	}
}
