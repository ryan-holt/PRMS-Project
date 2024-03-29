package Domain.Controllers;

import Presentation.Views.*;
import Utils.*;

import java.io.*;
import java.net.*;

/**
 * This class is responsible for communicating with the server
 * and holding the LoginController
 * Overall the client controller is used for communication with
 * the server
 *
 * @author Ryan Holt
 * @version 4.10.0
 * @since April 5, 2019
 */
public class ClientCommunicationController implements UserTypes{

    //MEMBER VARIABLES
    private ObjectOutputStream socketOut;
    private Socket aSocket;
    private ObjectInputStream socketIn;

    private LoginController loginController;
    private Controller mainController;
    private SearchListingController searchListingController;
    private ListingController listingController;
    private UserController userController;
    private CreateListingController createListingController;
    private PeriodicalReportController periodicalReportController;

    private User user;

    /**
     * Constructs a Client controller object
     *
     * @param serverName name of server
     * @param portNumber port number
     */
    public ClientCommunicationController(String serverName, int portNumber) {
        try {
            aSocket = new Socket(serverName, portNumber);

            socketIn = new ObjectInputStream(aSocket.getInputStream());
            socketOut = new ObjectOutputStream(aSocket.getOutputStream());

            LoginView loginView = new LoginView();

            loginController = new LoginController(loginView, this);
            searchListingController = new SearchListingController(new SearchListingView(), this);
            listingController = new ListingController(new ListingView(), this);
            createListingController = new CreateListingController(new CreateListingView(), this);
            periodicalReportController = new PeriodicalReportController(new PeriodicalReportView(), this);
            userController = new UserController(new UserView(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * runs the client side
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ClientCommunicationController ccc = new ClientCommunicationController("localhost", 7000);
    }

    /**
     *  displays the main window for the respective user
     */
    public void showMainWindow(){
        String userType;
        if(user != null) {
            userType = user.getUserType().toLowerCase();
        }else{
            userType = REGULAR_RENTER;
        }

        switch (userType){
            case REGISTERED_RENTER:
                createRenterMainWindow(true);
                break;
            case MANAGER:
                createManagerMainWindow();
                break;
            case LANDLORD:
                createLandlordMainWindow();
                break;
            default:
                createRenterMainWindow(false);
        }
        mainController.displayView();
    }

    /**
     * Creates a renter main view
     * @param registeredRenter registered renter user
     */
    public void createRenterMainWindow(boolean registeredRenter){
        setMainController(new RenterMainController(new RenterMainView(registeredRenter), this));
    }

    /**
     * Creates a manager main view
     */
    public void createManagerMainWindow(){
        setMainController(new ManagerMainController(new ManagerMainView(), this));
    }

    /**
     * Creates a landlord main view
     */
    public void createLandlordMainWindow(){
        setMainController(new LandlordMainController(new LandlordMainView(), this));
    }

    //GETTERS AND SETTERS
    public ObjectOutputStream getSocketOut() {
        return socketOut;
    }

    public ObjectInputStream getSocketIn() {
        return socketIn;
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public SearchListingController getSearchListingController() {
        return searchListingController;
    }

    public Controller getMainController() {
        return mainController;
    }

    public ListingController getListingController() {
        return listingController;
    }

    public CreateListingController getCreateListingController() {
        return createListingController;
    }

    public PeriodicalReportController getPeriodicalReportController() {
        return periodicalReportController;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public UserController getUserController() {
        return userController;
    }
}
