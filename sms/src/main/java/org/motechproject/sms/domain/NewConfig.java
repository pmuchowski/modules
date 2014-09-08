package org.motechproject.sms.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

/**
 * Represents a specific way to interact with an SMS provider
 */
@Entity
public class NewConfig {

    @Field
    private String name;

    @Field
    private String templateName;

    @Field
    private Integer maxRetries;

    @Field
    private Boolean excludeLastFooter;

    @Field
    private String splitHeader;

    @Field
    private String splitFooter;

    public NewConfig(String name, String templateName) {
        this.name = name;
        this.templateName = templateName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Integer getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(Integer maxRetries) {
        this.maxRetries = maxRetries;
    }

    public Boolean getExcludeLastFooter() {
        return excludeLastFooter;
    }

    public void setExcludeLastFooter(Boolean excludeLastFooter) {
        this.excludeLastFooter = excludeLastFooter;
    }

    public String getSplitHeader() {
        return splitHeader;
    }

    public void setSplitHeader(String splitHeader) {
        this.splitHeader = splitHeader;
    }

    public String getSplitFooter() {
        return splitFooter;
    }

    public void setSplitFooter(String splitFooter) {
        this.splitFooter = splitFooter;
    }

    @Override //NO CHECKSTYLE CyclomaticComplexity
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NewConfig)) {
            return false;
        }

        NewConfig newConfig = (NewConfig) o;

        if (excludeLastFooter != null ? !excludeLastFooter.equals(newConfig.excludeLastFooter) : newConfig
                .excludeLastFooter != null) {
            return false;
        }
        if (maxRetries != null ? !maxRetries.equals(newConfig.maxRetries) : newConfig.maxRetries != null) {
            return false;
        }
        if (!name.equals(newConfig.name)) {
            return false;
        }
        if (splitFooter != null ? !splitFooter.equals(newConfig.splitFooter) : newConfig.splitFooter != null) {
            return false;
        }
        if (splitHeader != null ? !splitHeader.equals(newConfig.splitHeader) : newConfig.splitHeader != null) {
            return false;
        }
        if (!templateName.equals(newConfig.templateName)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + templateName.hashCode();
        result = 31 * result + (maxRetries != null ? maxRetries.hashCode() : 0);
        result = 31 * result + (excludeLastFooter != null ? excludeLastFooter.hashCode() : 0);
        result = 31 * result + (splitHeader != null ? splitHeader.hashCode() : 0);
        result = 31 * result + (splitFooter != null ? splitFooter.hashCode() : 0);
        return result;
    }
}
