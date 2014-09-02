package org.motechproject.sms.it;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.sms.audit.SmsAuditService;
import org.motechproject.sms.audit.SmsRecordsDataService;
import org.motechproject.testing.osgi.BasePaxIT;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;

import javax.inject.Inject;

/**
 * Verify SmsService present & functional.
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class SmsServiceIT extends BasePaxIT{
    @Inject
    private SmsRecordsDataService smsRecordsDataService;

    @Inject
    private SmsAuditService smsAuditService;

    @Before
    public void cleanupDatabase() {
        smsRecordsDataService.deleteAll();
    }

    @Test
    public void verifyServiceFunctional() {
        getLogger().info("verifyServiceFunctional");


    }

}
