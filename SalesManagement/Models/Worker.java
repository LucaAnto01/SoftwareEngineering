/**
 * @author Luca Antognarelli
 *
 */
public abstract class Worker 
{
	/*----- ATTRIBUTES -----*/
	private String username;
	private String name;
	private String surname;
	
	/*----- CONSTRUCTOR METHODS -----*/
	/**
	 * Constructor method
	 * @param username
	 * @param name
	 * @param surname
	 */
	protected Worker(String username, String name, String surname) 
	{
		this.username = username;
		this.name = name;
		this.surname = surname;
	}
	
	/**
	 * Empty constructor method
	 */
	protected Worker() {};
	
	/*----- GETTERS & SETTERS -----*/
	/**
	 * @return the username
	 */
	public String getUsername() 
	{
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) 
	{
		this.username = username;
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
	
	/*----- METHODS -----*/
	/**
	 * Method to get the list of functions that the worker can do
	 * --> different between administrator and employee
	 * @return
	 */
	public abstract String getFunctionalities();
	
	/**
	 * Method that to execute a given functionality
	 * --> different between administrator and employee
	 * --> based on id fnc
	 * --> default false, logout
	 * @param fnc
	 */
	public abstract void doFunctionality(int fnc);
}
