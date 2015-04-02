package org.motechproject.csd.it;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.csd.domain.CodedType;
import org.motechproject.csd.domain.Facility;
import org.motechproject.csd.domain.FacilityDirectory;
import org.motechproject.csd.domain.Organization;
import org.motechproject.csd.domain.Person;
import org.motechproject.csd.domain.PersonName;
import org.motechproject.csd.domain.Provider;
import org.motechproject.csd.domain.Record;
import org.motechproject.csd.domain.Service;
import org.motechproject.csd.service.FacilityDirectoryService;
import org.motechproject.csd.service.FacilityService;
import org.motechproject.csd.service.OrganizationDirectoryService;
import org.motechproject.csd.service.OrganizationService;
import org.motechproject.csd.service.ProviderDirectoryService;
import org.motechproject.csd.service.ProviderService;
import org.motechproject.csd.service.ServiceDirectoryService;
import org.motechproject.csd.service.ServiceService;
import org.motechproject.testing.osgi.BasePaxIT;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class CSDEntityListenerIT extends BasePaxIT {

    private static final Integer MAX_RETRIES_BEFORE_FAIL = 20;
    private static final Integer WAIT_TIME = 2000;

    @Inject
    private FacilityService facilityService;

    @Inject
    private FacilityDirectoryService facilityDirectoryService;

    @Inject
    private ProviderService providerService;

    @Inject
    private ProviderDirectoryService providerDirectoryService;

    @Inject
    private OrganizationService organizationService;

    @Inject
    private OrganizationDirectoryService organizationDirectoryService;

    @Inject
    private ServiceService serviceService;

    @Inject
    private ServiceDirectoryService serviceDirectoryService;

    @Test
    public void shouldAddFacilityToFacilityDirectory() throws InterruptedException {
        Facility facility = new Facility("entityId", new HashSet<>(Arrays.asList(new CodedType("c", "c"))), new Record(new DateTime(), "st", new DateTime()), "name");
        int size = facilityDirectoryService.getFacilityDirectory().getFacilities().size();

        facilityService.create(facility);

        int retries = 0;
        while (retries < MAX_RETRIES_BEFORE_FAIL && facilityDirectoryService.getFacilityDirectory().getFacilities().size() <= size) {
            retries++;
            Thread.sleep(WAIT_TIME);
        }

        assertTrue(facilityDirectoryService.getFacilityDirectory().getFacilities().contains(facility));

        facilityDirectoryService.removeFacility(facility);
    }

    @Test
    public void shouldAddProviderToProviderDirectory() throws InterruptedException {
        Provider provider = new Provider("entityId", new HashSet<>(Arrays.asList(new CodedType("c", "s"))), new Record(new DateTime(), "st", new DateTime()), new Person(new HashSet<>(Arrays.asList(new PersonName(Arrays.asList("name"))))));
        int size = providerDirectoryService.getProviderDirectory().getProviders().size();

        providerService.create(provider);

        int retries = 0;
        while (retries < MAX_RETRIES_BEFORE_FAIL && providerDirectoryService.getProviderDirectory().getProviders().size() <= size) {
            retries++;
            Thread.sleep(WAIT_TIME);
        }

        assertTrue(providerDirectoryService.getProviderDirectory().getProviders().contains(provider));

        providerDirectoryService.removeProvider(provider);
    }

    @Test
    public void shouldAddOrganizationToOrganizationDirectory() throws InterruptedException {
        Organization organization = new Organization("entityId", new HashSet<>(Arrays.asList(new CodedType("c", "c"))), "name", new Record(new DateTime(), "st", new DateTime()));
        int size = organizationDirectoryService.getOrganizationDirectory().getOrganizations().size();

        organizationService.create(organization);

        int retries = 0;
        while (retries < MAX_RETRIES_BEFORE_FAIL && organizationDirectoryService.getOrganizationDirectory().getOrganizations().size() <= size) {
            retries++;
            Thread.sleep(WAIT_TIME);
        }

        assertTrue(organizationDirectoryService.getOrganizationDirectory().getOrganizations().contains(organization));

        organizationDirectoryService.removeOrganization(organization);
    }

    @Test
    public void shouldAddServiceToServiceDirectory() throws InterruptedException {
        Service service = new Service("entityId", new CodedType("c", "c"), new Record(new DateTime(), "st", new DateTime()));
        int size = serviceDirectoryService.getServiceDirectory().getServices().size();

        serviceService.create(service);

        int retries = 0;
        while (retries < MAX_RETRIES_BEFORE_FAIL && serviceDirectoryService.getServiceDirectory().getServices().size() <= size) {
            retries++;
            Thread.sleep(WAIT_TIME);
        }

        assertTrue(serviceDirectoryService.getServiceDirectory().getServices().contains(service));

        serviceDirectoryService.removeService(service);
    }
}
