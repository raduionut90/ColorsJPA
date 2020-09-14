package Entity;

import Service.MyColorConverter;
import Service.MyColorListener;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
@Table(name = "color")
@EntityListeners(MyColorListener.class)
public class MyColor {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "rgb")
    @Convert(converter = MyColorConverter.class)
    private Color color;

    private Date created_at;
    private Date updated_at;

    public MyColor() {
    }

    public MyColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date prePersist) {
        this.created_at = prePersist;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date preUpdate) {
        this.updated_at = preUpdate;
    }

    @Override
    public String toString() {
        return "MyColor{" +
                "id=" + id +
                ", color=" + color +
                '}';
    }
}
