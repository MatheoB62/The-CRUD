package ApiAdressGouv;

public class Properties {
    private String label;
    private String score;
    private String housenumber;
    private String id;
    private String type;
    private String name;
    private String postcode;
    private String citycode;
    private String x;
    private String y;
    private String city;
    private String context;
    private String importance;
    private String street;

    public String getLabel() {
        return label;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public String getStreet() {
        return street;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "label='" + label + '\'' +
                ", score='" + score + '\'' +
                ", housenumber='" + housenumber + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", postcode='" + postcode + '\'' +
                ", citycode='" + citycode + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", city='" + city + '\'' +
                ", context='" + context + '\'' +
                ", importance='" + importance + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
