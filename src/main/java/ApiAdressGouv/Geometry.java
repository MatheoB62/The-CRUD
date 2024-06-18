package ApiAdressGouv;

import java.util.Arrays;

public class Geometry {
    private String type;
    private double[] coordinates;

    @Override
    public String toString() {
        return "Geometry{" +
                "type='" + type + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
