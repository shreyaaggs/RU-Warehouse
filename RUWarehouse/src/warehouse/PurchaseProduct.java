package warehouse;

public class PurchaseProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        Warehouse warehouse = new Warehouse();
        int input=StdIn.readInt();
        int i=0;
        while(StdIn.hasNextChar() && i<input){
            if (StdIn.readString().equals("add")){
                int day=StdIn.readInt(), id=StdIn.readInt();
                String name=StdIn.readString();
                int stock=StdIn.readInt(), demand=StdIn.readInt();
                warehouse.addProduct(id, name, stock, day, demand);
            } 
            else{
                int day=StdIn.readInt(), id=StdIn.readInt(), amount=StdIn.readInt();
                warehouse.purchaseProduct(id, day, amount);
            }
            i++;
        }
        StdOut.println(warehouse);
    }
}
