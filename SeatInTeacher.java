package latoServer;

public class SeatInTeacher extends SeatInUser {
    private static final long serialVersionUID=1;
    private String ID;
    private String name;
    private String surname;
    private String email;
    private String department;
    private String password;
    private String IDTemp;
    private String state;

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

    public void setState(String state) {
        this.state = state;
    }

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

    public String getDepartment() {
        return department;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getIDTemp() {
        return IDTemp;
    }


    public String getState() {
        return state;
    }

    //String iD, String name, String surname, String email,
    // String password, String IDTemp,String stateProfile, String degreeCourse
    public SeatInTeacher(String iD, String name, String surname, String email, String password, String IDTemp, String department, String state) {
        super(iD, name, surname, email, password, IDTemp, state);
        this.department = department;
        this.ID = iD;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.IDTemp = IDTemp;
        this.state = state;

    }
    public String isClass()
    {
        return "SeatInAdmin";
    }
}
