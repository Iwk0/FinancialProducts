package bg.financialproducts.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseLoan implements Parcelable {

    public String createdAt;
    public String product;
    public String bank;

    public BaseLoan() {}

    private BaseLoan(Parcel in) {
        this.createdAt = in.readString();
        this.product = in.readString();
        this.bank = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(createdAt);
        parcel.writeString(product);
        parcel.writeString(bank);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public BaseLoan createFromParcel(Parcel in) {
            return new BaseLoan(in);
        }

        public BaseLoan[] newArray(int size) {
            return new BaseLoan[size];
        }
    };
}