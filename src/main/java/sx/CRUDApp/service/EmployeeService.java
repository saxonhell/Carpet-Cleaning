package sx.CRUDApp.service;

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
    public void delete(Employee employee){
        employeeRepo.delete(employee);
    }
}
