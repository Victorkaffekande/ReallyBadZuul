import java.util.Collection;
import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Player player;
    private Parser parser;
    Room outside, theater, pub, lab, office, cellar;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        createPlayer();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room("in the dank cellar");

        // initialise room exits
        outside.setExit("east",theater);
        outside.setExit("south",lab);
        outside.setExit("west",pub);
        theater.setExit("north",outside);
        pub.setExit("east",outside);
        lab.setExit("north",outside);
        lab.setExit("east", office);
        office.setExit("west", lab);

        // create items
        Item table, chair, knife, wallet, cookie;

        table = new Item("table",20,"A plate with four legs",false);
        chair = new Item("chair",5,"looks like a nice place to rest",true);
        knife = new Item("knife", 2,"looks sharp",true);
        wallet = new Item("wallet",4,"Ez money",true);
        cookie = new Item("cookie", 2,"Eat me! +5 to max carry weight",false);
        //add items
        outside.addItem(table.getItemName(), table);
        outside.addItem(chair.getItemName(), chair);
        outside.addItem(knife.getItemName(), knife);
        outside.addItem(wallet.getItemName(), wallet);
        outside.addItem(cookie.getItemName(), cookie);
    }

    private void createPlayer(){
        player = new Player(10);
        player.setCurrentRoom(outside);  // start game outside
    }


    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        CommandWords commandWords = new CommandWords();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        player.printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

       CommandWord commandWord = command.getCommandWord();
       if (commandWord == null){
           commandWord = CommandWord.UNKNOWN;
       }
        switch (commandWord) {
            case UNKNOWN -> System.out.println("I don't know what you mean...");
            case HELP -> printHelp();
            case GO -> player.goRoom(command);
            case QUIT -> wantToQuit = quit(command);
            case LOOK -> player.look();
            case EXAMINE -> player.examine(command);
            case BACK -> player.goBack();
            case TAKE -> player.pickUpItem(command);
            case INVENTORY -> player.checkInventory();
            case DROP -> player.dropItem(command);
            case EAT -> player.eatCookie(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }


}
