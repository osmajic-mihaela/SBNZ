import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import demo.facts.Transaction;
import org.drools.core.time.SessionPseudoClock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.internal.utils.KieService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;

public class AboveAverageTransactionAmountTest {

    private KieSession ksession;

    @Before
    public void start() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        ksession = kc.newKieSession("entrysession");

        new Thread() {
            @Override
            public void run() {
                ksession.fireUntilHalt();
            }
        }.start();
    }

    @Test
    public void aboveAverageTransactionAmount() throws InterruptedException {

        assertNotNull(ksession);

        Transaction transaction = new Transaction();
    }
}
