package ApiAdressGouv;

public class Feature {
    private String type;
    private Geometry geometry;
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "type='" + type + '\'' +
                ", geometry=" + geometry +
                ", properties=" + properties +
                '}';
    }
}
