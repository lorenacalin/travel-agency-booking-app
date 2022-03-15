package gui;

import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JFrame {
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private String[] userTypes = {"Agency administrator", "Client"};
    JComboBox userTypesComboBox;
    private JButton okButton;
    private JButton backButton;

    public RegisterGUI() {
        UserController userController = new UserController();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 396, 364);
        JPanel jPanel = new JPanel();
        jPanel.setForeground(new Color(177, 127, 127));
        jPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(jPanel);
        jPanel.setLayout(null);

        JLabel jLabel = new JLabel("CREATE NEW ACCOUNT");
        jLabel.setForeground(Color.BLACK);
        jLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        jLabel.setBounds(40, 10, 320, 55);
        jPanel.add(jLabel);

        JLabel jLabel1 = new JLabel("Username: ");
        jLabel1.setForeground(Color.BLACK);
        jLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        jLabel1.setBounds(74, 83, 99, 55);
        jPanel.add(jLabel1);

        JLabel jLabel2 = new JLabel("Password: ");
        jLabel2.setForeground(Color.BLACK);
        jLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        jLabel2.setBounds(74, 137, 99, 55);
        jPanel.add(jLabel2);

        usernameTextField = new JTextField();
        usernameTextField.setForeground(Color.WHITE);
        usernameTextField.setBackground(Color.GRAY);
        usernameTextField.setBounds(180, 102, 141, 25);
        jPanel.add(usernameTextField);
        usernameTextField.setColumns(10);

        passwordTextField = new JPasswordField();
        passwordTextField.setForeground(Color.WHITE);
        passwordTextField.setBackground(Color.GRAY);
        passwordTextField.setBounds(180, 156, 141, 25);
        jPanel.add(passwordTextField);

        backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setBounds(68, 252, 107, 31);
        jPanel.add(backButton);

        okButton = new JButton("Done");
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(Color.DARK_GRAY);
        okButton.setBounds(216, 252, 107, 31);
        jPanel.add(okButton);

        JLabel jLabel3 = new JLabel("User type: ");
        jLabel3.setForeground(Color.BLACK);
        jLabel3.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        jLabel3.setBounds(74, 191, 99, 55);
        jPanel.add(jLabel3);

        userTypesComboBox = new JComboBox(userTypes);
        userTypesComboBox.setForeground(Color.WHITE);
        userTypesComboBox.setBackground(Color.DARK_GRAY);
        userTypesComboBox.setBounds(180, 210, 161, 25);
        jPanel.add(userTypesComboBox);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = getUsernameTextField();
                String password = getPasswordTextField();
                String type = getUserTypesComboBox();
                User userAlreadyExists = userController.findUserByUsername(username);
                if(userAlreadyExists != null){
                    JOptionPane.showMessageDialog(null,"Username already registered. Go to log in page!");
                    setVisible(false);
                }else{
                    userController.insertFinalUser(username, password, type);
                    JOptionPane.showMessageDialog(null,"Successful registration!");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public String getUsernameTextField() {
        return usernameTextField.getText();
    }

    public String getPasswordTextField() {
        return passwordTextField.getText();
    }

    public String getUserTypesComboBox() {
        return userTypesComboBox.getSelectedItem().toString();
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void resetTextFields() {
        this.usernameTextField.setText("");
        this.passwordTextField.setText("");
    }
}
