package Role;

import Phone.Phone;

public abstract class Role {
    private String role;
    private int id;
    private String userName;
    private String password;
    private Phone phone;

    public Role(String role, int id, String userName, String password, Phone phone) {
        this.role = role;
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
