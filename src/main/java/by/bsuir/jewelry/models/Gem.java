package by.bsuir.jewelry.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code Gem} class represents a gemstone with attributes such as weight, price, and clarity.
 * Each instance of this class corresponds to a record in the "gems" table of the database.
 * It is annotated with JPA annotations for mapping to database columns.
 *
 * <p>The class provides constructors for creating instances with specified attributes, as well as
 * default and all-arguments constructors. The fields are annotated with appropriate JPA annotations
 * to define their mapping to database columns.</p>
 *
 * <p>Example usage:</p>
 * {@code
 * Gem gem = new Gem(2.5, 150.0, 85.0);
 * }
 * @see Entity
 * @see Table
 * @see Column
 * @see GeneratedValue
 * @see Id
 * @see Data
 * @see NoArgsConstructor
 * @see AllArgsConstructor
 */

@Entity
@Table(name = "gems")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The weight of the gem in carats.
     */
    @Column(name = "weight")
    private double weight;

    @Column(name = "price")
    private double price;

    /**
     * The clarity of the gem, represented as a percentage.
     */
    @Column(name = "clarity")
    private double clarity;

    /**
     * Constructs a new {@code Gem} instance with the specified weight, price, and clarity.
     *
     * @param weight  the weight of the gem in carats
     * @param price   the price of the gem in currency units
     * @param clarity the clarity of the gem as a percentage
     */
    public Gem(double weight, double price, double clarity) {
        this.weight = weight;
        this.price = price;
        this.clarity = clarity;
    }
}
