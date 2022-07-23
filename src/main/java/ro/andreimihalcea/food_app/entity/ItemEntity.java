package ro.andreimihalcea.food_app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.andreimihalcea.food_app.enums.ItemCategory;
import ro.andreimihalcea.food_app.enums.ItemSize;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private ItemSize size;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    @ManyToMany(mappedBy = "items")
    private List<OrderEntity> orders;

}