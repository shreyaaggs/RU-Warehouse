package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */ 
public class DeleteProduct {
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
            else warehouse.deleteProduct(StdIn.readInt());
            i++;
        }
        StdOut.println(warehouse);
    }
}
