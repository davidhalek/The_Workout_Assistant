package david.halek.theworkoutassistant.Routine;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import david.halek.theworkoutassistant.R;
import exercise.ExerciseObject;


public class ExerciseRecycler extends RecyclerView.Adapter<ExerciseRecycler.ViewHolder> {
//    private static final String ARG_ROUTINE_ID = "routineId";
    private ArrayList<ExerciseObject> exerciseList;

    public ExerciseRecycler(ArrayList<ExerciseObject> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_text_view_small, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Exercise.RecyclerAdapter.ViewHolder holder, int position) {
//        ExerciseObject ob = (ExerciseObject)exerciseList.get(position);
//        Log.e("ROUTINE", "ExerciseRecycler Name is: " + ob.getExerciseName());
//        holder.txt.setText(ob.getExerciseName());
//    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseObject ob = (ExerciseObject)exerciseList.get(position);
        Log.e("ROUTINE", "ExerciseRecycler Name is: " + ob.getExerciseName());
        holder.txtExerciseName.setText(ob.getExerciseName().toString());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtExerciseName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtExerciseName = itemView.findViewById(R.id.txt_exercise_smaller);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    exerciseSelected(v);
                }
            });
        }

        public void exerciseSelected(View v) {
            int position = getAdapterPosition();
            int id = -1;
            Snackbar.make(v,"Click detected on item " + position,
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            ExerciseObject ob = (ExerciseObject) exerciseList.get(position);
            id = (int)ob.getExerciseId();
            Log.e("ExerciseRecycler", "Selected: id is: " + id + " name is: " + ob.getExerciseName());

//            // Load the fragment
//            AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
//            Fragment frag = new RoutineEditFragment(id);
//            Bundle args = new Bundle();
//            args.putInt(ARG_ROUTINE_ID, id);
//            frag.setArguments(args);
//            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//            Log.e("Routine", "Routine ID is: " + id);
//            ft.add(R.id.layoutAddExerciseRoutine, frag);
//            ft.addToBackStack(null);
//            ft.commit();

        }
    }
}

