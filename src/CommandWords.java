import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    private HashMap<String,CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        //add command words to valid commands
        validCommands = new HashMap<String,CommandWord>();
        validCommands.put("help", CommandWord.HELP);
        validCommands.put("quit", CommandWord.QUIT);
        validCommands.put("go", CommandWord.GO);
        validCommands.put("?", CommandWord.UNKNOWN);

        /*
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
         */
    }



    /**
     * Check whether a given String is a valid command word.
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        /*
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        /*
        for (String command: validCommands.keySet()) {
            if (Objects.equals(command, aString)){
                return true;
            }
        }
*/
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public String getCommandList()
    {
        StringBuilder output= new StringBuilder();
        for (String command: validCommands.keySet()){
               output.append(command.toString() + ", ");

        }
        return output.delete(output.length()-2,output.length()).toString();
    }
}
