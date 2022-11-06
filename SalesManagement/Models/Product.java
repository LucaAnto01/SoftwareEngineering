/**
 * @author Luca Antognarelli
 *
 */

public class Product 
{

	/*----- ATTRIBUTES -----*/
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
		this.name = name;
		this.manufacturer = manufacturer;
		this.cost = cost;
	}

	/**
	 * Empty constructor method
	 */
	public Product() {}

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

	/**
	 * Method toString() of product
	 */
	@Override
	public String toString() {
		return "Product [name=" + name + ", manufacturer=" + manufacturer + ", cost=" + cost + "]";
	}
}
