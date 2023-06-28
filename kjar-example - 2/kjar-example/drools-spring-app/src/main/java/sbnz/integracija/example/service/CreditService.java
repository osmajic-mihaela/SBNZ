package sbnz.integracija.example.service;

import demo.facts.AccountPackage;
import demo.facts.CreditRequest;
import demo.facts.Transaction;
import demo.facts.User;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.repository.AccountPackageRepository;
import sbnz.integracija.example.repository.CreditRepository;
import sbnz.integracija.example.repository.TransactionRepository;
import sbnz.integracija.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CreditService {
    private final TransactionRepository transactionRepository = TransactionRepository.getInstance();
    private final CreditRepository repository = CreditRepository.getInstance();
    private final AccountPackageRepository accountPackageRepository = AccountPackageRepository.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();

    private final KieContainer kieContainer;
    @Autowired
    public CreditService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }
    public CreditRequest addCreditRequest(CreditRequest request) {
        User user = userRepository.getUserByEmail(request.getEmail());
        if(user==null)
        {
            return null;
        }
        user.setTransactions(new ArrayList<>());
        user.setTransactions(transactionRepository.getTransactionsByClient(request.getEmail()));

        System.out.println(user.getAccountPackages().size());

        for(Transaction p:transactionRepository.getTransactions()){
            for(AccountPackage a: user.getAccountPackages()){
                System.out.println("acc:"+a.getAccountNumber()+"trans ben"+p.getBeneficiarAccountNumber());
                System.out.println("contains"+!user.getTransactions().contains(p));
                if((a.getAccountNumber() == p.getBeneficiarAccountNumber()) && (!user.getTransactions().contains(p))){
                    user.getTransactions().add(p);
                    System.out.println(p.getAmountTrans());
                }
            }

        }

        KieSession kieSession = kieContainer.newKieSession("credit-rules");
        kieSession.getAgenda().getAgendaGroup("credit").setFocus();
        kieSession.insert(request);
        kieSession.insert(user);
        kieSession.fireAllRules();
        kieSession.dispose();


        repository.addRequest(request);


        return request;
    }



    public CreditRequest getRequestById(UUID id) {
        return repository.getRequestById(id);
    }

    public List<CreditRequest> getRequestsByClient(String email) {
        return repository.getRequestsByClient(email);
    }

    public CreditRequest acceptRequest(UUID id) {
        return repository.acceptRequest(id);
    }

    public CreditRequest rejectRequest(UUID id) {
        return repository.acceptRequest(id);
    }

    public List<CreditRequest> getClientPendingRequests(String email) {
        var res = repository.getClientPendingRequests(email);
        if(res!= null){
            return res;
        }
        return new ArrayList<CreditRequest>();
    }
    public List<CreditRequest> getAllRequests() {
        return repository.getRequests();
    }
}
