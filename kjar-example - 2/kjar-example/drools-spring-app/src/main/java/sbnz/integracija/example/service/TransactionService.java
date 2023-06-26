package sbnz.integracija.example.service;

import demo.facts.AccountPackage;
import demo.facts.Transaction;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.repository.AccountPackageRepository;
import sbnz.integracija.example.repository.TransactionRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository repository = TransactionRepository.getInstance();
    private final AccountPackageRepository accountPackageRepository = AccountPackageRepository.getInstance();

    public Transaction addTransaction(Transaction transaction) {
        //validacija, provera sredstva, provera racuna na koji se uplacuje
        boolean validation= transactionValidation(transaction);
        transaction.setValidateTransaction(validation);

        AccountPackage accPackage = accountPackageRepository.getPackageByAccountNumber(transaction.getSenderAccountNumber());
        AccountPackage benefacierPackage = accountPackageRepository.getPackageByAccountNumber(transaction.getBeneficiarAccountNumber());
        accPackage.setBalance(accPackage.getBalance() - transaction.getAmountTrans());
        benefacierPackage.setBalance(benefacierPackage.getBalance() + transaction.getAmountTrans());

        //pravila za detekciju prevare
        if(validation){



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
        if(!accPackage.getExpirationDate().equals(transaction.getCardExpirationDate())){return false;}
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
