package Owner;

import Phone.Phone;
import Role.Role;


/**
 * <h1>Owner Class</h1>
 * The Owner class is a model class that inherit the Role class and it stores
 * the data fields of Owner
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class Owner extends Role {

    /**
     * Instantiates a new Owner.
     *
     * @param id       the id
     * @param userName the user name
     * @param password the password
     * @param phone    the phone
     */
    public Owner(int id, String userName, String password, Phone phone) {
        super("Owner", id, userName, password, phone);
    }

    /**
     * A public method that returns the owner's username
     *
     * @return String of username
     */
    @Override
    public String getUserName() {
        return super.getUserName();
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + super.getId() +
                ", username='" + super.getUserName() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", phone=" + super.getPhone() +
                '}';
    }
}