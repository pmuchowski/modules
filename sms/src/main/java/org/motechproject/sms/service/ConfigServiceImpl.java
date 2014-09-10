package org.motechproject.sms.service;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import org.apache.commons.io.IOUtils;
import org.motechproject.server.config.SettingsFacade;
import org.motechproject.sms.configs.Config;
import org.motechproject.sms.configs.Configs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * todo
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {

    private static final String SMS_CONFIGS_FILE_NAME = "sms-configs.json";
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServiceImpl.class);
    private SettingsFacade settingsFacade;
    private Configs configs;

    private Configs loadConfigs() {
        Configs ret = null;
        InputStream is = settingsFacade.getRawConfig(SMS_CONFIGS_FILE_NAME);
        String errorMessage = null;
        if (is == null) {
            throw new JsonIOException(SMS_CONFIGS_FILE_NAME + " missing");
        }
        try {
            String jsonText = IOUtils.toString(is);
            Gson gson = new Gson();
            ret = gson.fromJson(jsonText, Configs.class);
        } catch (Exception e) {
            errorMessage = "Might you have a malformed " + SMS_CONFIGS_FILE_NAME + " file? " + e.toString();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                // Ignore IO exception, what are we going to do anyway?
                LOGGER.error("IOException when closing config file {}: {}", SMS_CONFIGS_FILE_NAME, e);
            }
        }
        if (errorMessage != null) {
            throw new JsonIOException(errorMessage);
        }
        return ret;
    }

    @Autowired
    public ConfigServiceImpl(@Qualifier("smsSettings") SettingsFacade settingsFacade) {
        this.settingsFacade = settingsFacade;
        this.configs = loadConfigs();
    }

    public Config getDefaultConfig() {
        return configs.getDefaultConfig();
    }

    public List<Config> getConfigList() {
        return configs.getConfigs();
    }

    public Configs getConfigs() {
        return configs;
    }

    public boolean hasConfig(String name) {
        return configs.hasConfig(name);
    }

    public Config getConfig(String name) {
        return configs.getConfig(name);
    }

    public Config getConfigOrDefault(String name) {
        return configs.getConfigOrDefault(name);
    }

    public void updateConfigs(Configs configs) {
        Gson gson = new Gson();
        String jsonText = gson.toJson(configs, Configs.class);
        ByteArrayResource resource = new ByteArrayResource(jsonText.getBytes());
        settingsFacade.saveRawConfig(SMS_CONFIGS_FILE_NAME, resource);
    }

    public boolean hasConfigs() {
        return !configs.isEmpty();
    }
}
