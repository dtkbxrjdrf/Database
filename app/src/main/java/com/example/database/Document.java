package com.example.database;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Entity(tableName = "documents")
public class Document {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;

    public Document(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void saveToFile(Context context) {
        try {
            File dir = new File(context.getFilesDir(), "Documents");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(dir, "document.txt");
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(Context context) {
        try {
            File file = new File(context.getFilesDir(), "Documents/document.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            content = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
