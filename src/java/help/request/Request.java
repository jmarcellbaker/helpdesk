package help.request;

/**
 *
 * @author jmarc
 */

import help.login.Employee;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Request implements Serializable {
    
 @ManyToOne
 private Employee employee;
    
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long requestId;
 
 // Variables for request
 private String requestStatus;
 private String requestedBy;
 private String contactInfo;
 private String requestDate;
 private String description;
 private String technician;
 private String completionDate;
 private String notes;
 
 public Request () {
     
 }
   

 public Request (Long requestId, String requestStatus, String requestedBy, String contactInfo, String requestDate,
                 String description, String technician, String completionDate, String notes) {
     
     this.requestId = requestId;
     this.requestStatus = requestStatus;
     this.contactInfo = contactInfo;
     this.requestDate = requestDate;
     this.description = description;
     this.technician = technician;
     this.completionDate = completionDate;
     this.notes = notes;
     this.employee = employee;
     
 }

    /**
     * @return the requestId
     */
    public Long getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the requestStatus
     */
    public String getRequestStatus() {
        return requestStatus;
    }

    /**
     * @param requestStatus the requestStatus to set
     */
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    /**
     * @return the requestDate
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the technician
     */
    public String getTechnician() {
        return technician;
    }

    /**
     * @param technician the technician to set
     */
    public void setTechnician(String technician) {
        this.technician = technician;
    }

    /**
     * @return the completionDate
     */
    public String getCompletionDate() {
        return completionDate;
    }

    /**
     * @param completionDate the completionDate to set
     */
    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the contactInfo
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * @param contactInfo the contactInfo to set
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
 
    public String getRequestedBy() {
        return requestedBy;
    }
    
    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
 
 
 
 
}
