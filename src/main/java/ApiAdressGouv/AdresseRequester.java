package ApiAdressGouv;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static utils.ApiDataAcces.requestObject;
import static utils.Util.jsonToObjet;
import static utils.Util.requestParser;

public class AdresseRequester {
    private String type;
    private String version;
    private ArrayList<Feature> features;
    private String attribution;
    private String licence;
    private String query;
    private int limit;

    public List<Feature> getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return "AdresseRequester{" +
                "type='" + type + '\'' +
                ", version='" + version + '\'' +
                ", features=" + features +
                ", attribution='" + attribution + '\'' +
                ", licence='" + licence + '\'' +
                ", query='" + query + '\'' +
                ", limit=" + limit +
                '}';
    }

    public void getAdresseRequester(String adresse,int nbAdresse) throws Exception {
        String response = requestObject(new URL("https://api-adresse.data.gouv.fr/search/?q=" + requestParser(adresse) + "&limit=" + nbAdresse), "GET");
        this.completeRequest(response);
    }

    public void getAdresseRequester(String adresse) throws Exception{
        this.getAdresseRequester(adresse, 1);
    }

    public void getReverseAdresse(double latitude, double longitude) throws Exception {
        String response = requestObject(new URL("https://api-adresse.data.gouv.fr/reverse/?lon=" + longitude + "&lat=" + latitude), "GET");
        this.completeRequest(response);
    }

    private void completeRequest(String response) throws Exception {
        AdresseRequester adresseRequester = jsonToObjet(response, AdresseRequester.class);

        this.type = adresseRequester.type;
        this.version = adresseRequester.version;
        this.features = adresseRequester.features;
        this.attribution = adresseRequester.attribution;
        this.licence = adresseRequester.licence;
        this.query = adresseRequester.query;
        this.limit = adresseRequester.limit;

    }
}
