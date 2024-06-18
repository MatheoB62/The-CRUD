package Graphics;

import ApiAdressGouv.AdresseRequester;
import ApiAdressGouv.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SugestedAdressDialog extends Stage {
    private AdresseRequester adresseRequester = new AdresseRequester();
    private ListView<String> listView;
    private Properties selectedAddress;
    private Map<String, Properties> suggestions = new HashMap<>();

    public SugestedAdressDialog(Integer numRue, String nomRue, String codePostal, String ville) throws Exception {
        // Initialisation des composants
        listView = new ListView<>();

        // Remplir la ListView avec les suggestions d'adresses
        sugestedAddress(numRue.toString(), nomRue, codePostal, ville);

        Button selectButton = new Button("Sélectionner");
        selectButton.setOnAction(event -> selectAddress());

        Button annulerButton = new Button("Annuler");
        annulerButton.setOnAction(event -> close());

        // Configuration de la disposition
        VBox vbox = new VBox(listView, selectButton, annulerButton);
        Scene scene = new Scene(vbox, 400, 300);
        setScene(scene);

        // Configuration de la fenêtre de dialogue
        setTitle("Suggestions d'Adresse");
        initModality(Modality.APPLICATION_MODAL);
    }

    private void sugestedAddress(String numRue, String nomRue, String codePostal, String ville) throws Exception {
        String adresse = numRue
                + " " + nomRue
                + " " + codePostal
                + " " + ville;

        adresseRequester.getAdresseRequester(adresse, 10);


        adresseRequester.getFeatures().forEach(
                feature -> suggestions.put(feature.getProperties().getLabel(), feature.getProperties())
        );

        ObservableList<String> items = FXCollections.observableArrayList(suggestions.keySet());
        listView.setItems(items);
    }

    private void selectAddress() {
        selectedAddress = suggestions.get(listView.getSelectionModel().getSelectedItem());
        close();
    }

    public Properties getSelectedAddress() {
        return selectedAddress;
    }
}
