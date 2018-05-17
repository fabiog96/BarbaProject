package latoServer;

public class SeatInStudent extends SeatInUser {
    private static final long serialVersionUID=1;

    private String ID;
    private String name;
    private String surname;
    private String email;
    private String department;
    private String password;
    private String IDTemp;
    private String degreeCourse;
    private String stateProfile;
    private int enrollmentYear;
    private int courseYear;
    private String courseState;

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

    @Override
    public String getDegreeCourse() {
        return degreeCourse;
    }

    public String getState() {
        return stateProfile;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public String getCourseState() {
        return courseState;
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

    public void setDegreeCourse(String degreeCourse) {
        this.degreeCourse = degreeCourse;
    }

    public void setStateProfile(String stateProfile) {
        this.stateProfile = stateProfile;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public void setCourseState(String courseState) {
        this.courseState = courseState;
    }

    public SeatInStudent(String iD, String name, String surname, String email, String password, String iDTemp,
                         String stateProfile, int enrollmentYear, int courseYear, String courseState, String degreeCourse) {
        super(iD, name, surname, email, password, iDTemp, degreeCourse);
        this.enrollmentYear = enrollmentYear;
        this.courseYear = courseYear;
        this.courseState = courseState;
        this.degreeCourse = degreeCourse;
        this.ID= iD;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.password=password;
        this.IDTemp=iDTemp;
        this.stateProfile=stateProfile;

    }

    public String isClass()
    {
        return "SeatInStudent";
    }

}