package sbnz.integracija.example.service;

import demo.facts.Credit;
import demo.facts.CreditRequest;
import demo.facts.User;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.dto.CreditConditions;
import sbnz.integracija.example.dto.CreditRequestDTO;
import sbnz.integracija.example.repository.CreditRepository;
import sbnz.integracija.example.repository.UserRepository;

import java.util.List;

@Service
public class CreditService {

    private final UserRepository userRepository = UserRepository.getInstance();

    private final CreditRepository creditRepository = CreditRepository.getInstance();

    private final KieContainer kieContainer;

    public CreditService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }


    public boolean checkCreditRequest(CreditRequestDTO requestDto) throws Exception {
        CreditRequest request;
        try {
            request = generateRequest(requestDto);
        }
        catch (Exception ex){
            throw new Exception("User cannot be found");
        }

        User user = userRepository.getUser(requestDto.getClientId());

        List<Credit> clientCredits = creditRepository.getClientCredits(user.getEmail());

        KieSession kieSession = kieContainer.newKieSession("credit-request-approval-rules");

        CreditConditions conditions = new CreditConditions();
        kieSession.setGlobal("conditions", conditions);

        kieSession.insert(request);
        kieSession.insert(user);
        kieSession.insert(clientCredits);

        kieSession.fireAllRules();
        kieSession.dispose();

        if(conditions.getConditionFulfillmentPercentage()>=0.8)
            return true;
        else return false;
    }



    private CreditRequest generateRequest(CreditRequestDTO requestDto) throws Exception{
        User user = userRepository.getUser(requestDto.getClientId());
        if(user==null) throw new Exception("User cannot be found");

        return new CreditRequest(requestDto.getClientId(), requestDto.getRequestedCredit(), requestDto.getNumberOfInstallments(), user.getEmploymentType(), user.getEmploymentStart(), user.getEmployedUntil());
    }
}
