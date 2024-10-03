package org.example.Client;

import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class AuthClient extends JFrame{
    private JButton authButton;

    public JButton getAuthButton(){
        return authButton;
    }
    private JTextField loginField;
    public String getLogin(){
        return loginField.getText();
    }
    private JPasswordField passwordField;
    public String getPassword(){
        return String.copyValueOf(passwordField.getPassword());
    }
    private JPanel rootPanel;

    public void WarningMessage(){
        showMessageDialog(null, "Неверные данные!", "Ошибка", WARNING_MESSAGE);
    }

    public AuthClient(){
        setContentPane(rootPanel);
        setSize(460,360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
