/**
 * Player.java
 * @author alicebaker
 * Last edited: Fri 1st Feb 2019
 * Description: This class represents a Player of the WordGame. 
 * A new Player is initialised at the start of every run of the program.
 */

package word.game;

import java.util.ArrayList;

public class Player 
{
    public static ArrayList<Integer> individualScoreList = new ArrayList<Integer>(5); // player's final scores during one run of the program (for comparison) 
    private static int questionsAttempted; 
    private static int questionsCorrect;
    private static int questionsSkipped;
    private static int highestScore;
    private static String username;
  
    // -----------------------------------------------
    // No argument Player constructor
    // -----------------------------------------------

    public Player()
    {
        questionsAttempted = 0;
        questionsCorrect = 0;
        questionsSkipped = 0;
        highestScore = 0;
        username = "anon";
    }
  
    // -----------------------------------------------
    // Five argument Player constructor
    // -----------------------------------------------
    
    public Player(int someQuestionsAttempted, int someQuestionsCorrect, int someQuestionsSkipped, int someHighestScore, String someUsername)
    { 
        if (someQuestionsAttempted >= 0 && someQuestionsAttempted < 11 && someQuestionsCorrect >= 0 
           && someQuestionsCorrect < 11 && someQuestionsSkipped >= 0 && someQuestionsSkipped < 11
           && someHighestScore >= 0 && someHighestScore < 11 && someUsername != null)
        {
            this.questionsAttempted = someQuestionsAttempted;
            this.questionsCorrect = someQuestionsCorrect;
            this.questionsSkipped = someQuestionsSkipped;
            this.highestScore = someHighestScore;
            this.username = someUsername;
        }
      
        else
        {
            System.out.println("Error creating player: impossible values assigned.");
            System.exit(0);
        }
    }
  
    // -----------------------------------------------
    // set...() methods for the five instance variables
    // -----------------------------------------------

    public void setQuestionsAttempted(int someQuestionsAttempted)
    {
        if (someQuestionsAttempted >= 0 && someQuestionsAttempted < 11)
        {
            this.questionsAttempted = someQuestionsAttempted;
        }
      
        else
        {
            System.out.println("Error setting number of questions attempted: impossible value assigned.");
        } 
    }
  
    public void setQuestionsCorrect(int someQuestionsCorrect)
    {
        if (someQuestionsCorrect >= 0 && someQuestionsCorrect < 11)
        {
            this.questionsCorrect = someQuestionsCorrect;
        }
          
        else
        {
            System.out.println("Error setting number of questions correct: impossible value assigned."); 
        }
    }
  
    public void setQuestionsSkipped(int someQuestionsSkipped)
    {
        if (someQuestionsSkipped >= 0 && someQuestionsSkipped < 11)
        {
            this.questionsSkipped = someQuestionsSkipped;
        }
        
        else
        {
            System.out.println("Error setting number of questions skipped: impossible value assigned.");
        }
    } 
  
    public void setHighestScore(int someHighestScore)
    {
        if (someHighestScore >= 0 && someHighestScore < 11)
        {
            this.highestScore = someHighestScore;
        }
          
        else
        {
            System.out.println("Error setting highest score: impossible value assigned."); 
        }
    }
  
    public void setUsername(String someUsername)
    {
        if (someUsername != null)
        {
            this.username = someUsername;
        }
          
        else
        {
            System.out.println("Error setting username: username is blank.");
        }
    }
   
    // -----------------------------------------------
    // get...() methods for the five instance variables
    // -----------------------------------------------
  
    public int getQuestionsAttempted()
    {
        return questionsAttempted;
    }

    public static int getQuestionsCorrect()
    {
        return questionsCorrect;
    }

    public int getQuestionsSkipped()
    {
        return questionsSkipped; 
    }
  
    public static int getHighestScore()
    {
        return highestScore;
    }
  
    public static String getUsername()
    {
        return username;
    }
  
    // -----------------------------------------------
    // Set...() method for the individualScoreList ArrayList 
    // -----------------------------------------------
  
    public static void setIndividualScoreList(ArrayList<Integer> anIndividualScoreList)
    {
        individualScoreList = anIndividualScoreList;  
    }
     
    // -----------------------------------------------
    // Get...() method for the individualScoreList ArrayList 
    // -----------------------------------------------
  
    public static ArrayList<Integer> getIndividualScoreList()
    {
        return individualScoreList;
    }
}
