package david.halek.theworkoutassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import static david.halek.theworkoutassistant.ConnectionClass.getExerciseList;
import static david.halek.theworkoutassistant.ConnectionClass.getFirstName;


public class MainActivity extends AppCompatActivity {

    int userId = 12; // Todo: get userid from bundle passed by login activity
    TextView txtWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//         Don't need this, defining toolbar in xml
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.pager, new FragExerciseList()); // fragExerciseListCoordinator

        // Todo: remove this
        txtWelcome = findViewById(R.id.txtWelcome);
        txtWelcome.setText(getFirstName(userId));
    }

    private void configureTabLayout() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);


    }
}
















//  Old recyclerview code

//  class defs
//    private RecyclerView recyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager layoutManager;
// todo get rid of these
//        recyclerView = (RecyclerView) findViewById(R.id.recTest);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        mAdapter = new ExerciseAdapterTest(getExerciseList());
//        recyclerView.setAdapter(mAdapter);
//        getExerciseList("", 1);

