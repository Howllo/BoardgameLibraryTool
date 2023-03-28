package UserInterface;

import java.awt.*;
import java.util.Comparator;

public class DimensionCompare implements Comparator<Dimension> {

    /**
     * @param d1 Takes in a Dimension object to be processed into area.
     * @return Returns the area of rectangle.
     */
    private static double area(Dimension d1){
        return d1.getWidth() * d1.getHeight();
    }


    /**
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return If o1 is equal to o2 then the value will be 0; d1 is less than d2 will be less than 0;
     * if d1 is greater than value greater than 0 will be the value.
     */
    @Override
    public int compare(Dimension o1, Dimension o2) {
        return Double.compare(area(o1), area(o2));
    }
}
