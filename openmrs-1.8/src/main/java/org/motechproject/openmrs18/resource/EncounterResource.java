package org.motechproject.openmrs18.resource;

import org.motechproject.openmrs18.rest.HttpException;
import org.motechproject.openmrs18.resource.model.Encounter;
import org.motechproject.openmrs18.resource.model.EncounterListResult;

public interface EncounterResource {

    Encounter createEncounter(Encounter encounter) throws HttpException;

    EncounterListResult queryForAllEncountersByPatientId(String id) throws HttpException;

    Encounter getEncounterById(String uuid) throws HttpException;

}
