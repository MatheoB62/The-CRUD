package Graphics;

import Entity.AdresseEntity;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ResultArea extends ScrollPane {
    private VBox resultBox;

    public ResultArea() {
        resultBox = new VBox();
        resultBox.setSpacing(10);
        resultBox.setPadding(new Insets(10));
        this.setContent(resultBox);
    }

    public void displayResults(List<AdresseEntity> resultList) {
        resultBox.getChildren().clear(); // Effacer les résultats précédents

        resultList.forEach(adresse -> {
            AdresseBox adresseBox = new AdresseBox(adresse, this);
            resultBox.getChildren().add(adresseBox);
        });
    }
}
