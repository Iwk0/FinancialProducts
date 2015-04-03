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

    /*Database*/
    public int DATABASE_VERSION = 1;
    public String DATABASE_NAME = "Change";
    public String TABLE_LOAN = "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER);";
    public String TABLE_NAME_LOAN = "loan";
    public String ID = "id";
    public String CONTENT = "content";
    public String TYPE = "type";

    public String INVALID = "Invalid";
}