package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mobilier")
public class MobilierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_piece")
    private PieceEntity idPiece;

    @Column(name = "description")
    private String description;

    @Column(name = "nature")
    private String nature;

}