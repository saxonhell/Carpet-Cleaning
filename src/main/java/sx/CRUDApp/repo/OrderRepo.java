package sx.CRUDApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sx.CRUDApp.models.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
}
