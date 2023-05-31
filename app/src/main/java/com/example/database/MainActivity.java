package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DocumentViewModel documentViewModel;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text_view);
        documentViewModel = new ViewModelProvider(this).get(DocumentViewModel.class);

        documentViewModel.getDocumentLiveData().observe(this, new Observer<Document>() {
            @Override
            public void onChanged(Document document) {
                if (document != null) {
                    textView.setText(document.getContent());
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String content = charSequence.toString();
                if (documentViewModel.getDocumentLiveData().getValue() == null) {
                    Document document = new Document(content);
                    document.setId(1);
                    documentViewModel.saveDocumentToFile(document);
                } else {
                    Document document = documentViewModel.getDocumentLiveData().getValue();
                    document.setContent(content);
                    documentViewModel.loadDocumentFromFile();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}