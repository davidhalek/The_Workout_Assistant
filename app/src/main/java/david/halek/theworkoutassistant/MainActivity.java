package david.halek.theworkoutassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import static david.halek.theworkoutassistant.ConnectionClass.getExerciseList;
import static david.halek.theworkoutassistant.ConnectionClass.getFirstName;
//import static david.halek.theworkoutassistant.DbFunctions.getFirstName;

public class MainActivity extends AppCompatActivity {

    int userId = 12; // Todo: get userid from bundle passed by login activity
    TextView txtWelcome;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectionClass db = new ConnectionClass();
        txtWelcome = findViewById(R.id.txtWelcome);
        txtWelcome.setText(getFirstName(userId));

        // Todo: remove this
        recyclerView = (RecyclerView) findViewById(R.id.recTest);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new TestAdapter(getExerciseList());
        recyclerView.setAdapter(mAdapter);
        getExerciseList("", 1);
    }


}
