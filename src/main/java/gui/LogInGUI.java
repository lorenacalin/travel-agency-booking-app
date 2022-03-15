package gui;

import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInGUI extends JFrame {
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JButton okButton;
    private JButton newAccountButton;
    private User client;

    public LogInGUI(){}

    public LogInGUI(RegisterGUI registerGUI, AgencyGUI agencyGUI, ClientGUI clientGUI){
        UserController userController = new UserController();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,396,306);
        JPanel jPanel = new JPanel();
        jPanel.setForeground(new Color(177, 127, 127));
        jPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(jPanel);
        jPanel.setLayout(null);

        JLabel jLabel = new JLabel("LOG IN");
        jLabel.setForeground(Color.BLACK);
        jLabel.setFont(new Font("Times New Roman",Font.BOLD,32));
        jLabel.setBounds(130,10,118,55);
        jPanel.add(jLabel);

        JLabel jLabel1 = new JLabel("Username: ");
        jLabel1.setForeground(Color.BLACK);
        jLabel1.setFont(new Font("Times New Roman",Font.PLAIN,19));
        jLabel1.setBounds(74,83,99,55);
        jPanel.add(jLabel1);

        JLabel jLabel2 = new JLabel("Password: ");
        jLabel2.setForeground(Color.BLACK);
        jLabel2.setFont(new Font("Times New Roman",Font.PLAIN,19));
        jLabel2.setBounds(74,137,99,55);
        jPanel.add(jLabel2);

        usernameTextField = new JTextField();
        usernameTextField.setForeground(Color.WHITE);
        usernameTextField.setBackground(Color.GRAY);
        usernameTextField.setBounds(180,102,141,25);
        jPanel.add(usernameTextField);
        usernameTextField.setColumns(10);

        passwordTextField = new JPasswordField();
        passwordTextField.setForeground(Color.WHITE);
        passwordTextField.setBackground(Color.GRAY);
        passwordTextField.setBounds(180,156,141,25);
        jPanel.add(passwordTextField);

        newAccountButton = new JButton("Create new account");
        newAccountButton.setForeground(Color.WHITE);
        newAccountButton.setBackground(Color.DARK_GRAY);
        newAccountButton.setBounds(48,212,157,31);
        jPanel.add(newAccountButton);

        okButton = new JButton("Done");
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(Color.DARK_GRAY);
        okButton.setBounds(236,212,107,31);
        jPanel.add(okButton);

        newAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerGUI.setVisible(true);
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = getPasswordTextField();
                String username = getUsernameTextField();
                User foundUser = userController.findUserByUsername(username);
                if(foundUser != null){
                    if(password.equals(foundUser.getPassword())){
                        //setVisible(false);
                        if(foundUser.getType().equals("Agency administrator")){
                            agencyGUI.setVisible(true);
                            resetFields();
                        }
                        else if(foundUser.getType().equals("Client")){
                            clientGUI.setCurrentUser(foundUser);
                            clientGUI.setVisible(true);
                            setClient(foundUser);
                            resetFields();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Incorrect password. Try again!");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Cannot find username. Please register first.");
                    registerGUI.setVisible(true);
                }
            }
        });
    }

    public String getUsernameTextField() {
        return usernameTextField.getText();
    }

    public String getPasswordTextField() {
        return passwordTextField.getText();
    }

    public User getClient() {
        return this.client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public void resetFields()
    {
        this.usernameTextField.setText("");
        this.passwordTextField.setText("");
    }
}
