package Graphics;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.NumField;
import Enum.*;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Footer extends HBox {

    private NumField numRueField;
    private TextField nomRueField;
    private NumField codePostalField;
    private TextField villeField;
    private ComboBox<String> typeBienComboBox;
    private ComboBox<String> typeChauffageComboBox;
    private ComboBox<String> typeEauChaudeComboBox;
    private ComboBox<String> classificationComboBox;
    private Button addButton;

    public Footer() {
        // Création des champs de texte
        numRueField = new NumField();
        numRueField.setPromptText("Numéro de rue");

        nomRueField = new TextField();
        nomRueField.setPromptText("Nom de rue");

        codePostalField = new NumField();
        codePostalField.setPromptText("Code postal");

        villeField = new TextField();
        villeField.setPromptText("Ville");

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

        // Création du bouton Ajouter
        addButton = new Button("Ajouter");


        Label classificationLabel = new Label("Classification : ");
        Label typeBienLabel = new Label("Type de bien : ");
        Label typeChauffageLabel = new Label("Type de chauffage : ");
        Label typeEauChaudeLabel = new Label("Type d'eau chaude : ");

        VBox classificationBox = new VBox();
        VBox typeBienBox = new VBox();
        VBox typeChauffageBox = new VBox();
        VBox typeEauChaudeBox = new VBox();

        classificationBox.getChildren().addAll(classificationLabel, classificationComboBox);
        typeBienBox.getChildren().addAll(typeBienLabel, typeBienComboBox);
        typeChauffageBox.getChildren().addAll(typeChauffageLabel, typeChauffageComboBox);
        typeEauChaudeBox.getChildren().addAll(typeEauChaudeLabel, typeEauChaudeComboBox);

        // Création des VBox pour l'alignement vertical
        VBox numRueBox = new VBox(new Label(""), numRueField);
        VBox nomRueBox = new VBox(new Label(""), nomRueField);
        VBox codePostalBox = new VBox(new Label(""), codePostalField);
        VBox villeBox = new VBox(new Label(""), villeField);
        VBox addButtonBox = new VBox(new Label(""), addButton);

        // Ajouter des marges pour améliorer l'espacement
        Insets elementMargin = new Insets(5);
        VBox[] vBoxes = {numRueBox, nomRueBox, codePostalBox, villeBox, classificationBox, typeBienBox, typeChauffageBox, typeEauChaudeBox};
        for (VBox vBox : vBoxes) {
            VBox.setMargin(vBox, elementMargin);
        }

        HBox content = new HBox(numRueBox, nomRueBox, codePostalBox, villeBox, classificationBox, typeBienBox, typeChauffageBox, typeEauChaudeBox, addButtonBox);
        content.setSpacing(10);

        TitledPane titledPane = new TitledPane("Ajouter un bien", content);
        titledPane.setCollapsible(false);

        // Ajout des éléments au pied de la fenêtre
        this.getChildren().add(titledPane);
        this.setSpacing(10);
        this.setPadding(new Insets(10));
    }

    public Button getAddButton() {
        return addButton;
    }

    public NumField getNumRueField() {
        return numRueField;
    }

    public TextField getNomRueField() {
        return nomRueField;
    }

    public NumField getCodePostalField() {
        return codePostalField;
    }

    public TextField getVilleField() {
        return villeField;
    }

    public ClassificationBien getClassification() {
        var classification = classificationComboBox.getValue();
        return classification == null ? null: ClassificationBien.valueOf(classification);
    }

    public TypeBien getTypeBien() {
        var typeBien = typeBienComboBox.getValue();
        return typeBien == null ? null: TypeBien.valueOf(typeBien);
    }

    public TypeChauffage getTypeChauffage() {
        var typeChauffage = typeChauffageComboBox.getValue();
        return typeChauffage == null ? null: TypeChauffage.valueOf(typeChauffage);
    }

    public TypeEauChaude getTypeEauChaude() {
        var typeEauChaude = typeEauChaudeComboBox.getValue();
        return typeEauChaude == null ? null: TypeEauChaude.valueOf(typeEauChaude);
    }


}

