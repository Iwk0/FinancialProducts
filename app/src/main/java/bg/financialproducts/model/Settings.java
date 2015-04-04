package bg.financialproducts.model;

public class Settings {

    public String url;
    public String id;
    public String username;

    public boolean isEmpty() {
        return id != null && id.trim().isEmpty() ||
               url != null && url.trim().isEmpty() ||
               username != null && username.trim().isEmpty();
    }
}