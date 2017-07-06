package help.login;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jmarc
 */

// Technician Class

@Entity
public class Technician implements Serializable {
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    private String firstName;
    private String lastName;
    @Id
    private String email;
    private String title;
    private String password;
    private String salt;
    
    public Technician() {
        
    }
    
    public Technician(Long employeeId, String firstName, String lastName,
                    String email, String title, String password, String salt) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
        this.password = password;
        this.salt = salt;
        
    }
    
        /**
     * @return the employeeId
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
}
