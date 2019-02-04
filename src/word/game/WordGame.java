/*
 * WordGame.java
 * Author: Alice Baker
 * Last edited: Fri 1st February 2019
 * Description: A single-player synonym-based quiz game
 */

package word.game;

import java.util.ArrayList; 
import java.util.Random; 
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordGame 
{
    public static ArrayList<Question> questionsList = FileController.getQuestionsList(); 
    static ArrayList<Question> listForGame; 
    static Player thePlayer = new Player(); 
  
    // -----------------------------------------------
    // Main method: runs program
    // -----------------------------------------------
  
    public static void main(String[] args)  
    {    
        FileController.getQuestionsList();
        chooseQuestions();
        inputUsername();
        printMainMenu();
    }

    // -----------------------------------------------
    // chooseQuestions() method: 
    // selects a random set of 10 questions for the quiz
    // -----------------------------------------------
  
    public static void chooseQuestions() 
    {
        WordGame.listForGame = new ArrayList<Question>(10);
        
        for (int i = 0; i < 10; i++)
        {
            int index = new Random().nextInt(questionsList.size());     
            Question nextQuestion = questionsList.get(index);
            listForGame.add(nextQuestion);
            questionsList.remove(index); 
            // remove ensures questions are not duplicated within a single 10-question game
        } 
    }
  
    // -----------------------------------------------
    // inputUsername() method: 
    // allows the player to choose a username for this run of the program.
    // This name is used when they save a score to the Leaderboard.
    // The player is free to choose a new username every time they run the program
    // and usernames need not be unique.
    // -----------------------------------------------
    
    public static void inputUsername()
    {
        Scanner scan = new Scanner(System.in);
      
        System.out.print("\n\tEnter your name: ");
        String name = scan.nextLine(); 
    
        while (name.length() >= 8) // prevents misalignment of the leaderboard column
        {
            System.out.print("\n\tOops! Names must be less than 8 characters (including spaces). Try again: ");
            name = scan.nextLine();
        }
    
        if (name.length() == 0 || name.trim().length() == 0) 
        {
            name = "*anon*";
        }
      
        thePlayer.setUsername(name);
        System.out.println("\n\tHi " + name + "! ");
    }
  
    // -----------------------------------------------
    // printMainMenu() method: displays game's Main Menu
    // -----------------------------------------------

    private static void printMainMenu() 
    {
        Scanner scan = new Scanner(System.in);
        char menuChoice = 'A'; 

        while (menuChoice != 'Q') 
        {
            try
            {
                System.out.print("\n\n\tWelcome to the Word Game");
                System.out.print("\n\tAbout (A)");
                System.out.print("\n\tPlay the Game (P)");
                System.out.print("\n\tShow the Leaderboard (L)");
                System.out.print("\n\tQuit (Q)");
                System.out.print("\n\n\tPlease choose an option:_");
                menuChoice = scan.next().charAt(0);
                menuChoice = Character.toUpperCase(menuChoice);
     
                if (menuChoice != 'A' && menuChoice != 'P' && menuChoice != 'L' && menuChoice != 'Q')
                {
                    throw new InvalidMenuChoiceException();
                }
                else
                {
                    switch (menuChoice) 
                    {
                        case 'A':
                            printAbout();
                            break;
        
                        case 'P': 
                            playGame();
                            chooseQuestions(); 
                            break;
                        
                        case 'L':
                            printLeaderboard();
                            break;
                 
                        case 'Q':      
                            saveScore();
                            System.out.println("\n\tAPPLICATION TERMINATED"); 
                            System.exit(0);
                            break;
                    }
                }
            }
            
            catch (InvalidMenuChoiceException e)
            {
                System.out.println(e.getMessage());       
            }  
        } 
    }
  
    //---------------------------------------------
    // printAbout() method: displays game instructions
    // --------------------------------------------
  
    private static void printAbout()   
    {
        System.out.print("\n\tInstructions:");
        System.out.print("\n\n\tThis is a multiple choice game designed to test and build your vocabulary.");
        System.out.print("\n\tYour objective is to match the question word with the correct synonym from the list."); 
        System.out.print("\n\tTo skip a question, press the X key.");
        System.out.print("\n\tTo end the game and return to the main menu, press the M key. Your progress will not be saved.");
        System.out.print("\n\tCorrect answers score 1 point.");
        System.out.print("\n\tIncorrect or skipped answers score 0 points.");
        System.out.print("\n\n\tGive it a try!");
        System.out.print("\n\tPress Enter to return to the main menu: "); 
        pressEnter();
    }
  
    //---------------------------------------------
    // playGame() method: executes one run of the game (10 questions)
    // --------------------------------------------
    
    private static void playGame() 
    {     
        Scanner scan = new Scanner(System.in);
 
        gameLoop:
        for (int i = 0; i < 10; i++)
        {
            int index = i;
            String answerChoice = "";
            Boolean multipleChances = true;
            final int TOTAL_QUESTIONS = 10;
            Question question = new Question ();
            question = WordGame.listForGame.get(i);
            while (multipleChances) 
            {
                System.out.println(question);
                System.out.print("\n\tPlease choose an option: ");
                answerChoice = scan.nextLine(); 
 
                try
                {
                    if (answerChoice.equalsIgnoreCase("m")) 
                    {
                        thePlayer.setQuestionsAttempted(0); 
                        thePlayer.setQuestionsCorrect(0); 
                        thePlayer.setQuestionsSkipped(0);
                        break gameLoop; // returns to main menu
                    }
                  
                    else if (answerChoice.equalsIgnoreCase("x") || answerChoice.equalsIgnoreCase(question.getRightAnswer()) 
                            || answerChoice.equalsIgnoreCase(question.getWrongAnswerOne()) || answerChoice.equalsIgnoreCase(question.getWrongAnswerTwo()) 
                            || answerChoice.equalsIgnoreCase(question.getWrongAnswerThree()))
                    {    
                        if (answerChoice.equalsIgnoreCase("x"))
                        {
                            System.out.println("\n\tQuestion skipped.");
                            thePlayer.setQuestionsSkipped(thePlayer.getQuestionsSkipped() + 1);
                        }

                        else if (answerChoice.equalsIgnoreCase(question.getRightAnswer()))
                        {
                            System.out.println("\n\tCorrect!");
                            thePlayer.setQuestionsAttempted(thePlayer.getQuestionsAttempted() + 1); 
                            thePlayer.setQuestionsCorrect(thePlayer.getQuestionsCorrect() + 1); 
                        }
         
                        else if (answerChoice.equalsIgnoreCase(question.getWrongAnswerOne()) || answerChoice.equalsIgnoreCase(question.getWrongAnswerTwo()) 
                                 || answerChoice.equalsIgnoreCase(question.getWrongAnswerThree()))
                        {
                            System.out.println("\n\tBad luck!");
                            thePlayer.setQuestionsAttempted(thePlayer.getQuestionsAttempted() + 1); 
                        }
                      
                        multipleChances = false;
                        System.out.println("\n\t\t" + question.getOriginalWord() + " means " + question.getRightAnswer());                
                        printScoreboard();
                    }      
         
                    else
                    {
                        throw new InvalidMenuChoiceException();
                    } 
                }
        
                catch (InvalidMenuChoiceException e)
                {
                    System.out.println(e.getMessage()); 
                    System.out.println("\t(You can press X to skip a question or M to return to the Main Menu.) ");
                    multipleChances = true;
                }
            }
        }
    }

    //---------------------------------------------
    // printScoreboard() method: 
    // lets player know their score after each question & at end of game,
    // gives the opportunity to save a high score,
    // and updates instance variable values as appropriate
    // --------------------------------------------

    public static void printScoreboard() //throws InterruptedException 
    {
        Scanner scan = new Scanner(System.in);
        int totalQuestions = (thePlayer.getQuestionsAttempted() + thePlayer.getQuestionsSkipped());

        if (totalQuestions < 10)
        {
            System.out.println("\n\n\tCurrent score:        " + thePlayer.getQuestionsCorrect() + "/" + totalQuestions); 
            System.out.print("\n\tPress Enter to continue: ");
            pressEnter();
        }
      
        else if (totalQuestions == 10)
        {
            System.out.println("\n\n\tGAME OVER!\n\tFinal score:        " + thePlayer.getQuestionsCorrect() + "/" + totalQuestions);       
            Player.individualScoreList.add(0, thePlayer.getQuestionsCorrect());
            printScoreFeedback();
            printProgressFeedback();
            saveScore();
            thePlayer.setQuestionsAttempted(0); 
            thePlayer.setQuestionsCorrect(0); 
            thePlayer.setQuestionsSkipped(0);
            // resets instance variables for next game
            System.out.print("\n\tPress Enter to return to the main menu: "); 
            pressEnter();
        }
    }
  
    // -----------------------------------------------
    // printScoreFeedback() method: 
    // provides feedback according to the player's final score for this particular game
    // -----------------------------------------------
  
    public static void printScoreFeedback()
    {
        if (thePlayer.getQuestionsCorrect() >= 8)
        {
            System.out.println("\n\tGreat job, genius! "); 
        }
                           
        else if (thePlayer.getQuestionsCorrect() >= 5)
        {
            System.out.println("\n\tGood effort! With a bit of revision, you'll be top of the class. "); 
        }
                             
        else 
        {
            System.out.println("\n\tOh dear! Never mind, it's the taking part that counts. "); 
        } 
          
        if (thePlayer.getQuestionsSkipped() >= thePlayer.getQuestionsCorrect())
        {
            System.out.println("\n\tTOP TIP: Try making your best guess instead of skipping questions! ");
        } 
    }
  
    // -----------------------------------------------
    // printProgressFeedback() method: 
    // tells player how their score compares to their previous score,
    // (re)sets the highestScore variable where appropriate
    // -----------------------------------------------
  
    public static void printProgressFeedback() 
    {
        SelectionSort.sortIndividualScores(thePlayer.getIndividualScoreList());

        int i = (thePlayer.getIndividualScoreList().size() - 1);
        if (i == 0)
        {
            System.out.println("\n\tWhy not play again? Practice makes perfect! ");
            thePlayer.setHighestScore(thePlayer.getQuestionsCorrect());      
        }
    
        else if (thePlayer.getIndividualScoreList().get(i) == thePlayer.getQuestionsCorrect() 
                 && thePlayer.getIndividualScoreList().get(i-1) != thePlayer.getQuestionsCorrect())
        {
            System.out.println("\n\tNew personal best! "); 
            thePlayer.setHighestScore(thePlayer.getQuestionsCorrect());
        }
 
        else if (thePlayer.getIndividualScoreList().get(i) > thePlayer.getQuestionsCorrect())
        {
            System.out.println("\n\tNot your best score: don't get cocky! ");
        }
    }

    // -----------------------------------------------
    // printLeaderboard() method: 
    // This is an old-fashioned arcade game-style leaderboard:
    // new high scores do not overwrite old high scores from the
    // same player, so one person could dominate the entire leaderboard.
    // -----------------------------------------------
  
    public static void printLeaderboard()
    {
        ArrayList<Leader> leaderboardList = FileController.getLeaderboardList();  
        FileController.readLeaderboard();
      
        if (leaderboardList.size() == 0)
        {
            System.out.println("\n\tThe leaderboard is empty! ");
            System.out.println("\tWhy not be the first to get a high score: ");
        }
      
        else
        {
            SelectionSort.sortLeaderboard(leaderboardList);
            System.out.println("\n\t*****LEADERBOARD*****");
            System.out.print("\n\tNAME \t\tSCORE");
            int maxLeaders;
      
            if ((leaderboardList.size() - 1) > 10)
            {
                maxLeaders = 10;
            }
    
            else
            {
                maxLeaders = leaderboardList.size() - 1;
            }
    
            for (int i = 0; i <= maxLeaders; i++)
            {
                Leader aLeader = new Leader ();
                aLeader = FileController.leaderboardList.get(i);
                System.out.println(aLeader.toString());
            }
            System.out.print("\n\tPress Enter to return to the main menu: "); 
            pressEnter();
            leaderboardList.clear(); 
        }
    }
  
    //---------------------------------------------
    // saveScore() method: 
    // gives player the option to save their current highestScore to the leaderboard
    // Player can save score if they have a highestScore of > 0.
    //  If player has already saved their score during the current run of the program, 
    //  they are only offered the option to save their score again if they exceed the previous score.
    //  If you wanted to change this, you could clear the individualScoreList after re-setting the highestScore to 0. 
    // --------------------------------------------
    
    public static void saveScore()
    {
        Scanner scan = new Scanner(System.in);
        char saveChoice = 'A'; 
        Boolean multipleChances = true;
        ArrayList<Integer> individualScoreList = Player.getIndividualScoreList();
        int highestScore = thePlayer.getHighestScore();
          
        saveLoop:
        if (highestScore > 0)
        {
            while(multipleChances)
            {
                try
                {
                    System.out.print("\n\tWould you like to save your high score? ");
                    System.out.print("\n\tYes (Y)");
                    System.out.print("\n\tNo (N)");
                    System.out.print("\n\n\tPlease choose an option:_");
                    saveChoice = scan.next().charAt(0);
                    saveChoice = Character.toUpperCase(saveChoice);
      
                    if (saveChoice != 'Y' && saveChoice != 'N')
                    {
                        throw new InvalidMenuChoiceException();
                    }
       
                    else
                    {
                        switch (saveChoice) 
                        {
                            case 'Y':
                                FileController.writeLeaderboard();
                                thePlayer.setHighestScore(0); 
                                // resets to 0 as otherwise player could repeatedly save this same score without playing the game again
                                break saveLoop;
                       
                            case 'N': 
                                break saveLoop; 
                        }
                    }
                    multipleChances = false;
                    System.out.println("\n\tThanks for playing " + thePlayer.getUsername() + "! ");
                }
      
                catch (InvalidMenuChoiceException e)
                {
                    System.out.println(e.getMessage()); 
                    multipleChances = true;
                } 
            }
        } 
    } 
  
    //---------------------------------------------
    // pressEnter() method: checks if player has pressed the Enter key
    // --------------------------------------------
    
    private static void pressEnter() 
    {
        try
        {
            System.in.read();
        }

        catch(Exception e)
        { 
        }
    }
 
}
