package sx.CRUDApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sx.CRUDApp.models.Carpet;

@Repository
public interface CarpetRepo extends JpaRepository<Carpet, Integer> {
}
