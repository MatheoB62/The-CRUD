package Graphics;

import Jpa.AdresseRepository;
import Jpa.JpaEntityManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Fenetre extends Application implements EventHandler {
    private static final JpaEntityManager jpaEntityManager = new JpaEntityManager();
    private static final AdresseRepository adresseRepository = new AdresseRepository();

    private Header header;
    private ResultArea resultArea;
    private Footer footer;

    public static void main(String[] args) {
        launch(args);
        jpaEntityManager.close();
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialisation des éléments
        resultArea = new ResultArea();
        header = new Header();
        footer = new Footer();

        searchProperties(header, resultArea, adresseRepository);
        eventHandler();

        // Création de la structure de la fenêtre
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(resultArea);
        root.setBottom(footer);

        // Affichage de la scène
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application Michka");
        primaryStage.show();
        primaryStage.setMaximized(true);

        // Set up the Timeline for periodic refresh
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            searchProperties(header, resultArea, adresseRepository);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void eventHandler() throws RuntimeException {
        header.getSearchButton().setOnAction(event -> searchProperties(header, resultArea, adresseRepository));
        header.getResetButton().setOnAction(event -> resetProperties(header, resultArea, adresseRepository));
        footer.getAddButton().setOnAction(event -> {
            try {
                addAddress(footer,adresseRepository);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
