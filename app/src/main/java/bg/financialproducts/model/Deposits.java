package bg.financialproducts.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Deposits extends BaseLoan implements Parcelable {

    public String AER;
    public String interestRateType;
    public String afterRevenueTaxAmount;

    public Deposits() {}

    private Deposits(Parcel in) {
        this.AER = in.readString();
        this.interestRateType = in.readString();
        this.afterRevenueTaxAmount = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(AER);
        parcel.writeString(interestRateType);
        parcel.writeString(afterRevenueTaxAmount);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Deposits createFromParcel(Parcel in) {
            return new Deposits(in);
        }

        public Deposits[] newArray(int size) {
            return new Deposits[size];
        }
    };
}