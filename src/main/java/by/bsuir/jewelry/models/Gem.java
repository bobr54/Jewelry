package by.bsuir.jewelry.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "weight")
    private double weight;

    @Column(name = "price")
    private double price;

    @Column(name = "clarity")
    private double clarity;

    public Gem(double weight, double price, double clarity) {
        this.weight = weight;
        this.price = price;
        this.clarity = clarity;
    }
}
