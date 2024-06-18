package Jpa;

import Entity.AdresseEntity;

import java.util.List;


public class AdresseRepository extends JpaEntityManager {

    public void create(AdresseEntity adresse) {
        transaction.begin();
        adresse.getBiens().forEach(bien -> {
            bien.setAdresse(adresse);
            entityManager.persist(bien);
        });

        entityManager.persist(adresse);
        transaction.commit();
    }

    public List<AdresseEntity> findBySearchCriteria(String searchText, String classification, String typeBien, String typeChauffage, String typeEauChaude) {
        var isSearchTextEmpty = searchText.isEmpty();
        var isClassificationEmpty = classification == null;
        var isTypeBienEmpty = typeBien == null;
        var isTypeChauffageEmpty = typeChauffage == null;
        var isTypeEauChaudeEmpty = typeEauChaude == null;

        var querry = String.format("""
                SELECT a
                FROM AdresseEntity a LEFT JOIN BienEntity b
                    ON a.id = b.adresse
                WHERE (%s OR a.ville LIKE :searchText)
                    AND (%s OR b.classification = :classification)
                    AND (%s OR b.typeBien = :typeBien)
                    AND (%s OR b.chauffage = :typeChauffage)
                    AND (%s OR b.typeEauChaude = :typeEauChaude)
                """,
                isSearchTextEmpty ? "true" : "false",
                isClassificationEmpty ? "true" : "false",
                isTypeBienEmpty ? "true" : "false",
                isTypeChauffageEmpty ? "true" : "false",
                isTypeEauChaudeEmpty ? "true" : "false"
        );

        return entityManager.createQuery(querry, AdresseEntity.class)
                    .setParameter("searchText", "%" + searchText + "%")
                    .setParameter("classification", classification)
                    .setParameter("typeBien", typeBien)
                    .setParameter("typeChauffage", typeChauffage)
                    .setParameter("typeEauChaude", typeEauChaude)
                .getResultList();

    }

    public void update(AdresseEntity adresse) {
        transaction.begin();
        adresse.getBiens().forEach(bien -> {
            bien.setAdresse(adresse);
            entityManager.merge(bien);
        });
        entityManager.merge(adresse);
        transaction.commit();
    }

    public void delete(AdresseEntity adresse) {
        transaction.begin();

        adresse = entityManager.merge(adresse);

        adresse.getBiens().forEach(bien -> {
            if(!entityManager.contains(bien))
                bien = entityManager.merge(bien);
            entityManager.remove(bien);
        });

        entityManager.remove(adresse);
        transaction.commit();
    }
}
