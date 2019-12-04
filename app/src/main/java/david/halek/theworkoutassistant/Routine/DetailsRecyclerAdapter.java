package david.halek.theworkoutassistant.Routine;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import david.halek.theworkoutassistant.R;
import exercise.ExerciseDetail;
import exercise.ExerciseObject;

import static david.halek.theworkoutassistant.Database.ConnectionClass.getExerciseDetail;

public class DetailsRecyclerAdapter extends RecyclerView.Adapter<DetailsRecyclerAdapter.ViewHolder> {
    private static final String ARG_DETAIL_ID = "detailId";
    private ArrayList<RoutineDetailObject> detailsList;
    private int selectedPos = RecyclerView.NO_POSITION;
    private int selectedId = -1;
    View lastView = null;
    RoutineEditFragment myFragment;

    public DetailsRecyclerAdapter(ArrayList<RoutineDetailObject> detailsList, RoutineEditFragment myFragment) {
        this.detailsList = detailsList;
        this.myFragment = myFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_routine_details, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoutineDetailObject ob = (RoutineDetailObject) detailsList.get(position);
//                onBindViewHolder ob type is: "+ob.toString());
        int exerciseId = ob.getExerciseId();
        ExerciseDetail exerciseOb = getExerciseDetail(exerciseId);
        String exercise = "";
        exercise = exerciseOb.getExerciseName().toString();
        Log.e("DETAILS", "DetailsRecyclerAdapter Name is: " + exercise);
        Log.e("DETAILS", "DetailsRecyclerAdapter holder is: " + holder);
        int sets = ob.getSets();
        int reps = ob.getReps();
        int duration = ob.getDuration();

        holder.txtExerciseName.setText(exercise);
        if (sets > 0) holder.txtSets.setText(sets+"");
        if (reps > 0) holder.txtReps.setText(reps+"");
        if (duration > 0) holder.txtDuration.setText(duration+"");
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtExerciseName;
        public TextView txtSets;
        public TextView txtReps;
        public TextView txtDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtExerciseName = (TextView) itemView.findViewById(R.id.txtExerciseName);
            txtSets = (TextView) itemView.findViewById(R.id.txtSets);
            txtReps = (TextView) itemView.findViewById(R.id.txtReps);
            txtDuration = (TextView) itemView.findViewById(R.id.txtDuration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    selectDetail(v);
                }
            });
        }



        public void selectDetail(View v) {
            int position = getAdapterPosition();
            int id = -1;

            // Switch previously selected item to unselected
            if (lastView != null) {
                lastView.setBackgroundResource(R.drawable.smaller_rectangle);
            }

            // Select current item
            v.setBackgroundResource(R.drawable.smaller_rectangle_selected);
            lastView = v;

//            Snackbar.make(v,"Click detected on item " + position,
//                    Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
            RoutineDetailObject ob = (RoutineDetailObject)detailsList.get(position);
            Log.e("DetailsRecyclerAdapter", "openRoutineDetail - selected: " + ob.getDetailsId());
            id = (int)ob.getDetailsId();
            selectedId = id;
            myFragment.setDetailSelected(selectedId, position);
//            Log.e("Exercise", "From query: " + ob.getExerciseRoutineName());

            // Load the fragment
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
