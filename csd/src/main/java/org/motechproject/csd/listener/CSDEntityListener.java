package org.motechproject.csd.listener;

import org.motechproject.csd.domain.Facility;
import org.motechproject.csd.domain.Organization;
import org.motechproject.csd.domain.Provider;
import org.motechproject.csd.domain.Service;
import org.motechproject.csd.mds.FacilityDataService;
import org.motechproject.csd.mds.OrganizationDataService;
import org.motechproject.csd.mds.ProviderDataService;
import org.motechproject.csd.mds.ServiceDataService;
import org.motechproject.csd.service.FacilityDirectoryService;
import org.motechproject.csd.service.OrganizationDirectoryService;
import org.motechproject.csd.service.ProviderDirectoryService;
import org.motechproject.csd.service.ServiceDirectoryService;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CSDEntityListener {

    private FacilityDataService facilityDataService;
    private ProviderDataService providerDataService;
    private OrganizationDataService organizationDataService;
    private ServiceDataService serviceDataService;

    private FacilityDirectoryService facilityDirectoryService;
    private ProviderDirectoryService providerDirectoryService;
    private OrganizationDirectoryService organizationDirectoryService;
    private ServiceDirectoryService serviceDirectoryService;

    @Autowired
    public CSDEntityListener(FacilityDataService facilityDataService, ProviderDataService providerDataService, OrganizationDataService organizationDataService,
                             ServiceDataService serviceDataService, FacilityDirectoryService facilityDirectoryService, ProviderDirectoryService providerDirectoryService,
                             OrganizationDirectoryService organizationDirectoryService, ServiceDirectoryService serviceDirectoryService) {
        this.facilityDataService = facilityDataService;
        this.providerDataService = providerDataService;
        this.organizationDataService = organizationDataService;
        this.serviceDataService = serviceDataService;
        this.facilityDirectoryService = facilityDirectoryService;
        this.providerDirectoryService = providerDirectoryService;
        this.organizationDirectoryService = organizationDirectoryService;
        this.serviceDirectoryService = serviceDirectoryService;
    }

    @MotechListener(subjects = { "mds.crud.careservicesdirectory.Facility.CREATE" })
    public void addFacilityToFacilityDirectory(MotechEvent event) {
        Long id = (Long)event.getParameters().get("object_id");
        Facility facility = facilityDataService.findById(id);

        if (facility != null) {
            facilityDirectoryService.addFacility(facility);
        }
    }

    @MotechListener(subjects = { "mds.crud.careservicesdirectory.Facility.DELETE" })
    public void removeFacilityFromFacilityDirectory(MotechEvent event) {
        facilityDirectoryService.update();
    }

    @MotechListener(subjects = { "mds.crud.careservicesdirectory.Provider.CREATE" })
    public void addProviderToProviderDirectory(MotechEvent event) {
        Long id = (Long)event.getParameters().get("object_id");
        Provider provider = providerDataService.findById(id);

        if (provider != null) {
            providerDirectoryService.addProvider(provider);
        }
    }

    @MotechListener(subjects = { "mds.crud.careservicesdirectory.Provider.DELETE" })
    public void removeProviderFromProviderDirectory(MotechEvent event) {
        providerDirectoryService.update();
    }

    @MotechListener(subjects = { "mds.crud.careservicesdirectory.Organization.CREATE" })
    public void addOrganizationToOrganizationDirectory(MotechEvent event) {
        Long id = (Long)event.getParameters().get("object_id");
        Organization organization = organizationDataService.findById(id);

        if (organization != null) {
            organizationDirectoryService.addOrganization(organization);
        }
    }

    @MotechListener(subjects = { "mds.crud.careservicesdirectory.Organization.DELETE" })
    public void removeOrganizationFromOrganizationDirectory(MotechEvent event) {
        organizationDirectoryService.update();
    }

    @MotechListener(subjects = { "mds.crud.careservicesdirectory.Service.CREATE" })
    public void addServiceToServiceDirectory(MotechEvent event) {
        Long id = (Long)event.getParameters().get("object_id");
        Service service = serviceDataService.findById(id);

        if (service != null) {
            serviceDirectoryService.addService(service);
        }
    }

    @MotechListener(subjects = { "mds.crud.careservicesdirectory.Service.DELETE" })
    public void removeServiceFromServiceDirectory(MotechEvent event) {
        serviceDirectoryService.update();
    }
}
