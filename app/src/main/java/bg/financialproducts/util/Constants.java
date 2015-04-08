package bg.financialproducts.util;

public interface Constants {

    /*General*/
    public String PRODUCT = "Product";
    public String MONTHLY_PAYMENT = "MonthlyPayment";
    public String CURRENCY = "Currency";
    public String BANK = "Bank";
    public String TOTAL_PAYED = "TotalPayed";
    public String INTEREST_RATE_TYPE = "InterestRateType";

    /*Consumer loans variables*/
    public String APR = "APR";

    /*Mortgage loans variables*/
    public String ML_INTEREST_TYPE = "MLInterestType";
    public String DOWN_PAYMENT = "DownPayment";

    /*Auto loans variables*/
    public String MIN_SELF_PARTICIPATION = "MinSelfParticipation";

    /*Credit cards variables*/
    public String CASH_RATE = "CashRate";
    public String PURCHASE_RATE = "PurchaseRate";
    public String CASH_APR = "CashAPR";
    public String CREDIT_CARD_LIMIT = "CreditCardLimit";
    public String ANNUAL_FEE_MAIN = "AnnualFeeMain";

    /*Deposits variables*/
    public String AER = "AER";
    public String AFTER_REVENUE_TAX_AMOUNT = "AfterRevenueTaxAmount";

    /*Database loan types*/
    public int AUTO = 1;
    public int CONSUMER = 2;
    public int CREDIT_CARDS = 3;
    public int DEPOSITS = 4;
    public int MORTGAGE = 5;

    /*Arrays*/
    public String AUTO_ARRAY = "Auto";
    public String CONSUMER_ARRAY = "Consumer";
    public String CREDIT_CARDS_ARRAY = "Credit_cards";
    public String DEPOSITS_ARRAY = "Deposits";
    public String MORTGAGE_ARRAY = "Mortgage";

    /*Database main*/
    public int DATABASE_VERSION = 4;
    public String DATABASE_NAME = "FinancialProducts";

    /*Database loan*/
    public String TABLE_LOAN = "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER, %s TEXT);";
    public String TABLE_NAME_LOAN = "loan";
    public String ID = "id";
    public String CONTENT = "content";
    public String TYPE = "type";
    public String CREATED_AT = "createdAt";

    /*Database settings*/
    public String TABLE_SETTINGS = "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT);";
    public String TABLE_NAME_SETTINGS = "settings";
    public String URL = "url";
    public String USER_ID = "userId";
    public String USERNAME = "username";

    public String INVALID = "Invalid";
}