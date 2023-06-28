package sbnz.integracija.example.repository;

import demo.facts.AccountPackage;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
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

        Date r = new Date();
        r.setYear(2024);
        AccountPackage pack = new AccountPackage(UUID.fromString("0018db5f-4c4d-49a3-bd9c-d497b16cd202"),"lazar@gmail.com", "Lazar","Lazar", 11111,11112,123,50000.0,r);
        AccountPackage pack2 = new AccountPackage(UUID.fromString("80bfb86e-2190-4cd8-9b0a-c3f308d5cfa7"),"banka@gmail.com", "Banka","Banka", 33333,33333,123,50000.0,r);
        AccountPackage pack3 = new AccountPackage(UUID.fromString("0018db5f-4c4d-49a3-bd9c-d497b16cd204"),"random", "Lazar","Lazar", 55555,55555,123,50000.0,r);

        packages.add(pack);
        packages.add(pack2);
        packages.add(pack3);
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
