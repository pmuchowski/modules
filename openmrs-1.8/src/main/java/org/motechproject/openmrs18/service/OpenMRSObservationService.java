package org.motechproject.openmrs18.service;

import org.motechproject.openmrs18.domain.OpenMRSObservation;
import org.motechproject.openmrs18.exception.ObservationNotFoundException;

import java.util.List;

public interface OpenMRSObservationService {
    /**
     * Voids an observation for the MOTECH user, with the given reason
     *
     * @param mrsObservation  OpenMRSObservation to be voided
     * @param reason  reason for voiding the OpenMRSObservation
     * @param mrsUserMotechId  MOTECH ID of the user whose OpenMRSObservation needs to be voided
     * @throws ObservationNotFoundException  if the expected Observation does not exist
     */
    void voidObservation(OpenMRSObservation mrsObservation, String reason, String mrsUserMotechId) throws ObservationNotFoundException;

    /**
     * Returns the latest OpenMRSObservation of the MRS patient, given the concept name. (e.g. WEIGHT)
     *
     * @param patientMotechId  MOTECH Id of the patient
     * @param conceptName  concept Name of the OpenMRSObservation
     * @return OpenMRSObservation if present.
     */
    OpenMRSObservation findObservation(String patientMotechId, String conceptName);
    List<OpenMRSObservation> findObservations(String patientMotechId, String conceptName);

    OpenMRSObservation getObservationById(String id);
}
