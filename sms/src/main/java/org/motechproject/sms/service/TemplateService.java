package org.motechproject.sms.service;

import org.motechproject.sms.templates.Template;
import org.motechproject.sms.templates.TemplateForWeb;

import java.util.Map;

/**
 * todo
 */
public interface TemplateService {
    Template getTemplate(String name);
    Map<String, TemplateForWeb> allTemplatesForWeb();
}
