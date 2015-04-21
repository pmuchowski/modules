package org.motechproject.csd.domain;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.motechproject.csd.adapters.DateTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>Java class for record complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="record">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="created" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="updated" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="status" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sourceDirectory" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class Record {

    private DateTime created;

    private DateTime updated;

    private String status;

    private String sourceDirectory;

    public Record() {
    }

    public Record(DateTime created, String status, DateTime updated) {
        this.created = created;
        this.status = status;
        this.updated = updated;
    }

    public Record(DateTime created, DateTime updated, String status, String sourceDirectory) {
        this.created = created;
        this.updated = updated;
        this.status = status;
        this.sourceDirectory = sourceDirectory;
    }

    public DateTime getCreated() {
        return created;
    }

    @XmlAttribute(required = true)
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(type = DateTime.class, value = DateTimeAdapter.class)
    public void setCreated(DateTime created) {
        this.created = created;
    }

    public String getSourceDirectory() {
        return sourceDirectory;
    }

    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anyURI")
    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    public String getStatus() {
        return status;
    }

    @XmlAttribute(required = true)
    public void setStatus(String status) {
        this.status = status;
    }

    public DateTime getUpdated() {
        return updated;
    }

    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(type = DateTime.class, value = DateTimeAdapter.class)
    public void setUpdated(DateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Record record = (Record) o;

        if (!created.isEqual(record.created)) {
            return false;
        }
        if (!updated.isEqual(record.updated)) {
            return false;
        }
        if (!status.equals(record.status)) {
            return false;
        }
        if (sourceDirectory != null ? !sourceDirectory.equals(record.sourceDirectory) : record.sourceDirectory != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = created.hashCode();
        result = 31 * result + updated.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (sourceDirectory != null ? sourceDirectory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "created=" + created.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm")) +
                ", updated=" + updated.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm")) +
                ", status='" + status + '\'';
    }
}
