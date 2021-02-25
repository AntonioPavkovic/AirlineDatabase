package Application.DataTypes;

public class Admin {

    private int adminID;
    private String firstName;
    private String lastName;
    private String password;

    public Admin() {}

    public Admin(int adminID, String firstName, String lastName, String password) {
        this.adminID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID){
        this.adminID = adminID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

     @Override
    public String toString() {
         return "Admin{" +
                 "adminId=" + adminID +
                 ", firstName='" + firstName + '\'' +
                 ", lastName='" + lastName + '\'' +
                 ", password='" + password + '\'' +
                 '}';
    }
}
