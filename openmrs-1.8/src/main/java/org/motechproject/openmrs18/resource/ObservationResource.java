package org.motechproject.openmrs18.resource;

import org.motechproject.openmrs18.rest.HttpException;
import org.motechproject.openmrs18.resource.model.Observation;
import org.motechproject.openmrs18.resource.model.ObservationListResult;

public interface ObservationResource {

    ObservationListResult queryForObservationsByPatientId(String id) throws HttpException;

    void deleteObservation(String id, String reason) throws HttpException;

    Observation getObservationById(String id) throws HttpException;

}
