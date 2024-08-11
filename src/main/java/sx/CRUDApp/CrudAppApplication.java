package sx.CRUDApp;

import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sx.CRUDApp.repo.RoleRepo;

import java.util.List;
import java.util.Optional;

@EnableWebMvc
@SpringBootApplication
public class CrudAppApplication {
    public static void main(String[] args) {
		SpringApplication.run(CrudAppApplication.class, args);
	}

}
