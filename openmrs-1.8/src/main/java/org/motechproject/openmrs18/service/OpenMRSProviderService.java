package org.motechproject.openmrs18.service;

import org.motechproject.openmrs18.domain.OpenMRSProvider;

/**
 * An interface to persist providers
 */
public interface OpenMRSProviderService {

    /**
     * Persists a provider
     * @param provider The provider to save
     * @return The saved provider object
     */
    OpenMRSProvider saveProvider(OpenMRSProvider provider);

    /**
     * Retrieves a provider by the provider's motech id
     * @param motechId The motech id of the provider
     * @return The provider given by the motech id
     */
    OpenMRSProvider getProviderByProviderId(String motechId);

    void removeProvider(String motechId);

}
