import demo.facts.*;
import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CredTests {



    @Test
    public void amountAboveAverage() throws UnknownHostException {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("sbnz.integracija2", "drools-spring-kjar2", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        KieSession kieSession = kContainer.newKieSession("transaction-rules");

        Date r = new Date();
        r.setYear(2024);

        User user = new User("lazar@gmail.com","proba","Lazar", "Mijatovic" , "566566", Role.USER, new Date(), 2000.0, "",new Date() );
        Transaction transaction1 = new Transaction(UUID.fromString("d38bf5f3-90ca-4c42-967c-995912032975"), LocalDateTime.now(), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, true, true, "books");

        ArrayList<Transaction> t = new ArrayList<>();
        t.add(transaction1);
        user.setTransactions(t);

        Transaction test = new Transaction(UUID.randomUUID(),  LocalDateTime.of(2023, 6, 25, 2, 20, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 500.0, InetAddress.getByName("93.87.123.177") , false, false, false,"books");

        kieSession.insert(transaction1);
        kieSession.insert(user);
        kieSession.insert(test);


        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(true, test.isSuspicious());
        kieSession.dispose();


    }

    @Test
    public void bigTransactionOverNight() throws UnknownHostException {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("sbnz.integracija2", "drools-spring-kjar2", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        KieSession kieSession = kContainer.newKieSession("transaction-rules");

        Date r = new Date();
        r.setYear(2024);

        User user = new User("lazar@gmail.com","proba","Lazar", "Mijatovic" , "566566", Role.USER, new Date(), 2000.0, "",new Date() );
        Transaction test = new Transaction(UUID.randomUUID(),  LocalDateTime.of(2023, 6, 25, 2, 20, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 12000.0, InetAddress.getByName("93.87.123.177") , false, false, false,"books");

        kieSession.insert(user);
        kieSession.insert(test);


        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(true, test.isSuspicious());
        kieSession.dispose();


    }

    @Test
    public void unusualLocation() throws UnknownHostException {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("sbnz.integracija2", "drools-spring-kjar2", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        KieSession kieSession = kContainer.newKieSession("transaction-rules");

        Date r = new Date();
        r.setYear(2024);

        User user = new User("lazar@gmail.com","proba","Lazar", "Mijatovic" , "566566", Role.USER, new Date(), 2000.0, "",new Date() );
        Transaction transaction1 = new Transaction(UUID.fromString("d38bf5f3-90ca-4c42-967c-995912032975"), LocalDateTime.now(), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, true, true, "books");
        ArrayList<Transaction> t = new ArrayList<>();
        t.add(transaction1);
        user.setTransactions(t);


        Transaction test = new Transaction(UUID.randomUUID(),  LocalDateTime.of(2023, 6, 25, 20, 20, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 500.0, InetAddress.getByName("50.87.123.177") , false, false, false,"books");

        kieSession.insert(transaction1);
        kieSession.insert(user);
        kieSession.insert(test);


        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(true, test.isSuspicious());
        kieSession.dispose();


    }

    @Test
    public void multipleTransactionInShortTime() throws UnknownHostException {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("sbnz.integracija2", "drools-spring-kjar2", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        KieSession kieSession = kContainer.newKieSession("transaction-rules");

        Date r = new Date();
        r.setYear(2024);

        User user = new User("lazar@gmail.com","proba","Lazar", "Mijatovic" , "566566", Role.USER, new Date(), 2000.0, "",new Date() );
        Transaction transaction1 = new Transaction(UUID.fromString("d38bf5f3-90ca-4c42-967c-995912032975"), LocalDateTime.of(2023, 6, 25, 20, 21, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, true, true, "books");

        Transaction transaction2 = new Transaction(UUID.fromString("d38bf5f3-90ca-4c42-967c-995912032976"), LocalDateTime.of(2023, 6, 25, 20, 22, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, true, true, "books");

        Transaction transaction3 = new Transaction(UUID.fromString("d38bf5f3-90ca-4c42-967c-995912032977"), LocalDateTime.of(2023, 6, 25, 20, 23, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, true, true, "books");

        ArrayList<Transaction> t = new ArrayList<>();
        t.add(transaction1);
        t.add(transaction2);
        t.add(transaction3);
        user.setTransactions(t);


        Transaction test = new Transaction(UUID.randomUUID(),  LocalDateTime.of(2023, 6, 25, 20, 24, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 2.0, InetAddress.getByName("93.87.123.177") , false, false, false,"books");

        kieSession.insert(transaction1);
        kieSession.insert(transaction2);
        kieSession.insert(transaction3);
        kieSession.insert(user);
        kieSession.insert(test);


        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(true, test.isSuspicious());
        kieSession.dispose();


    }

    @Test
    public void multipleTransactionInShortTimeOnFarLocation() throws UnknownHostException {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("sbnz.integracija2", "drools-spring-kjar2", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        KieSession kieSession = kContainer.newKieSession("transaction-rules");

        Date r = new Date();
        r.setYear(2024);

        User user = new User("lazar@gmail.com","proba","Lazar", "Mijatovic" , "566566", Role.USER, new Date(), 2000.0, "",new Date() );
        Transaction transaction1 = new Transaction(UUID.fromString("d38bf5f3-90ca-4c42-967c-995912032975"), LocalDateTime.of(2023, 6, 25, 20, 21, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("192.168.1.101") , false, true, true, "books");

        Transaction transaction2 = new Transaction(UUID.fromString("d38bf5f3-90ca-4c42-967c-995912032976"), LocalDateTime.of(2023, 6, 25, 20, 22, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("192.168.1.254") , false, true, true, "books");


        Transaction transaction4 = new Transaction(UUID.fromString("d38bf5f3-90ca-4c42-967c-975912032975"), LocalDateTime.of(2023, 6, 21, 20, 21, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("172.30.130.66") , false, true, true, "books");

        ArrayList<Transaction> t = new ArrayList<>();
        t.add(transaction1);
        t.add(transaction2);
        t.add(transaction4);
        user.setTransactions(t);

        Transaction test = new Transaction(UUID.randomUUID(),  LocalDateTime.of(2023, 6, 25, 20, 24, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 2.0, InetAddress.getByName("172.30.130.66") , false, false, false,"books");

        kieSession.insert(transaction1);
        kieSession.insert(transaction2);
        kieSession.insert(transaction4);
        kieSession.insert(user);
        kieSession.insert(test);


        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(true, test.isSuspicious());
        kieSession.dispose();


    }


    @Test
    public void happyPath() throws UnknownHostException {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("sbnz.integracija2", "drools-spring-kjar2", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        KieSession kieSession = kContainer.newKieSession("transaction-rules");

        Date r = new Date();
        r.setYear(2024);

        User user = new User("lazar@gmail.com","proba","Lazar", "Mijatovic" , "566566", Role.USER, new Date(), 2000.0, "",new Date() );

        Transaction transaction1 = new Transaction(UUID.randomUUID(), LocalDateTime.of(2023, 6, 21, 20, 40, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, true, true, "books");

        Transaction transaction2 = new Transaction(UUID.randomUUID(), LocalDateTime.of(2023, 6, 21, 20, 30, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, true, true, "books");

        Transaction transaction3 = new Transaction(UUID.randomUUID(), LocalDateTime.of(2023, 6, 21, 20, 00, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, true, true, "books");

        Transaction transaction4 = new Transaction(UUID.randomUUID(), LocalDateTime.of(2023, 6, 21, 20, 10, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, true, true, "books");

        Transaction transaction5 = new Transaction(UUID.randomUUID(), LocalDateTime.of(2023, 6, 21, 20, 20, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, true, true, "books");

        ArrayList<Transaction> t = new ArrayList<>();
        t.add(transaction1);
        t.add(transaction2);
        t.add(transaction3);
        t.add(transaction4);
        t.add(transaction5);
        user.setTransactions(t);

        Transaction test = new Transaction(UUID.fromString("0adc5424-5265-469c-9a49-d0d517bb96b8"), LocalDateTime.of(2023, 6, 21, 20, 50, 45), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 2.0, InetAddress.getByName("93.87.123.177") , false, true, false, "books");


        kieSession.insert(transaction1);
        kieSession.insert(transaction2);
        kieSession.insert(transaction3);
        kieSession.insert(transaction4);
        kieSession.insert(transaction5);
        kieSession.insert(user);
        kieSession.insert(test);


        int firedRules = kieSession.fireAllRules();
        assertEquals(1, firedRules);
        assertEquals(false, test.isSuspicious());
        kieSession.dispose();


    }


}
