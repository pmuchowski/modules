package org.motechproject.csd.domain;

import org.joda.time.DateTime;
import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;
import org.motechproject.mds.annotations.Ignore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlTransient
@XmlAccessorType(XmlAccessType.NONE)
public abstract class BaseMainEntity extends AbstractUniqueID {

    private DateTime creationDate;

    private DateTime modificationDate;

    @Ignore
    private Record record;

    @Field(required = true)
    String status;

    @Field
    private String sourceDirectory;

    @XmlTransient
    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    @XmlTransient
    public DateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(DateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Record getRecord() {
        if (record == null) {
            record = new Record(getCreationDate(), getModificationDate(), status, sourceDirectory);
        }
        return record;
    }

    @XmlElement(required = true)
    public void setRecord(Record record) {
        this.record = record;
        status = record.getStatus();
        sourceDirectory = record.getSourceDirectory();
        setCreationDate(record.getCreated());
        setModificationDate(record.getUpdated());
    }

    @XmlTransient
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public String getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }
}
