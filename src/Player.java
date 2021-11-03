import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

public class Player {
    /**
     * inv
     * look in inv
     */
    private int maxWeight;
    private int weight;
    private Room currentRoom;
    private Stack<Room> pastRooms;
    private HashMap<String, Item> inventory;


    public Player(int maxWeight){
        pastRooms = new Stack<>();
        inventory = new HashMap<>();
        this.maxWeight = maxWeight;
        weight = 0;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Returns the player to their previous room
     */
    public void goBack() {
        if (!pastRooms.empty()){
            currentRoom = pastRooms.pop();
            printLocationInfo();
        }
        else{
            System.out.println("You cannot go further back");
        }
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            pastRooms.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }
    public void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * look around the room command
     */
    public void look()
    {
        if (currentRoom.RoomHasItems()){
            System.out.println(currentRoom.getItemsString());
        }
        else
            System.out.println("This room is empty");
    }

    public void examine(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to examine
            System.out.println("Examine what?");
            return;
        }

        String item = command.getSecondWord();

        Item pickedItem = currentRoom.getItem(item);

        if (pickedItem == null) {
            System.out.println("there is no " + item + " in this room");
        }
        else {
            System.out.println(pickedItem.getLongDescription());
        }
    }

    /**
     * checks if the item is in the room, if you can carry it and if it is a pickupable item
     * @param command the item you want to take
     */
    public void pickUpItem(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to examine
            System.out.println("What do you want to take?");
            return;
        }

        String itemName = command.getSecondWord();

        if (currentRoom.containsItem(itemName)){
            Item pickedItem = currentRoom.getItem(itemName);
            if (weight + pickedItem.getWeight() <= maxWeight && pickedItem.canBePickedUp()){
                System.out.println("you put the " + itemName + " into your inventory");
                currentRoom.deleteItem(itemName);
                inventory.put(itemName,pickedItem);
                weight += pickedItem.getWeight();
            }
            else{
                System.out.println("I can't fit that in my pockets");
            }
        }
        else{
            System.out.println("there is no " + itemName + " in the room");
        }
    }

    /**
     * removes an item from the players inventory and places it in the current room
     * @param command the item you want to drop
     */
    public void dropItem(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to examine
            System.out.println("What do you want to drop?");
            return;
        }

        String itemName = command.getSecondWord();

        if (inventory.containsKey(itemName)){
            Item pickedItem = inventory.get(itemName);
                System.out.println("you drop your " + itemName + " on the ground");
                inventory.remove(itemName);
                currentRoom.addItem(itemName,pickedItem);
                weight -= pickedItem.getWeight();
            }
            else{
                System.out.println("There is no " + itemName + " in your inventory");
            }
        }

    /**
     * prints the inventory and carry weight of the player
     */
    public void checkInventory(){
        System.out.println("weight: " + weight + "/" + maxWeight);
        if (inventory.isEmpty()){
            System.out.println("Inventory is empty");
        }
        else
        {
            System.out.println("Your inventory contains:");
            for (String itemName:inventory.keySet()){
                System.out.println(itemName);
            }
        }
    }


}
