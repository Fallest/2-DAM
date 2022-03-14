package Dao;

import Model.Clients;
import Persistence.NewHibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientManager implements IClient {

    public static Clients tryLogin(String usr, String pwd) {
        // Comprueba que una pareca usuario-contraseña existe en la BD
        ArrayList<Clients> clients = (new ClientManager()).getAll();
        
        // Iteramos por todos los clientes
        for(Clients c: clients) {
            // Si encontramos un par de datos válido
            if (String.valueOf(c.getNif()).equals(usr) && c.getPw().equals(pwd)) {
                return c;
            }
        }
        
        return null;
        
    }

    @Override
    public ArrayList<Clients> getAll() {
        Session session = null;
        ArrayList<Clients> res = new ArrayList<>();

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Clients");
            res = (ArrayList<Clients>) query.list();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en ClientManager.getClients()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return res;
    }

    @Override
    public void update(Clients client) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(client);
            tx.commit();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en ClientManager.update()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Clients client) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(client);
            tx.commit();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en ClientManager.delete()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(Clients client) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(client);
            tx.commit();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en ClientManager.insert()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
