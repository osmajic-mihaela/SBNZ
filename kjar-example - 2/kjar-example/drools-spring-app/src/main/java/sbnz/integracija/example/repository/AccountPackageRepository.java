package sbnz.integracija.example.repository;

import demo.facts.AccountPackage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class AccountPackageRepository {

    private static AccountPackageRepository instance = new AccountPackageRepository();
    private List<AccountPackage> packages;


    public static AccountPackageRepository getInstance() {
        return instance;
    }

    public AccountPackageRepository(){

        initPackages();

    }


    private void initPackages(){
        packages = new ArrayList<>();

    }

    public List<AccountPackage> getPackages(){
        return packages;
    }


    public void addPackage(AccountPackage accountPackage){
        this.packages.add(accountPackage);
    }

    public AccountPackage getPackageById(UUID id)
    {
        return packages.stream().filter(u->u.getId().equals(id)).findFirst().orElse(null);
    }

    public AccountPackage getPackageByAccountNumber(int id)
    {
        return packages.stream().filter(u->u.getAccountNumber()==id).findFirst().orElse(null);
    }

    public ArrayList<AccountPackage> getPackageByUser(String email)
    {
        return (ArrayList<AccountPackage>) packages.stream()
                .filter(packge -> packge.getClientId().equals(email))
                .collect(Collectors.toList());
    }

}
