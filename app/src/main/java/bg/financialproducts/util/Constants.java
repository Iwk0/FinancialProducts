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
    public String AUTO = "Auto";
    public String CONSUMER = "Consumer";
    public String CREDIT_CARDS = "Credit cards";
    public String DEPOSITS = "Deposits";
    public String MORTGAGE = "Mortgage";

    /*Database*/
    public int DATABASE_VERSION = 1;
    public String DATABASE_NAME = "Change";
    public String TABLE_LOAN = "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT);";
    public String TABLE_NAME_LOAN = "loan";
    public String ID = "id";
    public String CONTENT = "content";
    public String TYPE = "type";

    public String INVALID = "Invalid";
}