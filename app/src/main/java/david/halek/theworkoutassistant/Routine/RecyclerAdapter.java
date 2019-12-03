package david.halek.theworkoutassistant.Routine;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import david.halek.theworkoutassistant.R;

import static david.halek.theworkoutassistant.ConnectionClass.getExerciseDetail;
import static david.halek.theworkoutassistant.Routine.RoutineEditFragment.newInstance;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String ARG_ROUTINE_ID = "routineId";
    private ArrayList<RoutineObject> routineList;

    public RecyclerAdapter(ArrayList<RoutineObject> routineList) {
        this.routineList = routineList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.routine_card_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoutineObject ob = (RoutineObject)routineList.get(position);
        Log.e("ROUTINE", "RecyclerAdapter Name is: " + ob.getExerciseRoutineName());
//                onBindViewHolder ob type is: "+ob.toString());
        holder.txtRoutineName.setText(ob.getExerciseRoutineName());
        holder.txtRoutineDesc.setText(ob.getExerciseRoutineDesc());
    }

    @Override
    public int getItemCount() {
        return routineList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRoutineName;
        public TextView txtRoutineDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRoutineName = itemView.findViewById(R.id.txtRoutineName);
            txtRoutineDesc = itemView.findViewById(R.id.txtRoutineDesc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    openRoutineDetail(v);
                }
            });
        }



        public void openRoutineDetail(View v) {
            int position = getAdapterPosition();
            int id = -1;
            Snackbar.make(v,"Click detected on item " + position,
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            RoutineObject ob = (RoutineObject)routineList.get(position);
            Log.e("Exercise", "Selected: " + ob.getExerciseRoutineName());
            id = (int)ob.getExerciseRoutineId();
            Log.e("Exercise", "From query: " + ob.getExerciseRoutineName());


            // Load the fragment
            AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
            Fragment frag = new RoutineEditFragment(id);
            Bundle args = new Bundle();
            args.putInt(ARG_ROUTINE_ID, id);
            frag.setArguments(args);
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            Log.e("Routine", "Routine ID is: " + id);
            ft.add(R.id.layoutAddExerciseRoutine, frag);
            ft.addToBackStack(null);
            ft.commit();

        }
    }
}
