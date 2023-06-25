package sbnz.integracija.example.service;

import demo.facts.AccountPackage;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.repository.AccountPackageRepository;
import sbnz.integracija.example.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AccountPackageService {
    private final AccountPackageRepository repository = AccountPackageRepository.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();

    public List<AccountPackage> getPackages() {
        return repository.getPackages();
    }

    public AccountPackage getPackageById(UUID id) {
        return repository.getPackageById(id);
    }

    public List<AccountPackage> getPackagesByUser(String email) {
        return repository.getPackageByUser(email);
    }

    public AccountPackage addPackage(AccountPackage accountPackage){
        repository.addPackage(accountPackage);
        userRepository.getLoggedUser().getAccountPackages().add(accountPackage);
        return accountPackage;
    }
}
