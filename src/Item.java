public class Item {
    private final String itemName;
    private final int weight;
    private final String description;
    private final boolean canBePickedUp;

    public Item(String itemName, int weight, String description,boolean canBePickedUp){
       this.canBePickedUp = canBePickedUp;
       this.itemName = itemName;
       this.weight = weight;
       this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getLongDescription(){
        return "Name: " + getItemName() +"\n" +
                "Weight: " + getWeight() + "\n" +
                "Description: " + getDescription();
    }

    public boolean canBePickedUp(){
        return canBePickedUp;
    }
}
