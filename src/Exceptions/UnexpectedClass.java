package Exceptions;

/**
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */

public class UnexpectedClass extends Exception
{
	public UnexpectedClass()
	{
		
	}
	
	public UnexpectedClass(String message)
	{
		super(message);
	}
}
