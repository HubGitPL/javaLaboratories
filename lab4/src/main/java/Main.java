import java.util.List;
import java.util.logging.Level;
//Zrodla
//https://www.javaguides.net/2019/11/hibernate-h2-database-example-tutorial.html
import entity.Mage;
import entity.Tower;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Student;
import util.HibernateUtil;

public class Main {

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        Database database = new Database();
        Tower tower1 = new Tower("tower1", 300);
        Mage mage1 = new Mage("mage1", 10);
        Mage mage2 = new Mage("mage2", 10);
        Mage mage3 = new Mage("mage3", 3);
        Mage mage4 = new Mage("mage4", 6);
        tower1.addMage(mage1);
        tower1.addMage(mage2);
        tower1.addMage(mage3);
        tower1.addMage(mage4);

        database.addEntity(tower1);

        database.getEntities("from Tower");


        database.getMagesWhereLevelIsGreaterThan(5);

        database.getEntities("from Mage");
        database.deleteEntity(tower1);
        database.getEntities("from Mage");

        Tower tower2 = new Tower("tower2", 400);
        for (int i = 0; i < 10; i++) {
            tower2.addMage(new Mage("mage" + i, i));
        }
        database.addEntity(tower2);
        database.getEntities("from Mage");
        database.deleteEntity(tower2);


        database.addEntity(tower1);
        Tower tower3 = new Tower("tower3", 250);
        Tower tower4 = new Tower("tower4", 200);
        tower1.addMage(new Mage("mage50", 8));
        tower1.addMage(new Mage("mage520", 1));
        tower3.addMage(new Mage("mage30", 86));
        tower4.addMage(new Mage("mage40", 36));
        database.addEntity(tower3);
        database.addEntity(tower4);
        database.getEntities("from Tower");
        database.getEntities("from Mage");

        database.getTowersWhereHeightIsLessThan(250);

        database.getMagesWhichAreGreaterThanLevelAndInTower(5, "tower1");

        database.closeSession();
    }
}