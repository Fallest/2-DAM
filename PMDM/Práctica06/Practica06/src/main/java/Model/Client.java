package Model;

import java.util.Date;

public class Client {

    // Atributos
    private int nif;
    private String pic;
    private Date reg_date;
    private String dir;
    private String pw;

    // Constructor
    public Client(int nif, String pic, Date reg_date, String dir, String pw) {
        this.nif = nif;
        this.pic = pic;
        this.reg_date = reg_date;
        this.dir = dir;
        this.pw = pw;
    }

    // Setters y Getters
    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
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
        return "Client{" + "nif=" + nif + ", pic=" + pic + ", reg_date=" + reg_date + ", dir=" + dir + ", pw=" + pw + '}';
    }
}
