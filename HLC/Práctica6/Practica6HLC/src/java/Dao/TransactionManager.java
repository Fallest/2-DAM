package Dao;

import Model.Shops;
import Persistence.NewHibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionManager implements IShop {
    @Override
    public ArrayList<Shops> getAll() {
        Session session = null;
        ArrayList<Shops> res = new ArrayList<>();

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Shops");
            res = (ArrayList<Shops>) query.list();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en TransactionManager.getAll()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return res;
    }

    @Override
    public void update(Shops shop) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(shop);
            tx.commit();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en TransactionManager.update()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Shops shop) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(shop);
            tx.commit();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en TransactionManager.delete()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(Shops shop) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(shop);
            tx.commit();
        } catch (HibernateException ex) {
            System.out.println("ERROR: HibernateException en TransactionManager.insert()");
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
