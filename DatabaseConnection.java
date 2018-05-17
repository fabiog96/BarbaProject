package latoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStreamReader;
import java.sql.*;

public class DatabaseConnection {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static Connection connection = null;

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        DatabaseConnection.connection = connection;
    }

    public DatabaseConnection(){
        String url="jdbc:postgresql://localhost:5432/postgres";
        try {
          Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Properties props = new Properties();
        props.setProperty("user","postgres");
        System.out.println(" inserisci password per accedere al database");
        String password= null;
        try {
            password = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        props.setProperty("password",password);

        try {
            this.connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
 //   public static void close ()
   // {
    //     try {
     //        connection.close();
      //   } catch (SQLException e) {
        //   e.printStackTrace();
         //}
   // }

}
