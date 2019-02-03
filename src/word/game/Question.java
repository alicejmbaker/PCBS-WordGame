/**
 * Question.java
 * @author alicebaker
 * Last edited: Sun 3rd Feb 2019
 * Description: This class represents a quiz question
 */
package word.game;

public class Question 
{    
    private String originalWord;
    private String wrongAnswerOne;
    private String wrongAnswerTwo;
    private String wrongAnswerThree;
    private String rightAnswer;
  
    // -----------------------------------------------
    // No argument Question constructor
    // -----------------------------------------------

    public Question()
    {
        originalWord = "";
        wrongAnswerOne = "";
        wrongAnswerTwo = "";
        wrongAnswerThree = "";
        rightAnswer = "";
    }
  
    // -----------------------------------------------
    // Five argument Question constructor
    // -----------------------------------------------
    
    public Question(String anOriginalWord, String aWrongAnswerOne, String aWrongAnswerTwo, String aWrongAnswerThree, String aRightAnswer)
    { 
        if (anOriginalWord == null || aWrongAnswerOne == null || aWrongAnswerTwo == null || aWrongAnswerThree == null || aRightAnswer == null)
        {
            System.out.println("Fatal error creating question.");
            System.exit(0); 
        }
      
        else
        {
            this.originalWord = anOriginalWord;
            this.wrongAnswerOne = aWrongAnswerOne;
            this.wrongAnswerTwo = aWrongAnswerTwo;
            this.wrongAnswerThree = aWrongAnswerThree;
            this.rightAnswer = aRightAnswer;
        }
    }
  
    // -----------------------------------------------
    // createQuestion() method: creates a new Question with correctly stored instance variables
    // -----------------------------------------------

    public static Question createQuestion(String[] metadata) throws ArrayIndexOutOfBoundsException
    {  
        String originalWord = metadata[0];
        String wrongAnswerOne = metadata[1];
        String wrongAnswerTwo = metadata[2];
        String wrongAnswerThree = metadata[3];
        String rightAnswer = metadata[4];
      
        if (metadata[0] == null || metadata[1] == null || metadata[2] == null || metadata[3] == null || metadata[4] == null) //only works if comma is also missing, try isEmpty()?
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        return new Question(originalWord, wrongAnswerOne, wrongAnswerTwo, wrongAnswerThree, rightAnswer);  
    } 
  
    // -----------------------------------------------
    // Set...() methods for the five instance variables
    // -----------------------------------------------

    public void setOriginalWord(String anOriginalWord)
    {
        if (anOriginalWord == null)
        {
             System.out.println("Fatal error setting question word.");
             System.exit(0);
        }
      
        else
        {
            this.originalWord = anOriginalWord;
        }
    }
  
    public void setWrongAnswerOne(String aWrongAnswerOne)
    {
        if (aWrongAnswerOne == null)
        {
             System.out.println("Fatal error setting answer options.");
             System.exit(0);
        }
      
        else
        {
             this.wrongAnswerOne = aWrongAnswerOne;
        }
    }
  
    public void setWrongAnswerTwo(String aWrongAnswerTwo)
    {
        if (aWrongAnswerTwo == null)
        {
             System.out.println("Fatal error setting answer options.");
             System.exit(0);
        }
      
        else
        {
             this.wrongAnswerTwo = aWrongAnswerTwo;
        }
      
    }
  
    public void setWrongAnswerThree(String aWrongAnswerThree)
    {
        if (aWrongAnswerThree == null)
        {
             System.out.println("Fatal error setting answer options.");
             System.exit(0);
        }
      
        else
        {
            this.wrongAnswerThree = aWrongAnswerThree;
        }
    }
  
    public void setRightAnswer(String aRightAnswer)
    {
        if (aRightAnswer == null)
        {
             System.out.println("Fatal error setting answer options.");
             System.exit(0);
        }
      
        else
        {
             this.rightAnswer = aRightAnswer;
        }
    }
  
    // -----------------------------------------------
    // Get...() methods for the five instance variables
    // -----------------------------------------------
  
    public String getOriginalWord()
    {
        return originalWord;
    }

    public String getWrongAnswerOne()
    {
        return wrongAnswerOne;
    }

    public String getWrongAnswerTwo()
    {
        return wrongAnswerTwo;
    }

    public String getWrongAnswerThree()
    {
        return wrongAnswerThree;
    }

    public String getRightAnswer()
    {
        return rightAnswer;
    }
  
    // -----------------------------------------------
    // toString() method: represents a Question as a String. Switches position of rightAnswer at random.
    // -----------------------------------------------
  
    public String toString()
    {
        int answerOrder = (int)(Math.random() * 4);  //  Result is between 0 and 3 inclusive (because of truncation)
        String output = "";

        switch (answerOrder)
        {
            case 0:
                output = "\n\tNew word: " + originalWord + "\n\n\t " + wrongAnswerOne + "\n\t "
                 + wrongAnswerTwo + "\n\t " + wrongAnswerThree + "\n\t " + rightAnswer;
                break;
            
            case 1:
                output = "\n\tNew word: " + originalWord + "\n\n\t " + wrongAnswerOne + "\n\t " 
                 + wrongAnswerTwo + "\n\t " + rightAnswer + "\n\t " + wrongAnswerThree;
                break;
            
            case 2:
                output = "\n\tNew word: " + originalWord + "\n\n\t " + wrongAnswerOne + "\n\t "
                 + rightAnswer + "\n\t " + wrongAnswerTwo + "\n\t " + wrongAnswerThree;
                break;
            
            case 3:
                output = "\n\tNew word: " + originalWord + "\n\n\t " + rightAnswer + "\n\t " 
                 + wrongAnswerOne + "\n\t " + wrongAnswerTwo + "\n\t " + wrongAnswerThree;
                break;
        }
        return output;  
    } 
    
}
