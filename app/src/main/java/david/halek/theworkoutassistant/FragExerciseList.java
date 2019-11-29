package david.halek.theworkoutassistant;

import android.app.Activity;
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

import static android.content.ContentValues.TAG;
import static david.halek.theworkoutassistant.ConnectionClass.getExerciseList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class FragExerciseList extends Fragment implements ExerciseClickListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnFragmentInteractionListener mListener;
    private ArrayList exerciseList;
    private Context thisContext;

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

        thisContext = getContext();
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
        if (mColumnCount <= 1)
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        } else {
//            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//        }
        if (exerciseList == null) {
            Log.e("FRAG", "exerciseList is null");
            exerciseList = getExerciseList();
        } else {
            Log.e("FRAG", "exerciseList is NOT null");
        }

        ExerciseAdapterTest mAdapter = new ExerciseAdapterTest(exerciseList, context);
//            @Override
//            public void onItemClick(int position, View v) {
//                Log.e("FragExerciseList", "onITEMClicked " + position);
//            }
//
//            @Override public void onPositionClicked(int position) {
//                Log.e("FragExerciseList", "onPositionClicked " + position);
//            }
//        });
//        mAdapter.setOnClickListener(new ExerciseAdapterTest.ClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//                Log.e("FragExerciseList", "onItemClick position: " + position);
//            }
//        });
//        if (exerciseList.size() > 0 && mListener != null) {
            recyclerView.setAdapter(mAdapter);
            mAdapter.setClickListener(this);
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
            Log.e("FRAG", "onAttach mlistener called");
        }
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
        Log.e("***", "***************");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view, int position) {
        Log.e("FRAG", "OnClick called: "+position);
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
