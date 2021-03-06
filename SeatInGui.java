package prova;

//import latoServer.SeatInPeople;

//import latoServer.SeatInStudent;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
        import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public class SeatInGui extends JFrame {
    private String mail;
    private static final long serialVersionUID = 1;

    public Container mainContainer = getContentPane();



    public SeatInGui() {
        super("SeatIn");
        mainContainer.setLayout(new FlowLayout());
        mainContainer.setPreferredSize(new Dimension(600, 400));


    }

    public void Connection() throws RemoteException, NotBoundException {
      /*  if (System.getSecurityManager() == null)
        {
            System.setSecurityManager
                    (new RMISecurityManager());
        }
        String host="192.168.1.83";
        int port=1099;
        Registry reg=LocateRegistry.getRegistry(host, port);
        stub=(SeatInServerInterface)reg.lookup("classeRemota");

       // stub.checkEmail(mail);
*/
    }



    public void welcometoSeatIn() {


        mainContainer.removeAll();
        mainContainer.validate();
        mainContainer.repaint();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(BorderLayout.CENTER, loginInterface());
        //mainContainer.add(BorderLayout.WEST, registerProfile());

        setVisible(true);
        setSize(600, 300);

    }


    /** Panello Login **/
    public JPanel loginInterface() {
        JPanel welcomeLogin = new JPanel();
        // pannello contenente email, pw, password dimenticata, tasto accesso

        JTextField email = new JTextField("studenti", 20);
        JButton access = new JButton("Accedi");
        access.setSize(10, 10);
        access.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*if () {
                    //se l'utente � istanceOf[Studente]
                    mainContainer.removeAll();
                    mainContainer.validate();
                    mainContainer.repaint();
                    mainContainer.setLayout(new BorderLayout());
                    mainContainer.add(BorderLayout.CENTER, mainPanelAfterLoginStudent());
*/
                //}else if () {
                    mainContainer.removeAll();
                    mainContainer.validate();
                    mainContainer.repaint();
                    mainContainer.setLayout(new BorderLayout());
                    mainContainer.add(BorderLayout.CENTER,adminPanel());
   //             }
                    setVisible(true);
            }
        });
        JButton passwordForgot = new JButton("Password dimenticata");
        passwordForgot.setSize(60, 20);
        JButton submit = new JButton("Registrati!");

        welcomeLogin.setLayout(new GridLayout(5, 3));
        welcomeLogin.add(new JLabel("Benvenuti nella Piattaforma SeatIn  "));
        welcomeLogin.add(new JLabel(""));
        welcomeLogin.add(new JLabel(""));
        welcomeLogin.add(email);
        welcomeLogin.add(new JLabel(""));
        welcomeLogin.add(new JLabel(""));
        JPasswordField passwordField = new JPasswordField("Password", 20);
        passwordField.addFocusListener(new FocusListener() {
            //quando clicco sul textField Email scompare il suggerimento
            public void focusGained(FocusEvent e) {
                passwordField.setText("password");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        welcomeLogin.add(passwordField);
        welcomeLogin.add(new JLabel(""));
        welcomeLogin.add(new JLabel(""));
        welcomeLogin.add(access);
        welcomeLogin.add(new JLabel(""));
        welcomeLogin.add(new JLabel(""));
        welcomeLogin.add(passwordForgot);
        passwordForgot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordForgotten();
            }
        });
        welcomeLogin.add(new JLabel(""));
        welcomeLogin.add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                registerProfile();
                welcomeLogin.setVisible(false);
            }
        });
        /*


         */


        return welcomeLogin;
    }



    /** Panello Registartzione **/
    public JPanel registerProfile() {
        Container container = getContentPane();
        JPanel submit = new JPanel();
        JTextField registerInformation[] = {
                //           nome                         cognome                 matricola       email             course degree              matricola           anno immatricolazione
                new JTextField(20), new JTextField(20), new JTextField(), new JTextField(20), new JTextField(), new JTextField(), new JTextField(4)
        };

        String year[] = {"----", "1", "2", "3"};
        String kindYear[] = {"-----", "fuori corso", "in corso"};
        final String actor[] = {"----- ", "studente", "docente", "amministratore"};
        final String[] departmentList = {"-----", "DISTA", "DIMAT"};
        JComboBox comboBoxYear[] = {new JComboBox<>(year), new JComboBox(kindYear), new JComboBox(actor), new JComboBox<String>(departmentList)};
        JPanel panelForYear = new JPanel();
        panelForYear.setLayout(new GridLayout(1, 2));
        for (JComboBox it : comboBoxYear)
            panelForYear.add(it);
        registerInformation[3].setText("nome@dominio.it");
        registerInformation[3].addFocusListener(new FocusListener() {
            //quando clicco sul textField Email scompare il suggerimento
            public void focusGained(FocusEvent e) {
                registerInformation[3].setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        submit.setLayout(new GridLayout(14, 2));
        JLabel introducintToSubscription[] = {new JLabel("oppure ISCRIVITI!"), new JLabel(""), new JLabel("sono un: ")};
        for (JLabel label : introducintToSubscription)
            submit.add(label);
        JLabel labelForReg[] = {new JLabel("Nome"), new JLabel("Cognome"), new JLabel("Matricola"), new JLabel("Email"), new JLabel("Dipartimento :"),
                new JLabel("Corso di laurea: "), new JLabel("Anno di immatricolazione: "), new JLabel("Anno di corso:"), new JLabel("Stato Corso")};
        submit.add(comboBoxYear[2]);
        JButton ok = new JButton("REGISTRATI");
        Object objectReg[] = {registerInformation[0], registerInformation[1], registerInformation[2], registerInformation[3],
                comboBoxYear[3], registerInformation[4], registerInformation[5], panelForYear, ok};
        for (int i = 0; i < 9; i++) {
            submit.add(labelForReg[i]);
            submit.add(labelForReg[i]);
            submit.add((Component) objectReg[i]);
        }

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //bottone Registrati
                mail = registerInformation[4].getText();
             /*  try {
//                   stub.checkEmail(mail);
                   SeatInStudent user = new SeatInStudent();
                    stub.ciaoStringa(user);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }*/


                JOptionPane.showMessageDialog(submit, "Bottone Registrati");

            }
        });
        comboBoxYear[2].addItemListener(new ItemListener() {
                                            @Override
                                            public void itemStateChanged(ItemEvent e) {
                                                if (e.getStateChange() == ItemEvent.SELECTED) {
                                                    //System.out.print(iam.getSelectedIndex());
                                                    comboBoxYear[3].removeAllItems();
                                                    comboBoxYear[0].removeAllItems();
                                                    comboBoxYear[1].removeAllItems();
                                                    if (comboBoxYear[2].getSelectedIndex() == 0) {
                                                        for (int i = 4; i <= 5; i++) {
                                                            registerInformation[i].setEditable(false);
                                                            registerInformation[i].setText("non disponibile per questo profilo");
                                                            registerInformation[i].setBackground((Color.LIGHT_GRAY));
                                                        }
                                                        for (int i = 0; i <= 1; i++)
                                                            comboBoxYear[i].setBackground((Color.LIGHT_GRAY));
                                                    }
                                                    if (comboBoxYear[2].getSelectedIndex() == 1) {
                                                        //if student
                                                        comboBoxYear[3].addItem("--------");
                                                        for (int i = 4; i <= 5; i++) {
                                                            registerInformation[i].setEditable(true);
                                                            registerInformation[i].setText("");
                                                            registerInformation[i].setBackground((Color.WHITE));
                                                        }
                                                        comboBoxYear[1].setBackground(Color.WHITE);
                                                        comboBoxYear[0].setBackground(Color.WHITE);
                                                        //String year[] = {"", "1", "2", "3"};
                                                        //String kindYear[] = {"", "fuori corso", "in corso"};
                                                        for (int i = 0; i < 3; i++) {
                                                            comboBoxYear[1].addItem(kindYear[i]);
                                                        }
                                                        for (int i = 0; i < 4; i++) {

                                                            comboBoxYear[0].addItem(year[i]);
                                                        }
                                                        registerInformation[3].setText("nome@studenti.uninsubria.it");

                                                    }
                                                    if (comboBoxYear[2].getSelectedIndex() == 2 || comboBoxYear[2].getSelectedIndex() == 3) {
                                                        //if student
                                                        //   submit.remove(panelForStudent);
                                                        for (String i : departmentList)
                                                            comboBoxYear[3].addItem(i);
                                                        for (int i = 4; i <= 5; i++) {
                                                            registerInformation[i].setEditable(false);
                                                            registerInformation[i].setText("non disponibile per questo profilo");
                                                            registerInformation[i].setBackground((Color.LIGHT_GRAY));
                                                        }
                                                        comboBoxYear[0].setBackground((Color.LIGHT_GRAY));
                                                        comboBoxYear[1].setBackground((Color.LIGHT_GRAY));
                                                        registerInformation[3].setText("nome@uninsubria.it");
                                                    }
                                                }
                                            }
                                        }
        );
        // mainContainer.add(submit);

        container.add(submit);

        setVisible(true);
        return submit;
    }


    /** Panello RecuperoPassword **/
    public void passwordForgotten() {
        JButton backtoLogin = new JButton("<--");
        backtoLogin.setPreferredSize(new Dimension(5, 5));
        backtoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //torno alla schermata iniziale
                welcometoSeatIn();
            }
        });
        JPanel panelBackToLogin = new JPanel();
        panelBackToLogin.setLayout(new GridLayout(1, 3));
        panelBackToLogin.add(backtoLogin);
        panelBackToLogin.add(new JLabel("Procedura di ripristino passsword"));

        JPanel panelForChangePw = new JPanel();
        panelForChangePw.setLayout(new BorderLayout());
        panelForChangePw.add(BorderLayout.NORTH, panelBackToLogin);
        JButton ok = new JButton("OK");
        mainContainer.removeAll();
        mainContainer.validate();
        mainContainer.repaint();
        JPanel panelForChangePassword = new JPanel();
        panelForChangePassword.setLayout(new GridLayout(2, 2));
        JTextField email = new JTextField();
        email.setSize(new Dimension(10, 10));
        panelForChangePassword.add(new JLabel("email:"));
        panelForChangePassword.add(email);
        panelForChangePassword.add(new JLabel(""));
        panelForChangePassword.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SeatInGui.this, "una email e' stata inviata all'indirizzo " + email.getText());
                welcometoSeatIn();

            }
        });
        panelForChangePw.add(panelForChangePassword);
        mainContainer.add(panelForChangePw);
        setSize(600, 300);
        setVisible(true);


    }


    /** Panello studente **/
    public JPanel mainPanelAfterLoginStudent() {


    /*
        JMenu menu[] = {new JMenu("Profilo"), new JMenu("Corsi"), new JMenu("Email")};
        JMenuItem menuItemForProfile[] = {new JMenuItem("visualizza profilo"), new JMenuItem("Modifica password"),
                new JMenuItem("Richiedi modifica dei dati personali"), new JMenuItem("logout")};
        for (JMenuItem item : menuItemForProfile) {
            menu[0].add(item);
        }


        JMenuItem menuItemForCourse[] = {
                new JMenuItem("visualizza corsi a cui sei iscritto/a"),
                new JMenuItem("iscriviti ad un corso")
        };
        for (JMenuItem item : menuItemForCourse)
            menu[1].add(item);


        JMenuItem menuItemForEmail[] = {
                new JMenuItem("invia email ad un docente")
        };


        for (JMenuItem item : menuItemForEmail)
            menu[2].add(item);
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        for (JMenu menuInVector : menu)
            bar.add(menuInVector);
*/
        JPanel panelAfterLogin = new JPanel();
        panelAfterLogin.setLayout(new BorderLayout());
        JMenuBar bar  = new JMenuBar();

        JMenu m1 = new JMenu("Utenti");
        JMenu m2 = new JMenu("Corsi");
        JMenu m3 = new JMenu("Email");


        JMenuItem m1a = new JMenuItem("Visualizza profilo Utente");
        JMenuItem m1b = new JMenuItem("Modifica Paswword");
        m1b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordForgotten();
            }
        });
        JMenuItem m1c = new JMenuItem("Richiedi modfica dei dati personali");
        /** invia mail all'admin**/

        JMenuItem m1d = new JMenuItem("Logout");
        m1d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcometoSeatIn();
                panelAfterLogin.setVisible(false);
            }
        });


        JMenuItem m2a = new JMenuItem("Visualizza corsi");
        JMenuItem m2b = new JMenuItem("Iscriviti ad un Corso");

        JMenuItem m3a = new JMenuItem("Invia una mail");

        bar.add(m1);
        bar.add(m2);
        bar.add(m3);

        m1.add(m1a);
        m1.add(m1b);
        m1.add(m1c);
        m1.add(m1d);

        m2.add(m2a);
        m2.add(m2b);

        m3.add(m3a);


        panelAfterLogin.add(BorderLayout.NORTH, bar);

        panelAfterLogin.add(BorderLayout.CENTER, viewProfile());
        Container container = getContentPane();
        container.add(panelAfterLogin);
        setVisible(true);
        return panelAfterLogin;


    }
    public JPanel viewProfile() {
        JPanel profileInformation = new JPanel();
        profileInformation.setLayout(new GridLayout(8, 2));
        JLabel personalData[] = {new JLabel("nome"), new JLabel("cognome"), new JLabel("matricola"), new JLabel("email"), new JLabel("corso"), new JLabel("anno immatricolazione"), new JLabel("anno:"), new JLabel("tipologia")};
        JTextField personalDataTextField[] = {new JTextField("nome"), new JTextField("cognome"), new JTextField("matricola"),
                new JTextField("email"), new JTextField("cdl"), new JTextField("anno immatricolazione"), new JTextField("anno"), new JTextField("tipologia")};

        for (int i = 0; i < 8; i++) {
            profileInformation.add(personalData[i]);
            profileInformation.add(personalDataTextField[i]);
            personalDataTextField[i].setEditable(false);
        }

        setVisible(true);
        return profileInformation;
    }





    /** Panello Admin **/
    public JPanel Viewcorsi (){
       JPanel corsi = new JPanel();
       /* corsi.setLayout(new GridLayout(1,3));

        String[] ArrayCorsi = {"Corso1\n","Corso2\n","Corso3\n"};


        JTextArea area1 = new JTextArea(1,0);
        JTextArea area2 = new JTextArea(1,0);
        JButton select = new JButton("Seleziona Corsi");
        for(int i =0; i<ArrayCorsi.length;i++) {
            area1.append(ArrayCorsi[i]);
        }

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = area1.getSelectedText();
                if(text == area2.getText()) {
                    area2.append(text + "\n");
                }else{
                    JOptionPane.showMessageDialog(corsi,"Il corso è già stato selezionato");
                }

            }
        });



        corsi.add(area1);
        corsi.add(area2);

        corsi.add(select);



        setVisible(true);
       return corsi; */
        JTextArea area1, area2;
        JButton check = new JButton("Abilita");
        JButton backmain = new JButton("Esci");

        Box box = Box.createHorizontalBox();
        String[] ArrayCorsi = {"Corso1\n","Corso2\n","Corso3\n"};


        area1 = new JTextArea(10,15);
        box.add(new JScrollPane(area1));

        for(int i =0; i<ArrayCorsi.length;i++) {
            area1.append(ArrayCorsi[i]);
        }



        JButton select = new JButton("->");
        box.add(select);
        box.add(new JLabel(""));
        JButton back = new JButton("<-");
        box.add(back);
        area2 = new JTextArea(10,15);
        area2.setEditable(false);
        box.add(new JScrollPane(area2));

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = area1.getSelectedText();
                    area2.append(s+"\n");

            


            }
        });








        corsi.add(box);

        setVisible(true);
        setSize(600,400);

        return corsi;
    }



    public JPanel adminPanel(){
        JPanel admin = new JPanel();
        admin.setLayout(new BorderLayout());


        JMenuBar mb  = new JMenuBar();

        JMenu m1 = new JMenu("Utenti");
        JMenu m2 = new JMenu("Corsi");
        JMenu m3 = new JMenu("Opzioni");

        JMenuItem m1i = new JMenuItem("Visualizza profilo Utente");

        JMenuItem m3i = new JMenuItem("Abilita Corsi");
        m3i.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // admin.add(Viewcorsi(),BorderLayout.CENTER);
                admin.add(Viewcorsi(),BorderLayout.CENTER);



            }
        });
        JMenuItem m4i = new JMenuItem("Logout");
        m4i.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              welcometoSeatIn();
              admin.setVisible(false);
            }
        });
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);

        m1.add(m1i);


        m2.add(m3i);
        m3.add(m4i);

        admin.add(mb,BorderLayout.NORTH);
        setVisible(true);
        setSize(600,400);
        return admin;
    }





    public static void main(String[] args) throws RemoteException, NotBoundException {
        SeatInGui sIn = new SeatInGui();
        sIn.welcometoSeatIn();
        sIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}

