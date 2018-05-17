package latoServer;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SeatInServerInterface extends Remote {
    public Object login(SeatInPeople person) throws RemoteException;

    public boolean checkEmail(String email) throws RemoteException;

    public boolean insertProfileIntoDatabase(SeatInPeople person) throws RemoteException;

    public boolean resetPasswordRequest(SeatInPeople person) throws RemoteException;

    public boolean updateProfileState(SeatInPeople person, String state) throws RemoteException;

    public Object profileInformation(SeatInPeople person) throws RemoteException, SQLException;

    public ArrayList<String[]> showCourse() throws IOException, RemoteException;

    public HashMap<String, List<? extends SeatInPeople>> showUserCSV () throws  RemoteException;
}
