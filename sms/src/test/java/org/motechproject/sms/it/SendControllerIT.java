package org.motechproject.sms.it;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.sms.audit.*;
import org.motechproject.testing.osgi.BasePaxIT;
import org.motechproject.testing.osgi.TestContext;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.motechproject.testing.osgi.http.SimpleHttpClient;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
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

    @Before
    public void waitForBeans() {
        // To prevent the annoying "BeanFactory not initialized or already closed" errors
        try { Thread.sleep(2000); } catch (InterruptedException e) {  }
    }

    @Test
    public void verifyFunctional() throws Exception {
        getLogger().info("verifyFunctional");

        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("localhost").setPort(TestContext.getJettyPort())
                .setPath("/sms/send");
        URI uri = builder.build();
        HttpGet httpGet = new HttpGet(uri);
        assertTrue(SimpleHttpClient.execHttpRequest(httpGet, HttpStatus.SC_OK));
    }
}
