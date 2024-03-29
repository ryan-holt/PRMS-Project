package Domain.Controllers;

import Presentation.Views.*;
import Utils.*;

import javax.swing.*;
import java.io.IOException;

/**
 * This class is responsible for controlling the login view
 * as well as creating the main window after login
 *
 * @author  Harsohail Brar
 * @version 4.10.0
 * @since April 5, 2019
 */
public class LoginController extends Controller implements Messages, UserTypes{

    //MEMBER VARIABLES
    private LoginView loginView;
    private AccountCreationView accountCreationView;
    private boolean verified;

    /**
     * Creates a LoginController object and adds the object listener for the
     * log in button
     */
    public LoginController(LoginView l, ClientCommunicationController ccc){
        super(ccc);
        loginView = l;
        verified = false;

        loginView.addLoginListener(e -> loginListen());
        loginView.addRegularRenterListener(e -> regularRenterListen());
        loginView.addCreateAccountListener(e -> registerListen());
    }

    /**
     * Action Listener implementation for Login Button
     */
    public void loginListen(){
        try{
            String username = loginView.getUsernameField().getText();
            String password = loginView.getPasswordField().getText();

            // Sending action to server
            clientCommunicationController.getSocketOut().writeObject(LOGIN);
            // Sending user credentials to server
            clientCommunicationController.getSocketOut().writeObject(new User(username, password));

            User serverResponseUser = (User) clientCommunicationController.getSocketIn().readObject();
            clientCommunicationController.setUser(serverResponseUser);

            if(serverResponseUser != null){
                this.hideView();
                verified = true;
                System.out.println("User logged in!");
            }else{
                JOptionPane.showMessageDialog(null, "Invalid User!");
            }

            if(verified && serverResponseUser != null) {
                clientCommunicationController.showMainWindow();
            }

            clientCommunicationController.getSocketOut().flush();
        }catch(Exception f){
            f.printStackTrace();
        }
    }

    /**
     * Allows the renter into the system without login if regular renter button pressed
     */
    public void regularRenterListen(){
        try {
            // Send action to server that no verification is needed
            clientCommunicationController.getSocketOut().writeObject(REGULAR);
        }catch (IOException e){
            e.printStackTrace();
        }
        this.hideView();
        clientCommunicationController.showMainWindow();
    }

    /**
     * Prompts the create account view if register button pressed
     */
    public void registerListen(){
        this.hideView();
        accountCreationView = new AccountCreationView();

        accountCreationView.addBackToLoginListener(e -> backToLoginListen());
        accountCreationView.addCreateAccountListener(e -> createAccountListen());

        accountCreationView.display();
    }

    /**
     * Returns user to login if back to login button pressed
     */
    public void backToLoginListen(){
        accountCreationView.setVisible(false);
        accountCreationView = null;
        this.displayView();
    }

    /**
     *
     */
    public void createAccountListen(){
        try {
            // Sending action to server
            clientCommunicationController.getSocketOut().writeObject(CREATE_USER);
            // Sending user object to server

            String accountType = (String)accountCreationView.getAccountTypeList().getSelectedItem();
            User newUser = new User(accountCreationView.getUsernameField().getText(),
                                    accountCreationView.getPasswordField().getText(),
                                    new Name(accountCreationView.getFirstNameField().getText(), accountCreationView.getLastNameField().getText()),
                                    accountType.toLowerCase(),
                                    accountCreationView.getAddressField().getText(),
                                    accountCreationView.getEmailField().getText());

            clientCommunicationController.getSocketOut().writeObject(newUser);

            // Reads account creation verification
            String accountCreation = (String) clientCommunicationController.getSocketIn().readObject();

            if(accountCreation.equals(SUCCESS)) {
                JOptionPane.showMessageDialog(null, "Account Created! Please login!");
                accountCreationView.hide();
                this.displayView();
            }else{
                JOptionPane.showMessageDialog(null, "User already exists. Try again!");
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void displayView() {
        loginView.display();
    }

    @Override
    public void hideView() {
        loginView.hide();
    }

    //getters and setters

    public boolean isVerified() {
        return verified;
    }

    public MainView getView(){
        return null;
    }
}