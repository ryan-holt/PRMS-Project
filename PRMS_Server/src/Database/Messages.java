package Database;

/**
 * This interface stores the messages to be used in communication.
 * Each message corresponds to database actions or a database flag.
 */
public interface Messages {

    static final String SUCCESS = "success";
    static final String FAILED = "failed";
    static final String CREATE_USER = "createUser";
    static final String LOGIN = "login";
    static final String SEARCH_LISTINGS = "searchlistings";
    static final String NO_INPUT = "-";
    static final String GET_ALL_LISTINGS = "getAllListings";
    static final String CREATE_LISTING = "createListing";
    static final String SEARCH_LISTINGS_BY_LANDLORD = "searchListingsByLandlord";
    static final String GET_LISTING_FEE = "getListingFee";
    static final String ACTIVATE_LISTING = "activateListing";
    static final String EDIT_LISTING = "editListing";
    static final String EDIT_FEE = "editFee";
    static final String GET_ALL_USERS = "getAllUsers";
    static final String GET_REPORT_DATA = "getReportData";
    static final String SEND_EMAIL_TO_LANDLORD = "sendEmail";
    static final String UNSUBSCRIBE = "unsubscribe";


}
