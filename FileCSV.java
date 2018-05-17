package latoServer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class FileCSV {

    private final static String csvUserFile=    "/Users/gdelvecchio/Desktop/laboratorioB/Utente.csv";
    private final static String csvCourseFile = "/Users/gdelvecchio/Desktop/laboratorioB/corsi.csv";
    static BufferedReader br = null;
    //campi per file di tipo "corso"
    private String idCode, name, activationYear, degreeCourse, description;
    private static ArrayList<String[]> courseList = new ArrayList<>();

    public FileCSV(String idCode, String name, String activationYear, String degreeCourse, String description) {
        this.idCode = idCode;
        this.name = name;
        this.degreeCourse = degreeCourse;
        this.description = description;
    }

    public FileCSV() {
    }


    public static ArrayList<String[]> readCourseCSV() throws IOException {
         String line = "";
         String cvsSplitBy = ";";
        ArrayList<String[]> courseList = new ArrayList<>();

        try {

            br = new BufferedReader(new FileReader(csvCourseFile));
            while ((line = br.readLine()) != null) {
                // il vettore temp conterra' i seguenti campi nell'ordine elencato:
               // String idCode, name, activationYear, degreeCourse, description;
                // use comma as separator
                String[] temp = line.split(cvsSplitBy);
                courseList.add(temp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return courseList;
    }

    public static HashMap<String, List<? extends SeatInPeople>> readUserCSV () {
        String line = "";
        String cvsSplitBy = ";";
        //il file csv contiene record di utenti
        //l'hashmap ha come possibili chiavi:
        //utente,docente, studente seguite da una lista di utenti del tipo corrispondente.
        HashMap<String, List<? extends SeatInPeople > > csvPeople =  new HashMap<>();
        List<SeatInStudent> studentList =new ArrayList<>();
        List <SeatInAdmin> adminList = new ArrayList<>();
        List <SeatInTeacher> teacherList = new ArrayList<>();
        try {

            br = new BufferedReader(new FileReader(csvUserFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] temp = line.split(cvsSplitBy);
               // significa che e' l'intestazione}
                 //temp[12] contiene una stringa che identifica la tipologia di utente a cui sono riferiti i dati
                if (temp[12].contains("S"))
                    //switch (temp[12]) {

                       // case "S":
                        {
                            studentList.add(new SeatInStudent(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[7], Integer.parseInt(temp[8]), Integer.parseInt(temp[9]), temp[10], temp[11]));
                        }
                        else if (temp[12].contains("D"))
                     //   case "D":
                            {
                            teacherList.add(new SeatInTeacher(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]));
                        }
                        else
                       // case "A":
                        {
                            adminList.add(new SeatInAdmin(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]));
                        }

                    //}
            }
            csvPeople.put("studente", studentList);
            csvPeople.put("docente", teacherList);
            csvPeople.put("amministratore", adminList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return csvPeople;
    }
    public static void main (String[] args)
    {
        HashMap x=FileCSV.readUserCSV();
        System.out.print(x);
    }
}



