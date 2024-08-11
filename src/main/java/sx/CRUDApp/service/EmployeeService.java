package sx.CRUDApp.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sx.CRUDApp.models.Carpet;
import sx.CRUDApp.models.Employee;
import sx.CRUDApp.repo.CarpetRepo;
import sx.CRUDApp.repo.EmployeeRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> findAll(){
        return employeeRepo.findAll().stream().toList();
    }

    public Optional<Employee> findOne(int id){
        return employeeRepo.findById(id);
    }

    @Transactional
    public void save(Employee employee){
        employeeRepo.save(employee);
    }

    @Transactional
    public void deleteById(int id){
        employeeRepo.deleteById(id);
    }

    @Transactional
    public void updateEmployee(Employee updatedEmployee) {
        if (employeeRepo.existsById(updatedEmployee.getId())) {
            employeeRepo.updateEmployee(
                    updatedEmployee.getId(),
                    updatedEmployee.getFio(),
                    updatedEmployee.getPhoneNumber(),
                    updatedEmployee.getRole().getId(),
                    updatedEmployee.getUsername()
            );
        } else {
            throw new EntityNotFoundException("Employee not found with id: " + updatedEmployee.getId());
        }
    }

}
