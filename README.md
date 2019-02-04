# PCBS-WordGame

## Introduction
The result-focused aim of this project was to produce a simple word game, designed as a vocabulary exercise for intermediate to advanced ESL learners.

The skills-focused aims of this project were to:
- Consolidate and expand my Java programming skills (introductory course taken in 2017)
- Practice writing clean, readable code
- Start programming outside of the Codio platform 

## Classes

- [WordGame.java](src/word/game/WordGame.java) is the main class, responsible for executing the program. The methods here allow the player to navigate through the menu and play the game; more detail is given within the comments.
- [Question.java](src/word/game/Question.java), [Player.java](src/word/game/Player.java) and [Leader.java](src/word/game/Leader.java) all represent objects. **Question.java** represents a quiz question, and its toString() method is responsible for randomising the order that the answer options appear in. **Player.java** represents a player. A single player object is initialised at the start of each run of the program. **Leader.java** represents an entry in the `leaderboardList` ArrayList.
- [FileController.java](src/word/game/FileController.java) manages input from and output to files: [synonyms_questions.csv](src/word/game/synonym_questions.csv) (the source of the quiz questions) and leaderboard.txt. Information from these files is converted into ArrayLists (`questionsList` and `leaderboardList`), so this class also contains the getters and setters for those ArrayLists.

Note: the path given for the .csv file (lines 45-6) may need to be changed for this program to run on another device.

```
BufferedReader inputStream = new BufferedReader(new FileReader(***"/Users/alicebaker/NetBeansProjects/Synonyms Game/src/word/game/synonym_questions.csv"***));  
File quizFile = new File(***"/Users/alicebaker/NetBeansProjects/Synonyms Game/src/word/game/synonym_questions.csv"***);
```

- [SelectionSort.java](src/word/game/SelectionSort.java) contains both of the sort...() methods used in this program. One sorts the player's individual scores, so that their high score can be determined and appropriate feedback on their progress can be given, and the other sorts the Leaderboard, so that scores are displayed on the leaderboard from high to low, and where two scores are the same, in alphabetical order of username.
- [InvalidMenuChoiceException.java](src/word/game/InvalidMenuChoiceException.java) is an extension of the Exception class. As part of my goal to write readable code, I have tried to make error messages printed and exceptions thrown as informative as possible. Aside from providing more specific exceptions, my techniques have included initialising and keeping track of a variable lineNumber in [FileController.java](src/word/game/FileController.java):
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
  Which allows for the position of a formatting error in the .csv file to be identified, avoiding a very tedious search:
  
```
 catch(ArrayIndexOutOfBoundsException e)
 {
     System.out.println("Error reading from synonym_questions.csv: incorrect number of fields in line " + lineNumber + ". "); 
     System.exit(0);
 } 
```

## Workflow

- I began by creating the .csv file. Each line contains a word X, a synonym of X, and three filler words belonging to the same part of speech. A random word generator was used for this.
- From then on, the game was developed in NetBeans.
- I began by creating the main menu and the About section.
- I then moved on to the quiz part of the program, creating methods for reading the .csv file, choosing the questions to be asked, randomising the order of the answer options etc.
- For additional functionality, I moved on to tracking the player's score throughout a single run of the program, printing their current score (the 'scoreboard') after each question and then providing customised feedback depending on their final score.
- The natural extension of this was to add a Leaderboard. The one I have added here is very primitive or, more kindly, retro. The system is similar to old-fashioned arcade games: the player enters a name at the start of a run of the program, which is used if they choose to save a high score(s). There is no 'login': the same player can choose a different username on every run of the programme, or different players could enter the same username. Additionally, a single player could come to dominate the leaderboard if they entered their high score to the leaderboard and then kept beating it within a single run of the programme, as the player is then given a chance to submit the new higher score, and this does not erase the older leaderboard entry.
- On many run throughs, I then tweaked the program slightly, according to my own opinions and the feedback of others. As an example of the former, `questionsList.remove(index)` ensures that the same question is not repeated within a single 10-question quiz, as I feel this makes the game more interesting for players:

```

 public static void chooseQuestions() 
 {
      WordGame.listForGame = new ArrayList<Question>(10);
        
      for (int i = 0; i < 10; i++)
      {
           int index = new Random().nextInt(questionsList.size());     
           Question nextQuestion = questionsList.get(index);
           listForGame.add(nextQuestion);
           questionsList.remove(index); 
       } 
}
```
[WordGame.java](src/word/game/WordGame.java) (32-49)

And as an example of the latter, the answer options were previously numbered. This misled some players into attempting to type the number as their response, which the program then considered invalid input. I thought about tweaking this, but as a vocabulary building game I felt having to type the whole word was appropriate, and so I simply deleted the numbers rather than adding any additional complications. 

## Reflections and moving forwards
- [x] **Consolidate and expand my Java programming skills**

  Although the concept of the game is not itself particularly impressive or complex, I made an effort to incorporate a range of commands and ways of doing things into the programme, such as using `switch (menuChoice)` rather than more `if ... else` statements in [WordGame.java](src/word/game/WordGame.java) (line 109). I was also able to implement several new-to-me commands that improved the functionality of the game. For example:
  
  ```
  menuChoice = Character.toUpperCase(menuChoice);
  ```
  [WordGame.java](src/word/game/WordGame.java) (line 101)
  
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
  
   I would like to add the functionality to login with a username and password, letting players to compare their scores across multiple runs of the programme, and also allowing for a more modern style of leaderboard. I suspect this can be done by a combination of ArrayLists and file input/output.
   
   I would also like to add pauses, so that chunks of text do not print themselves to the screen all in one go. I am aware of the `Thread.sleep()` method and actually initially incorporated it. However, I already had a lot of `while`, `if`, `else` etc. statements and nesting even more `try` and `catch` blocks to handle the InterruptedException every time I wanted to pause made things very cluttered. I also tried to create a special method for `Thread.sleep()` that threw the exception and then just call that method whenever I wanted to pause, but it still wanted me to throw the exception every time I called the method. I intend to research some alternative solutions.
  
- [x] **Practice writing clean, readable code**

  I have made a concerted effort to format the code clearly (correct indentation practice etc.) and to name methods and variables transparently, for example. I followed the Java Ranch style guidelines.
  
  **To work on:**
  
  I hope that my instincts regarding which methods to put in which classes will improve naturally with experience. This is actually what I probably spent the most time on in constructing this project. I am aware that, for example, the FileController class is perhaps a little random: I eventually settled on this because of the plethora of import statements that I needed only for reading and writing to files. I preferred to place all of the methods that needed them in a single dedicated class, rather than having large chunks of import statements cluttering other classes, but I am aware that it might have been better to group those methods with the things they pertain to instead (the question methods, and the other leaderboard methods).

- [x] **Start programming outside of the Codio platform**

  Although it sounds silly, this is the goal that I was most intimidated by. I am pleased with my progress. I learnt the basics of how to use NetBeans and (eventually) managed to work out how to commit to Github from NetBeans. I also learnt how to create a .csv file (in Numbers), and how to format this ReadMe page!
  
  **To work on:**
  I would like to continue familiarising myself with NetBeans, as I've only touched a fraction of the platform's functions. More generally, I plan to make a habit of challenging myself more frequently to try new technologies. It is embarassing how long I procrastinated out of fear of not understanding NetBeans, and then I put off committing to GitHub for as long as possible out of fear of not understanding that (despite knowing that you wanted to see our progress), and then I even stalled on writing this ReadMe page because I was concerned I wouldn't be able to work out how to format it! All of this avoidance negatively affected my experience of this module and possibly my grade, and will certainly impact my ability to conduct research and work with data in the future if I do not become more zen about new technology.
  I have already decided that for my first challenge, I will be familiarising myself with LaTeX. I have wasted countless hours formatting syntactic trees and syllabic diagrams in Word and Google Docs, knowing that LaTeX would be quicker and neater, because I was too afraid to learn. 
  
## Conclusion

Although this project is not overwhelmingly impressive in subject matter, and there is certainly room for improvement in both the functionality and my coding style, I am pleased with the end product. I feel that it is fairly readable - hopefully you agree - and quite apart from its content, it has improved my confidence regarding trying new technology.

## Bibliography

Savitch, W. (2013). *Absolute Java International Edition*, 5th Edition. Pearson Education Limited: Essex, UK.
