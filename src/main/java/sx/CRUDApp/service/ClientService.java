package sx.CRUDApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sx.CRUDApp.models.Carpet;
import sx.CRUDApp.models.Client;
import sx.CRUDApp.repo.CarpetRepo;
import sx.CRUDApp.repo.ClientRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public List<Client> findAll(){
        return clientRepo.findAll().stream().toList();
    }

    public Optional<Client> findOne(int id){
        return clientRepo.findById(id);
    }

    @Transactional
    public void save(Client client){
        clientRepo.save(client);
    }

    @Transactional
    public void delete(Client client){
        clientRepo.delete(client);
    }
}
