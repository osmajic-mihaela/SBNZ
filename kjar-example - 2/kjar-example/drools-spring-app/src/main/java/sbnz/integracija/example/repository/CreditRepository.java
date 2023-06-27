package sbnz.integracija.example.repository;

import demo.facts.Credit;

import java.util.ArrayList;
import java.util.List;

public class CreditRepository {
    private static CreditRepository instance = new CreditRepository();
    private List<Credit> credits;

    public CreditRepository(){
        init();
    }

    private void init(){


    }

    public static CreditRepository getInstance() {
        return instance;
    }

    public static void setInstance(CreditRepository instance) {
        CreditRepository.instance = instance;
    }

    public List<Credit> getClientCredits(String clientId){
        List<Credit> clientCredits = new ArrayList<>();
        for(Credit c : credits){
            if(c.getClientId().equals(clientId))
                clientCredits.add(c);
        }

        return clientCredits;
    }

}
