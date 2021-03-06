package exercise;

import android.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList exerciseList;

    public RecyclerAdapter(ArrayList exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_text_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseObject ob = (ExerciseObject)exerciseList.get(position);
        holder.textView.setText(ob.getExerciseName());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_exercise);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    openExerciseDetail(v);
                }
            });
        }

        public void openExerciseDetail(View v) {
            int position = getAdapterPosition();
            Snackbar.make(v,"Click detected on item " + position,
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            ExerciseObject ob = (ExerciseObject)exerciseList.get(position);
            Log.e("Exercise", "Selected: " + ob.getExerciseName());
            ExerciseDetail details = (ExerciseDetail)getExerciseDetail(ob.getExerciseId());
            Log.e("Exercise", "From query: " + details.getExerciseName());

            AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
            Fragment myFragment = new ExerciseFragment(ob.getExerciseId());

            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//            ft.add(R.id.linearLayout, myFragment);
            ft.add(R.id.layoutExerciseFragment, myFragment);
            ft.addToBackStack(null);
            ft.commit();

            // Resize frame
//            try {
//                FrameLayout frame = (FrameLayout)activity.findViewById(R.id.layoutExerciseFragment);
////            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//                frame.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }
}
