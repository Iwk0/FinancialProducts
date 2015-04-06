package bg.financialproducts.model;

public class Auto extends BaseLoan /*implements Parcelable */{

    public String apr;
    public String currency;
    public String monthlyPayment;
    public String totalPayed;
    public String minSelfParticipation;

    /*public Auto() {}

    private Auto(Parcel in) {
        this.apr = in.readString();
        this.currency = in.readString();
        this.monthlyPayment = in.readString();
        this.totalPayed = in.readString();
        this.minSelfParticipation = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(apr);
        parcel.writeString(currency);
        parcel.writeString(monthlyPayment);
        parcel.writeString(totalPayed);
        parcel.writeString(minSelfParticipation);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Auto createFromParcel(Parcel in) {
            return new Auto(in);
        }

        public Auto[] newArray(int size) {
            return new Auto[size];
        }
    };*/
}