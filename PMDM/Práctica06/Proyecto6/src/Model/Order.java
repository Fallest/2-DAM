package Model;

public class Order {

    // Atributos
    private int loc;
    private int nif_cli;
    private float price;

    // Constructor
    public Order(int loc, int nif_cli, float price) {
        this.loc = loc;
        this.nif_cli = nif_cli;
        this.price = price;
    }

    // Getters y Setters
    public int getLoc() {
        return loc;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    public int getNif_cli() {
        return nif_cli;
    }

    public void setNif_cli(int nif_cli) {
        this.nif_cli = nif_cli;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    // toString
    @Override
    public String toString() {
        return "Order{" + "loc=" + loc + ", nif_cli=" + nif_cli + ", price=" + price + '}';
    }

}
