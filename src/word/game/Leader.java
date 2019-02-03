/**
 * Leader.java
 * @author alicebaker
 * Last edited: Sat 2nd February 2019
 * Description: This class represents a Leader, ie an entry on the Leaderboard.
 */

package word.game;

public class Leader implements Comparable
{
    private String leaderName;
    private int leaderScore;
  
    // -----------------------------------------------
    // No argument Leader constructor
    // -----------------------------------------------

    public Leader()
    {
        leaderName = "";
        leaderScore = 0;
    }
  
    // -----------------------------------------------
    // Two argument Leader constructor
    // -----------------------------------------------
    
    public Leader(String aLeaderName, int aLeaderScore)
    { 
        if (aLeaderScore > 0 && aLeaderScore < 11)
        {
            this.leaderName = aLeaderName;
            this.leaderScore = aLeaderScore;
        }
        
        else
        {
            System.out.println("Error creating Leader: invalid score value assigned. ");
        }
    }
    
    // -----------------------------------------------
    // compareTo() method: leader receives a negative value if it has a higher score than, or a username that alphabetically precedes, otherLeader's.
    // -----------------------------------------------
    
    public int compareTo(Object other)
    {
        Leader otherLeader = (Leader) other;
        if (leaderScore > otherLeader.leaderScore)
        {
            return -1;
        }     
        
        else if (leaderScore == otherLeader.leaderScore && leaderName.compareToIgnoreCase(otherLeader.leaderName) < 0)
        {
            return -1;
        }
        
        else if (leaderScore == otherLeader.leaderScore)//note: still want both to appear tho
        {
            return 0;
        }
        
        else
        {
             return 1;
        }
    } 
    
    // -----------------------------------------------
    // toString() method: represents a Leader as a String 
    // -----------------------------------------------
  
    public String toString()
    {
        return "\n\t" + leaderName + "\t\t" + leaderScore;
    }  
    
}
