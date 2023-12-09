package by.bsuir.jewelry.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Necklace} class represents a necklace in the system with attributes such as
 * a unique identifier, a user owner, and a collection of gems with their quantities.
 * Each instance of this class corresponds to a record in the "necklaces" table of the database.
 * It is annotated with JPA annotations for mapping to database columns and relationships.
 *
 * <p>The class provides constructors for creating instances with specified attributes, as well as
 * default and all-arguments constructors. The fields are annotated with appropriate JPA annotations
 * to define their mapping to database columns, relationships, and fetch type.</p>
 *
 * <p>The necklace is associated with a user owner and contains a collection of gems. The relationship
 * is represented by a foreign key in the "necklaces" table, connecting to the "users" table through
 * the "user_id" column. The gems and their quantities are stored in a separate table called "necklace_gems,"
 * which acts as an intermediary table for the many-to-many relationship between necklaces and gems. Each
 * entry in this table consists of a necklace ID, a gem ID, and the quantity of that gem in the necklace.</p>
 *
 * <p>Since gems are stored in a collection, it is possible for the same type of gem to appear multiple
 * times in a single necklace. The relationship allows for flexibility in
 * representing various necklaces with different combinations of gems and quantities.</p>
 *
 * <p>Example usage:</p>
 * {@code
 * User user = new User("john_doe", "securePassword123", "USER");
 * Gem gem1 = new Gem(2.5, 1500.0, 90.0);
 * Gem gem2 = new Gem(3.0, 2000.0, 85.0);
 * Map<Gem, Integer> gemQuantities = new HashMap<>();
 * gemQuantities.put(gem1, 3);
 * gemQuantities.put(gem2, 1);
 * Necklace necklace = new Necklace(user, gemQuantities);
 * }
 *
 * @see Gem
 * @see User
 * @see Entity
 * @see Table
 * @see Column
 * @see GeneratedValue
 * @see Id
 * @see ManyToOne
 * @see JoinColumn
 * @see ElementCollection
 * @see CollectionTable
 * @see MapKeyJoinColumn
 * @see Data
 * @see NoArgsConstructor
 */

@Entity
@Table(name = "necklaces")
@Data
@NoArgsConstructor
public class Necklace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The user who owns the necklace. It cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The collection of gems in the necklace with their quantities.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "necklace_gems", joinColumns = @JoinColumn(name = "necklace_id"))
    @MapKeyJoinColumn(name = "gem_id")
    @Column(name = "quantity" )
    private Map<Gem, Integer> gemQuantities = new HashMap<>();

    /**
     * Constructs a new {@code Necklace} instance with the specified user owner and gem quantities.
     *
     * @param user           the user who owns the necklace
     * @param gemQuantities  the collection of gems with their quantities
     */
    public Necklace(User user, Map<Gem, Integer> gemQuantities) {
        this.user = user;
        this.gemQuantities = gemQuantities;
    }
}
