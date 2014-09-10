package org.motechproject.sms.service;

import org.motechproject.sms.configs.Config;
import org.motechproject.sms.configs.Configs;

import java.util.List;

/**
 * todo
 */
public interface ConfigService {
    Config getDefaultConfig();
    Configs getConfigs();
    List<Config> getConfigList();
    boolean hasConfig(String name);
    Config getConfig(String name);
    Config getConfigOrDefault(String name);
    void updateConfigs(Configs configs);
    boolean hasConfigs();
}
