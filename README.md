# PCBS-WordGame

## Introduction
The result-focused aim of this project was to produce a simple word game, designed as a vocabulary exercise for intermediate to advanced ESL learners.
The skills-focused aims of this project were to:
- Consolidate and expand my Java programming skills
- Practice writing clean, readable code
- Start programming outside of the Codio platform 

## Resources
The game was developed in NetBeans. I used *Absolute Java* (Walter Savitch), as well as online resources such as Stack Overflow, to refresh my memory and solve specific problems as they arose.

## Classes

- [WordGame.java](src/word/game/WordGame.java) is the main class, responsible for executing the program and almost all direct interaction with the player. The methods here allow the player to navigate through the menu and play the game; more detail is given within the comments.
- [Question.java](src/word/game/Question.java), [Player.java](src/word/game/Player.java) and [Leader.java](src/word/game/Leader.java) all represent objects. **Question.java** represents a quiz question, and its toString() method is responsible for randomising the order that the answer options appear in. **Player.java** represents a player. A single player object is initialised at the start of each run of the program. **Leader.java** represents an entry in the `leaderboardList` ArrayList.
- [FileController.java](src/word/game/FileController.java) manages input from and output to files: [synonyms_questions.csv](src/word/game/synonym_questions.csv) (the source of the quiz questions) and leaderboard.txt. Information from these files is converted into ArrayLists (`questionsList` and `leaderboardList`), so this class also contains the getters and setters for those ArrayLists.

Note: the path given for the .csv file (lines 45-6) may need to be changed for this program to run on another device.

```
 BufferedReader inputStream = new BufferedReader(new FileReader(***"/Users/alicebaker/NetBeansProjects/Synonyms Game/src/word/game/synonym_questions.csv"***));  
File quizFile = new File(***"/Users/alicebaker/NetBeansProjects/Synonyms Game/src/word/game/synonym_questions.csv"***);
```

- [SelectionSort.java](src/word/game/SelectionSort.java) contains both of the sort...() methods used in this program. One sorts the player's individual scores, so that their high score can be determined and appropriate feedback on their progress can be given, and the other sorts the Leaderboard, so that scores are displayed on the leaderboard from high to low, and where two scores are the same, in alphabetical order.
- [InvalidMenuChoiceException.java](src/word/game/InvalidMenuChoiceException.java) is an extension of the Exception class. As part of my goal to write readable code, I have tried to make error messages printed and exceptions thrown as specific or informative as possible. For example, initialising and keeping track of a variable lineNumber in [FileController.java](src/word/game/FileController.java):
```
int lineNumber = 0;
       
try
{
   .....         
        
        else
        {
            while (line != null)
            {
                  lineNumber += 1; 
                  String[] attributes = line.split(",");
                  Question question = Question.createQuestion(attributes);
                  questionsList.add(question);
                  line = inputStream.readLine(); 
            }
            inputStream.close();
         } 
}
```
  Allows for the position of a formatting error in the .csv file to be identified, avoiding a very tedious search:
  
```
 catch(ArrayIndexOutOfBoundsException e)
 {
     System.out.println("Error reading from synonym_questions.csv: incorrect number of fields in line " + lineNumber + ". "); 
     System.exit(0);
 } 
```

## Workflow

- I began by creating the .csv file. Each line contains a word X, a synonym of X, and three filler words belonging to the same part of speech. A random word generator was used for this.


## Reflections and moving forwards
- [x] **Consolidate and expand my Java programming skills**

  I was pleasantly surprised with how much I remembered from the Java introductory course I took in 2017. I was also able to implement several new-to-me commands that improved the functionality of the game. For example:
  
  ```
  menuChoice = Character.toUpperCase(menuChoice);
  ```
  
  I already knew the `equalsIgnoreCase()` method for Strings, but `toUpperCase()` allowed me to essentially do the same thing for Chars, and makes the Main Menu less frustrating to navigate for users. I could have individually allowed for lower case chars, of course, and introduced a `case 'a'` which was identical to `case 'A'`, that is:
  
  ```
  case 'a':
  printAbout();
  break;
                            
  case 'A':
  printAbout();
  break;
        
   ```
   
   However, I would have needed to do this duplication for every single menu choice, and that would have made the code long, repetitive and less readable. Similarly, I could have made `menuChoice` a String rather than a Char and just proceeded to use `equalsIgnoreCase()`, but it seemed stylistically inelegant, knowing users should only ever be inputting a single character, to use the String data type when there exists the more suitable Char type.
   
   **To work on:**
   
   Logging in!!
   Obviously this is an area where one could continue to improve indefinitely. In terms of this particular game, one thing that I would be interested in working out how to do, is allowing players to answer with a number rather than by typing the entire word. That is, the question would be:
   
   >New word:
   >resign
   
   > 1) communicate
   > 2) preserve
   > 3) quit
   > 4) refuse
   
   And the player could type *3* rather than *quit*.
   
   As it stands, typing the entire word does make sense for the purpose of the game (vocabulary building), but I still think this would be a useful exercise. I randomise the order of the answer options for each question, so I would need at that stage to then somehow pair up the answer options with an associated number, and then instead of checking that `answerChoice` matches `rightAnswer`, I would check that it matched the number associated with `rightAnswer` instead.
   
   I would also like to add pauses, so that chunks of text do not print themselves to the screen all in one go. I am aware of the `Thread.sleep()` method and actually initially incorporated it. However, I already had a lot of `while`, `if`, `else` etc. statements and nesting even more `try` and `catch` blocks to handle the InterruptedException every time I wanted to pause made things very cluttered. I also tried to create a special method for `Thread.sleep()` that threw the exception and then just call that method whenever I wanted to pause, but it still wanted me to throw the exception. Something for me to work on!
  
- [x] **Practice writing clean, readable code**

  I have made a concerted effort to format the code clearly (correct indentation practice etc.) and to name methods and variables transparently, for example. I aimed to follow the Java Ranch style guidelines.
  
  **To work on:**
  
  I hope that my instincts regarding which methods to put in which classes will improve naturally with experience. This is actually what I probably spent the most time on in constructing this project. I am aware that, for example, the FileController class is perhaps a little random: I eventually settled on this because of the plethora of import statements that I needed only for reading and writing to files. I preferred to place all of the methods that needed them in a single dedicated class, rather than having large chunks of import statements cluttering other classes, but I am aware that it might have been better to group those methods with the things they pertain to instead (the question methods, and the other leaderboard methods).

- [x] **Start programming outside of the Codio platform**

  Although it sounds silly, this is the goal that I was most intimidated by. I am pleased with my progress.
  
  I learnt the basics of how to use NetBeans and (eventually) managed to work out how to commit to Github from NetBeans. I also learnt how to create a .csv file (in Numbers), and how to format this ReadMe page!
  
  **To work on:**
  I would like to continue familiarising myself with NetBeans, as I've only touched a fraction of the platform's functions. More generally, I plan to make a habit of challenging myself more frequently to try new ways of doing things. It is embarassing how long I procrastinated out of fear of not understanding NetBeans or GitHub. And then I procrastinated *again* because I was concerned I wouldn't be able to work out how to format this ReadMe file to look like the example you provided! All of this avoidance negatively affected my experience of this module and possibly my grade, and will certainly impact my ability to conduct research and work with data in the future if I do not become more zen about new technology.
  I am going to start by familiarising myself with LaTeX. I have wasted countless hours formatting syntactic trees and syllabic diagrams in Word and Google Docs, knowing that LaTeX would be quicker and neater, because I was too afraid to learn. This is kind of thing is blatantly ridiculous and needs to end.

## Bibliography

Savitch, W. (2013). *Absolute Java International Edition*, 5th Edition. Pearson Education Limited: Essex, UK.
