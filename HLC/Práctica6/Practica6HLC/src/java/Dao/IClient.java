package Dao;

import Model.Clients;
import java.util.ArrayList;

public interface IClient {

    public ArrayList<Clients> getAll();

    public void update(Clients client);

    public void delete(Clients client);

    public void insert(Clients client);
}
