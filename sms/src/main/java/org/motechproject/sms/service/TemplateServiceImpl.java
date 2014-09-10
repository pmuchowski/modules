package org.motechproject.sms.service;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.motechproject.server.config.SettingsFacade;
import org.motechproject.sms.templates.Template;
import org.motechproject.sms.templates.TemplateForWeb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * todo
 */
@Service("templateService")
public class TemplateServiceImpl implements TemplateService {

    private static final String FILE_NAME = "sms-templates.json";
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateServiceImpl.class);
    private SettingsFacade settingsFacade;
    private Map<String, Template> templates = new HashMap<>();

    private Map<String, Template> getTemplates() {
        List<Template> templateList = null;
        Type type = new TypeToken<List<Template>>() { } .getType();
        InputStream is = settingsFacade.getRawConfig(FILE_NAME);
        String errorMessage = null;
        try {
            String jsonText = IOUtils.toString(is);
            Gson gson = new Gson();
            templateList = gson.fromJson(jsonText, type);
        } catch (Exception e) {
            errorMessage = "Might you have a malformed " + FILE_NAME + " file? " + e.toString();
        }

        if (templateList == null) {
            if (errorMessage == null) {
                errorMessage = "Unable to read templates.";
            }
            throw new JsonIOException(errorMessage);
        }

        Map<String, Template> templateMap = new HashMap<>();
        for (Template template : templateList) {
            templateMap.put(template.getName(), template);
        }
        return templateMap;
    }

    @Autowired
    public TemplateServiceImpl(@Qualifier("smsSettings") SettingsFacade settingsFacade) {
        this.settingsFacade = settingsFacade;
        templates = getTemplates();
    }

    public Template getTemplate(String name) {
        if (templates.containsKey(name)) {
            return templates.get(name);
        }
        String message = String.format("Unknown template: '%s'.");
        LOGGER.error(message);
        throw new IllegalArgumentException(message);
    }

    public Map<String, TemplateForWeb> allTemplatesForWeb() {
        Map<String, TemplateForWeb> ret = new HashMap<>();
        for (Map.Entry<String, Template> entry : templates.entrySet()) {
            ret.put(entry.getKey(), new TemplateForWeb(entry.getValue()));
        }
        return ret;
    }
}
