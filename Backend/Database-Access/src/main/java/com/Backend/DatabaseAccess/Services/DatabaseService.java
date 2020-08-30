package com.Backend.DatabaseAccess.Services;

import com.Backend.DatabaseAccess.Utils.Utils;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Service
public class DatabaseService {

    private final Firestore db;
    private final FirebaseDatabase firebaseDatabase;

    DatabaseService() throws IOException {

        FileInputStream serviceAccount = new FileInputStream(Utils.PATH_TO_KEY);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://resound-f8d61.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();

        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public Firestore getDb() {
        return db;
    }
    public FirebaseDatabase getRealtimeDB() {
        return firebaseDatabase;
    }
}
