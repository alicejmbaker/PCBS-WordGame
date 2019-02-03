/**
 * SelectionSort.java
 * @author alicebaker
 * Last edited: Sat 2nd Feb 2019
 * Description: This class contains the ArrayList sorting methods for use with the WordGame.
 */
package word.game;

import java.util.ArrayList;
import java.util.Collections;

public class SelectionSort 
{
    public static ArrayList<Integer> individualScoreList = Player.getIndividualScoreList(); 
    public static ArrayList<Leader> leaderboardList = FileController.getLeaderboardList(); 
  
    // -----------------------------------------------
    // sortIndividualScores(): sorts individualScoreList (ie. the final scores 
    // achieved during one run of the program) into ascending order
    // -----------------------------------------------
  
    public static ArrayList<Integer> sortIndividualScores(ArrayList<Integer> individualScoreList)
    {
        for (int indexA = 0; indexA < individualScoreList.size(); indexA++)
        {
            int indexOfSmallest = indexA;
            for (int indexB = indexA; indexB < individualScoreList.size(); indexB++)
            {
                if (individualScoreList.get(indexB) < individualScoreList.get(indexOfSmallest))
                {
                    indexOfSmallest = indexB;
                }        
            }
                int smallestScore = individualScoreList.get(indexOfSmallest);
                individualScoreList.set(indexOfSmallest, individualScoreList.get(indexA));
                individualScoreList.set(indexA, smallestScore);      
        }
        return individualScoreList;
    }
  
    // -----------------------------------------------
    // sortLeaderboard(): sorts leaderboardList according to  
    // the values assigned by Leader.compareTo()
    // -----------------------------------------------
    
    @SuppressWarnings("unchecked") // unchecked method invocation warning re. sort(leaderboardList). Program runs w/out problem.
    public static ArrayList<Leader> sortLeaderboard(ArrayList<Leader> leaderboardList) 
    {         
        Collections.sort(leaderboardList);         
        return leaderboardList;     
    }
}
