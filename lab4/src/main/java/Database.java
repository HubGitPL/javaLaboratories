import entity.Mage;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;
import java.util.*;
public class Database {
    private static Session session;

    public Database() {
        session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("Session created");
    }

    public void addEntity(Object entity) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            System.out.println("Entity" + entity + " added");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteEntity(Object entity) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            System.out.println("Entity" + entity + " deleted");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<?> getEntities(String query) {
        List<?> entities = null;
        try {
            entities = session.createQuery(query).list();
            System.out.println("Starting to retrieve entities");
            entities.forEach(entity -> System.out.println(entity));
            System.out.println("Entities retrieved");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    public List <?> getMagesWhereLevelIsGreaterThan(int level) {
        List<?> mages = null;
        try {
            mages = session.createQuery("from Mage where level > " + level).list();
            mages.forEach(mage -> System.out.println(mage));
            System.out.println("Function getMagesWhereLevelIsGreaterThan works!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mages;
    }

    public List <?> getTowersWhereHeightIsLessThan(int height) {
        List<?> towers = null;
        try {
            towers = session.createQuery("from Tower where height < " + height).list();
            towers.forEach(tower -> System.out.println(tower));
            System.out.println("Towers retrieved");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return towers;
    }

    public List<Mage> getMagesWhichAreGreaterThanLevelAndInTower(int level, String towerName) {
        List<Mage> mages = null;
        try {
            Query query = session.createQuery("from Mage where level > :level and tower.name = :towerName", Mage.class);
            query.setParameter("level", level);
            query.setParameter("towerName", towerName);
            mages = query.getResultList();
            mages.forEach(mage -> System.out.println(mage));
            System.out.println("Mages retrieved");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mages;
    }

    public void updateEntity(Object entity) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();

            System.out.println("Entity" + entity + " updated");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void closeSession() {
        session.close();
        System.out.println("Session closed");
    }
}
