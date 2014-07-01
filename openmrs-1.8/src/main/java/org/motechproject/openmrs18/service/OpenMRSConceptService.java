package org.motechproject.openmrs18.service;

import org.motechproject.openmrs18.domain.OpenMRSConcept;

import java.util.List;

public interface OpenMRSConceptService {

    String resolveConceptUuidFromConceptName(String conceptName);

    /**
     * Saves a concept to the MRS system
     *
     * @param concept Object to be saved
     * @return Saved instance of the concept
     */
    OpenMRSConcept saveConcept(OpenMRSConcept concept);

    /**
     * Fetches a concept by the given concept id
     *
     * @param conceptId Value to be used to find a concept
     * @return Concept with the given concept id if exists
     */
    OpenMRSConcept getConcept(String conceptId);

    /**
     * Searches for concepts in the MRS system by concept's name
     *
     * @param name     Name of the concept to be searched for
     * @return List of matched Concepts
     */
    List<? extends OpenMRSConcept> search(String name);

    List<? extends OpenMRSConcept> getAllConcepts();

    void deleteConcept(String conceptId);
    OpenMRSConcept updateConcept(OpenMRSConcept concept);

}
