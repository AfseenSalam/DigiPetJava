package co.grandcircus.digipetjava;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




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
    @GetMapping("/accounts/{id}")
    public Account getById(@PathVariable("id") Long id) {
        return this.accountRepo.findById(id).orElse(null);
    }
    @GetMapping("/accounts/by-key/{apiKey}")
    public Account getByApiKey(@PathVariable("apiKey") String apiKey) {
        return this.accountRepo.findByApiKey(apiKey);
    }
    
     @PostMapping("/accounts")
    public Account addAccount(@RequestBody Account entity) {
       
        UUID uuid = UUID.randomUUID();
        entity.setApiKey(uuid.toString());
        entity.setId(null);
        accountRepo.save(entity);
        return entity;
    }
    @DeleteMapping("/accounts/{id}")
    public void DeleteAccount(@PathVariable ("id") Long id){
        this.accountRepo.deleteById(id);
    }
    @PutMapping("/accounts/{id}/add-credits")
    public Account UpdateAccount( @PathVariable Long id ,int amount){
        Account UpdateAccount = accountRepo.findById(id).orElse(null);
        UpdateAccount.setCredits(UpdateAccount.getCredits()+amount);
        this.accountRepo.save(UpdateAccount);
        return UpdateAccount;
    }
    @PostMapping("/accounts/{id}/action")
    public Account postByAction(@PathVariable Long id,@RequestParam String action) {
        Account UpdateAccount = accountRepo.findById(id).orElse(null);
        int newCredit =0;
        if(action.equals("CREATE")){
            newCredit=UpdateAccount.getCredits()-5;
        }
        else if(action.equals("HEAL")){
           newCredit = UpdateAccount.getCredits()-1;
        }else if(action.equals("TRAIN")){
            newCredit = UpdateAccount.getCredits()-1;
         }else if(action.equals("BATTLE")){
            newCredit = UpdateAccount.getCredits()-2;
         }
         UpdateAccount.setCredits(newCredit);
         this.accountRepo.save(UpdateAccount);
        return UpdateAccount;
    }
    @PostMapping("/accounts/by-key/{apiKey}/action")
    public Account postMethodName(@PathVariable("apiKey") String apiKey,@RequestParam String action) {
        Account UpdateAccount= accountRepo.findByApiKey(apiKey);
        int newCredit =0;
        if(action.equals("CREATE")){
            newCredit=UpdateAccount.getCredits()-5;
        }
        else if(action.equals("HEAL")){
           newCredit = UpdateAccount.getCredits()-1;
        }else if(action.equals("TRAIN")){
            newCredit = UpdateAccount.getCredits()-1;
         }else if(action.equals("BATTLE")){
            newCredit = UpdateAccount.getCredits()-2;
         }
         UpdateAccount.setCredits(newCredit);
         this.accountRepo.save(UpdateAccount);
        return UpdateAccount;
    }
    
    

}
