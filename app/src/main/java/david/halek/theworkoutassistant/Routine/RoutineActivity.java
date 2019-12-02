package david.halek.theworkoutassistant.Routine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import david.halek.theworkoutassistant.R;
import exercise.AddExerciseFragment;
import exercise.ExerciseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

public class RoutineActivity extends AppCompatActivity {

    int userID = 9; // Flex Wheeler TODO remove this, get id from login activity
    Activity me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        Button btnAddExerciseRoutine = (Button) findViewById(R.id.btnAddExerciseRoutine);
        btnAddExerciseRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment myFragment = new AddRoutineFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.add(R.id.linearLayout, myFragment);
                ft.replace(R.id.coordinatorLayout, myFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        configureTabLayout();
    }

    public void configureTabLayout() {
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.getTabAt(1).select();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        Log.e("Routine", "Trainees Tab Selected");
                        break;
                    case 1:
                        Log.e( "Routine", "Routines");
                        break;
                    case 2:
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
}
