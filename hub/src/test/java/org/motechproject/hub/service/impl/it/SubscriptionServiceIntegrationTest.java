package org.motechproject.hub.service.impl.it;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.hub.exception.HubException;
import org.motechproject.hub.mds.HubSubscription;
import org.motechproject.hub.mds.HubTopic;
import org.motechproject.hub.mds.service.HubDistributionContentMDSService;
import org.motechproject.hub.mds.service.HubDistributionStatusMDSService;
import org.motechproject.hub.mds.service.HubPublisherTransactionMDSService;
import org.motechproject.hub.mds.service.HubSubscriberTransactionMDSService;
import org.motechproject.hub.mds.service.HubSubscriptionMDSService;
import org.motechproject.hub.mds.service.HubSubscriptionStatusMDSService;
import org.motechproject.hub.mds.service.HubTopicMDSService;
import org.motechproject.hub.model.Modes;
import org.motechproject.hub.service.SubscriptionService;
import org.motechproject.testing.osgi.BasePaxIT;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;

/**
 * This class tests the service <code>SubscriptionService</code>
 * 
 * @author Anuranjan
 * 
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class SubscriptionServiceIntegrationTest extends BasePaxIT {

    @Inject
    private SubscriptionService subscriptionService;
    @Inject
    private HubTopicMDSService hubTopicMDSService;
    @Inject
    private HubDistributionStatusMDSService hubDistributionStatusMDSService;
    @Inject
    private HubPublisherTransactionMDSService hubPublisherTransactionMDSService;
    @Inject
    private HubSubscriberTransactionMDSService hubSubscriberTransactionMDSService;
    @Inject
    private HubSubscriptionMDSService hubSubscriptionMDSService;
    @Inject
    private HubSubscriptionStatusMDSService hubSubscriptionStatusMDSService;
    @Inject
    private HubDistributionContentMDSService hubDistributionContentMDSService;

    @Test
    public void testSubscriptionAndUnsubscription() throws HubException {
        List<HubTopic> hubTopics = hubTopicMDSService.findByTopicUrl("topic_url");
        List<HubSubscription> hubSubscriptons = hubSubscriptionMDSService.findSubByCallbackUrl("callback_url");
        Assert.assertNotNull(hubTopics);
        Assert.assertEquals(0, hubTopics.size());
        Assert.assertNotNull(hubSubscriptons);
        Assert.assertEquals(0, hubSubscriptons.size());
        
        //add a subscription
        subscriptionService.subscribe("callack_url", Modes.SUBSCRIBE, "topic_url", null, null);
        hubTopics = hubTopicMDSService.findByTopicUrl("topic_url");
        hubSubscriptons = hubSubscriptionMDSService.findSubByCallbackUrl("callback_url");
        Assert.assertNotNull(hubTopics);
        Assert.assertEquals(1, hubTopics.size());
        Assert.assertNotNull(hubSubscriptons);
        Assert.assertEquals(1, hubSubscriptons.size());
        
        //delete the subscription
        subscriptionService.subscribe("callack_url", Modes.UNSUBSCRIBE, "topic_url", null, null);
        hubTopics = hubTopicMDSService.findByTopicUrl("topic_url");
        hubSubscriptons = hubSubscriptionMDSService.findSubByCallbackUrl("callback_url");
        Assert.assertNotNull(hubTopics);
        Assert.assertEquals(0, hubTopics.size());
        Assert.assertNotNull(hubSubscriptons);
        Assert.assertEquals(0, hubSubscriptons.size());
    }
    
    
    @After
    public void tearDown() {
        hubTopicMDSService.deleteAll();
        hubDistributionStatusMDSService.deleteAll();
        hubPublisherTransactionMDSService.deleteAll();
        hubSubscriberTransactionMDSService.deleteAll();
        hubSubscriptionMDSService.deleteAll();
        hubSubscriptionStatusMDSService.deleteAll();
        hubDistributionContentMDSService.deleteAll();
    }

}
