package org.motechproject.openmrs18.service;

import org.motechproject.openmrs18.domain.OpenMRSEncounter;

import java.util.List;

/**
 * Interface for fetching and storing MRSEncounter details
 */
public interface OpenMRSEncounterService {
    /**
     * Stores a given MRSEncounter in the MRS system
     * @param mrsEncounter Object to be saved.
     * @return Saved MRS Encounter object
     */
    OpenMRSEncounter createEncounter(OpenMRSEncounter mrsEncounter);

    /**
     * Fetches the latest encounter of a patient identified by MOTECH ID and the encounter type.
     * @param motechId Identifier of the patient
     * @param encounterType Type of the encounter. (e.g. ANCVISIT)
     * @return The latest MRSEncounter if found.
     */
    OpenMRSEncounter getLatestEncounterByPatientMotechId(String motechId, String encounterType);

    /**
     * Fetches an encounter by its UUID
     * @param id The UUID in OpenMRS of the encounter to retrieve
     * @return The MRSEncounter with the specified id
     */
    OpenMRSEncounter getEncounterById(String id);

    /**
     * Fetches a list of encounters of a patient identified by MOTECH ID and the encounter type.
     * @param motechId Identifier of the patient
     * @param encounterType Type of the encounter. (e.g. ANCVISIT)
     * @return A list of all MRSEncounters of the corresponding encounter type for the patient identified by MOTECH ID
     */
    List<OpenMRSEncounter> getEncountersByEncounterType(String motechId, String encounterType);
}
