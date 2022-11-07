/**
 * @author Luca Antognarelli
 *
 */

public class Product 
{	
	private static int idCounter = 0;
	
	/*----- ATTRIBUTES -----*/
	private int id;
	private String name;
	private String manufacturer;
	private double cost;	
	
	/**
	 * Constructor method
	 * @param name
	 * @param manufacturer
	 * @param cost
	 */
	public Product(String name, String manufacturer, double cost) 
	{
		super();
		this.id = idCounter;
		idCounter++;
		this.name = name;
		this.manufacturer = manufacturer;
		this.cost = cost;
	}

	/**
	 * Empty constructor method
	 */
	public Product() 
	{
		this.id = idCounter;
		idCounter++;
	}

	/*----- GETTERS & SETTERS -----*/
	/**
	 * @return the id
	 */
	public int getId() 
	{
		return id;
	}
	
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
	 * @return the manufacturer
	 */
	public String getManufacturer() 
	{
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) 
	{
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the cost
	 */
	public double getCost() 
	{
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) 
	{
		this.cost = cost;
	}

	/*----- METHODS -----*/
	/**
	 * Method toString() of product
	 */
	@Override
	public String toString() {
		return "Product [name=" + name + ", manufacturer=" + manufacturer + ", cost=" + cost + "]";
	}
}
