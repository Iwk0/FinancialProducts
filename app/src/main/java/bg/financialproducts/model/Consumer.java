package bg.financialproducts.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Consumer extends BaseLoan implements Parcelable {

    public String currency;
    public String monthlyPayment;

    public Consumer() {}

    private Consumer(Parcel in) {
        this.product = in.readString();
        this.currency = in.readString();
        this.monthlyPayment = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(product);
        parcel.writeString(currency);
        parcel.writeString(monthlyPayment);
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