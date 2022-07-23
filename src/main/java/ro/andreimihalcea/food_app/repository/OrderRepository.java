package ro.andreimihalcea.food_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.andreimihalcea.food_app.entity.OrderEntity;

import java.util.List;

/**
 * Repository interface that generate queries.
 */
@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity,Long> {

    /**
     * Retrieve a list of all orders by user id.
     *
     * @param userId the input {@link Long}
     * @return {@link List<OrderEntity>}
     */
    @Query(nativeQuery = true, value = "SELECT * FROM orders AS o WHERE o.user_id= :userId")
    List<OrderEntity> findOrdersForUserId(@Param("userId") long userId);
}
