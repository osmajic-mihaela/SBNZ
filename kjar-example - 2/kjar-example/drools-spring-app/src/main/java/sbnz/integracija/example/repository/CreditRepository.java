package sbnz.integracija.example.repository;

import demo.facts.CreditRequest;
import demo.facts.CreditRequestType;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CreditRepository {
    private static CreditRepository instance= new CreditRepository();


    private List<CreditRequest> requests;

    public static CreditRepository getInstance() {
        return instance;
    }

    public CreditRepository()  {

        init();
    }

    private void init() {

        requests = new ArrayList<>();
        Date r = new Date();

    }

    public List<CreditRequest> getRequests() {
        return requests;
    }

    public void addRequest(CreditRequest request) {
        requests.add(request);
    }

    public CreditRequest acceptRequest(UUID id) {
       CreditRequest res= getRequestById(id);
       res.setCreditRequestType(CreditRequestType.ACCEPT);
       return res;
    }

    public CreditRequest rejectRequest(UUID id) {
        CreditRequest res= getRequestById(id);
        res.setCreditRequestType(CreditRequestType.REJECT);
        return res;
    }

    public CreditRequest getRequestById(UUID id) {
        return requests.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<CreditRequest> getRequestsByClient(String email) {
        return (ArrayList<CreditRequest>) requests.stream()
                .filter(packge -> packge.getEmail().equals(email))
                .collect(Collectors.toList());
    }

    public List<CreditRequest> getClientPendingRequests(String email) {
        return (ArrayList<CreditRequest>) requests.stream()
                .filter(packge -> packge.getEmail().equals(email))
                .filter(packge -> packge.getEmail().equals(email))
                .collect(Collectors.toList());
    }
}
