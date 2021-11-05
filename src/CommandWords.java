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
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
        /*
        //add command words to valid commands
        validCommands = new HashMap<String,CommandWord>();
        validCommands.put("help", CommandWord.HELP);
        validCommands.put("quit", CommandWord.QUIT);
        validCommands.put("go", CommandWord.GO);
        validCommands.put("take", CommandWord.TAKE);
        validCommands.put("drop", CommandWord.DROP);
        validCommands.put("inventory", CommandWord.INVENTORY);
        validCommands.put("look", CommandWord.LOOK);
        validCommands.put("eat", CommandWord.EAT);
        validCommands.put("examine", CommandWord.EXAMINE);

        validCommands.put("?", CommandWord.UNKNOWN);

         */
    }



    /**
     * Check whether a given String is a valid command word.
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Find the CommandWord associated with a command word.
     *
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN if it is
     * not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if (command != null)
        {
            return command;
        }
        else
        {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * Print all valid commands to System.out.
     */
    public String getCommandList()
    {
        StringBuilder output= new StringBuilder();
        for (String command: validCommands.keySet()){
               output.append(command.toString()).append(", ");

        }
        return output.delete(output.length()-2,output.length()).toString();
    }
}
