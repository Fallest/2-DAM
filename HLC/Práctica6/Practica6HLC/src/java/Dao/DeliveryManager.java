package Dao;

import Model.Delivery;
import Persistence.NewHibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeliveryManager implements IDelivery {
    @Override
    public ArrayList<Delivery> getAll() {
        Session session = null;
        ArrayList<Delivery> res = new ArrayList<>();

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Delivery");
            res = (ArrayList<Delivery>) query.list();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en DeliveryManager.getAll()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return res;
    }

    @Override
    public void update(Delivery delivery) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(delivery);
            tx.commit();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en DeliveryManager.update()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Delivery delivery) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(delivery);
            tx.commit();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en DeliveryManager.delete()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(Delivery delivery) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(delivery);
            tx.commit();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en DeliveryManager.insert()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
