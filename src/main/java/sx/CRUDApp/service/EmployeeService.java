package sx.CRUDApp.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sx.CRUDApp.models.Carpet;
import sx.CRUDApp.models.Employee;
import sx.CRUDApp.repo.CarpetRepo;
import sx.CRUDApp.repo.EmployeeRepo;
import sx.CRUDApp.security.EmployeeDetails;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Employee> findAll(){
        return employeeRepo.findAll().stream().toList();
    }

    public Optional<Employee> findOne(int id){
        return employeeRepo.findById(id);
    }

    @Transactional
    public void save(Employee employee){
        employee.setPass(passwordEncoder.encode(employee.getPass()));
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

    public Employee findByName(String name){
        if(employeeRepo.findByUsername(name).isPresent()){
            return  employeeRepo.findByUsername(name).get();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepo.findByUsername(username);

        if (employee.isEmpty()){
            throw new UsernameNotFoundException("нету человека с таким юзернеймом");
        }

        return new EmployeeDetails(employee.get());
    }
}
