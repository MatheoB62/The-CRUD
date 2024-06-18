package Entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "piece")
public class PieceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "num_bien")
    private BienEntity numBien;

    @Column(name = "id_affectation")
    private Integer idAffectation;

    @Column(name = "description")
    private String description;

    @Column(name = "surface")
    private Integer surface;

    @Column(name = "nb_murs")
    private Integer nbMurs;

    @Column(name = "nb_portes")
    private Integer nbPortes;

    @Column(name = "nb_fenetre")
    private Integer nbFenetre;

    @Column(name = "affectation_piece")
    private String affectationPiece;

    @OneToMany(mappedBy = "idPiece")
    private Set<MobilierEntity> mobiliers = new LinkedHashSet<>();

}