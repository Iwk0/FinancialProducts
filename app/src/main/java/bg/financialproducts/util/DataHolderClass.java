package bg.financialproducts.util;

/**
 * Created by Iwk0 on 22/10/2015.
 */
public class DataHolderClass<T> {

    private static DataHolderClass dataHolderClass;

    private T item;

    private DataHolderClass() {}

    public static DataHolderClass getInstance() {
        if (dataHolderClass == null) {
            dataHolderClass = new DataHolderClass<>();
        }

        return dataHolderClass;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}