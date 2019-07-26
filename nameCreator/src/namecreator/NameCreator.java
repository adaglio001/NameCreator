package namecreator;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *This program is intended to take an existing name and swap out different chosen 
 * parts/letters in order to create a new/transformed/unique username. You may 
 * chooses which letters to change and how you would like to change them.
 * @author Lutaru
 */
public class NameCreator 
{ 

    public static ArrayList<String> likedList = new ArrayList<String>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create a method that will intake the name to start with and put it in a variable
        //display this name and the number of letters in the name 
        //need a print message for each new state of the name -pointing at different parts of the name and -displaying the name before and after changes
        //ask user if they like the changes they made and want to keep them
        //if the user wants to save the name in the "liked names" list to keep track of ones he kinda liked along the way
        //
        
        System.out.println("* * * * * Welcome to the name Creator!!! * * * * *");
        
        //method to get the starting name
        String startingName = startingName();
        System.out.println("You have entered " + startingName + " as your starting name.");
        System.out.println("This name has " + startingName.length() + " characters. (0 - " + (startingName.length() - 1) + ")\n");
        
        System.out.println(startingName + " has been added to the Liked List!");
        likedList.add(startingName); //added the initially entered name to the list of Note-worthy names "liked list"
        
        int menuChoice;
        
        do
        {
            menuChoice = displayMenu();
        
            switch (menuChoice)
            {
                case 0:
                    exitNameCreator();
                    break;
                case 1:
                    startingName = addEndCharacters(startingName);
                    System.out.println("***Your current username is " + startingName + "***\n");
                    break;
                case 2:
                    startingName = addBegCharacters(startingName);
                    System.out.println("***Your current username is " + startingName + "***\n");
                    break;
                case 3:
                    startingName = changeEveryOccurence(startingName);
                    System.out.println("***Your current username is " + startingName + "***\n");
                    break;
                case 4:                    
                    startingName = changeCharacterRange(startingName);
                    System.out.println("***Your current username is " + startingName + "***\n");
                    break;
                case 5:
                    startingName = changeIndividualLetters(startingName); //change one letter at a time
                    System.out.println("***Your current username is " + startingName + "***\n");
                    break;
                case 6:
                    displayList();
                    break;
                default:
                    System.out.println("Invalid menu option selected");
                    break;
            }
        }while(menuChoice != 0);
    }
    
    
    public static String startingName()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the starting name: ");
        String enteredStartName = keyboard.nextLine();
        //System.out.println("You have entered: " + enteredStartName + " = confirmation 1");
        return enteredStartName;
    }
    
    
    public static int displayMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        int menuChoice;
        do
        {
            System.out.println("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
            System.out.println("***MAIN MENU*** \nChoose from the menu choices below to help you create your new name: (Enter a number 0-6)");
            System.out.println("1 = Add characters to the end");
            System.out.println("2 = Add characters to the beginning");
            System.out.println("3 = Change every occurence of a particular character at once");
            System.out.println("4 = Choose a range of characters to change");
            System.out.println("5 = Change individual characters- one at a time");
            System.out.println("6 = Display Liked List");
            System.out.println("0 = Exit NameCreator");
            menuChoice = keyboard.nextInt();
            
        }while(menuChoice > 6 || menuChoice < 0);
                        
        return menuChoice;
    }
    
    
    public static void displayCurrentUsername(String startingName)
    {
        System.out.println("Your current username is: " + startingName);
        
        //display the starting name with numbers underneath each character
        for(int i = 0; i < startingName.length(); i++)
        {
            System.out.print(startingName.charAt(i) + " "); 
        }
        System.out.println("");
        for(int i = 0; i < startingName.length(); i++)
        {
            System.out.print((i) + " "); 
        }
        System.out.println("");
    }
    
    
    public static String changeCharacterRange(String startingName)
    {
        Scanner keyboard = new Scanner(System.in);
        String correct;
        String newName = "default";
        int charsToChangeStart;
        int charsToChangeEnd;
        String addToList;
        
        System.out.println("(You have chosen to change a range of character(s))\n");
        
        displayCurrentUsername(startingName);
            do
            {
                System.out.println("Which range of characters would you like to change?");
                do
                {
                    System.out.print("(Inclusively) Enter the starting character number you'd like to change: ");
                    charsToChangeStart = keyboard.nextInt();
                }while(charsToChangeStart < 0 || charsToChangeStart > startingName.length() - 1);
                do
                {
                    System.out.print("(Inclusively) Enter the ending character number you'd like to change: ");
                    charsToChangeEnd = keyboard.nextInt();
                }while(charsToChangeEnd < charsToChangeStart || charsToChangeEnd > startingName.length() - 1);
                 
                keyboard.nextLine(); //clear the buffer
                System.out.println("What would you like to replace it with?");
                String replacementChars = keyboard.nextLine();
                
                if(charsToChangeEnd == startingName.length() - 1) //if they want to include changing the last letter of the username
                {
                    System.out.println("So you want to replace " + startingName.substring(charsToChangeStart) + " with " + replacementChars);
                    System.out.println("Is this correct? (y/n)");
                    correct = keyboard.nextLine();

                    if(correct.charAt(0) == 'y' || correct.charAt(0) == 'Y') //if this change is desirable, make the change
                    {
                        newName = swapper(startingName, charsToChangeStart, charsToChangeEnd, replacementChars);
                        System.out.println("Add " + newName + " to the Liked List? (y/n)");
                        addToList = keyboard.nextLine();
                        if(addToList.charAt(0) == 'y' || addToList.charAt(0) == 'Y')
                        {
                            likedList.add(newName);
                            System.out.println(newName + " added to Liked List! :)");
                        }
                    }
                }
                else //if they are changing characters that do not appear at the end of the username
                {
                    System.out.println("So you want to replace " + startingName.substring(charsToChangeStart, charsToChangeEnd + 1) + " with " + replacementChars);
                    System.out.println("Is this correct? (y/n)");
                    correct = keyboard.nextLine();

                    if(correct.charAt(0) == 'y' || correct.charAt(0) == 'Y')
                    {
                        newName = swapper(startingName, charsToChangeStart, (charsToChangeEnd + 1), replacementChars);
                        System.out.println("Add " + newName + " to the Liked List? (y/n)");
                        addToList = keyboard.nextLine();
                        if(addToList.charAt(0) == 'y' || addToList.charAt(0) == 'Y')
                        {
                            likedList.add(newName);
                            System.out.println(newName + " added to Liked List! :)");
                        }
                    }
                }
                
            }while(correct.charAt(0) == 'n' || correct.charAt(0) == 'N');
            startingName = newName;
            System.out.println("Your username has been changed to " + newName + "\n");  
        
        return startingName;
    }
    
    
    /*
    This method swaps a number of specified characters in the username with another 
    number of characters specified by the user.
    */
    public static String swapper(String origName, int beg, int end, String replaceWith)
    {
        String newName = "";
        
        if (end == origName.length() - 1) //if the changes appear at the end of the name
        {
            newName = origName.substring(0, beg) + replaceWith;
        }
        else if (beg == 0) //if the changes appear in the front of the username
        {
            newName = replaceWith + origName.substring(end);
        }
        else //if the changes appear in the middle of the  username
        {
            newName = origName.substring(0, beg) + replaceWith + origName.substring(end);
        }
        
        return newName;
    }
    
    
    public static String addEndCharacters(String startingName)
    {
        System.out.println("(You have chosen to add characters to the end)\n");
        String approval;
        Scanner keyboard;
        String extraEndChars;
        String addIt;
        
        displayCurrentUsername(startingName);
        
        do
        {
            System.out.println("Enter the characters you would like to add to the end of " + startingName);
            System.out.println("(or enter MENU to return to the Main Menu)");
            keyboard = new Scanner(System.in);
            extraEndChars = keyboard.nextLine();

            if(extraEndChars.equalsIgnoreCase("MENU"))
            {
                System.out.println("returning to main menu");
                break;
            }
            
            System.out.println("Your username will change to " + startingName + extraEndChars);
            System.out.println("Is this okay? (y/n)");
            approval = keyboard.nextLine();
            if(approval.charAt(0) == 'y' || approval.charAt(0) == 'Y')
            {
                startingName = startingName + extraEndChars; //full name with changes at end
                System.out.println("Add " + startingName + " to the Liked List? (y/n)");
                    addIt = keyboard.nextLine();
                    if(addIt.charAt(0) == 'y' || addIt.charAt(0) == 'Y')
                    {
                        likedList.add(startingName);
                        System.out.println(startingName + " added to Liked List! :)");
                    }
            }
        }while(approval.charAt(0) == 'n');
        return startingName;
    }
    
    
    public static String addBegCharacters(String startingName)
    {
        System.out.println("(You have chosen to add characters to the beginning)\n");
        String approval;
        Scanner keyboard;
        String extraBegChars;
        String addTo;
        
        displayCurrentUsername(startingName);
        
        do
        {
            System.out.println("Enter the characters you would like to add to the begining of " + startingName);
            System.out.println("(or enter MENU to return to the Main Menu)");
            keyboard = new Scanner(System.in);
            extraBegChars = keyboard.nextLine();
            
            if(extraBegChars.equalsIgnoreCase("MENU"))
            {
                System.out.println("returning to main menu");
                break;
            }

            System.out.println("Your username will change to " + extraBegChars + startingName);
            System.out.println("Is this okay? (y/n)");
            approval = keyboard.nextLine();
            if(approval.charAt(0) == 'y' || approval.charAt(0) == 'Y')
            {
                startingName = extraBegChars + startingName; //full name with changes at beginning
                System.out.println("Add " + startingName + " to the Liked List? (y/n)");
                    addTo = keyboard.nextLine();
                    if(addTo.charAt(0) == 'y' || addTo.charAt(0) == 'Y')
                    {
                        likedList.add(startingName);
                        System.out.println(startingName + " added to Liked List! :)");
                    }
            }
        }while(approval.charAt(0) == 'n');
        return startingName;
    }
    
    
    public static String changeEveryOccurence(String startingName)
    {
        Scanner keyboard = new Scanner(System.in);
        String cont = "no";
        String letterToChange;
        String changeTo;
        String isCorrect;
        String newName = "defaultName";
        String addIt;
        
        System.out.println("(You have chosen to change every occurence of a character)\n");
        
        do
        {
            System.out.println("Your current username is: " + startingName);
            System.out.println("Which character would you like to change? (Enter a character)");
            letterToChange = keyboard.nextLine();
            System.out.println("What would you like to change each occurence of " + letterToChange + " to?");
            changeTo = keyboard.nextLine();
            System.out.println("All the " + letterToChange + "'s will be changed to " + changeTo);
            System.out.println("Is this correct? (y/n)");
            isCorrect = keyboard.nextLine();
            if(isCorrect.charAt(0) == 'y' || isCorrect.charAt(0) == 'Y' )
            {
                newName = startingName.replace(letterToChange, changeTo);
                System.out.println("Add " + newName + " to the Liked List? (y/n)");
                    addIt = keyboard.nextLine();
                    if(addIt.charAt(0) == 'y' || addIt.charAt(0) == 'y')
                    {
                        likedList.add(newName);
                        System.out.println(newName + " added to Liked List! :)");
                    }
            }
            startingName = newName;
            System.out.println("Would you like to continue to change more occurences of characters? (y/n)");
            cont = keyboard.nextLine();
        }while(cont.charAt(0) == 'y' || cont.charAt(0) == 'Y');
        return newName;
    }
    
    
    public static String changeIndividualLetters(String startingName)
    {
        System.out.println("You have chosen to change one character at a time.\n");
        int numLetterToChange;
        String changeTo1;
        String newName = "default1";
        Scanner keyboard = new Scanner(System.in);
        String cont = "no"; //continue
        String isCorrect = "no";
        String addName;
        
        do
        {
            displayCurrentUsername(startingName);
            
            System.out.println("Which character would you like to change? (Enter a number corresponding to the character you'd like to change)");
            numLetterToChange = keyboard.nextInt();
            keyboard.nextLine(); //clear the buffer
            System.out.println("What would you like to change this character " + startingName.charAt(numLetterToChange) + " to?");
            changeTo1 = keyboard.nextLine();
            System.out.println("The character " + startingName.charAt(numLetterToChange) + " will be changed to " + changeTo1);
            System.out.println("Is this correct? (y/n)");
            isCorrect = keyboard.nextLine();
            
            if(isCorrect.charAt(0) == 'y' || isCorrect.charAt(0) == 'Y') //if correct, make the changes
            {
                if(numLetterToChange == 0) //if the letter to change is first in the name
                {
                    newName = changeTo1 + startingName.substring(1);
                }
                else if(numLetterToChange == (startingName.length() - 1)) //if letter to change is last in name
                {
                    newName = startingName.substring(0,(startingName.length()-1)) + changeTo1;
                }
                else //if the chosen letter in in the middle
                {
                    newName = startingName.substring(0,numLetterToChange) + changeTo1 + startingName.substring(numLetterToChange + 1);
                }
                
                System.out.println("Add " + newName + " to the Liked List? (y/n)");
                    addName = keyboard.nextLine();
                    if(addName.charAt(0) == 'y' || addName.charAt(0) == 'y')
                    {
                        likedList.add(newName);
                        System.out.println(newName + " added to Liked List! :)");
                    }
                startingName = newName;
                System.out.println("Would you like to continue changing individual characters? (y/n)");
                cont = keyboard.nextLine();    
            }    
        }while(cont.charAt(0) == 'y' || cont.charAt(0) == 'Y' || isCorrect.charAt(0) == 'n' || isCorrect.charAt(0) == 'N');
        return startingName;
    }
    
    public static void displayList() //print the arraylist
    {
        System.out.println("*LIKED LIST*");
        for(int i = 0; i < likedList.size(); i++)
        {
            System.out.println((i+1) + ") " + likedList.get(i));
        }
    }
    
    
    public static void exitNameCreator()
    {
        System.out.println("Thank you for choosing Name Creator! Goodbye! ");
        System.out.println("Your final Liked List:");
        displayList();
    }
}
