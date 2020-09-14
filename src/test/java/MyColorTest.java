import Entity.MyColor;
import org.junit.Assert;
import org.junit.Test;
import setup.TransactionalSetup;
import org.unitils.reflectionassert.ReflectionAssert;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class MyColorTest extends TransactionalSetup {

    @Test
    public void sendColorToDB(){
        MyColor myColor = new MyColor();
        myColor.setColor(Color.GREEN);

        em.persist(myColor);
        flushAndClear();

        // verify database state with a native query
        {
            List<Object[]> data = em.createNativeQuery("select id,rgb from color").getResultList();
            Assert.assertEquals(1, data.size());
            for (Object[] objects : data) {
                Assert.assertEquals(1, objects[0]);
                Assert.assertEquals(-16711936, objects[1]);
            }
        }

        // verify
        MyColor myColor2 = em.find(MyColor.class, myColor.getId());
        Assert.assertNotNull(myColor2);
        ReflectionAssert.assertReflectionEquals(myColor, myColor2);
    }

    @Test
    public void prePersistTest(){
        MyColor myColor1 = new MyColor();
        myColor1.setColor(Color.GREEN);
        MyColor myColor2 = new MyColor();
        myColor2.setColor(Color.RED);
        em.persist(myColor1);
        em.persist(myColor2);
        flushAndClear();


        MyColor myColor3 = em.find(MyColor.class, 1);
        Assert.assertNotNull(myColor3);
        Assert.assertEquals(myColor3.getId(), myColor1.getId());
        Assert.assertEquals(myColor3.getColor(), myColor1.getColor());

        Assert.assertNotEquals(myColor1.getCreated_at(), myColor2.getCreated_at());

        myColor3.setColor(Color.CYAN);
        em.merge(myColor2);
        flushAndClear();

        // verify listener filled fields
        Assert.assertNotEquals(myColor3.getUpdated_at(), myColor3.getCreated_at());

    }

}
