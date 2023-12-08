package by.bsuir.jewelry.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "necklaces")
@Data
@NoArgsConstructor
public class Necklace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "necklace_gems", joinColumns = @JoinColumn(name = "necklace_id"))
    @MapKeyJoinColumn(name = "gem_id")
    @Column(name = "quantity" )
    private Map<Gem, Integer> gemQuantities = new HashMap<>();

    public Necklace(User user, Map<Gem, Integer> gemQuantities) {
        this.user = user;
        this.gemQuantities = gemQuantities;
    }
}
