package bg.financialproducts.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Mortgage extends BaseLoan implements Parcelable {

    public String apr;
    public String currency;
    public String monthlyPayment;
    public String totalPayed;
    public String downPayment;
    public String interestType;

    public Mortgage() {}

    private Mortgage(Parcel in) {
        this.product = in.readString();
        this.apr = in.readString();
        this.currency = in.readString();
        this.monthlyPayment = in.readString();
        this.totalPayed = in.readString();
        this.downPayment = in.readString();
        this.interestType = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(product);
        parcel.writeString(apr);
        parcel.writeString(currency);
        parcel.writeString(monthlyPayment);
        parcel.writeString(totalPayed);
        parcel.writeString(downPayment);
        parcel.writeString(interestType);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Mortgage createFromParcel(Parcel in) {
            return new Mortgage(in);
        }

        public Mortgage[] newArray(int size) {
            return new Mortgage[size];
        }
    };
}