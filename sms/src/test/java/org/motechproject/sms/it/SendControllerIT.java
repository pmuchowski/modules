package org.motechproject.sms.it;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.security.service.MotechUserService;
import org.motechproject.sms.audit.SmsRecordsDataService;
import org.motechproject.sms.service.OutgoingSms;
import org.motechproject.testing.osgi.BasePaxIT;
import org.motechproject.testing.osgi.TestContext;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.motechproject.testing.osgi.http.SimpleHttpClient;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.Assert.assertTrue;

/**
 * Verify SendController present & functional.
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class SendControllerIT extends BasePaxIT {
    @Inject
    private SmsRecordsDataService smsRecordsDataService;

    @Inject
    private MotechUserService motechUserService;

    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";

    @Before
    public void waitForBeans() {
        // To prevent the annoying "BeanFactory not initialized or already closed" errors
        try { Thread.sleep(2000); } catch (InterruptedException e) {  }
    }

    @Before
    public void createUser() {
        motechUserService.register(USERNAME, PASSWORD, "user@email.com", "user.id", new ArrayList<String>(), Locale.US);
        getLogger().info("Created user '{}'", USERNAME);
    }

    @Test
    public void verifyFunctional() throws Exception {
        getLogger().info("verifyFunctional");

        OutgoingSms outgoingSms = new OutgoingSms("foo", Arrays.asList("12065551212"), "hello, world");
        ObjectMapper mapper = new ObjectMapper();
        String outgoingSmsJson = mapper.writeValueAsString(outgoingSms);

        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("localhost").setPort(TestContext.getJettyPort())
                .setPath("/sms/send");

        HttpPost httpPost = new HttpPost(builder.build());
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(new StringEntity(outgoingSmsJson));

        //TODO: Enable code below when we figure out a way around security
        // We're specifying a nonexistent config so the controller should respond with a 404
        assertTrue(SimpleHttpClient.execHttpRequest(httpPost, HttpStatus.SC_NOT_FOUND, USERNAME, PASSWORD));

        //TODO: Also figure out how to create configs an then use them to pretend send using an SimpleHttpServer that
        //TODO: responds the way an SMS provider would.

    }
}
