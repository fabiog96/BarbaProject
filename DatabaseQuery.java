package latoServer;


import java.sql.*;
import java.util.ArrayList;


public class DatabaseQuery extends DatabaseConnection {
    Connection conn = getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs;

    public DatabaseQuery() throws SQLException {
        super();
    }

    //prima operazione da eseguire all'avvio del server: controllo se nel db esiste almeno un profilo utente
    public boolean ifAdministratorExists() throws SQLException {
        try {
            rs = stmt.executeQuery("SELECT * from AMMINISTRATORE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs.next())
            return true;

        return false;
    }

    public String login(SeatInPeople person) throws SQLException {

        String sql = ("Select password, statoProfilo from login where email= ?");
        //String sql=("Select password from loginTable" );
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        prepStat.setString(1, person.getEmail());
        rs = prepStat.executeQuery();
        if (rs.next()) {
            if (!rs.getString("statoProfilo").equals("bloccato"))
                return rs.getString("password");
            else
                return "utente bloccato";
        } else
            return "utente inesistente";
    }

    public boolean insertAdmin(SeatInAdmin admin) throws SQLException {
        String sql = "insert into AMMINISTRATORE values (?,?, ?, ? ,? ,?, ?,?)";
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        prepStat.setString(1, admin.getID());
        prepStat.setString(2, admin.getName());
        prepStat.setString(3, admin.getSurname());
        prepStat.setString(4, admin.getEmail());
        prepStat.setString(5, admin.getPassword());
        prepStat.setString(6, admin.getIDTemp());
        prepStat.setString(7, "non attivo");
        //ho bisogno di un int, l'utente mi fornisce una stringa
        prepStat.setString(8, admin.getDepartment());
        prepStat.executeUpdate();

        return true;

    }

    public boolean emailController(String email) throws SQLException {
        try {
            rs = stmt.executeQuery("SELECT * from login");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs.next()) {
            if (email.equals(rs.getString("email"))) {
                return false;
            }
        }


        return true;
    }

    public boolean insertStudent(SeatInStudent student) throws SQLException {
        //matricola varchar(20) unique, nome varchar(20), cognome varchar(20),
        // email varchar(30) primary key, password varchar(20),
        //codiceAttivazione varchar(10), statoProfilo varchar(15),
        // annoIscrizione numeric(4), annoCorso numeric(1), statoCorso varchar(10), codiceCorsoLaurea integer,

        String sql = "insert into STUDENTE values (?,?, ?, ? ,? ,?, ?, ?, ?,?,?)";
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        //Studente(matricola,nome,cognome,email,password,codiceattivazione,statoProfilo,anno,statoCorso,nomeCorsoDiLaurea)
        prepStat.setString(1, student.getID());
        prepStat.setString(2, student.getName());
        prepStat.setString(3, student.getSurname());
        prepStat.setString(4, student.getEmail());
        prepStat.setString(5, student.getPassword());
        prepStat.setString(6, student.getIDTemp());
        prepStat.setString(7, "non attivo");
        prepStat.setInt(8, student.getEnrollmentYear());
        prepStat.setInt(9, student.getCourseYear());
        prepStat.setString(10, student.getCourseState());

        //questo parametro va controllato in un'altra tabella -> vincolo di integrità
        prepStat.setString(11, student.getDegreeCourse());
        prepStat.executeUpdate();
        return true;


    }

    public boolean insertTeacher(SeatInTeacher teacher) throws SQLException {
        //create table docente(matricola varchar(20) unique, nome varchar(20), cognome varchar(20),
        // email varchar(30) primary key, password varchar(20), codiceAttivazione
        //varchar(10), statoProfilo varchar(15), nomeDipartimento varchar(30)
        String sql = "insert into DOCENTE values (?,?, ?, ? ,? ,?, ?, ?)";
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        prepStat.setString(1, teacher.getID());
        prepStat.setString(2, teacher.getName());
        prepStat.setString(3, teacher.getSurname());
        prepStat.setString(4, teacher.getEmail());
        prepStat.setString(5, teacher.getPassword());
        prepStat.setString(6, teacher.getIDTemp());
        prepStat.setString(7, "non attivo");
        // l'utente mi fornisce una stringa
        prepStat.setString(8, teacher.getDepartment());
        prepStat.executeUpdate();

        return true;
    }

    public boolean findCourseDegree(String course) throws SQLException {
        String sql = "select * from corsoDiLaurea where nome=?";
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        prepStat.setString(1, course);
        rs = prepStat.executeQuery();
        if (rs.next())
            return true;
        return false;
    }

    public boolean updateAdminProfileState(String state, String email) throws SQLException {
        String sql = "UPDATE amministratore SET statoProfilo = ? WHERE email=?";
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
            // prepStat.setString(1, tableName);
            prepStat.setString(1, state);
            prepStat.setString(2, email);
            prepStat.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudentProfileState(String state, String email) throws SQLException {
        String sql = "UPDATE studente SET statoProfilo = ? WHERE email=?";
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
            // prepStat.setString(1, tableName);
            prepStat.setString(1, state);
            prepStat.setString(2, email);
            prepStat.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTeacherProfileState(String state, String email) throws SQLException {
        String sql = "UPDATE docente SET statoProfilo = ? WHERE email=?";
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
            // prepStat.setString(1, tableName);
            prepStat.setString(1, state);
            prepStat.setString(2, email);
            prepStat.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudentPassword(SeatInStudent person) {
        String sql = "UPDATE studente SET password = ? WHERE email=?";
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
            // prepStat.setString(1, tableName);
            prepStat.setString(1, person.getPassword());
            prepStat.setString(2, person.getEmail());
            prepStat.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAdminPassword(SeatInAdmin person) {
        String sql = "UPDATE amministratore SET password = ? WHERE email=?";
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
            // prepStat.setString(1, tableName);
            prepStat.setString(1, person.getPassword());
            prepStat.setString(2, person.getEmail());
            prepStat.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTeacherPassword(SeatInTeacher person) {
        String sql = "UPDATE docente SET password = ? WHERE email=?";
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
            // prepStat.setString(1, tableName);
            prepStat.setString(1, person.getPassword());
            prepStat.setString(2, person.getEmail());
            prepStat.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object studentProfile(String email) throws SQLException {
        //matricola varchar(20) unique, nome varchar(20), cognome varchar(20),
        // email varchar(30) primary key, password varchar(20),
        //codiceAttivazione varchar(10), statoProfilo varchar(15),
        // annoIscrizione numeric(4), annoCorso numeric(1), statoCorso varchar(10), codiceCorsoLaurea integer,

        String sql = "select * from studente where email=?";
        PreparedStatement prepStat = null;
        try {
          prepStat = conn.prepareStatement(sql);
            prepStat.setString(1, email);
          rs=  prepStat.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rs.next()) {
            //ritorna un oggetto che verra' inviato al client
            String ID = rs.getString("matricola");
            String name = rs.getString("nome");
            String surname = rs.getString("cognome");
            //String email=rs.getString(""email");
            // String password=rs.getString();
            //String codiceAttivazione=rs.getString();
            String profileState = rs.getString("statoProfilo");
            int enrollmentYear = rs.getInt("annoIscrizione");
            int courseYear = rs.getInt("annoCorso");
            String stateCourse = rs.getString("statoCorso");
            String courseDegree = rs.getString("corsoLaurea");
            return new SeatInStudent(ID, name, surname, email, "", "", profileState, enrollmentYear, courseYear, stateCourse, courseDegree);
        }
        return "not found";
    }

    public Object adminProfile(String email) throws SQLException {
        String sql = "select * from amministratore where email=?";
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
            prepStat.setString(1, email);
            rs = prepStat.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs.next()) {
            //ritorna un oggetto che verra' inviato al client
            String ID = rs.getString("matricola");
            String name = rs.getString("nome");
            String surname = rs.getString("cognome");
            //String email=rs.getString(""email");
            // String password=rs.getString();
            //String codiceAttivazione=rs.getString();
            String profileState = rs.getString("statoProfilo");
            String department = rs.getString("nomeDipartimento");
            return new SeatInAdmin(ID, name, surname, email, "", "", profileState, department);

        }

        return "not found";
    }

    public Object teacherProfile(String email) throws SQLException {
        String sql = "select * from docente where email=?";
        PreparedStatement prepStat = null;
        try {
            prepStat = conn.prepareStatement(sql);
            prepStat.setString(1, email);
            prepStat.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs.next()) {
            //ritorna un oggetto che verra' inviato al client
            String ID = rs.getString("matricola");
            String name = rs.getString("nome");
            String surname = rs.getString("cognome");
            //String email=rs.getString(""email");
            // String password=rs.getString();
            //String codiceAttivazione=rs.getString();
            String profileState = rs.getString("statoProfilo");
            String department = rs.getString("nomeDipartimento");
            return new SeatInTeacher(ID, name, surname, email, "", "", profileState, department);
        }
        return "not found";
    }


    public boolean insertCourse(ArrayList<String[]> courses) {
        //codice nome anno cdl descrizione
        String sql = "insert into corso values (?.?,?,?,?)";

return true;
    }
}


