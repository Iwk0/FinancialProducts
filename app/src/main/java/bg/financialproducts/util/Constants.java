package bg.financialproducts.util;

public interface Constants {

    /*General*/
    public String LOAN = "Loan";
    public String PRODUCT = "Product";

    /*Consumer loans variables*/
    public String APR = "APR";
    public String CURRENCY = "Currency";
    public String MONTHLY_PAYMENT = "MonthlyPayment";
    public String TOTAL_PAYED = "TotalPayed";
    public String INTEREST_RATE_TYPE = "InterestRateType";
    public String BANK = "Bank";

    /*Database loan types*/
    public int AUTO = 1;
    public int CONSUMER = 2;
    public int CREDIT_CARDS = 3;
    public int DEPOSITS = 4;
    public int MORTGAGE = 5;

    /*Database main*/
    public int DATABASE_VERSION = 2;
    public String DATABASE_NAME = "FinancialProducts";

    /*Database loan*/
    public String TABLE_LOAN = "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER);";
    public String TABLE_NAME_LOAN = "loan";
    public String ID = "id";
    public String CONTENT = "content";
    public String TYPE = "type";

    /*Database settings*/
    public String TABLE_SETTINGS = "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT);";
    public String TABLE_NAME_SETTINGS = "settings";
    public String URL = "url";
    public String USER_ID = "userId";
    public String USERNAME = "username";

    public String INVALID = "Invalid";
}