/*
 * Names: Mona Afshar, Shenelle Jayakody, Lily Phan, and Nancy Zhu
 * Date: 04/15/2021
 * Concept: This game is an interactive hangman game that focuses on relevant issues in student lives! It'll raise awareness for struggles students may be facing. The questions and their answers will provide tips on how to counteract issues, or help identify what the struggles may be. Hopefully this can help improve student mental and physical health through a fun interactive game.
 * Note: Please run this using Ready to Program
 */

//import statements
import java.awt.*; //enables access to AWT package
import hsa.Console; //enables access to HSA console
import java.io.*; //enables access to IO files
import javax.swing.JOptionPane; //enables access to use JOptionPane
import java.awt.image.*; //for importing images
import javax.imageio.ImageIO; // so that we can import images

public class HealthyHangman
{
    //Declaration Section
    Console c; //console
    String word; //chooses the word the user needs to guess
    String sentence;
    String question; //trivia question for user
    String choice = (""); //user choice on what they want to do; also determines the course of action in multiple methods
    String[] underScores; //holds the the value the user needs to guess (eg. the word hello will be stored in this array as _ _ _ _ _ until the user guesses each letter)
    boolean userWin = false; //detects whether the user wins or not during each game. Will be used to trigger responses in multiple methods
    final int POSSIBLE_WORDS = 50; //possible words to guess
    int oldIndex = 50; //Stops the same word from being used twice, starting value is 50 because the index isn't able to be 50 so the initialization doesn't affect the chooseWord method the first time it's run
    int health = 50; //determines which brain image is displayed

    public HealthyHangman ()  //class constructor
    {
	c = new Console ("Healthy Hangman"); //creates the console
    }


    private void title ()  //title method
    {
	c.clear (); //clears the screen
	c.print (' ', 33); //Centres the title
	c.println ("Healthy Hangman"); //Title
	c.println (""); //indent
    }


    private void pauseProgram ()  //pauseProgram method
    {
	c.println ("\nPress any key to continue..."); //prompts user input
	c.getChar (); //Pauses program, waits for user to enter any keyboard press
    }


    private String[] checkChar (char userLetter)  //blackbox method checkChar
    {
	boolean check = false; //Decides if a brain
	String userWord = ""; //variable to determine how much of the word has been guessed
	for (int i = 0 ; i < word.length () ; i++)
	{
	    if (word.charAt (i) == userLetter)
	    {
		underScores [i] = (userLetter + " ");
		check = true;
	    }
	    userWord = userWord + underScores [i].trim ();
	}
	if (userWord.equalsIgnoreCase (word))
	{
	    userWin = true;
	}
	if (!check)
	{
	    health -= 5;
	    if (health <= 0)
	    {
		userWin = true;
	    }
	}
	return underScores; //Returns array
    }


    public void splashScreen ()  //splashScreen method
    {
	BufferedImage life1 = null;
	BufferedImage life2 = null;
	BufferedImage life3 = null;
	BufferedImage life4 = null;
	BufferedImage life5 = null;
	try
	{
	    life1 = ImageIO.read (new File ("brain_w_health.png"));
	    life2 = ImageIO.read (new File ("third_fire_brain_w_health.png"));
	    life3 = ImageIO.read (new File ("second_fire_brain_w_health.png"));
	    life4 = ImageIO.read (new File ("first_fire_brain_w_health.png"));
	    life5 = ImageIO.read (new File ("fire_w_health.png"));

	}
	catch (IOException e)
	{
	}
	c.setColor(Color.BLACK);
	c.setFont(new Font("Courier", Font.PLAIN, 62));
	c.drawString("HEALTHY", 190, 80);
	c.drawString("HANGMAN", 190, 450);
	c.setFont(new Font("Courier", Font.PLAIN, 15));
	c.drawString("Game by Nancy Zhu, Mona Afshar, Shenelle Jayakody and Lily Phan.", 32, 490);
	c.setColor (Color.WHITE); //yellow
	for (double i = 0.2 ; i > 0.0 ; i -= 0.1)
	{
	    c.fillRect (200, 100, 400, 280);
	    c.drawImage (life1, 200, 100, null); //call the image
	    try
	    {
		Thread.sleep (1000);
	    }
	    catch (InterruptedException ie)
	    {
	    }
	    c.fillRect (200, 100, 400, 280);
	    c.drawImage (life2, 200, 100, null); //call the image
	    try
	    {
		Thread.sleep (1000);
	    }
	    catch (InterruptedException ie)
	    {
	    }
	    c.fillRect (200, 100, 400, 280);
	    c.drawImage (life3, 200, 100, null); //call the image
	    try
	    {
		Thread.sleep (1000);
	    }
	    catch (InterruptedException ie)
	    {
	    }
	    c.fillRect (200, 100, 400, 280);
	    c.drawImage (life4, 200, 100, null); //call the image
	    try
	    {
		Thread.sleep (1000);
	    }
	    catch (InterruptedException ie)
	    {
	    }
	    c.fillRect (200, 100, 400, 300);
	    c.drawImage (life5, 200, 100, null); //call the image
	    try
	    {
		Thread.sleep (1000);
	    }
	    catch (InterruptedException ie)
	    {
	    }
	}

    }


    public void chooseWord ()  //chooseWord method
    {
	int index; //holds the index of the word the user has to choose
	String[] data = new String [POSSIBLE_WORDS]; //creates a string array to hold all the possible words
	String[] data2 = new String [POSSIBLE_WORDS]; //creates a string array to hold all the possible words
	String[] data3 = new String [POSSIBLE_WORDS]; //creates a string array to hold all the possible trivia questions
	try //tries to open the file
	{
	    BufferedReader input = new BufferedReader (new FileReader ("TriviaQuestions.txt"));
	    for (int count = 0 ; count < POSSIBLE_WORDS ; count++)
	    {
		data3 [count] = input.readLine ();
		data2 [count] = input.readLine ();
		data [count] = input.readLine ();
	    }
	    do
	    {
		index = (int) (POSSIBLE_WORDS * Math.random ()); //chooses a random index
	    }
	    while (index == oldIndex); //runs at least once; ensures that the word chosen is not the same as the previous word
	    word = data [index].toLowerCase ().trim ();
	    if (data2 [index].trim ().equals ("none"))
	    {
		sentence = "";
	    }
	    else
	    {
		sentence = data2 [index].toLowerCase ().trim ();
	    }
	    question = data3 [index].trim ();
	    oldIndex = index; //sets oldIndex to be equal to the old index
	    underScores = new String [word.length ()]; //Underscores initialization
	    for (int i = 0 ; i < word.length () ; i++) //Decides the number of underscores
	    {
		underScores [i] = "_ "; //Puts underscores into array
	    }
	}
	catch (IOException e)  //in the case something goes wrong with opening the file, or file doesn't exist
	{
	    JOptionPane.showMessageDialog (null, "Sorry, something went wrong with the files.", "Error", JOptionPane.ERROR_MESSAGE); //error message
	}
    }


    public void instructions ()  //instructions method
    {
	title (); //calls title method to clear the screen
	c.setTextBackgroundColor (Color.pink); //sets text background colour to pink
	c.println ("How To Play"); //title
	c.setTextBackgroundColor (Color.white);
	c.println ("Welcome to Healthy Hangman Game! In this game, you will be given");
	c.println ("a sentence with a fill in the blank word.");
	c.println ("You must continuously guess the characters until you get the word.");
	c.println ("Every wrong letter results in a larger flame being added to your brain.");
	c.println ("The game ends when all your brain is completely on fire, or ");
	c.println ("when you guess the word correctly!");
	c.println ("Just a reminder that all numeric answers should be written in words");
	c.println ("(eg. 2 is two).");
	pauseProgram (); //calls pauseProgram method
    }


    public void mainMenu ()  //mainMenu method
    {
	while (true) //runs until the user enters an appropriate value
	{
	    title (); //executes title
	    c.println ("Please choose one of the following options:"); //shows menu options
	    c.print (' ', 5); //indent
	    c.println ("1. Instructions"); //option
	    c.print (' ', 5); //indent
	    c.println ("2. Play Game"); //option
	    c.print (' ', 5); //indent
	    c.println ("3. Exit"); //option
	    c.println (" "); //indent

	    choice = c.readLine (); //reads in user choice
	    //this is a string instead of a char to ensure the user doesn't spam the keyboard and the program crashes
	    if (!(choice.equals ("1") || choice.equals ("2") || choice.equals ("3"))) //tests if user has not entered any of the options
	    {
		JOptionPane.showMessageDialog (null, "Invalid option chosen. Please try again.", "Error", JOptionPane.ERROR_MESSAGE); //error message
	    }
	    else
	    {
		break; //if user entered choice is valid, the while loop is broken
	    }
	}
    }


    public void executeGame ()  //executeGame method
    {
	int index = 0; //used to control which index is chosen in the oldChars method
	boolean ask = true; //user to control how long the program should ask the user to enter a letter
	userWin = false; //resets userWin to false after each game to indicate the user has not won yet
	health = 50;
	String letter = " "; //used to hold the user entered letter
	String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}; //used to hold all the possible letters
	String[] oldChars = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "}; //used to hold all the old letters the user has chosen

	while (!userWin) //runs while the user has not guessed the word yet or hasn't won yet
	{
	    ask = true; //sets ask to true during each gameplay to ask the user for a letter
	    title (); //calls title method to clear the screen

	    if (health >= 45)  //tests if user entered a wrong letter
	    {
		BufferedImage life1 = null;
		try
		{
		    life1 = ImageIO.read (new File ("brain_w_health.png"));
		}
		catch (IOException e)
		{
		}
		c.drawImage (life1, 270, 120, null); //call the image
	    }
	    else if (health >= 35)    //tests if user entered a wrong letter
	    {
		BufferedImage life2 = null;
		try
		{
		    life2 = ImageIO.read (new File ("third_fire_brain_w_health.png"));
		}
		catch (IOException e)
		{
		}
		c.drawImage (life2, 270, 100, null); //call the image
	    }
	    else if (health >= 25) //tests if user entered a wrong letter again
	    {
		BufferedImage life3 = null;
		try
		{
		    life3 = ImageIO.read (new File ("second_fire_brain_w_health.png"));
		}
		catch (IOException e)
		{
		}
		c.drawImage (life3, 270, 100, null); //call the image

	    }
	    else if (health >= 10) //tests if user entered a wrong letter again
	    {
		BufferedImage life4 = null;
		try
		{
		    life4 = ImageIO.read (new File ("first_fire_brain_w_health.png"));
		}
		catch (IOException e)
		{
		}
		c.drawImage (life4, 270, 100, null); //call the image
	    }
	    else if (health >= 0) //tests if user entered a wrong letter again
	    {
		BufferedImage life5 = null;
		try
		{
		    life5 = ImageIO.read (new File ("fire_w_health.png"));
		}
		catch (IOException e)
		{
		}
		c.drawImage (life5, 270, 100, null); //call the image
	    }
	    //draws the letters
	    for (int i = 0 ; i < 13 ; i++) //for loop to output all the possible letters
	    {
		c.setColor (Color.black);
		c.setFont (new Font ("Monospaced", Font.PLAIN, 40));
		c.drawString (alphabet [i], 35 + i * 45, 420); //outputs the first 13 letters in the top row
		c.drawString (alphabet [i + 13], 35 + i * 45, 465); //outputs the last 13 letters in the bottom row
	    }

	    c.setFont (new Font ("Monospaced", Font.PLAIN, 17));

	    if(question.length () >= 114){
c.drawString (question.substring (0, 52) + "-", 17, 90);
		c.drawString (question.substring (52, 114), 17, 120);
c.drawString (question.substring (114, question.length ()) + "-", 17, 140);
}else if (question.length () > 52 && ((question.charAt(53) >= 'a' && question.charAt(53) <= 'z') || (question.charAt(53) >= 'A' && question.charAt(53) <= 'Z')))
	    {
		c.drawString (question.substring (0, 52) + "-", 35, 90);
		c.drawString (question.substring (52, question.length ()), 17, 120);
	    }
	    else
	    {
		c.drawString (question, 17, 90);
	    }
	    if (!sentence.equals ("none"))
	    {
		c.drawString (sentence, 17, 155);
	    }

	    for (int i = 0 ; i < word.length () ; i++) //for loop to output the current stage the user is at (eg. _ _ l l o)
	    {
		c.setFont (new Font ("Arial", Font.PLAIN, 17));
		c.drawString (underScores [i], 17 + i * 17, 180);
	    }
	    while (ask) //runs until the user enters a valid letter
	    {
		c.setCursor (3, 1);
		c.print (' ', 80);
		c.setCursor (3, 1);
		c.print ("Enter a letter: "); //prompts user input
		letter = c.readLine (); //reads in a value
		if (letter.length () > 1 || letter.equals ("")) //tests if user entered a value too long or too short
		{
		    JOptionPane.showMessageDialog (null, "Please enter 1 letter.", "Error", JOptionPane.ERROR_MESSAGE); //error message

		}
		else if ((letter.charAt (0) < 'a' || letter.charAt (0) > 'z') && (letter.charAt (0) < 'A' || letter.charAt (0) > 'Z')) //tests if entered value is actually a letter
		{
		    JOptionPane.showMessageDialog (null, "Please enter only letters.", "Error", JOptionPane.ERROR_MESSAGE); //error message
		}
		else //if user entered an appropriate letter
		{
		    boolean saveLetter = true; //boolean to test whether the entered letter should be saved in the array oldChars or not
		    for (int i = 0 ; i < oldChars.length ; i++) //for loop to run through all the Strings in oldChars array
		    {
			if (oldChars [i].equalsIgnoreCase (letter)) //tests if user already entered the letter
			{
			    JOptionPane.showMessageDialog (null, "You already entered this letter!", "Error", JOptionPane.ERROR_MESSAGE); //error message
			    saveLetter = false; //tells program not to save the letter to oldChars array; this prevents duplicate letters being stored in the array
			    break; //breaks out of the loop to stop searching
			}
		    }
		    if (saveLetter) //tests if the letter should be saved in oldChars array; this means that the user entered value is appropriate
		    {
			oldChars [index] = letter; //enters in user entered letter to oldChars array
			index++; //moves the the next index in oldChars
			ask = false; //stops the while loop from asking the user for a letter
		    }
		}
	    }
	    underScores = checkChar (letter.toLowerCase ().charAt (0)); //calls checkChar blackbox method to do the processing
	    //finds the letter the user inputted and replaces it with a blank space
	    for (int i = 0 ; i < alphabet.length ; i++) //loops through the entire alphabet
	    {
		if (letter.equalsIgnoreCase (alphabet [i])) //finds the letter
		{
		    alphabet [i] = " "; //replaces the letter with a blank space
		}
	    }
	}
	c.setColor (Color.white);
	c.fillRect (0, 180, 200, 17); //covers up the old string so that the remaining underscore doesn't show through
	c.setColor (Color.black);
	for (int i = 0 ; i < word.length () ; i++) //draws the letters out one last time since the while loop ends early and doesn't draw the last entered letter
	{
	    c.setFont (new Font ("Arial", Font.PLAIN, 17));
	    c.drawString (underScores [i], 17 + i * 17, 180);
	}
	try
	{
	    Thread.sleep (500);
	}
	catch (Exception e)
	{
	}
	if (userWin && health > 0) //tests if the user won (if userWin is true and if the whole stickman hasn't been drawn yet)
	{
	    c.setColor (new Color (252, 222, 23)); //yellow
	    c.fillRect (70, 150, 500, 150); //draws yellow rectangle
	    c.setColor (Color.white);
	    c.drawRect (85, 165, 470, 120); //draws white border
	    c.setFont (new Font ("Lucida Bright", Font.BOLD, 33)); //sets font
	    c.drawString ("Congrats, You Won! :)", 140, 210); //congrats message
	}
	else //otherwise the user lost, this outputs
	{
	    c.setColor (Color.red); //red
	    c.fillRect (70, 150, 500, 150); //draws red rectangle
	    c.setColor (Color.white);
	    c.drawRect (85, 165, 470, 120); //draws white border
	    c.setFont (new Font ("Lucida Bright", Font.BOLD, 33)); //sets font
	    c.drawString ("Oops, you lost... :(", 170, 210); //oops message
	}
	//this is outside the if-else statement since this outputs either way
	c.setFont (new Font ("Monospaced", Font.BOLD, 16)); //sets font
	c.drawString ("The word was " + word + ".", 210 - word.length (), 245); //tells user what the word was and centers it
	c.drawString ("Press any key to continue.", 180, 270); //prompts user input
	c.getChar (); //waits for user to press any key
    }

    public void goodbye ()  //goodbye method
    {
	title (); //executes title method
	c.println ("Thank you for playing the Healthy Hangman game!"); //goodbye message
	c.println ("\nAll program rights belong to Mona Afshar, Shenelle Jayakody, Lily Phan, and Nancy Zhu."); //credits
	c.println("\nBrain image reference: www.123rf.com/photo_115025353_stock-vector-pixel-art-vector-pink-brain-isolated-on-white-background-.html");
c.println("Fire image reference: www.pinterest.ca/pin/704039354236421814/");
	c.println ("Created April 15, 2021. Healthy Hangman for LyonHacks"); //program information
	try
	{
	    Thread.sleep (5000); //times the animation
	}
	catch (Exception e)
	{
	}
	System.exit (0); //closes the output screen
    }


    public static void main (String[] args)
    {
	HealthyHangman d = new HealthyHangman (); //creates new HealthyHangman object
	d.splashScreen (); //executes splashScreen method once at the beginning of the program
	while (!d.choice.equals ("3")) //runs until the user wants to exit
	{
	    d.mainMenu (); //executes mainMenu
	    if (d.choice.equals ("1")) //tests if user chooses 1
	    {
		d.instructions (); //executes instructions method
	    }
	    else if (d.choice.equals ("2"))
	    {
		d.chooseWord (); //executes chooseWord method to choose the word the user needs to guess
		d.executeGame (); //executes executeGame method to enable the user to play the game
			 }
		 }
	d.goodbye (); //executes goodbye method once at the end of the program
    } // main method
} // HealthyHangman class
