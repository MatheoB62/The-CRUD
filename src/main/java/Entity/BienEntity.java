package Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bien")
public class BienEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "surface")
    private Integer surface;

    @Column(name = "nbPiece")
    private Integer nbPiece;

    @Column(name = "type_eau_chaude")
    private String typeEauChaude;

    @Column(name = "chauffage")
    private String chauffage;

    @Column(name = "type_bien")
    private String typeBien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adresse")
    private AdresseEntity adresse;

    @Column(name = "classification")
    private String classification;

    @Column(name = "etage")
    private Integer etage;

    @Column(name = "num_logement")
    private Integer numLogement;

    @OneToMany(mappedBy = "numBien")
    private Set<AnnexeEntity> annexes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "numBien")
    private Set<PieceEntity> pieces = new LinkedHashSet<>();

    public BienEntity() {
        //Do not remove, used by JPA
    }

    public BienEntity(String typeEauChaude, String chauffage, String typeBien, String classification) {
        this.typeEauChaude = typeEauChaude;
        this.chauffage = chauffage;
        this.typeBien = typeBien;
        this.classification = classification;;
    }

    public String getTypeEauChaude() {
        return typeEauChaude;
    }

    public void setTypeEauChaude(String typeEauChaude) {
        this.typeEauChaude = typeEauChaude;
    }

    public String getChauffage() {
        return chauffage;
    }

    public void setChauffage(String chauffage) {
        this.chauffage = chauffage;
    }

    public String getTypeBien() {
        return typeBien;
    }

    public void setTypeBien(String typeBien) {
        this.typeBien = typeBien;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setAdresse(AdresseEntity adresse) {
        this.adresse = adresse;
    }

}