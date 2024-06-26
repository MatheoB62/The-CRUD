package Graphics;

import Entity.AdresseEntity;
import Jpa.AdresseRepository;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Enum.*;

import java.util.Arrays;
import java.util.Objects;

public class ModificationDialog extends Stage {
    private static final AdresseRepository adresseRepository = new AdresseRepository();

    private AdresseEntity adresseEntity;
    private TextField numRueField;
    private TextField nomRueField;
    private TextField codePostalField;

    private ComboBox<String> typeBienComboBox;
    private ComboBox<String> typeChauffageComboBox;
    private ComboBox<String> typeEauChaudeComboBox;
    private ComboBox<String> classificationComboBox;
    private TextField villeField;

    public ModificationDialog(AdresseEntity adresseEntity) {
        this.adresseEntity = adresseEntity;

        // Création des champs de texte pour la modification
        numRueField = new TextField(adresseEntity.getNumRue() == null ? null : String.valueOf(adresseEntity.getNumRue()));
        nomRueField = new TextField(adresseEntity.getNomRue());
        codePostalField = new TextField(adresseEntity.getCodePostal());
        villeField = new TextField(adresseEntity.getVille());

        typeBienComboBox = new ComboBox<>();
        typeChauffageComboBox = new ComboBox<>();
        typeEauChaudeComboBox = new ComboBox<>();
        classificationComboBox = new ComboBox<>();


        //ajouter l'ENUM ClassificationBien
        Arrays.stream(ClassificationBien.values()).forEach(classification ->
                classificationComboBox.getItems().add(classification.toString()));

        Arrays.stream(TypeBien.values()).forEach(typeBien ->
                typeBienComboBox.getItems().add(typeBien.toString()));

        Arrays.stream(TypeChauffage.values()).forEach(typeChauffage ->
                typeChauffageComboBox.getItems().add(typeChauffage.toString()));

        Arrays.stream(TypeEauChaude.values()).forEach(typeEauChaude ->
                typeEauChaudeComboBox.getItems().add(typeEauChaude.toString()));

        //mettre les valeurs actuelles
        typeBienComboBox.setValue(adresseEntity.getBiens().iterator().next().getTypeBien());
        typeChauffageComboBox.setValue(adresseEntity.getBiens().iterator().next().getChauffage());
        typeEauChaudeComboBox.setValue(adresseEntity.getBiens().iterator().next().getTypeEauChaude());
        classificationComboBox.setValue(adresseEntity.getBiens().iterator().next().getClassification());

        // Création du formulaire de modification
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        gridPane.add(new Label("Numéro de rue:"), 0, 0);
        gridPane.add(numRueField, 1, 0);
        gridPane.add(new Label("Nom de rue:"), 0, 1);
        gridPane.add(nomRueField, 1, 1);
        gridPane.add(new Label("Code postal:"), 0, 2);
        gridPane.add(codePostalField, 1, 2);
        gridPane.add(new Label("Ville:"), 0, 3);
        gridPane.add(villeField, 1, 3);

        gridPane.add(new Label("Type de bien:"), 0, 4);
        gridPane.add(typeBienComboBox, 1, 4);
        gridPane.add(new Label("Type de chauffage:"), 0, 5);
        gridPane.add(typeChauffageComboBox, 1, 5);
        gridPane.add(new Label("Type d'eau chaude:"), 0, 6);
        gridPane.add(typeEauChaudeComboBox, 1, 6);
        gridPane.add(new Label("Classification:"), 0, 7);
        gridPane.add(classificationComboBox, 1, 7);


        // Création des boutons de validation et d'annulation
        Button validerButton = new Button("Valider");
        validerButton.setOnAction(event -> validerModification());
        Button annulerButton = new Button("Annuler");
        annulerButton.setOnAction(event -> close());

        gridPane.add(validerButton, 0, 8);
        gridPane.add(annulerButton, 1, 8);

        // Configuration de la scène
        Scene scene = new Scene(gridPane);
        setScene(scene);

        // Configuration de la fenêtre de dialogue
        setTitle("Modifier l'adresse");
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
    }

    private void validerModification() {
        adresseEntity.setNumRue(numRueField.getText() == null || Objects.equals(numRueField.getText(), "") ? null : Integer.parseInt(numRueField.getText()));
        adresseEntity.setNomRue(nomRueField.getText());
        adresseEntity.setCodePostal(codePostalField.getText());
        adresseEntity.setVille(villeField.getText());
        adresseEntity.getBiens().forEach(bien -> {
            bien.setClassification(classificationComboBox.getValue());
            bien.setTypeBien(typeBienComboBox.getValue());
            bien.setChauffage(typeChauffageComboBox.getValue());
            bien.setTypeEauChaude(typeEauChaudeComboBox.getValue());
        });


        adresseRepository.update(adresseEntity);

        close();
    }
}
