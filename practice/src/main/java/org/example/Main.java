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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        GUI frame = new GUI();
        FirestoreConnection firestoreConnection = new FirestoreConnection(frame);

        frame.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String rawScore = frame.rawScoreField.getText();
                String totalScore = frame.totalScoreField.getText();
                Scores score = new Scores(rawScore, totalScore);
                frame.personDataTable.addScore(score);
            }
        });


        frame.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = frame.firstNameField.getText();
                String lastName = frame.lastNameField.getText();
                firestoreConnection.addScore(firstName, lastName, frame.personDataTable.scoresArrayList);
            }
        });

        frame.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = frame.personDataTable.scoresArrayList.size();
                for (int i = size - 1; i >= 0; i--) {
                    frame.personDataTable.removeScore(i);
                }
            }
        });



}
    }

