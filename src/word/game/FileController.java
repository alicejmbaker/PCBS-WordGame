/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package word.game;

/**
 * FileController.java
 * @author alicebaker
 * Last edited: Sat 2nd Feb 2019 
 * Description: This class contains the methods which involve 
 * reading from or writing to a file. These are the quiz questions file 
 * (mini_quiz.csv) and the leaderboard entries file (leaderboard.txt).
 */

import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.FileReader; 
import java.io.IOException; 
import java.io.PrintWriter; 

import java.util.ArrayList; 
import java.util.Scanner; 

public class FileController 
{
    static Player thePlayer;
    public static ArrayList<Question> questionsList = new ArrayList<>(10);
    public static ArrayList<Leader> leaderboardList = new ArrayList<>(3);

    // -----------------------------------------------
    // readQuestionFile() method: reads a csv file, 
    // stores contents as Questions in the questionsList ArrayList
    // -----------------------------------------------

    public static ArrayList<Question> readQuestionFile()
    {
        int lineNumber = 0;
        try
        {
            BufferedReader inputStream = new BufferedReader(new FileReader("/Users/alicebaker/NetBeansProjects/Synonyms Game/src/word/game/synonym_questions.csv"));  
            File quizFile = new File("/Users/alicebaker/NetBeansProjects/Synonyms Game/src/word/game/synonym_questions.csv");
            String line = inputStream.readLine();
          
            if (quizFile.length() == 0)
            {
                System.out.println("Fatal error: file synonym_questions.csv is empty.");
                System.exit(0);
            }

            else
            {
                while (line != null)
                {
                    lineNumber += 1; //allows ArrayIndexOutOfBoundsException to specify first line where there is a problem
                    String[] attributes = line.split(",");
                    Question question = Question.createQuestion(attributes);
                    questionsList.add(question);
                    line = inputStream.readLine(); 
                }
            inputStream.close();
            } 
        }
      
        catch(FileNotFoundException e)
        {
            System.out.println("File synonym_questions.csv was not found ");
            System.out.println("or could not be opened. ");
            System.exit(0);
        }
      
        catch(IOException e)
        {
            System.out.println("Error reading from synonym_questions.csv. ");
            System.exit(0);
        }
     
        catch(ArrayIndexOutOfBoundsException e) //whoop, finally works! Thrown in createQuestion()
        {
            System.out.println("Error reading from synonym_questions.csv: incorrect number of fields in line " + lineNumber + ". "); 
            System.exit(0);
        } 
        return questionsList;
    }
  
    // -----------------------------------------------
    // set...() and get...() methods for the questionsList ArrayList
    // -----------------------------------------------

    public static void setQuestionsList(ArrayList<Question> aQuestionsList) 
    {
        if (aQuestionsList.isEmpty())
        {
            System.out.println("Error setting questionsList: list is empty. ");
            System.exit(0); 
        } 
        
        else
        {
            questionsList = aQuestionsList; 
        }       
    }
  
    public static ArrayList<Question> getQuestionsList()
    {
        return readQuestionFile();
    }
  
    // -----------------------------------------------
    // writeLeaderboard() method: writes a player's highest score and chosen name to leaderboard.txt 
    // -----------------------------------------------
  
    public static void writeLeaderboard()
    {
        PrintWriter outputStream = null;
      
        try
        {
            outputStream = new PrintWriter(new FileOutputStream("leaderboard.txt", true)); 
        }
       
        catch (FileNotFoundException e)
        {
            System.out.println("Error opening the file \"leaderboard.txt\". ");
        }
        
        if (Player.getHighestScore() > 0) 
        {
        outputStream.println(Player.getUsername());
        outputStream.println(Player.getHighestScore());
        System.out.println("\tScore saved!\n\tName: " + thePlayer.getUsername() + "\tHigh score: " + thePlayer.getHighestScore());
        }
        outputStream.close();                                             
    }
  
    // -----------------------------------------------
    // readLeaderboard() method: reads leaderboard.txt, stores 
    // contents as Leaders in the leaderboardList ArrayList 
    // -----------------------------------------------
  
    public static void readLeaderboard()
    {
        File leaderboardFile = new File("leaderboard.txt");
        Boolean multipleChances = true;
        Scanner inputStream = null;
      
        while (multipleChances)
        {
            try
            {
                inputStream = new Scanner(new FileInputStream("leaderboard.txt"));
                multipleChances = false;
            } 
      
            catch(FileNotFoundException e)
            {
                writeLeaderboard(); // creates blank leaderboard.txt file if one doesn't exist yet
                multipleChances = true;
            }
        }
      
        while (inputStream.hasNextLine())
        {
            String leaderName = inputStream.nextLine();
            int leaderScore = inputStream.nextInt();
            Leader aLeader = new Leader(leaderName, leaderScore);
            leaderboardList.add(aLeader);
            inputStream.nextLine();
        }
        inputStream.close();                
    }
  
    // -----------------------------------------------
    // set...() and get...() methods for the leaderboardList ArrayList
    // -----------------------------------------------
   
    public static void setLeaderboardList(ArrayList<Leader> aLeaderboardList)
    {
        if (aLeaderboardList.isEmpty())
        {
            System.out.println("Error setting leaderboardList: list is empty. ");
        } 
        
        else
        {
            leaderboardList = aLeaderboardList;  
        }
    }
  
    public static ArrayList<Leader> getLeaderboardList()
    {
        return leaderboardList;
    }  
}
