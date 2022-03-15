package gui;

import controller.VacationPackageController;
import model.User;
import model.VacationPackage;
import model.enums.Status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class ClientGUI extends JFrame {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    private EntityManager entityManager;

    private JButton availableVacationsButton;
    private JButton myVacationsButton;
    private JButton searchByDestinationButton;
    private JButton searchByPriceButton;
    private JButton searchByPeriodButton;
    private JButton backButton;
    private JButton bookButton;
    private JTextField destinationNameTextField;
    private JTextField maxPriceTextField;
    private JTextField minPriceTextField;
    private JTextField afterDateTextField;
    private JTextField beforeDateTextField;
    private JTextField packageIdBookedTextField;
    private JTextArea textArea;
    User currentUser;

    public ClientGUI(){
        VacationPackageController vacationPackageController = new VacationPackageController();
        entityManager = entityManagerFactory.createEntityManager();
        LogInGUI logInGUI = new LogInGUI();
        this.currentUser = logInGUI.getClient();

        setTitle("You successfully logged in as a client!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,700,650);
        JPanel jPanel = new JPanel();
        jPanel.setForeground(new Color(177, 127, 127));
        jPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(jPanel);
        jPanel.setLayout(null);

        myVacationsButton = new JButton("Display my vacations");
        myVacationsButton.setForeground(Color.WHITE);
        myVacationsButton.setBackground(Color.DARK_GRAY);
        myVacationsButton.setBounds(10,10,200,30);
        jPanel.add(myVacationsButton);

        availableVacationsButton = new JButton("Display available packages");
        availableVacationsButton.setForeground(Color.WHITE);
        availableVacationsButton.setBackground(Color.DARK_GRAY);
        availableVacationsButton.setBounds(10,50,200,30);
        jPanel.add(availableVacationsButton);

        JLabel jLabel7 = new JLabel("Insert keyword: ");
        jLabel7.setForeground(Color.BLACK);
        jLabel7.setFont(new Font("Times New Roman",Font.PLAIN,18));
        jLabel7.setBounds(10,90,200,55);
        jPanel.add(jLabel7);

        destinationNameTextField = new JTextField();
        destinationNameTextField.setForeground(Color.WHITE);
        destinationNameTextField.setBackground(Color.GRAY);
        destinationNameTextField.setBounds(210,105,130,25);
        jPanel.add(destinationNameTextField);

        searchByDestinationButton = new JButton("Search");
        searchByDestinationButton.setForeground(Color.WHITE);
        searchByDestinationButton.setBackground(Color.DARK_GRAY);
        searchByDestinationButton.setBounds(360,105,80,25);
        jPanel.add(searchByDestinationButton);

        JLabel jLabel8 = new JLabel("Insert maximum price: ");
        jLabel8.setForeground(Color.BLACK);
        jLabel8.setFont(new Font("Times New Roman",Font.PLAIN,18));
        jLabel8.setBounds(10,120,200,55);
        jPanel.add(jLabel8);

        maxPriceTextField = new JTextField();
        maxPriceTextField.setForeground(Color.WHITE);
        maxPriceTextField.setBackground(Color.GRAY);
        maxPriceTextField.setBounds(190,135,60,25);
        jPanel.add(maxPriceTextField);

        JLabel jLabel9 = new JLabel(", minimum price: ");
        jLabel9.setForeground(Color.BLACK);
        jLabel9.setFont(new Font("Times New Roman",Font.PLAIN,18));
        jLabel9.setBounds(255,120,200,55);
        jPanel.add(jLabel9);

        minPriceTextField = new JTextField();
        minPriceTextField.setForeground(Color.WHITE);
        minPriceTextField.setBackground(Color.GRAY);
        minPriceTextField.setBounds(380,135,60,25);
        jPanel.add(minPriceTextField);

        searchByPriceButton = new JButton("Search");
        searchByPriceButton.setForeground(Color.WHITE);
        searchByPriceButton.setBackground(Color.DARK_GRAY);
        searchByPriceButton.setBounds(470,135,80,25);
        jPanel.add(searchByPriceButton);

        JLabel jLabel1 = new JLabel("Insert period: after date:");
        jLabel1.setForeground(Color.BLACK);
        jLabel1.setFont(new Font("Times New Roman",Font.PLAIN,18));
        jLabel1.setBounds(10,150,250,55);
        jPanel.add(jLabel1);

        afterDateTextField = new JTextField();
        afterDateTextField.setForeground(Color.WHITE);
        afterDateTextField.setBackground(Color.GRAY);
        afterDateTextField.setBounds(210,165,100,25);
        jPanel.add(afterDateTextField);

        JLabel jLabel2 = new JLabel(", before date: ");
        jLabel2.setForeground(Color.BLACK);
        jLabel2.setFont(new Font("Times New Roman",Font.PLAIN,18));
        jLabel2.setBounds(310,150,150,55);
        jPanel.add(jLabel2);

        beforeDateTextField = new JTextField();
        beforeDateTextField.setForeground(Color.WHITE);
        beforeDateTextField.setBackground(Color.GRAY);
        beforeDateTextField.setBounds(430,165,100,25);
        jPanel.add(beforeDateTextField);

        searchByPeriodButton = new JButton("Search");
        searchByPeriodButton.setForeground(Color.WHITE);
        searchByPeriodButton.setBackground(Color.DARK_GRAY);
        searchByPeriodButton.setBounds(570,165,80,25);
        jPanel.add(searchByPeriodButton);

        JLabel jLabel3 = new JLabel("Insert package id: ");
        jLabel3.setForeground(Color.BLACK);
        jLabel3.setFont(new Font("Times New Roman",Font.PLAIN,18));
        jLabel3.setBounds(10,180,200,55);
        jPanel.add(jLabel3);

        packageIdBookedTextField = new JTextField();
        packageIdBookedTextField.setForeground(Color.WHITE);
        packageIdBookedTextField.setBackground(Color.GRAY);
        packageIdBookedTextField.setBounds(170,195,130,25);
        jPanel.add(packageIdBookedTextField);

        bookButton = new JButton("Book");
        bookButton.setForeground(Color.WHITE);
        bookButton.setBackground(Color.DARK_GRAY);
        bookButton.setBounds(340,195,80,25);
        jPanel.add(bookButton);

        textArea = new JTextArea();
        textArea.setBounds(10,230,670,380);
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setBounds(10,230,670,380);
        jPanel.add(scroll);

        backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.PINK);
        backButton.setBounds(600,0,80,25);
        jPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer packageId = Integer.parseInt(getPackageIdBookedTextField());
                VacationPackage vacationPackage = vacationPackageController.findFinalPackageById(packageId);
                currentUser.getVacationPackages().add(vacationPackage);
                vacationPackage.getUsers().add(currentUser);
                vacationPackageController.bookFinal(vacationPackage);
                if (!entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().begin();
                }
                entityManager.getTransaction().commit();
            }
        });

        myVacationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentUser.getVacationPackages().isEmpty()) {
                    setTextArea("You don't have any booked vacations yet.");
                }else{
                    setTextArea(currentUser.getVacationPackages().toString());
                }
            }
        });

        availableVacationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vacationPackageController.findAvailablePackages() != null){
                    setTextArea(vacationPackageController.findAvailablePackages().toString());
                }else{
                    JOptionPane.showMessageDialog(null,"No available packages found.");
                    setTextArea("No available packages.");
                }
            }
        });

        searchByDestinationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vacationPackageController.findFinalByKeyword((getDestinationNameTextField())) != null){
                    setTextArea(vacationPackageController.findFinalByKeyword(getDestinationNameTextField()).toString());
                }else{
                    JOptionPane.showMessageDialog(null,"No results found.");
                    setTextArea("No results found.");
                }
            }
        });

        searchByPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vacationPackageController.findFinalBetweenPrice(Double.parseDouble(getMinPriceTextField()), Double.parseDouble(getMaxPriceTextField())) != null){
                    setTextArea(vacationPackageController.findFinalBetweenPrice(Double.parseDouble(getMinPriceTextField()), Double.parseDouble(getMaxPriceTextField())).toString());
                }
                else{
                    JOptionPane.showMessageDialog(null,"No vacation packages found between the provided prices.");
                    setTextArea("No vacation packages found between the provided prices.");
                }
            }
        });

        searchByPeriodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = getAfterDateTextField();
                String[] dateComponents = date.split("/");
                LocalDate startDate = LocalDate.of(Integer.parseInt(dateComponents[2]),Integer.parseInt(dateComponents[1]),Integer.parseInt(dateComponents[0]));
                Date startDateConversion = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                date = getBeforeDateTextField();
                dateComponents = date.split("/");
                LocalDate endDate = LocalDate.of(Integer.parseInt(dateComponents[2]),Integer.parseInt(dateComponents[1]),Integer.parseInt(dateComponents[0]));
                Date endDateConversion = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                if(vacationPackageController.findFinalBetweenPeriod(startDateConversion, endDateConversion) != null){
                    setTextArea(vacationPackageController.findFinalBetweenPeriod(startDateConversion, endDateConversion).toString());
                }else{
                    JOptionPane.showMessageDialog(null,"No vacation packages found between the provided dates.");
                    setTextArea("No vacation packages between the provided dates.");
                }
            }
        });
    }

    public String getDestinationNameTextField() {
        return destinationNameTextField.getText();
    }

    public String getMaxPriceTextField() {
        return maxPriceTextField.getText();
    }

    public String getMinPriceTextField() {
        return minPriceTextField.getText();
    }

    public String getAfterDateTextField() {
        return afterDateTextField.getText();
    }

    public String getBeforeDateTextField() {
        return beforeDateTextField.getText();
    }

    public String getPackageIdBookedTextField() {
        return packageIdBookedTextField.getText();
    }

    public void setTextArea(String textArea) {
        this.textArea.setText(textArea);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
