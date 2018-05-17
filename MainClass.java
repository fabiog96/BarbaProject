package latoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass {
    public static DatabaseQuery query;
    public static String email, password;
    static {
        try {

            query = new DatabaseQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        SeatInServer server = new SeatInServer();
      //richiedo user e password utilizzate per inviare mail
        System.out.print("email");
        email=in.readLine();
        System.out.print("pw");
        password=in.readLine();

        //controllo se nel database vi è almeno un profilo "amministratore"
         if (!query.ifAdministratorExists()) {
            //nessun amministratore nel sistema, il server richiede la creazione
            System.out.println("specificare i seguenti dati per la registrazione: ");
            String[] administratorData = {"matricola", "nome", "cognome", "email", "dipartimento"};
             String[] whatToInsert = new String[5];
                for (int i = 0; i < 5; i++)
                {
                  System.out.print(administratorData[i]);
                  whatToInsert[i] = in.readLine();
                 }
                 SeatInAdmin administrator=new SeatInAdmin(whatToInsert[0], whatToInsert[1], whatToInsert[2], whatToInsert[3], "", "", whatToInsert[4] , "non attivo");
                 server.insertProfileIntoDatabase(administrator);

        }
          // il server puo' ora partire
        int portaRegistry = 2099;//Integer.parseInt(args[0]);
        int portaClasseRemota = 11699; //Integer.parseInt(args[1]);
        //System.setProperty("java.rmi.server.hostname", "192.168.1.79");
        SeatInServer cR = new SeatInServer();
        String bind_name = "classeRemota";
        Registry r = null;
        SeatInServerInterface i = null;
        try {
            r = LocateRegistry.getRegistry(portaRegistry);
        } catch (RemoteException e) {}
            try {
                r = LocateRegistry.createRegistry(portaRegistry);
                System.out.print("get registry" + r);
            } catch (RemoteException e1) {
            }

            try {
                i = (SeatInServerInterface) UnicastRemoteObject.exportObject(cR, portaClasseRemota);
                r.rebind(bind_name, i);
                System.out.print(i);
            } catch (Exception ex) {
                Logger.getLogger(SeatInServer.class.getName()).log(Level.SEVERE, "Error while starting server.", ex);
                System.exit(-1);
            }
            Logger.getLogger(SeatInServer.class.getName()).log(Level.INFO, "ClasseRemota succesfully binded to RMI Registry with name :" + bind_name);

    }
}

