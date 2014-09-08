package org.motechproject.sms.service;

import org.motechproject.sms.domain.NewConfig;
import org.motechproject.sms.domain.NewTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * See {@link org.motechproject.sms.service.SmsConfigService}
 */
@Service("smsConfigService")
public class SmsConfigServiceImpl implements SmsConfigService {

    //TODO: ====================================
    //TODO: obviously temporary placeholder code
    //TODO: ====================================

    private static List<NewTemplate> templates = Arrays.asList(new NewTemplate("template"));
    private static List<NewConfig> configs = Arrays.asList(new NewConfig("config", "template"));

    public List<NewTemplate> allTemplates() {
        return templates;
    }

    public List<NewConfig> allConfigs() {
        return configs;
    }

    public NewConfig getConfig(String name) {
        return configs.get(0);
    }

    public NewTemplate getTemplate(String name) {
        return templates.get(0);
    }
}
