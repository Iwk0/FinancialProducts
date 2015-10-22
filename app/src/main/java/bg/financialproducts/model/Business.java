package bg.financialproducts.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Iwk0 on 22/10/2015.
 */
public class Business extends BaseLoan implements Parcelable {

    public Business() {
    }

    protected Business(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Business> CREATOR = new Creator<Business>() {
        @Override
        public Business createFromParcel(Parcel in) {
            return new Business(in);
        }

        @Override
        public Business[] newArray(int size) {
            return new Business[size];
        }
    };
}
