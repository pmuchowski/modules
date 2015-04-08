package org.motechproject.csd.service.impl;

import org.motechproject.csd.domain.Facility;
import org.motechproject.csd.mds.FacilityDataService;
import org.motechproject.csd.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("facilityService")
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityDataService facilityDataService;

    public Facility getFacility(String uuid) {
        return null;
    }

    public List<Facility> allFacilities() {
        return facilityDataService.retrieveAll();
    }

    @Override
    public Facility getFacilityByEntityID(String entityID) {
        return facilityDataService.findByEntityID(entityID);
    }

    @Override
    public Facility update(Facility facility) {
        delete(facility.getEntityID());
        return facilityDataService.create(facility);
    }

    @Override
    public void delete(String entityID) {
        Facility facility = getFacilityByEntityID(entityID);

        if (facility != null) {
            facilityDataService.delete(facility);
        }
    }

    @Override
    public Set<Facility> update(Set<Facility> facilities) {
        for (Facility facility : facilities) {
            update(facility);
        }
        return facilities;
    }

    @Override
    public Facility create(Facility facility) {
        if (facility != null) {
            return facilityDataService.create(facility);
        }
        return null;
    }

    @Override
    public void delete(Facility facility) {
        facilityDataService.delete(facility);
    }
}
