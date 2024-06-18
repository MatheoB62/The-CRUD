package Graphics;

import Entity.AdresseEntity;
import Entity.BienEntity;
import Jpa.AdresseRepository;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Objects;

public class AdresseBox extends HBox {
    private AdresseEntity adresseEntity;
    private Label addressLabel;
    private Button modifyButton;
    private Button removeButton;

    public AdresseBox(AdresseEntity adresseEntity, ResultArea resultArea) {
        this.adresseEntity = adresseEntity;
        this.setStyle("-fx-background-color: #af9bdc; -fx-padding: 10px;");
        this.setSpacing(5);

        this.setPrefWidth(0.9 * resultArea.getWidth());

        TextFlow addressTextFlow = new TextFlow();

        Text numRueText = new Text("Numéro de rue: ");
        Text numRueValue = new Text(adresseEntity.getNumRue() + "\n");
        numRueValue.setFont(Font.font("System", FontWeight.BOLD, 12));
        numRueText.setUnderline(true);

        Text nomRueText = new Text("Nom de rue: ");
        Text nomRueValue = new Text(adresseEntity.getNomRue() + "\n");
        nomRueValue.setFont(Font.font("System", FontWeight.BOLD, 12));
        nomRueText.setUnderline(true);

        Text codePostalText = new Text("Code postal: ");
        Text codePostalValue = new Text(adresseEntity.getCodePostal() + "\n");
        codePostalValue.setFont(Font.font("System", FontWeight.BOLD, 12));
        codePostalText.setUnderline(true);


        Text villeText = new Text("Ville: ");
        Text villeValue = new Text(adresseEntity.getVille() + "\n");
        villeValue.setFont(Font.font("System", FontWeight.BOLD, 12));
        villeText.setUnderline(true);

        addressTextFlow.getChildren().addAll(numRueText, numRueValue, nomRueText, nomRueValue, codePostalText, codePostalValue, villeText, villeValue);

        if (adresseEntity.getBiens() != null || !adresseEntity.getBiens().isEmpty()) {
            Text classificationText = new Text("Classification: ");
            Text classificationValue = new Text(adresseEntity.getBiens()
                    .stream()
                    .map(BienEntity::getClassification)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse("N/A") + "\n");
            classificationValue.setFont(Font.font("System", FontWeight.BOLD, 12));
            classificationText.setUnderline(true);

            Text typeBienText = new Text("Type de bien: ");
            Text typeBienValue = new Text(adresseEntity.getBiens()
                    .stream()
                    .map(BienEntity::getTypeBien)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse("N/A") + ",\n");
            typeBienValue.setFont(Font.font("System", FontWeight.BOLD, 12));
            typeBienText.setUnderline(true);

            Text chauffageText = new Text("Type de chauffage: ");
            Text chauffageValue = new Text(adresseEntity.getBiens()
                    .stream()
                    .map(BienEntity::getChauffage)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse("N/A") + "\n");
            chauffageValue.setFont(Font.font("System", FontWeight.BOLD, 12));
            chauffageText.setUnderline(true);

            Text eauChaudeText = new Text("Type eau chaude: ");
            Text eauChaudeValue = new Text(adresseEntity.getBiens()
                    .stream()
                    .map(BienEntity::getTypeEauChaude)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse("N/A") + "\n");
            eauChaudeValue.setFont(Font.font("System", FontWeight.BOLD, 12));
            eauChaudeText.setUnderline(true);

            addressTextFlow.getChildren().addAll(classificationText, classificationValue, typeBienText, typeBienValue, chauffageText, chauffageValue, eauChaudeText, eauChaudeValue);
        }


        modifyButton = new Button("Modifier");
        modifyButton.setOnAction(event -> modifyAddress());

        removeButton = new Button("Supprimer");
        removeButton.setOnAction(event -> removeAddress());

        // Create a flexible region to push the buttons to the right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(addressTextFlow, spacer, modifyButton, removeButton);
    }

    private void modifyAddress() {
        ModificationDialog dialog = new ModificationDialog(adresseEntity);
        dialog.showAndWait();
    }

    private void removeAddress() {
        AdresseRepository adresseRepository = new AdresseRepository();
        adresseRepository.delete(adresseEntity);
    }
}

