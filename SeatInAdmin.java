package latoServer;

public class SeatInAdmin extends SeatInPeople {
    private static final long serialVersionUID=1;
    private String ID;
    private String name;
    private String surname;
    private String email;
    private String department;
    private String password;
    private String IDTemp;
    private String stateProfile;

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getIDTemp() {
        return IDTemp;
    }

    public String getStateProfile() {
        return stateProfile;
    }

    public String getDepartment() {

        return department;
    }

    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setIDTemp(String IDTemp) {
        this.IDTemp = IDTemp;
    }

    public void setStateProfile(String stateProfile) {
        this.stateProfile = stateProfile;
    }

    public SeatInAdmin(String ID, String name, String surname, String email, String password, String IDTemp, String department, String stateProfile) {
        super(ID, name, surname, email, password, IDTemp , stateProfile);
        this.ID=ID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.IDTemp = IDTemp;
        this.department = department;
        this.stateProfile = stateProfile;

    }
    public String isClass()
    {
        return "SeatInAdmin";
    }
}


