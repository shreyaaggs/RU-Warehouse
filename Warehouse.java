package warehouse;

/*
 *
 * This class implements a warehouse on a Hash Table like structure, 
 * where each entry of the table stores a priority queue. 
 * Due to your limited space, you are unable to simply rehash to get more space. 
 * However, you can use your priority queue structure to delete less popular items 
 * and keep the space constant.
 * 
 * @author Ishaan Ivaturi
 */ 
public class Warehouse {
    private Sector[] sectors;
    
    // Initializes every sector to an empty sector
    public Warehouse() {
        sectors = new Sector[10];

        for (int i = 0; i < 10; i++) {
            sectors[i] = new Sector();
        }
    }
    
    /**
     * Provided method, code the parts to add their behavior
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void addProduct(int id, String name, int stock, int day, int demand) {
        evictIfNeeded(id);
        addToEnd(id, name, stock, day, demand);
        fixHeap(id);
    }

    /**
     * Add a new product to the end of the correct sector
     * Requires proper use of the .add() method in the Sector class
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    private void addToEnd(int id, String name, int stock, int day, int demand) {
        int i = id%10;
        Product p = new Product(id, name, stock, day, demand);
        sectors[i].add(p);
    }

    /**
     * Fix the heap structure of the sector, assuming the item was already added
     * Requires proper use of the .swim() and .getSize() methods in the Sector class
     * @param id The id of the item which was added
     */
    private void fixHeap(int id) {
        int i = id%10;
        if (sectors[i].getSize()!=1)
            sectors[i].swim(sectors[i].getSize());
    }

    /**
     * Delete the least popular item in the correct sector, only if its size is 5 while maintaining heap
     * Requires proper use of the .swap(), .deleteLast(), and .sink() methods in the Sector class
     * @param id The id of the item which is about to be added
     */
    private void evictIfNeeded(int id) {
        int i = id%10;
        if (sectors[i].getSize()==5){
            sectors[i].swap(1, 5);
            sectors[i].deleteLast();
            sectors[i].sink(1);
        }
    }

    /**
     * Update the stock of some item by some amount
     * Requires proper use of the .getSize() and .get() methods in the Sector class
     * Requires proper use of the .updateStock() method in the Product class
     * @param id The id of the item to restock
     * @param amount The amount by which to update the stock
     */
    public void restockProduct(int id, int amount) {
        int i = id%10;
        int s = sectors[i].getSize();
        for (int n = s; n>0; n--) {
            if (sectors[i].get(n).getId()==id){
                sectors[i].get(n).setStock(sectors[i].get(n).getStock()+amount);
                break;
            }
        }
    }
    
    /**
     * Delete some arbitrary product while maintaining the heap structure in O(logn)
     * Requires proper use of the .getSize(), .get(), .swap(), .deleteLast(), .sink() and/or .swim() methods
     * Requires proper use of the .getId() method from the Product class
     * @param id The id of the product to delete
     */
    public void deleteProduct(int id) {
        int i= id%10;
        int s = sectors[i].getSize();
        for (int n = s; n>0; n--){
            if (sectors[i].get(n).getId()==id){
                sectors[i].swap(n, s);
                sectors[i].deleteLast();
                sectors[i].sink(n);
            }
        }
    }
    
    /**
     * Simulate a purchase order for some product
     * Requires proper use of the getSize(), sink(), get() methods in the Sector class
     * Requires proper use of the getId(), getStock(), setLastPurchaseDay(), updateStock(), updateDemand() methods
     * @param id The id of the purchased product
     * @param day The current day
     * @param amount The amount purchased
     */
    public void purchaseProduct(int id, int day, int amount) {
        for (int n = sectors[id%10].getSize(); n>0; n--){
            if (sectors[id%10].get(n).getId()==id){
                if (sectors[id % 10].get(n).getStock() >= amount) {
                    sectors[id % 10].get(n).setStock(sectors[id % 10].get(n).getStock() - amount);
                    sectors[id % 10].get(n).setDemand(sectors[id % 10].get(n).getDemand() + amount);
                    sectors[id % 10].get(n).setLastPurchaseDay(day);
                    sectors[id % 10].sink(n);
                }
                break;
            }
        }
    }
    
    /**
     * Construct a better scheme to add a product, where empty spaces are always filled
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void betterAddProduct(int id, String name, int stock, int day, int demand) {
        int i=id%10;
        int size = sectors[i%10].getSize();
        do{
            int m = i%10;
            if (sectors[i%10].getSize()<5){
                sectors[m].add(new Product(id, name, stock, day, demand));
                sectors[m].swim(sectors[i%10].getSize());
                return;
            }
            i++;
        }while(i%10!=id%10);
        int ID = id;
        String NAME = name;
        int STOCK = stock;
        int DAY = day;
        int DEMAND = demand;
        addProduct(ID, NAME, STOCK, DAY, DEMAND);
    }

    /*
     * Returns the string representation of the warehouse
     */
    public String toString() {
        String warehouseString = "[\n";

        for (int i = 0; i < 10; i++) {
            warehouseString += "\t" + sectors[i].toString() + "\n";
        }
        
        return warehouseString + "]";
    }

    /*
     * Do not remove this method, it is used by Autolab
     */ 
    public Sector[] getSectors () {
        return sectors;
    }
}
