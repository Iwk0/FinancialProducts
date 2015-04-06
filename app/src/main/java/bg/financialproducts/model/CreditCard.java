package bg.financialproducts.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CreditCard extends BaseLoan implements Parcelable {

    public String cashRate;
    public String purchaseRate;
    public String cashAPR;
    public String creditCardLimit;
    public String annualFeeMain;

    public CreditCard() {}

    private CreditCard(Parcel in) {
        this.cashRate = in.readString();
        this.purchaseRate = in.readString();
        this.cashAPR = in.readString();
        this.creditCardLimit = in.readString();
        this.annualFeeMain = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cashRate);
        parcel.writeString(purchaseRate);
        parcel.writeString(cashAPR);
        parcel.writeString(creditCardLimit);
        parcel.writeString(annualFeeMain);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public CreditCard createFromParcel(Parcel in) {
            return new CreditCard(in);
        }

        public CreditCard[] newArray(int size) {
            return new CreditCard[size];
        }
    };
}