package Graphics;

import ApiAdressGouv.Properties;
import Entity.AdresseEntity;
import Entity.BienEntity;
import Jpa.AdresseRepository;

import java.util.Set;

public interface EventHandler {
    default void searchProperties(Header header, ResultArea resultArea, AdresseRepository adresseRepository) {
        var searchText = header.getSearchField().getText();
        var classification = header.getClassificationComboBox().getValue();
        var typeBien = header.getTypeBienComboBox().getValue();
        var typeChauffage = header.getTypeChauffageComboBox().getValue();
        var typeEauChaude = header.getTypeEauChaudeComboBox().getValue();

        var result = adresseRepository.findBySearchCriteria(searchText, classification, typeBien, typeChauffage, typeEauChaude);

        resultArea.displayResults(result);
    }

    default void resetProperties(Header header, ResultArea resultArea, AdresseRepository adresseRepository) {
        header.getSearchField().clear();
        header.getClassificationComboBox().getSelectionModel().clearSelection();
        header.getTypeBienComboBox().getSelectionModel().clearSelection();
        header.getTypeChauffageComboBox().getSelectionModel().clearSelection();
        header.getTypeEauChaudeComboBox().getSelectionModel().clearSelection();
        searchProperties(header, resultArea, adresseRepository);
    }

    default void addAddress(Footer footer, AdresseRepository adresseRepository) throws Exception {
        Integer numRue = footer.getNumRueField().getValue();
        String nomRue = footer.getNomRueField().getText();
        String codePostal = footer.getCodePostalField().getText();
        String ville = footer.getVilleField().getText();

        if (numRue == null || nomRue.isEmpty() || codePostal.isEmpty() || ville.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs");
            return;
        }

        SugestedAdressDialog dialog = new SugestedAdressDialog(numRue, nomRue, codePostal, ville);
        dialog.showAndWait();
        Properties address = dialog.getSelectedAddress();

        if (address != null) {
            numRue =  address.getHousenumber() == null ? null : Integer.valueOf(address.getHousenumber());
            nomRue = address.getStreet();
            codePostal = address.getPostcode();
            ville = address.getCity();
        }


        //String typeEauChaude, String chauffage, String typeBien String classification
        var typeEauChaude = footer.getTypeEauChaude() == null ? null : footer.getTypeEauChaude().toString();
        var chauffage = footer.getTypeChauffage() == null ? null : footer.getTypeChauffage().toString();
        var typeBien = footer.getTypeBien() == null ? null : footer.getTypeBien().toString();
        var classification = footer.getClassification() == null ? null : footer.getClassification().toString();

        var bien = new BienEntity(typeEauChaude, chauffage, typeBien, classification);
        adresseRepository.create(new AdresseEntity(numRue, nomRue, codePostal, ville, Set.of(bien)));

        System.out.println("Données saisies : Numéro de rue : " + numRue + ", Nom de rue : " + nomRue +
                ", Code postal : " + codePostal + ", Ville : " + ville);

        footer.getNumRueField().clear();
        footer.getNomRueField().clear();
        footer.getCodePostalField().clear();
        footer.getVilleField().clear();
        footer.getTypeEauChaudeComboBox().getSelectionModel().clearSelection();
        footer.getTypeChauffageComboBox().getSelectionModel().clearSelection();
        footer.getTypeBienComboBox().getSelectionModel().clearSelection();
        footer.getClassificationComboBox().getSelectionModel().clearSelection();

    }
}
