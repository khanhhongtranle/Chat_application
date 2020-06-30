package Models;

import Entities.Account;
import Utils.HibernateUtil;
import Utils.ProtectPasswordUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AccountModel {
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction transaction;

    public static Account select(String enteredAccount, String enteredPassword){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<Account> result = new ArrayList<>();
        String exchangedPassword = ProtectPasswordUtil.change(enteredPassword);
        try{
            String hql = "SELECT a FROM Account as a WHERE a.accountName = :aN AND a.accountPassword = :aP";
            result =   session.createQuery(hql).setParameter("aN", enteredAccount).setParameter("aP", exchangedPassword).list();
            transaction.commit();
            if (!result.isEmpty()){
                return result.get(0);
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Account select(String username){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<Account> result = new ArrayList<>();
        try{
            String hql = "SELECT a FROM Account as a WHERE a.accountName = :aN ";
            result =   session.createQuery(hql).setParameter("aN", username).list();
            transaction.commit();
            if (!result.isEmpty()){
                return result.get(0);
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void insert(Account record){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        record.setAccountPassword(ProtectPasswordUtil.change(record.getAccountPassword()));
        try{
            session.save(record);
            transaction.commit();
        }catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
