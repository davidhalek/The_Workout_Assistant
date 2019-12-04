package exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import david.halek.theworkoutassistant.R;
import david.halek.theworkoutassistant.Routine.RoutineActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

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

        // floating action button
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Creating Add Exercise Fragment", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                Fragment myFragment = new AddExerciseFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.add(R.id.linearLayout, myFragment);
                ft.replace(R.id.layoutExerciseFragment, myFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        configureTabLayout();
    }

    //
    // Setup Tabs
    //

    public void configureTabLayout() {
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.getTabAt(1).select();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 3:
                        Log.e("Routine", "Trainees Tab Selected");
                        break;
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), RoutineActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Log.e( "Routine", "Exercises");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        TabLayout.Tab tab = (TabLayout.Tab) findViewById(R.id.tabRoutines);
//        TabLayout.Tab tab = (TabLayout.Tab) tabLayout.findViewById(R.id.tabRoutines);

    }

    public void updateList() {

        exerciseList = getExerciseList();
        Log.e("Exercise", "updateList() called.");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
