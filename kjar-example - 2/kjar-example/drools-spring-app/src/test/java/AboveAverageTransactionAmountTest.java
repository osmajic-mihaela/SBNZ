import static org.junit.Assert.assertNotNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import demo.facts.AccountPackage;
import demo.facts.Transaction;
import demo.facts.User;
import demo.facts.Role;
import org.drools.core.time.SessionPseudoClock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.internal.utils.KieService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(JUnit4ClassRunner.class)
@SpringBootTest
public class AboveAverageTransactionAmountTest {


    private final KieContainer kieContainer;
    private KieSession ksession;
    @Before
    public void start(KieContainer kieContainer) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        ksession = kc.newKieSession("transaction-rules");
    }
    @Test
    public void aboveAverageTransactionAmount() throws InterruptedException, UnknownHostException {

        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        ksession = kc.newKieSession("transaction-rules");
        assertNotNull(ksession);

        Date r = new Date();
        r.setYear(2024);

        User user4 = new User("lazar@gmail.com","proba","Lazar", "Mijatovic" , "566566", Role.USER, new Date(), 2000.0, "",new Date() );
        AccountPackage pack = new AccountPackage(UUID.fromString("0018db5f-4c4d-49a3-bd9c-d497b16cd202"),"lazar@gmail.com", "Lazar","Lazar", 11111,11112,123,50000.0,r);
        AccountPackage pack2 = new AccountPackage(UUID.fromString("80bfb86e-2190-4cd8-9b0a-c3f308d5cfa7"),"banka@gmail.com", "Banka","Banka", 33333,33333,123,50000.0,r);

        Transaction transaction1 = new Transaction(UUID.randomUUID(), LocalDateTime.now(), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, false, true);

        ksession.insert(user4);
        ksession.insert(transaction1);
        ksession.fireAllRules();
    }

    @After
    public void stop()
    {
        if (ksession != null) {
            ksession.dispose();
        }
    }
}
