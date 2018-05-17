package latoServer;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.rmi.server.*;

//implementazione metodi
public class SeatInServer implements SeatInServerInterface{
    DatabaseQuery query;

    {
        try {
            query = new DatabaseQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SeatInServer() throws RemoteException {
    }
        private static String[] createTemporaryPasswordAndCode() throws RemoteException{
        String temporaryPassword = "";
        String temporaryCode = "";
        Random r = new Random();

        for (int i = 0; i < 5; i++) {
            temporaryPassword += String.valueOf(( (String.valueOf((char)(r.nextInt(26) + 'a'))).concat(String.valueOf((int)(Math.random() * 10)))));
            temporaryCode += String.valueOf(( (String.valueOf((char)(r.nextInt(26) + 'a'))).concat(String.valueOf((int)(Math.random() * 10)))));
        }
        System.out.print("pw" + temporaryPassword + "code"+ temporaryCode);
        String[] pwAndCode= {temporaryPassword, temporaryCode};
            return  pwAndCode;
        }
       public Object login(SeatInPeople person) throws RemoteException
        {
            System.out.print(person.getPassword());
            System.out.print(person.getEmail());
            String password= "";
            try {
                password =query.login(person);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            switch (password)
            {
                case "utente bloccato" : return "utente bloccato";
                case "utente inesistente": return "utente inesistente";
                default:
                    try {
                        if (person.getPassword().equals(password))
                        return profileInformation(person);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
            return "password errata";
        }

    public boolean checkEmail(String email) throws RemoteException
    {
        boolean value=false;
        try {
           value= query.emailController(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    public boolean insertProfileIntoDatabase(SeatInPeople person) throws RemoteException
    {
        String[] passwordAndCode=createTemporaryPasswordAndCode();
        person.setPassword(passwordAndCode[0]);
        person.setIDTemp(passwordAndCode[1]);
        System.out.print("password" +person.getPassword());
        boolean flag=false;
        System.out.print(person.getID());
        if (person instanceof SeatInStudent)
        {
            try {
                // controllo prima se il corso che ha inserito l'utente esista realmente nel database
                if (query.findCourseDegree(((SeatInStudent) person).getDegreeCourse()))
                    flag= query.insertStudent((SeatInStudent)person);
                System.out.print("Uno studente si sta registrando" );

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (person instanceof SeatInAdmin)
        {
            try {

               flag= query.insertAdmin((SeatInAdmin)person);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
            // è un docente
        {
            try {
               flag= query.insertTeacher((SeatInTeacher)person);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String body="benvenuto sulla piattaforma seatIn! questi dati dovranno essere da te utilizzati al prossimo accesso:\nPASSWORD:"+person.getPassword()+"\nCODICE ATTIVAZIONE:"+ person.getIDTemp();
        try {
            EmailSender.sendUninsubriaEmail(MainClass.email, MainClass.password, person.getEmail(), "registrazione al portale SEATIN",body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return flag;
        }

        public boolean updateProfileState(SeatInPeople person, String state) throws RemoteException
        {
            boolean flag=false;
            if (person instanceof SeatInStudent)
            {
                try {
                    flag=query.updateStudentProfileState(state, person.getEmail());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if (person instanceof SeatInAdmin)
            {
                try {
                    flag=query.updateAdminProfileState(state, person.getEmail());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                   flag= query.updateTeacherProfileState(state, person.getEmail());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return flag;

        }

        public boolean resetPasswordRequest(SeatInPeople person) throws RemoteException{
            if (person.getPassword().equals("")) {
                String[] password = createTemporaryPasswordAndCode();
                person.setPassword(password[0]);
            }
            boolean flag=false;
                // else significa che l'utente ha deciso di inserire una password custom
                //avvio procedura di update
            if (person instanceof SeatInStudent)
                flag= query.updateStudentPassword((SeatInStudent) person);
            else if (person instanceof SeatInAdmin)
              flag=query.updateAdminPassword((SeatInAdmin) person);
            else
              flag=query.updateTeacherPassword((SeatInTeacher) person);
            return flag;
        }
        //viene mandato un profilo contenente campi nulli ma con email, ritorna profilo con tutte le informazioni
         public Object profileInformation (SeatInPeople person) throws RemoteException, SQLException {
             boolean flag = false;
             switch (person.isClass()) {
                 case "SeatInStudent":
                     return (query.studentProfile(person.getEmail()));
                 case "SeatInTeacher":
                     return (query.adminProfile(person.getEmail()));
                 case "SeatInAdmin":
                     return (query.teacherProfile(person.getEmail()));


             }
             return "class not found";

         }
         public ArrayList<String[]> showCourse () throws IOException, RemoteException{
            return FileCSV.readCourseCSV();
         }
         public HashMap<String, List<? extends SeatInPeople>> showUserCSV () {
             return FileCSV.readUserCSV();
         }


    }


