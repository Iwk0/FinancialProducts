package bg.financialproducts;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import bg.financialproducts.model.BannerSet;

public class GlobalVariable extends Application {

    private List<BannerSet> bannerSetList;

    public List<BannerSet> getBannerSetList() {
        if (bannerSetList == null) {
            return new ArrayList<>();
        }
        return bannerSetList;
    }

    public void setBannerSetList(List<BannerSet> bannerSetList) {
        this.bannerSetList = bannerSetList;
    }
}