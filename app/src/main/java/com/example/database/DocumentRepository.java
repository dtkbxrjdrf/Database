package com.example.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

public class DocumentRepository {
    private DocumentDao documentDao;
    private LiveData<Document> documentLiveData;

    public DocumentRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        documentDao = database.documentDao();
        documentLiveData = documentDao.getDocumentById(1);
    }

    public void insertDocument(Document document) {
        new InsertAsyncTask(documentDao).execute(document);
    }

    public void updateDocument(Document document) {
        new UpdateAsyncTask(documentDao).execute(document);
    }

    public LiveData<Document> getDocumentLiveData() {
        return documentLiveData;
    }

    private static class InsertAsyncTask extends AsyncTask<Document, Void, Void> {
        private DocumentDao documentDao;
        public InsertAsyncTask(DocumentDao documentDao) {
            this.documentDao = documentDao;
        }
        @Override
        protected Void doInBackground(Document... documents) {
            documentDao.insert(documents[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Document, Void, Void> {
        private DocumentDao documentDao;
        public UpdateAsyncTask(DocumentDao documentDao) {
            this.documentDao = documentDao;
        }

        @Override
        protected Void doInBackground(Document... documents) {
            documentDao.update(documents[0]);
            return null;
        }
    }
}
