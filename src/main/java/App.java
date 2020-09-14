import Entity.MyColor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.awt.*;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        MyColor red = new MyColor(Color.BLUE);
        MyColor green = new MyColor(Color.GREEN);
        MyColor black = new MyColor(Color.BLACK);

        System.out.println(red.toString());
        em.persist(red);
        em.persist(green);
        em.persist(black);
        green.setColor(Color.RED);

        em.getTransaction().commit();


        MyColor color1 = em.find(MyColor.class, 1);
        System.out.println( "db color = " + color1.toString());


    }




}
