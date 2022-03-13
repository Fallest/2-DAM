package Dao;

import Model.Orders;
import java.util.ArrayList;

public interface IOrder {

    public ArrayList<Orders> getAll();

    public void update(Orders order);

    public void delete(Orders order);

    public void insert(Orders order);
}
