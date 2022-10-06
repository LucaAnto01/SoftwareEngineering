import java.util.ArrayList;
import java.util.List;

/**
 * @author Luca Antognarelli
 *
 */
public class Client 
{
	/*----- ATTRIBUTES -----*/
	private String name;
	private String surname;
	private String taxCode;
	private String residence;
	private List<Purchase> purchasesList;  	

	/*----- CONSTRUCTOR METHODS -----*/
	/**
	 * Constructor method
	 * @param name
	 * @param surname
	 * @param taxCode
	 * @param residence
	 * @param purchasesList
	 */
	public Client(String name, String surname, String taxCode, String residence, List<Purchase> purchasesList) 
	{
		super();
		this.name = name;
		this.surname = surname;
		this.taxCode = taxCode;
		this.residence = residence;
		this.purchasesList = purchasesList;
	}


	/**
	 * Constructor method
	 * @param name
	 * @param surname
	 * @param taxCode
	 * @param residence
	 */
	public Client(String name, String surname, String taxCode, String residence) 
	{
		super();
		this.name = name;
		this.surname = surname;
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
	 * @return the name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname()
	{
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) 
	{
		this.surname = surname;
	}

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
		return "Client: name=" + name + ", surname=" + surname + ", taxCode=" + taxCode + ", residence=" + residence + "\n";
	}
}
