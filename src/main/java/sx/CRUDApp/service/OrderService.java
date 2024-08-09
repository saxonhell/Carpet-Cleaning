package sx.CRUDApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sx.CRUDApp.models.Carpet;
import sx.CRUDApp.models.Order;
import sx.CRUDApp.repo.CarpetRepo;
import sx.CRUDApp.repo.OrderRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> findAll(){
        return orderRepo.findAll().stream().toList();
    }

    public Optional<Order> findOne(int id){
        return orderRepo.findById(id);
    }

    @Transactional
    public void save(Order order){
        orderRepo.save(order);
    }

    @Transactional
    public void delete(int id){
        orderRepo.deleteById(id);
    }

    
}
