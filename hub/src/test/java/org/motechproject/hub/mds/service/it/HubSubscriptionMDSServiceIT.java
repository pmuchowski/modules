package org.motechproject.hub.mds.service.it;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.hub.mds.HubSubscription;
import org.motechproject.hub.mds.service.HubSubscriptionMDSService;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;


@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class HubSubscriptionMDSServiceIT {

    @Inject
    private HubSubscriptionMDSService hubSubscriptionMDSService;
    
    private String callbackUrl = "http://callback/url";
    
    @Test
    public void testHubSubscription() {
        List<HubSubscription> hubSubscriptions = hubSubscriptionMDSService.findSubByCallbackUrl(callbackUrl);
        Assert.assertNotNull(hubSubscriptions);
        Assert.assertEquals(0, hubSubscriptions.size());
        
        HubSubscription hubSubscription = new HubSubscription();
        hubSubscription.setCallbackUrl(callbackUrl);
        hubSubscription.setHubSubscriptionStatusId(3);
        hubSubscription.setHubTopicId(1);
        hubSubscriptionMDSService.create(hubSubscription);
        
        hubSubscriptions = hubSubscriptionMDSService.findSubByCallbackUrl(callbackUrl);
        Assert.assertNotNull(hubSubscriptions);
        Assert.assertEquals(1, hubSubscriptions.size());
    }
    
    @After
    public void tearDown() {
        hubSubscriptionMDSService.deleteAll();
    }
}
