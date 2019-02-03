/**
 * InvalidMenuChoiceException.java
 * @author alicebaker
 * Last edited: Fri 1st Feb 2019
 * Description: This class returns an error message when an InvalidMenuChoiceException is thrown
 */

package word.game;

public class InvalidMenuChoiceException extends Exception 
{

    public InvalidMenuChoiceException() 
    {
        super("\n\tOops! That's not an available option. Please try again. ");
    }

    public InvalidMenuChoiceException(String msg) 
    {
        super(msg);
    }
}
