package utils;

import javafx.scene.control.TextField;

public class NumField extends TextField {
    
    public NumField() {
        super();
        // Définir un écouteur pour contrôler le contenu du champ de texte
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                this.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public Integer getValue() {
        if (getText().isEmpty()) {
            return null;
        }
        return Integer.parseInt(getText());
    }
}