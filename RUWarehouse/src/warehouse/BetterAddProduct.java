package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        Warehouse warehouse= new Warehouse();
        int input=StdIn.readInt();
        int i=0;
        while(StdIn.hasNextChar() && i<input){
            int day=StdIn.readInt(), id=StdIn.readInt();
            String name=StdIn.readString();
            int stock=StdIn.readInt(), demand=StdIn.readInt();
            warehouse.betterAddProduct(id, name, stock, day, demand);
            i++;
        }
        StdOut.println(warehouse);
    }
}
