package Service;

import Entity.MyColor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.awt.*;

@Converter
public class MyColorConverter implements AttributeConverter<Color, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Color color) {
        return color.getRGB();
    }

    @Override
    public Color convertToEntityAttribute(Integer integer) {
        return new Color(integer);
    }

//    @Override
//    public Integer convertToDatabaseColumn(MyColor color) {
//        String colorString = color.getColor();
//        int rbg = Color.getColor(colorString).getRGB();
//        return rbg;
//    }
//
//    @Override
//    public MyColor convertToEntityAttribute(Integer rgbCode) {
//        String color = new Color(rgbCode).toString();
//
//        return new MyColor(color);
//    }
}
