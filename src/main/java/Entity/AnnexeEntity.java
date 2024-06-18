package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "annexe")
public class AnnexeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "num_bien")
    private BienEntity numBien;

    @Column(name = "surface")
    private Integer surface;

    @Column(name = "num_annexe")
    private Integer numAnnexe;

    @Column(name = "nb_piece")
    private Integer nbPiece;

    @Column(name = "description")
    private String description;

}