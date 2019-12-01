package exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import david.halek.theworkoutassistant.R;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import static david.halek.theworkoutassistant.ConnectionClass.getExerciseList;

public class ExerciseActivity extends AppCompatActivity {
    int userId = 12; // Todo: get userID from bundle passed by main activity
    private ArrayList exerciseList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Log.e("Exercise", "onCreate"); // TODO: remove this
        recyclerView = (RecyclerView) findViewById(R.id.recyclerExercise);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (exerciseList == null) {
            Log.e("Exercise", "Activity retrieving exercise list");
            exerciseList = getExerciseList();
            Log.e("Exercise", "Activity retrieved: "+exerciseList.size());
        }

        adapter = new RecyclerAdapter(exerciseList);
        recyclerView.setAdapter(adapter);
    }
}
