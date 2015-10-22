package bg.financialproducts.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Auto extends BaseLoan implements Parcelable {

    public String currency;
    public String monthlyPayment;
    public String totalPaid;

    public Auto() {}

    private Auto(Parcel in) {
/*        this.bank = in.readString();
        this.product = in.readString();
        this.gpr = in.readString();*/
        this.currency = in.readString();
        this.monthlyPayment = in.readString();
        this.totalPaid = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
/*        parcel.writeString(bank);
        parcel.writeString(product);
        parcel.writeString(gpr);*/
        parcel.writeString(currency);
        parcel.writeString(monthlyPayment);
        parcel.writeString(totalPaid);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Auto createFromParcel(Parcel in) {
            return new Auto(in);
        }

        public Auto[] newArray(int size) {
            return new Auto[size];
        }
    };
}