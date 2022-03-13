package Dao;

import Model.Shops;
import java.util.ArrayList;

public interface IShop {

    public ArrayList<Shops> getAll();

    public void update(Shops shop);

    public void delete(Shops shop);

    public void insert(Shops shop);
}
