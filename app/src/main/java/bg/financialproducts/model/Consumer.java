package bg.financialproducts.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Consumer extends BaseLoan implements Parcelable {

    public String apr;
    public String currency;
    public String monthlyPayment;
    public String totalPayed;
    public String interestRateType;

    public Consumer() {}

    private Consumer(Parcel in) {
        this.product = in.readString();
        this.apr = in.readString();
        this.currency = in.readString();
        this.monthlyPayment = in.readString();
        this.totalPayed = in.readString();
        this.interestRateType = in.readString();
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
        parcel.writeString(interestRateType);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Consumer createFromParcel(Parcel in) {
            return new Consumer(in);
        }

        public Consumer[] newArray(int size) {
            return new Consumer[size];
        }
    };
}