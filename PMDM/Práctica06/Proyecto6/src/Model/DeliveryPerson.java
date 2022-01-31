package Model;

public class DeliveryPerson {

    // Atributos
    private int del_cod;
    private String company;
    private String del_name;
    private String pw;

    // Constructor
    public DeliveryPerson(int del_cod, String company, String del_name, String pw) {
        this.del_cod = del_cod;
        this.company = company;
        this.del_name = del_name;
        this.pw = pw;
    }

    // Getters y Setters
    public int getDel_cod() {
        return del_cod;
    }

    public void setDel_cod(int del_cod) {
        this.del_cod = del_cod;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDel_name() {
        return del_name;
    }

    public void setDel_name(String del_name) {
        this.del_name = del_name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    // toString
    @Override
    public String toString() {
        return "DeliveryPerson{" + "del_cod=" + del_cod + ", company=" + company + ", del_name=" + del_name + ", pw=" + pw + '}';
    }

}
