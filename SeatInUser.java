package latoServer;

public class SeatInUser extends SeatInPeople {
    private static final long serialVersionUID=1;
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

    public String getDegreeCourse() {
        return degreeCourse;
    }

    public String getStateProfile() {
        return stateProfile;
    }

    private String ID;
    private String name;
    private String surname;
    private String email;
    private String department;
    private String password;
    private String IDTemp;
    private String degreeCourse;
    private String stateProfile;


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

    public void setDegreeCourse(String degreeCourse) {
        this.degreeCourse = degreeCourse;
    }

    public void setStateProfile(String stateProfile) {
        this.stateProfile = stateProfile;
    }

    public SeatInUser(String iD, String name, String surname, String email, String password, String IDTemp,String stateProfile) {
        super(iD, name, surname, email, password, IDTemp, stateProfile);
        this.degreeCourse = degreeCourse;
        this.ID=iD;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.password=password;
        this.IDTemp=IDTemp;
        this.degreeCourse=degreeCourse;
        this.stateProfile=stateProfile;



    }
    public String isClass() {
        return "SeatInUser";
    }
}