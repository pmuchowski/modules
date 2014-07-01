package org.motechproject.openmrs18.service;

import org.joda.time.DateTime;
import org.motechproject.openmrs18.domain.OpenMRSAttribute;
import org.motechproject.openmrs18.domain.OpenMRSPerson;

import java.util.List;

public interface OpenMRSPersonService {

    /**
     * Persists a person object
     * @param person The person to be saved 
     * @throws OpenMrsException Thrown if the person object violated constraints in the implementing module
     */
    OpenMRSPerson addPerson(OpenMRSPerson person);

    /**
     * Creates and persists a person object from field values
     * @param personId The id of the person
     * @param firstName The person's first name
     * @param lastName The person's last name
     * @param dateOfBirth The person's date of birth
     * @param gender The person's gender
     * @param address The address of the person
     * @param attributes A list of attributes for the person
     * @throws OpenMrsException Thrown if the person object violated constraints in the implementing module
     */
    OpenMRSPerson addPerson(String personId, String firstName, String lastName, DateTime dateOfBirth, String gender,
            String address, List<OpenMRSAttribute> attributes);

    /**
     * Updates a person object
     * @param person The person to update
     */
    OpenMRSPerson updatePerson(OpenMRSPerson person);

    /**
     * Removes a person from storage
     * @param person The person to remove
     */
    void removePerson(OpenMRSPerson person);

    /**
     * Retrieves a list of all person objects
     * @return A list of all persons, of a type from the implementing module
     */
    List<? extends OpenMRSPerson> findAllPersons();

    /**
     * Finds a list of persons by a particular id
     * @param personId The id of the person to search for
     * @return A list of all persons of given id, of a type from the implementing module
     */
    List<? extends OpenMRSPerson> findByPersonId(String personId);

    /**
     * Removes all person instances from storage
     */
    void removeAll();

}
