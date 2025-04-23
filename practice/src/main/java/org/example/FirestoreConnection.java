package org.example;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirestoreConnection {
    Firestore db;
    GUI frame;
    public FirestoreConnection(GUI frame) {
        this.frame = frame;

        try {
            db = null;
            FileInputStream serviceAccount = new FileInputStream("src/main/java/org/example/ecpe205-32612-firebase-adminsdk-fbsvc-a63f10ddec.json");
            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://ecpe205-32612-default-rtdb.asia-southeast1.firebasedatabase.app/").build();
            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addScore(String rawScore, String totalScore) {
        Map<String, Object> score = new HashMap<>();
        score.put("RawScore", rawScore);
        score.put("TotalScore", totalScore);

        ApiFuture<WriteResult> result = db.collection("StudentScores").document(frame.firstNameField.getText() + "\n" + frame.lastNameField.getText()).create(score);

    }
    public ArrayList<Scores> getScores() {

        try {
            ApiFuture<QuerySnapshot> query = db.collection("StudentScores").get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();
            ArrayList<Scores> scores = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                String rawScore = document.getString("RawScore");
                String totalScore = document.getString("TotalScore");
                scores.add(new Scores(rawScore, totalScore));
            }
            return scores;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteScore(int index) {
        if (index >= 0 && index < frame.personDataTable.scoresArrayList.size()) {
            Scores deleteThis = frame.personDataTable.scoresArrayList.get(index);

            CollectionReference personCollection = db.collection("StudentScores");
            ApiFuture<QuerySnapshot> query = personCollection.get();

            try {
                QuerySnapshot querySnapshot = query.get();
                List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

                for (QueryDocumentSnapshot document : documents) {
                    String rawScore = document.getString("RawScore");
                    String totalScore = document.getString("TotalScore");

                    if (rawScore != null && totalScore != null &&
                            rawScore.equals(deleteThis.getRawScore()) && totalScore.equals(deleteThis.getTotalScore())) {
                        ApiFuture<com.google.cloud.firestore.WriteResult> deleteResult = personCollection.document(document.getId()).delete();
                        deleteResult.get();
                        System.out.println("DELETED DOCUMENT WITH ID: " + document.getId());
                        frame.personDataTable.scoresArrayList.remove(index);
                        frame.personDataTable.fireTableDataChanged();
                        return;
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid index: " + index);
        }
    }


}
