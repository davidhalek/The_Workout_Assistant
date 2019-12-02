package david.halek.theworkoutassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import david.halek.theworkoutassistant.Routine.RoutineActivity;
import david.halek.theworkoutassistant.dummy.DummyContent;
import exercise.ExerciseActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import static david.halek.theworkoutassistant.ConnectionClass.getExerciseList;
import static david.halek.theworkoutassistant.ConnectionClass.getFirstName;


public class MainActivity extends AppCompatActivity implements FragExerciseList.OnFragmentInteractionListener {

    int userId = 12; // Todo: get userid from bundle passed by login activity
    TextView txtWelcome;
    FragExerciseList.OnFragmentInteractionListener callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: fix this to make exercise a menu option
//        Intent intent = new Intent(this, ExerciseActivity.class);
        Intent intent = new Intent(this, RoutineActivity.class);
        startActivity(intent);
//         Don't need this, defining toolbar in xml
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //FragmentManager manager = getSupportFragmentManager();
        //FragmentTransaction transaction = manager.beginTransaction();
        //transaction.replace(R.id.pager, new FragExerciseList()); // fragExerciseListCoordinator

        // Todo: remove this
//        txtWelcome = findViewById(R.id.txtWelcome);
//        txtWelcome.setText(getFirstName(userId));
//        configureTabLayout();
    }

    private void configureTabLayout() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.e("MainActivity", "Dummy Content");
    }

    @Override
    public void onListFragmentInteraction(String string) {
        Log.e("MainActivity", "String Content");
    }

    public void setOnListFragmentInteractionListener(FragExerciseList.OnFragmentInteractionListener callback) {
        this.callback = callback;
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

