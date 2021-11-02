public class Item {
    private String itemName;
    private int weight;
    private String description;

    public Item(String itemName, int weight, String description){
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


}
