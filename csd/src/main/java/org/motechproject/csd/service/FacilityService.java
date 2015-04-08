package org.motechproject.csd.service;


import org.motechproject.csd.domain.Facility;

import java.util.List;
import java.util.Set;

/**
 * See {@link org.motechproject.csd.domain.Facility}
 */
public interface FacilityService {

    Facility getFacility(String uuid);

    List<Facility> allFacilities();

    Facility getFacilityByEntityID(String entityID);

    Facility update(Facility facility);

    void delete(String entityID);

    Set<Facility> update(Set<Facility> facilities);

    Facility create(Facility facility);

    void delete(Facility facility);
}
