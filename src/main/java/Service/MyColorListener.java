package Service;

import Entity.MyColor;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class MyColorListener {

    @PrePersist
    public void prePersist(MyColor color){
        color.setCreated_at(new Date());
    }

    @PreUpdate
    public void preUpdate(MyColor color){
        color.setUpdated_at(new Date());
    }
}
