package org.motechproject.commcare.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.motechproject.commcare.client.CommCareAPIHttpClient;
import org.motechproject.commcare.config.Config;
import org.motechproject.commcare.domain.CommcareDataForwardingEndpoint;
import org.motechproject.commcare.domain.CommcareDataForwardingEndpointsJson;
import org.motechproject.commcare.service.CommcareDataForwardingEndpointService;
import org.motechproject.commons.api.json.MotechJsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommcareDataForwardingEndpointServiceImpl implements CommcareDataForwardingEndpointService {

    static final int DEFAULT_PAGE_SIZE = 20;

    private MotechJsonReader motechJsonReader;

    private CommCareAPIHttpClient commcareHttpClient;

    @Autowired
    public CommcareDataForwardingEndpointServiceImpl(CommCareAPIHttpClient commcareHttpClient) {
        this.commcareHttpClient = commcareHttpClient;
        this.motechJsonReader = new MotechJsonReader();
    }

    @Override
    public List<CommcareDataForwardingEndpoint> getAllDataForwardingEndpoints(Config config) {
        CommcareDataForwardingEndpointsJson dataForwardingEndpoints = null;
        Type commcareDataForwardingEndpointType = new TypeToken<CommcareDataForwardingEndpointsJson>() {
                } .getType();
        List<CommcareDataForwardingEndpoint> allDataForwardingEndpoints = new ArrayList<>();
        Integer pageNumber = 1;

        do {
            String response = commcareHttpClient.dataForwardingEndpointsRequest(config.getAccountConfig(), DEFAULT_PAGE_SIZE, pageNumber);
            dataForwardingEndpoints = (CommcareDataForwardingEndpointsJson) motechJsonReader
                            .readFromString(response, commcareDataForwardingEndpointType);
            allDataForwardingEndpoints.addAll(dataForwardingEndpoints.getObjects());
            pageNumber++;
        } while (allDataForwardingEndpoints != null
                && StringUtils.isNotBlank(dataForwardingEndpoints.getMeta().getNextPageQueryString()));

        return allDataForwardingEndpoints;
    }

    @Override
    public List<CommcareDataForwardingEndpoint> getDataForwardingEndpoints(Integer pageSize, Integer pageNumber, Config config) {
        String response = commcareHttpClient.dataForwardingEndpointsRequest(config.getAccountConfig(), pageSize, pageNumber);
        Type commcareDataForwardingEndpointType = new TypeToken<CommcareDataForwardingEndpointsJson>() {
                } .getType();
        CommcareDataForwardingEndpointsJson appStructureResponseJson = (CommcareDataForwardingEndpointsJson) motechJsonReader
                .readFromString(response, commcareDataForwardingEndpointType);

        return appStructureResponseJson.getObjects();
    }

    @Override
    public boolean createNewDataForwardingRule(CommcareDataForwardingEndpoint newForwardingRule, Config config) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String outputData = gson.toJson(newForwardingRule);

        int response = commcareHttpClient.dataForwardingEndpointUploadRequest(config.getAccountConfig(), outputData);

        return response == HttpStatus.SC_CREATED; // HTTP 201
    }

    @Override
    public boolean updateDataForwardingRule(CommcareDataForwardingEndpoint updatedForwardingRule, Config config) {
        String resourceId = updatedForwardingRule.getId();

        Gson gson = new Gson();
        String outputData = gson.toJson(updatedForwardingRule);

        int response = commcareHttpClient.dataForwardingEndpointUpdateRequest(config.getAccountConfig(), resourceId, outputData);

        return response == HttpStatus.SC_NO_CONTENT; // HTTP 204
    }
}
