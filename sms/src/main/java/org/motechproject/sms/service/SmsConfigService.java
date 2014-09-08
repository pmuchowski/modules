package org.motechproject.sms.service;

import org.motechproject.sms.domain.NewConfig;
import org.motechproject.sms.domain.NewTemplate;

import java.util.List;

/**
 * Provides access to NewConfig & NewTemplate entities
 */
public interface SmsConfigService {
    List<NewTemplate> allTemplates();
    List<NewConfig> allConfigs();
    NewTemplate getTemplate(String name);
    NewConfig getConfig(String name);
}
