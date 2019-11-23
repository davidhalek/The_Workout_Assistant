package david.halek.theworkoutassistant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class ExerciseAdapterTest extends RecyclerView.Adapter<ExerciseAdapterTest.MyViewHolder> {
//    private String[] mDataset;
        private ArrayList exerciseList;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View vv) {
                    int position = getAdapterPosition();

                    Snackbar.make(vv, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExerciseAdapterTest(ArrayList myDataset) {
        exerciseList = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ExerciseAdapterTest.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_text_view, parent, false);
//        ...
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ExerciseListElement ob = (ExerciseListElement)exerciseList.get(position);

//        holder.textView.setText(exerciseList.get(position).getExerciseName());
        holder.textView.setText(ob.getExerciseName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}