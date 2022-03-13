package Dao;

import Model.Delivery;
import java.util.ArrayList;

public interface IDelivery {

    public ArrayList<Delivery> getAll();

    public void update(Delivery delivery);

    public void delete(Delivery delivery);

    public void insert(Delivery delivery);
}
