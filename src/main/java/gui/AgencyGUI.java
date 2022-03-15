package gui;

import controller.VacationDestinationController;
import controller.VacationPackageController;
import model.VacationDestination;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AgencyGUI extends JFrame {
    private JTextField destinationNameTextField;
    private JTextField destinationIdTextField;
    private JButton addDestinationButton;
    private JButton deleteDestinationButton;
    private JButton viewAllDestinationsButton;
    private JTextArea destinationsArea;

    private JTextField packageNameTextField;
    private JTextField packagePriceTextField;
    private JTextField packageStartDateTextField;
    private JTextField packageEndDateTextField;
    private JTextField packageDetailsTextField;
    private JTextField packageNrOfSeatsTextField;
    private JTextField packageDestinationIdTextField;
    private JTextField packageIdTextField;
    private JButton addPackageButton;
    private JButton editPackageButton;
    private JButton deletePackageButton;
    private JButton viewAllPackagesButton;
    private JButton backButton;
    private JTextArea packagesArea;

    public AgencyGUI(){
        VacationDestinationController vacationDestinationController = new VacationDestinationController();
        VacationPackageController vacationPackageController = new VacationPackageController();

        setTitle("You successfully logged in as an agency administrator!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,1000,650);
        JPanel jPanel = new JPanel();
        jPanel.setForeground(new Color(177, 127, 127));
        jPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(jPanel);
        jPanel.setLayout(null);

        JLabel jLabelDestinations = new JLabel("DESTINATIONS: ");
        jLabelDestinations.setForeground(Color.BLACK);
        jLabelDestinations.setFont(new Font("Times New Roman",Font.BOLD,23));
        jLabelDestinations.setBounds(10,0,200,55);
        jPanel.add(jLabelDestinations);

        JLabel idLabel = new JLabel("Id: ");
        idLabel.setForeground(Color.BLACK);
        idLabel.setFont(new Font("Times New Roman",Font.PLAIN,19));
        idLabel.setBounds(210,0,40,55);
        jPanel.add(idLabel);

        destinationIdTextField = new JTextField("Complete ONLY for delete operation");
        destinationIdTextField.setForeground(Color.WHITE);
        destinationIdTextField.setBackground(Color.GRAY);
        destinationIdTextField.setBounds(240,15,200,25);
        jPanel.add(destinationIdTextField);

        JLabel jLabel1 = new JLabel("Name: ");
        jLabel1.setForeground(Color.BLACK);
        jLabel1.setFont(new Font("Times New Roman",Font.PLAIN,19));
        jLabel1.setBounds(10,60,99,55);
        jPanel.add(jLabel1);

        destinationNameTextField = new JTextField();
        destinationNameTextField.setForeground(Color.WHITE);
        destinationNameTextField.setBackground(Color.GRAY);
        destinationNameTextField.setBounds(100,75,160,25);
        jPanel.add(destinationNameTextField);

        addDestinationButton = new JButton("Add");
        addDestinationButton.setForeground(Color.WHITE);
        addDestinationButton.setBackground(Color.DARK_GRAY);
        addDestinationButton.setBounds(10,110,80,30);
        jPanel.add(addDestinationButton);

        deleteDestinationButton = new JButton("Delete");
        deleteDestinationButton.setForeground(Color.WHITE);
        deleteDestinationButton.setBackground(Color.DARK_GRAY);
        deleteDestinationButton.setBounds(100,110,80,30);
        jPanel.add(deleteDestinationButton);

        viewAllDestinationsButton = new JButton("View all");
        viewAllDestinationsButton.setForeground(Color.WHITE);
        viewAllDestinationsButton.setBackground(Color.DARK_GRAY);
        viewAllDestinationsButton.setBounds(190,110,80,30);
        jPanel.add(viewAllDestinationsButton);

        backButton = new JButton("back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.PINK);
        backButton.setBounds(10,155,80,30);
        jPanel.add(backButton);

        destinationsArea = new JTextArea();
        destinationsArea.setBounds(10,210,450,400);
        JScrollPane scroll = new JScrollPane(destinationsArea);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setBounds(10,210,450,400);
        jPanel.add(scroll);

        JLabel jLabelPackages = new JLabel("PACKAGES: ");
        jLabelPackages.setForeground(Color.BLACK);
        jLabelPackages.setFont(new Font("Times New Roman",Font.BOLD,23));
        jLabelPackages.setBounds(550,0,200,55);
        jPanel.add(jLabelPackages);

        JLabel idLabel2 = new JLabel("Id: ");
        idLabel2.setForeground(Color.BLACK);
        idLabel2.setFont(new Font("Times New Roman",Font.PLAIN,19));
        idLabel2.setBounds(720,0,40,55);
        jPanel.add(idLabel2);

        packageIdTextField = new JTextField("ONLY for delete and update");
        packageIdTextField.setForeground(Color.WHITE);
        packageIdTextField.setBackground(Color.GRAY);
        packageIdTextField.setBounds(750,15,200,25);
        jPanel.add(packageIdTextField);

        JLabel jLabel2 = new JLabel("Name: ");
        jLabel2.setForeground(Color.BLACK);
        jLabel2.setFont(new Font("Times New Roman",Font.PLAIN,19));
        jLabel2.setBounds(470,60,99,55);
        jPanel.add(jLabel2);

        packageNameTextField = new JTextField();
        packageNameTextField.setForeground(Color.WHITE);
        packageNameTextField.setBackground(Color.GRAY);
        packageNameTextField.setBounds(530,75,160,25);
        jPanel.add(packageNameTextField);

        JLabel jLabel3 = new JLabel("Price: ");
        jLabel3.setForeground(Color.BLACK);
        jLabel3.setFont(new Font("Times New Roman",Font.PLAIN,19));
        jLabel3.setBounds(470,90,99,55);
        jPanel.add(jLabel3);

        packagePriceTextField = new JTextField();
        packagePriceTextField.setForeground(Color.WHITE);
        packagePriceTextField.setBackground(Color.GRAY);
        packagePriceTextField.setBounds(530,105,160,25);
        jPanel.add(packagePriceTextField);

        JLabel jLabel4 = new JLabel("Details: ");
        jLabel4.setForeground(Color.BLACK);
        jLabel4.setFont(new Font("Times New Roman",Font.PLAIN,19));
        jLabel4.setBounds(470,120,99,55);
        jPanel.add(jLabel4);

        packageDetailsTextField = new JTextField();
        packageDetailsTextField.setForeground(Color.WHITE);
        packageDetailsTextField.setBackground(Color.GRAY);
        packageDetailsTextField.setBounds(530,135,160,25);
        jPanel.add(packageDetailsTextField);

        JLabel jLabel8 = new JLabel("Available seats: ");
        jLabel8.setForeground(Color.BLACK);
        jLabel8.setFont(new Font("Times New Roman",Font.PLAIN,17));
        jLabel8.setBounds(710,30,120,55);
        jPanel.add(jLabel8);

        packageNrOfSeatsTextField = new JTextField();
        packageNrOfSeatsTextField.setForeground(Color.WHITE);
        packageNrOfSeatsTextField.setBackground(Color.GRAY);
        packageNrOfSeatsTextField.setBounds(820,45,130,25);
        jPanel.add(packageNrOfSeatsTextField);

        JLabel jLabel5 = new JLabel("Start date: ");
        jLabel5.setForeground(Color.BLACK);
        jLabel5.setFont(new Font("Times New Roman",Font.PLAIN,19));
        jLabel5.setBounds(710,60,99,55);
        jPanel.add(jLabel5);

        packageStartDateTextField = new JTextField("DD/MM/YYYY");
        packageStartDateTextField.setForeground(Color.WHITE);
        packageStartDateTextField.setBackground(Color.GRAY);
        packageStartDateTextField.setBounds(800,75,160,25);
        jPanel.add(packageStartDateTextField);

        JLabel jLabel6 = new JLabel("End date: ");
        jLabel6.setForeground(Color.BLACK);
        jLabel6.setFont(new Font("Times New Roman",Font.PLAIN,19));
        jLabel6.setBounds(710,90,99,55);
        jPanel.add(jLabel6);

        packageEndDateTextField = new JTextField("DD/MM/YYYY");
        packageEndDateTextField.setForeground(Color.WHITE);
        packageEndDateTextField.setBackground(Color.GRAY);
        packageEndDateTextField.setBounds(800,105,160,25);
        jPanel.add(packageEndDateTextField);

        JLabel jLabel7 = new JLabel("Destination id: ");
        jLabel7.setForeground(Color.BLACK);
        jLabel7.setFont(new Font("Times New Roman",Font.PLAIN,18));
        jLabel7.setBounds(710,120,140,55);
        jPanel.add(jLabel7);

        packageDestinationIdTextField = new JTextField();
        packageDestinationIdTextField.setForeground(Color.WHITE);
        packageDestinationIdTextField.setBackground(Color.GRAY);
        packageDestinationIdTextField.setBounds(835,135,130,25);
        jPanel.add(packageDestinationIdTextField);

        addPackageButton = new JButton("Add");
        addPackageButton.setForeground(Color.WHITE);
        addPackageButton.setBackground(Color.DARK_GRAY);
        addPackageButton.setBounds(500,165,80,30);
        jPanel.add(addPackageButton);

        deletePackageButton = new JButton("Delete");
        deletePackageButton.setForeground(Color.WHITE);
        deletePackageButton.setBackground(Color.DARK_GRAY);
        deletePackageButton.setBounds(680,165,80,30);
        jPanel.add(deletePackageButton);

        editPackageButton = new JButton("Edit");
        editPackageButton.setForeground(Color.WHITE);
        editPackageButton.setBackground(Color.DARK_GRAY);
        editPackageButton.setBounds(590,165,80,30);
        jPanel.add(editPackageButton);

        viewAllPackagesButton = new JButton("View all");
        viewAllPackagesButton.setForeground(Color.WHITE);
        viewAllPackagesButton.setBackground(Color.DARK_GRAY);
        viewAllPackagesButton.setBounds(770,165,80,30);
        jPanel.add(viewAllPackagesButton);

        packagesArea = new JTextArea();
        packagesArea.setBounds(510,210,450,400);
        JScrollPane scroll1 = new JScrollPane(packagesArea);
        scroll1.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scroll1.setBounds(510,210,450,400);
        jPanel.add(scroll1);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        addDestinationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = getDestinationNameTextField();
                vacationDestinationController.insertFinalDestination(name);
            }
        });

        deleteDestinationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer id = Integer.parseInt(getDestinationIdTextField());
                vacationDestinationController.deleteFinalDestination(id);
            }
        });

        viewAllDestinationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDestinationsArea(vacationDestinationController.getAllFinalDestinations().toString());
            }
        });

        addPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = getPackageNameTextField();
                Double price = Double.parseDouble(getPackagePriceTextField());
                String date = getPackageStartDateTextField();
                String[] dateComponents = date.split("/");
                LocalDate startDate = LocalDate.of(Integer.parseInt(dateComponents[2]),Integer.parseInt(dateComponents[1]),Integer.parseInt(dateComponents[0]));
                Date startDateConversion = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                date = getPackageEndDateTextField();
                dateComponents = date.split("/");
                LocalDate endDate = LocalDate.of(Integer.parseInt(dateComponents[2]),Integer.parseInt(dateComponents[1]),Integer.parseInt(dateComponents[0]));
                Date endDateConversion = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                String details = getPackageDetailsTextField();
                Integer nrOfAvailableSeats = Integer.parseInt(getPackageNrOfSeatsTextField());
                Integer destinationId = Integer.parseInt(getPackageDestinationIdTextField());
                if(vacationDestinationController.findFinalDestinationById(destinationId) == null){
                    JOptionPane.showMessageDialog(null,"Invalid destination id. Please introduce an existing id.");
                }
                else{
                    vacationPackageController.insertFinalPackage(name, price, startDateConversion, endDateConversion, details, nrOfAvailableSeats, destinationId);
                }
            }
        });

        editPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer id = Integer.parseInt(getPackageIdTextField());
                String name = getPackageNameTextField();
                Double price = Double.parseDouble(getPackagePriceTextField());
                String date = getPackageStartDateTextField();
                String[] dateComponents = date.split("/");
                LocalDate startDate = LocalDate.of(Integer.parseInt(dateComponents[2]),Integer.parseInt(dateComponents[1]),Integer.parseInt(dateComponents[0]));
                Date startDateConversion = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                date = getPackageEndDateTextField();
                dateComponents = date.split("/");
                LocalDate endDate = LocalDate.of(Integer.parseInt(dateComponents[2]),Integer.parseInt(dateComponents[1]),Integer.parseInt(dateComponents[0]));
                Date endDateConversion = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                String details = getPackageDetailsTextField();
                Integer nrOfAvailableSeats = Integer.parseInt(getPackageNrOfSeatsTextField());
                Integer destinationId = Integer.parseInt(getPackageDestinationIdTextField());
                VacationDestination destination = vacationDestinationController.findFinalDestinationById(destinationId);
                if(destination == null){
                    JOptionPane.showMessageDialog(null,"There is no vacation destination with this id.");
                }
                else{
                    vacationPackageController.updateFinalPackage(id, name, price, startDateConversion, endDateConversion, details, nrOfAvailableSeats, destination);
                    setPackagesArea(vacationPackageController.getAllFinalPackages().toString());
                }
            }
        });

        deletePackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer id = Integer.parseInt(getPackageIdTextField());
                vacationPackageController.deleteFinalPackage(id);
                setPackagesArea(vacationPackageController.getAllFinalPackages().toString());
            }
        });

        viewAllPackagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPackagesArea(vacationPackageController.getAllFinalPackages().toString());
            }
        });
    }

    public String getDestinationNameTextField() {
        return destinationNameTextField.getText();
    }

    public String getPackageNameTextField() {
        return packageNameTextField.getText();
    }

    public String getPackagePriceTextField() {
        return packagePriceTextField.getText();
    }

    public String getPackageStartDateTextField() {
        return packageStartDateTextField.getText();
    }

    public String getPackageEndDateTextField() {
        return packageEndDateTextField.getText();
    }

    public String getPackageDetailsTextField() {
        return packageDetailsTextField.getText();
    }

    public String getPackageNrOfSeatsTextField() {
        return packageNrOfSeatsTextField.getText();
    }

    public String getPackageDestinationIdTextField() {
        return packageDestinationIdTextField.getText();
    }

    public String getDestinationIdTextField() {
        return destinationIdTextField.getText();
    }

    public String getPackageIdTextField() {
        return packageIdTextField.getText();
    }

    public void setDestinationsArea(String destinations) {
        this.destinationsArea.setText(destinations);
    }

    public void setPackagesArea(String packages) {
        this.packagesArea.setText(packages);
    }
}
