package sbnz.integracija.example.service;

import demo.facts.AccountPackage;
import demo.facts.Transaction;
import demo.facts.User;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.repository.AccountPackageRepository;
import sbnz.integracija.example.repository.TransactionRepository;
import sbnz.integracija.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository repository = TransactionRepository.getInstance();
    private final AccountPackageRepository accountPackageRepository = AccountPackageRepository.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();

    private final KieContainer kieContainer;
    @Autowired
    public TransactionService(KieContainer kieContainer) {
        System.out.println("proslo sranje");
        this.kieContainer = kieContainer;
        System.out.println("proslo sranje");
    }
    public Transaction addTransaction(Transaction transaction) {
        User user = userRepository.getUserByEmail(transaction.getSenderEmail());
        if(user==null)
        {
            transaction.setValidateTransaction(false);
            return transaction;
        }
        user.setTransactions(new ArrayList<>());
        user.setTransactions(repository.getTransactionsByClient(transaction.getSenderEmail()));

        //validacija, provera sredstva, provera racuna na koji se uplacuje
        boolean validation= transactionValidation(transaction);
        transaction.setValidateTransaction(validation);

        //pravila za detekciju prevare
        if(validation){
            System.out.println("detekcija prevare");
            KieSession kieSession = kieContainer.newKieSession("transaction-rules");
            kieSession.insert(transaction);
            kieSession.insert(user);
            kieSession.fireAllRules();
            kieSession.dispose();

            AccountPackage accPackage = accountPackageRepository.getPackageByAccountNumber(transaction.getSenderAccountNumber());
            AccountPackage benefacierPackage = accountPackageRepository.getPackageByAccountNumber(transaction.getBeneficiarAccountNumber());
            accPackage.setBalance(accPackage.getBalance() - transaction.getAmountTrans());
            benefacierPackage.setBalance(benefacierPackage.getBalance() + transaction.getAmountTrans());

            repository.addTransaction(transaction);
        }

        return transaction;
    }

    private boolean transactionValidation(Transaction transaction){
        AccountPackage accPackage = accountPackageRepository.getPackageByAccountNumber(transaction.getSenderAccountNumber());
        if(accPackage == null){
            return false;
        }

        AccountPackage benefacierPackage = accountPackageRepository.getPackageByAccountNumber(transaction.getBeneficiarAccountNumber());
        if(benefacierPackage == null){return false;}
        if(accPackage.getBalance() < transaction.getAmountTrans()){return false;}
        if(accPackage.getCvv() != transaction.getCvv()){return false;}
        //if(!accPackage.getExpirationDate().equals(transaction.getCardExpirationDate())){return false;}
        return  true;
    }


    public Transaction getTransactionById(UUID id) {
        return repository.getTransactionById(id);
    }

    public List<Transaction> getTransactionsByClientId(String email) {
        return repository.getTransactionsByClient(email);
    }

    public List<Transaction> getAllTransactions() {
        return repository.getTransactions();
    }
}
