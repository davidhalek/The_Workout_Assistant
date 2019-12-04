package david.halek.theworkoutassistant.Routine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import david.halek.theworkoutassistant.R;
import exercise.AddExerciseFragment;
import exercise.ExerciseActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static david.halek.theworkoutassistant.ConnectionClass.getExerciseList;

public class RoutineActivity extends AppCompatActivity implements AddRoutineFragment.OnFragmentInteractionListener {

    int userID = 9; // Flex Wheeler TODO remove this, get id from login activity
    Activity me;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    private ArrayList<RoutineObject> routineList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        // Setup recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (routineList == null) {
            Log.e("Routine", "Activity retrieving routineList");
            routineList = RoutineObject.getRoutineList();
            Log.e("Routine", "Activity retrieved: "+routineList.size());
        }

        adapter = new RecyclerAdapter(routineList);
        recyclerView.setAdapter(adapter);

        // Setup button to add new exercise routine
        Button btnAddExerciseRoutine = (Button) findViewById(R.id.btnNewRoutine);
        btnAddExerciseRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment myFragment = new AddRoutineFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.add(R.id.linearLayout, myFragment);
                ft.replace(R.id.layoutAddExerciseRoutine, myFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        configureTabLayout();
    }

    public void configureTabLayout() {
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.getTabAt(0).select();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 3:
                        Log.e("Routine", "Trainees Tab Selected");
                        break;
                    case 0:
                        Log.e( "Routine", "Routines");
                        break;
                    case 1:
                        Log.e( "Routine", "Exercises");
                        Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
                        Log.e( "Routine", "Exercises");
                        //ExerciseActivity.class);
                        startActivity(intent);
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

    public void loadRoutineDetails(int id) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
