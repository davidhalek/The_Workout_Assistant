package david.halek.theworkoutassistant.Routine;

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
            Snackbar.make(v,"Click detected on item " + position,
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            RoutineObject ob = (RoutineObject)routineList.get(position);
            Log.e("Exercise", "Selected: " + ob.getExerciseRoutineName());
            RoutineObject details = new RoutineObject(ob.getExerciseRoutineId());
            Log.e("Exercise", "From query: " + details.getExerciseRoutineName());

// ///////////////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////
            // TODO add these back in
            // Open fragment
            AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
//            Fragment myFragment = new AddRoutineFragment(ob.getExerciseRoutineId());
//            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//            ft.add(R.id.layoutExerciseFragment, myFragment);
//            ft.addToBackStack(null);
//            ft.commit();

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
