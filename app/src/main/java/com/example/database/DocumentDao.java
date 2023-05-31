package com.example.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DocumentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Document document);

    @Update
    void update(Document document);

    @Query("SELECT * FROM documents WHERE id = :id")
    LiveData<Document> getDocumentById(int id);
}
