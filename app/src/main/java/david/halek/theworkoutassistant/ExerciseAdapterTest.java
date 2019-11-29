package david.halek.theworkoutassistant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciseAdapterTest extends RecyclerView.Adapter<ExerciseAdapterTest.MyViewHolder> { //implements View.OnClickListener {
//    private FragExerciseList.OnFragmentInteractionListener mListener;
    private ArrayList exerciseList;
    private ExerciseClickListener mClickListener;
    private Context mContext;

//    public ExerciseAdapterTest(ArrayList myDataset, FragExerciseList.OnFragmentInteractionListener listener) {
    public ExerciseAdapterTest(ArrayList myDataset, Context context) {
//                               ExerciseClickListener listener) {
        this.exerciseList = myDataset;
        this.mContext = context;
//        this.mClickListener = listener;
    }
//    public ExerciseAdapterTest(ArrayList myDataset) {
//        exerciseList = myDataset;
//    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
//        TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.exercise_text_view, parent, false);
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_text_view, parent, false);
//        MyViewHolder vh = new MyViewHolder(v, mClickListener);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ExerciseListElement ob = (ExerciseListElement)exerciseList.get(position);
        holder.textView.setText(ob.getExerciseName());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
//        private ExerciseClickListener clickListener;

//        public MyViewHolder(View v, ExerciseClickListener listener) {
        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.txt_exercise);
            Log.e("ADAPTER", "Setting OnClickListener: "+this.getAdapterPosition() +
                    " " + textView.equals(v));
            v.setTag(v);
            v.setOnClickListener(this);  // TODO: this may need to be setclicklistener()
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                mClickListener.onClick(v, getAdapterPosition());
            }
            Log.e("ADAPTER", "onClick called, position " + getAdapterPosition());
//            clickListener.onItemClick(getAdapterPosition(), v);
//            loadExerciseDetail(v);
        }

        public void loadExerciseDetail(View v) {
            Log.e("Adapter", "Click detected.");
            int position = getAdapterPosition();

            Snackbar.make(v, "Click detected on item " + position,
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    public void setClickListener(ExerciseClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return exerciseList == null ? 0 : exerciseList.size();
    }


}