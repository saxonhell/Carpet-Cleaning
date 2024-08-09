package sx.CRUDApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sx.CRUDApp.models.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.fio = :fio, e.phoneNumber = :phoneNumber, e.role = :role, e.username = :username WHERE e.id = :id")
    void updateEmployee(int id, String fio, String phoneNumber, String role, String username);

}
