package bg.financialproducts.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.model.Loan;

public class CreateView {

    public static Spinner spinner(Context context, String tag, LinearLayout.LayoutParams layoutParams, List<Loan> loans) {
        ArrayAdapter<Loan> adapter = new ArrayAdapter<>(context, R.layout.spinner_item, loans);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = new Spinner(context);
        spinner.setTag(tag);
        spinner.setLayoutParams(layoutParams);
        spinner.setAdapter(adapter);
        spinner.setBackground(context.getResources().getDrawable(R.drawable.gradient_spinner));
        spinner.getBackground().setAlpha(180);

        return spinner;
    }

    public static EditText editText(Context context, String tag, String hint, LinearLayout.LayoutParams layoutParams, TextWatcher textWatcher) {
        EditText editText = new EditText(context);
        editText.setHint(hint);
        editText.setTag(tag);
        editText.setTextColor(Color.WHITE);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.addTextChangedListener(textWatcher);
        editText.setLayoutParams(layoutParams);
        editText.setBackground(context.getResources().getDrawable(R.drawable.edit_text));

        return editText;
    }

    public static List<NameValuePair> allFieldsAreRequired(ViewGroup viewGroup) {
        List<NameValuePair> params = new ArrayList<>();
        final int SIZE = viewGroup.getChildCount();
        int disabledView = 0;

        for (int i = 0; i < SIZE; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof Spinner) {
                Spinner spinner = (Spinner) view;
                Loan loan = (Loan) spinner.getSelectedItem();

                if (!loan.id.equals("-1")) {
                    params.add(new BasicNameValuePair((String) spinner.getTag(), (String.valueOf(loan.id))));
                }
            } else if (view instanceof EditText) {
                EditText text = (EditText) view;

                if (!text.getTag().equals(Constants.INVALID) && !text.getText().toString().isEmpty()) {
                    params.add(new BasicNameValuePair(text.getTag().toString(), text.getText().toString()));
                }
            }

            if (!view.isEnabled()) {
                disabledView++;
            }
        }

        if (params.size() < SIZE - disabledView) {
            return null;
        }

        return params;
    }

    public static void editTextValidation(EditText textField, Resources resources, int min, int max) {
        String text = textField.getText().toString();
        if (!text.isEmpty()) {
            double loanAmount = Double.valueOf(text);
            if (loanAmount < min || loanAmount > max) {
                textField.setTextColor(Color.RED);
                textField.setTag(Constants.INVALID);
                textField.setError(resources.getString(R.string.error) + " " + min + " and " + max);
            }
        }
    }
}