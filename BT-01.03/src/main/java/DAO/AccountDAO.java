package DAO;


import Entities.Account;
import Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AccountDAO {
    public void insertAccount(Account data){
        Session session= HibernateUtil.getFACTORY().openSession();
        Transaction transaction=session.beginTransaction();
        session.save(data);
        transaction.commit();
        session.close();
        return;
    }

    public int delData(Account data) {
        Session session=HibernateUtil.getFACTORY().openSession();
        Transaction transaction=session.beginTransaction();
        Account account = session.get(Account.class, data.getId());
        if (account != null) {
            session.delete(account);
        }
        transaction.commit();
        session.close();

        return 0;
    }
    public Account getAccountByFilePathAndFileName(String filePath,String fileName){
        Session session= HibernateUtil.getFACTORY().openSession();
        CriteriaBuilder cb=session.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);

        Predicate p1=cb.equal(root.get("pathName").as(String.class),filePath);
        Predicate p2=cb.equal(root.get("fileName").as(String.class),fileName);
        query.where(cb.and(p1,p2));
        Account student=(Account) session.createQuery(query.select(root)).getSingleResult();
        session.close();
        return student;
    }
}
