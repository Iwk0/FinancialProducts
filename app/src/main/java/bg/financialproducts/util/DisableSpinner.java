package bg.financialproducts.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import bg.financialproducts.model.Loan;

public class DisableSpinner implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;

    public DisableSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Loan loan = (Loan) parent.getSelectedItem();
        if (loan.id.equals("1")) {
            spinner.setEnabled(false);
            spinner.setSelection(0);
        } else {
            spinner.setEnabled(true);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}