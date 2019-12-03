package david.halek.theworkoutassistant.Routine;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import david.halek.theworkoutassistant.ConnectionClass;
import david.halek.theworkoutassistant.R;
import exercise.ExerciseObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RoutineEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RoutineEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoutineEditFragment extends Fragment {

    private static final String ARG_ROUTINE_ID = "routineId";
    private RoutineDetailObject detailObject;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    ArrayList<ExerciseObject> exerciseList;

    private int routineId = -1;

    private OnFragmentInteractionListener mListener;

    public RoutineEditFragment() { }
    public RoutineEditFragment(int id) {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id routineId
     * @return A new instance of fragment RoutineEditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoutineEditFragment newInstance(int id) {
        RoutineEditFragment fragment = new RoutineEditFragment(id);
        Bundle args = new Bundle();
        args.putInt(ARG_ROUTINE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            routineId = getArguments().getInt(ARG_ROUTINE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_routine_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (routineId == -1) {
            Log.e("Details", "RoutineEditFragment does NOT have routine set!");
        } else {
            Log.e("Details", "RoutineEditFragment has routineId of: "+routineId);
        }

        RoutineObject ob = new RoutineObject(routineId);
        TextView name = (TextView) view.findViewById(R.id.txtRoutineName);
        TextView desc = (TextView) view.findViewById(R.id.txtRoutineDesc);
        name.setText(ob.getExerciseRoutineName());
        desc.setText(ob.getExerciseRoutineDesc());

        // Setup exercise list recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerAvailableExercises);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        if (exerciseList == null) {
            Log.e("Routine", "ExerciseRecycler: Fragment retrieving routineList");
            exerciseList = ConnectionClass.getExerciseList();
            Log.e("Routine", "ExerciseRecycler: Fragment retrieved: "+exerciseList.size());
        }

        adapter = new ExerciseRecycler(exerciseList);
        recyclerView.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
