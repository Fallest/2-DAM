package Model;

public class ShopTransaction {

    // Atributos
    private int loc;
    private int del_cod;
    private int transaction_code;
    private String shop_name;
    private float del_costs;

    // Constructor
    public ShopTransaction(int loc, int del_cod, int transaction_code, String shop_name, float del_costs) {
        this.loc = loc;
        this.del_cod = del_cod;
        this.transaction_code = transaction_code;
        this.shop_name = shop_name;
        this.del_costs = del_costs;
    }

    // Setters y Getters
    public int getLoc() {
        return loc;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    public int getDel_cod() {
        return del_cod;
    }

    public void setDel_cod(int del_cod) {
        this.del_cod = del_cod;
    }

    public int getTransaction_code() {
        return transaction_code;
    }

    public void setTransaction_code(int transaction_code) {
        this.transaction_code = transaction_code;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public float getDel_costs() {
        return del_costs;
    }

    public void setDel_costs(float del_costs) {
        this.del_costs = del_costs;
    }

    // toString
    @Override
    public String toString() {
        return "ShopTransaction{" + "loc=" + loc + ", del_cod=" + del_cod + ", transaction_code=" + transaction_code + ", shop_name=" + shop_name + ", del_costs=" + del_costs + '}';
    }

}
