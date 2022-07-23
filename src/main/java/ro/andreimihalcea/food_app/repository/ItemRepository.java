package ro.andreimihalcea.food_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.andreimihalcea.food_app.entity.ItemEntity;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface that generate queries.
 */
@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    /**
     * Retrieve a list of items contained in an order.
     *
     * @param orderId the input {@link Long}
     * @return {@link List<ItemEntity>}
     */
    @Query(nativeQuery = true, value = "SELECT (i.*) FROM items AS i " +
            "JOIN order_items AS oi ON oi.item_id = i.id WHERE oi.order_id= :orderId")
    List<ItemEntity> findItemsFromOrderId(@Param("orderId") long orderId);

    /**
     * Retrieve a specific item by name price and size.
     *
     * @param name  the input {@link String}
     * @param price the input {@link Double}
     * @param size  the input {@link String}
     * @return {@link Optional<ItemEntity>}
     */
    Optional<ItemEntity> findByNameAndPriceAndSize(String name, double price, String size);

    /**
     * Retrieve a list of all items with a specific category.
     *
     * @param category the input {@link String}
     * @return {@link List<ItemEntity>}
     */
    List<ItemEntity> findByCategory(String category);
}