package bg.financialproducts.model;

public class Loan {

    public String id;
    public String value;

    public Loan() {
    }

    public Loan(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}