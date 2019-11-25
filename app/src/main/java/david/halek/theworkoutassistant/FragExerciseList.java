package david.halek.theworkoutassistant;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import david.halek.theworkoutassistant.dummy.DummyContent.DummyItem;

import static david.halek.theworkoutassistant.ConnectionClass.getExerciseList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class FragExerciseList extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnFragmentInteractionListener mListener;
    private ArrayList exerciseList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragExerciseList() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FragExerciseList newInstance(int columnCount) {
        FragExerciseList fragment = new FragExerciseList();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        Log.e("frag", "----- FragExerciseList onCreate() ---------");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.recTest, container, false);
        View view = inflater.inflate(R.layout.exercise_fragment_list, container, false);

        // Set the adapter
//        if (view instanceof RecyclerView) {
        Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recTest);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        exerciseList = getExerciseList();
        ExerciseAdapterTest mAdapter = new ExerciseAdapterTest(exerciseList);
//        if (exerciseList.size() > 0 && mListener != null) {
            recyclerView.setAdapter(mAdapter);
            Log.e("frag", "-------------------------");
//        }
        Log .e("frag", "--> Exercise list size: " + exerciseList.size() + " <--");
//        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
        void onListFragmentInteraction(String string);
    }
}
