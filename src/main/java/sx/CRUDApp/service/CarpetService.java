package sx.CRUDApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sx.CRUDApp.models.Carpet;
import sx.CRUDApp.repo.CarpetRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarpetService {

    private final CarpetRepo carpetRepo;

    @Autowired
    public CarpetService(CarpetRepo carpetRepo) {
        this.carpetRepo = carpetRepo;
    }

    public List<Carpet> findAll(){
        return carpetRepo.findAll().stream().toList();
    }

    public Optional<Carpet> findOne(int id){
        return carpetRepo.findById(id);
    }

    @Transactional
    public void save(Carpet carpet){
        carpetRepo.save(carpet);
    }

    @Transactional
    public void delete(Carpet carpet){
        carpetRepo.delete(carpet);
    }


}
