package bg.financialproducts.util;

public interface Constants {

    /*General*/
    String PRODUCT = "Product";
    String MONTHLY_PAYMENT = "MonthlyPayment";
    String CURRENCY = "Currency";
    String BANK = "Bank";
    String TOTAL_PAYED = "TotalPayed";
    String INTEREST_RATE_TYPE = "InterestRateType";

    /*Consumer loans variables*/
    String APR = "APR";

    /*Mortgage loans variables*/
    String ML_INTEREST_TYPE = "MLInterestType";
    String DOWN_PAYMENT = "DownPayment";

    /*Auto loans variables*/
    String MIN_SELF_PARTICIPATION = "MinSelfParticipation";

    /*Credit cards variables*/
    String CASH_RATE = "CashRate";
    String PURCHASE_RATE = "PurchaseRate";
    String CASH_APR = "CashAPR";
    String CREDIT_CARD_LIMIT = "CreditCardLimit";
    String ANNUAL_FEE_MAIN = "AnnualFeeMain";

    /*Deposits variables*/
    String AER = "AER";
    String AFTER_REVENUE_TAX_AMOUNT = "AfterRevenueTaxAmount";

    /*Database loan types*/
    int AUTO = 1;
    int CONSUMER = 2;
    int CREDIT_CARDS = 3;
    int DEPOSITS = 4;
    int MORTGAGE = 5;

    /*Arrays*/
    String AUTO_ARRAY = "Auto";
    String CONSUMER_ARRAY = "Consumer";
    String CREDIT_CARDS_ARRAY = "Credit_cards";
    String DEPOSITS_ARRAY = "Deposits";
    String MORTGAGE_ARRAY = "Mortgage";

    /*Database main*/
    int DATABASE_VERSION = 4;
    String DATABASE_NAME = "FinancialProducts";

    /*Database loan*/
    String TABLE_LOAN = "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER, %s TEXT);";
    String TABLE_NAME_LOAN = "loan";
    String ID = "id";
    String CONTENT = "content";
    String TYPE = "type";
    String CREATED_AT = "createdAt";

    /*Banner sets*/
    String TOP = "top";
    String BOTTOM = "bottom";
    String SEARCH = "search";
    String DETAILS = "details";
    String RESULTS = "results";
    String REPAYMENT = "repayment";

    /*Database settings*/
    String TABLE_SETTINGS = "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT);";
    String TABLE_NAME_SETTINGS = "settings";
    String URL = "url";
    String USER_ID = "userId";
    String USERNAME = "username";

    String INVALID = "Invalid";
}