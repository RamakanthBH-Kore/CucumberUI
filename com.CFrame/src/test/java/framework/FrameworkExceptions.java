package framework;


/**
 * @Class: FrameworkException
 * @Description: This class is to throw custom exception.It is an User-Defined Exceptions.
 *       
 * @throws Exception  with custom string.
 */
@SuppressWarnings("serial")
public class FrameworkExceptions extends Exception {
	
	// Parameterized constructor 
	public FrameworkExceptions(String str)
		{ 
		super(str);
		}

}
