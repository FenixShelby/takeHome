package org.example;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        GUI frame = new GUI();
        FirestoreConnection firestoreConnection = new FirestoreConnection(frame);

        for (int i = 0 ; i < firestoreConnection.getScores().size(); i++) {
            frame.personDataTable.addScore(firestoreConnection.getScores().get(i));
        }

        frame.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firestoreConnection.addScore(frame.rawScoreField.getText(), frame.totalScoreField.getText());
                frame.personDataTable.addScore(new Scores(frame.rawScoreField.getText(), frame.totalScoreField.getText()));
            }
        });

        frame.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedPersons = frame.table.getSelectedRows();
                for (int i = selectedPersons.length -1 ; i >= 0 ; i--) {
                    firestoreConnection.deleteScore(selectedPersons[i]);
                }
            }
        });



}
    }

