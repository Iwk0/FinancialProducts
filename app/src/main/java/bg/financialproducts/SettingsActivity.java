package bg.financialproducts;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import bg.financialproducts.model.Settings;
import bg.financialproducts.util.Database;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final EditText urlText = (EditText) findViewById(R.id.urlText);
        final EditText userIdText = (EditText) findViewById(R.id.idText);
        final EditText usernameText = (EditText) findViewById(R.id.usernameText);

        Settings settings = new Database(this).findLastSettingsRecord();

        if (settings != null) {
            urlText.setText(settings.url);
            userIdText.setText(settings.id);
            usernameText.setText(settings.username);
        } else {
            try {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(getResources().openRawResource(R.raw.default_settings));
                String id = document.getElementsByTagName("id").item(0).getTextContent();
                String username = document.getElementsByTagName("username").item(0).getTextContent();
                String url = document.getElementsByTagName("url").item(0).getTextContent();

                userIdText.setText(id);
                usernameText.setText(username);
                urlText.setText(url);
            } catch (Exception e) {
                Log.e("Exception", e.getMessage());
            }
        }

        Button save = (Button) findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Database database = new Database(getApplicationContext());
                database.insertSettings(urlText.getText().toString(),
                        userIdText.getText().toString(),
                        usernameText.getText().toString());
                finish();
            }
        });

        Button cancel = (Button) findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}