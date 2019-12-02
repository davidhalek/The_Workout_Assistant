package exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import david.halek.theworkoutassistant.R;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static david.halek.theworkoutassistant.ConnectionClass.getExerciseList;

public class ExerciseActivity extends AppCompatActivity implements ExerciseFragment.OnFragmentInteractionListener {
    int userId = 12; // Todo: get userID from bundle passed by main activity
    private ArrayList exerciseList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    FloatingActionButton floatingActionButton;

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
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
