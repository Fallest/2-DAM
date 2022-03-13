package Dao;

import Model.Orders;
import Persistence.NewHibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderManager implements IOrder {

    @Override
    public ArrayList<Orders> getAll() {
        Session session = null;
        ArrayList<Orders> res = new ArrayList<>();

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Orders");
            res = (ArrayList<Orders>) query.list();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en OrderManager.getAll()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return res;
    }

    @Override
    public void update(Orders order) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(order);
            tx.commit();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en OrderManager.update()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Orders order) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(order);
            tx.commit();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en OrderManager.delete()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(Orders order) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(order);
            tx.commit();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en OrderManager.insert()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
