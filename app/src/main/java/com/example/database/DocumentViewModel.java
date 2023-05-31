package com.example.database;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class DocumentViewModel extends AndroidViewModel {
    private DocumentRepository documentRepository;
    private MediatorLiveData<Document> documentLiveData;

    public DocumentViewModel(@NonNull Application application) {
        super(application);
        documentRepository = new DocumentRepository(application);
        documentLiveData = new MediatorLiveData<>();
        documentLiveData.addSource(documentRepository.getDocumentLiveData(), document -> {
            if (document != null) {
                documentLiveData.setValue(document);
            }
        });
    }

    public void saveDocumentToFile(Document document) {
        document.saveToFile(getApplication());
        documentRepository.updateDocument(document);
    }

    public void loadDocumentFromFile() {
        Document document = documentLiveData.getValue();
        if (document != null) {
            document.loadFromFile(getApplication());
            documentRepository.updateDocument(document);
        }
    }

    public LiveData<Document> getDocumentLiveData() {
        return documentLiveData;
    }
}