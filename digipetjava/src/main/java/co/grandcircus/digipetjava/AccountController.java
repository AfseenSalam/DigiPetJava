package co.grandcircus.digipetjava;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepo;

    @GetMapping("/")
    public String getDefault() {
        return "Digi Pets";
    }
    @GetMapping("/accounts")
    public List<Account> getAccount() {
        return this.accountRepo.findAll();
    }

    
     @PostMapping("/accounts")
    public Account addCar(@RequestBody Account entity) {
       
        UUID uuid = UUID.randomUUID();
        entity.setApiKey(uuid.toString());
        entity.setId(null);
        accountRepo.save(entity);
        return entity;
    }

}
