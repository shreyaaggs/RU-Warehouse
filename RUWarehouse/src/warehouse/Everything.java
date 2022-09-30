package warehouse;

/*
 * Use this class to put it all together.
 */ 
public class Everything {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        Warehouse warehouse = new Warehouse();
        int input=StdIn.readInt();
        int i=0;
        while(StdIn.hasNextChar() && i<input){
            String det = StdIn.readString();
            if (det.equals("add")){
                int day=StdIn.readInt(), id=StdIn.readInt();
                String name=StdIn.readString();
                int stock=StdIn.readInt(), demand=StdIn.readInt();
                warehouse.addProduct(id, name, stock, day, demand);
            } 
            else if (det.equals("purchase")){
                int day=StdIn.readInt(), id=StdIn.readInt(), amount=StdIn.readInt();
                warehouse.purchaseProduct(id, day, amount);
            }
            else if (det.equals("restock")){
                warehouse.restockProduct(StdIn.readInt(), StdIn.readInt());
            }
            else {
                warehouse.deleteProduct(StdIn.readInt());
            }
            i++;
        }
        StdOut.println(warehouse);

    }
}
