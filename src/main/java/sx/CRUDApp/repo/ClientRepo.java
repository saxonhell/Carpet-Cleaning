package sx.CRUDApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sx.CRUDApp.models.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
}
