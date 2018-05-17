package latoServer;


import java.io.Serializable;

public abstract class SeatInPeople implements Serializable {
    private static final long serialVersionUID=1;
    private String ID;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String IDTemp;

    public String getStateProfile() {
        return stateProfile;
    }

    private String stateProfile;
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIDTemp(String IDTemp) {
        this.IDTemp = IDTemp;
    }

    public String getID() {

        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getIDTemp() {
        return IDTemp;
    }

    public SeatInPeople(String iD, String name, String surname, String email, String password, String iDTemp,
                        String stateProfile) {
        super();
        this.ID = iD;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.IDTemp = iDTemp;
        this.stateProfile = stateProfile;
    }

    public abstract String isClass();
}









