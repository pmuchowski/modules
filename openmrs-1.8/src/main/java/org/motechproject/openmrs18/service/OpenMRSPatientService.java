package org.motechproject.openmrs18.service;

import org.motechproject.openmrs18.domain.OpenMRSPatient;
import org.motechproject.openmrs18.exception.PatientNotFoundException;

import java.util.Date;
import java.util.List;

/**
 * Interface for handling Patients
 */
public interface OpenMRSPatientService {
    /**
     * Saves a patient to the MRS system
     *
     * @param patient  Object to be saved
     * @return saved instance of the patient
     */
    OpenMRSPatient savePatient(OpenMRSPatient patient);

    /**
     * Finds a patient by current Motech id,
     * and updates the patient's details (including new Motech Id) in the MRS system
     *
     * @param patient  patient instance with updated values (MOTECH identifier cannot be changed here)
     * @param currentMotechId  string Current Motech Id of this patient (used for searching)
     * @return Updated instance of the patient
     */
    OpenMRSPatient updatePatient(OpenMRSPatient patient, String currentMotechId);

    /**
     * Finds a patient by Motech id and updates the patient's details in the MRS system
     *
     * @param patient Patient instance with updated values (MOTECH identifier cannot be changed)
     * @return The MOTECH identifier of the updated patient if successfully updated
     */
    OpenMRSPatient updatePatient(OpenMRSPatient patient);

    /**
     * Fetches a patient by the given patient id
     *
     * @param patientId Value to be used to find a patient
     * @return Patient with the given patient id if exists
     */
    OpenMRSPatient getPatient(String patientId);

    /**
     * Fetches a patient by MOTECH id
     *
     * @param motechId Value to be used to find a patient
     * @return Patient with the given MOTECH id if exists
     */
    OpenMRSPatient getPatientByMotechId(String motechId);

    /**
     * Searches for patients in the MRS system by patient's name and MOTECH id
     *
     * @param name     Name of the patient to be searched for
     * @param motechId Motech id of the patient to be searched for
     * @return List of matched Patients
     */
    List<OpenMRSPatient> search(String name, String motechId);

    List<OpenMRSPatient> getAllPatients();

    /**
     * Gets the age of a patient by MOTECH id
     * Deprecated: The caller should instead get Patient and get the age from that
     *
     * @param motechId Motech id of the patient
     * @return The age of the patient if found
     */
    @Deprecated
    Integer getAgeOfPatientByMotechId(String motechId);

    /**
     * Marks a patient as dead with the given date of death and comment
     *
     * @param motechId    Deceased patient's MOTECH id
     * @param conceptName Concept name for tracking deceased patients
     * @param dateOfDeath Patient's date of death
     * @param comment     Additional information for the cause of death
     * @throws PatientNotFoundException Exception when the expected Patient does not exist
     */
    void deceasePatient(String motechId, String conceptName, Date dateOfDeath, String comment) throws PatientNotFoundException;

    void deletePatient(OpenMRSPatient patient) throws PatientNotFoundException;
}