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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

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

    public int exerciseSelected = -1;
    public int detailsSelected = -1;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    RecyclerView detailsRecyclerView;
    RecyclerView.LayoutManager detailsLayoutManager;
    RecyclerView.Adapter detailsAdapter;

    EditText editSets;
    EditText editReps;
    EditText editDuration;

    ArrayList<ExerciseObject> exerciseList;
    ArrayList<RoutineDetailObject> detailsList;

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
//        if (routineId == -1) {
//            Log.e("Details", "RoutineEditFragment does NOT have routine set!");
//        } else {
//            Log.e("Details", "RoutineEditFragment has routineId of: "+routineId);
//        }

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
            exerciseList = ConnectionClass.getExerciseList();
//            Log.e("Routine", "ExerciseRecycler: Fragment retrieved: "+exerciseList.size());
        }

        adapter = new ExerciseRecycler(exerciseList, RoutineEditFragment.this);
        recyclerView.setAdapter(adapter);

        // Setup details list recyclerview
        if (detailsList == null) {
            detailsList = RoutineDetailObject.getDetailsList();
//            Log.e("detailsList", "detailsList size: "+detailsList.size());
        }

//        detailsRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerExercisesInRoutine);
//        detailsLayoutManager = new LinearLayoutManager(getContext());
//        detailsRecyclerView.setLayoutManager(detailsLayoutManager);
//        detailsAdapter = new DetailsRecycler(detailsList, RoutineEditFragment.this);
//        detailsRecyclerView.setAdapter(detailsAdapter);




        // Set click event for first button
        Button button = view.findViewById(R.id.btnAddExerciseToRoutine);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(v);
            }
        });

        // Set click event for other button
        button = view.findViewById(R.id.btnDeleteExercise);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(v);
            }
        });

        editSets = (EditText) view.findViewById(R.id.editSets);
        editReps = (EditText) view.findViewById(R.id.editReps);
        editDuration = (EditText) view.findViewById(R.id.editDuration);
    }

    public void msg(View v, String msg) {
        Snackbar.make(v,msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(View v) {
        if (mListener != null) {
            mListener.onFragmentInteraction(v);
        }

        switch (v.getId()) {
            case R.id.btnAddExerciseToRoutine:
                addExerciseToRoutine(v);
                break;
            case R.id.btnDeleteExercise:
                break;
        }

    }

    public void addExerciseToRoutine(View v) {
        Log.e("RoutineEditFragment", "addExerciseToRoutine exerciseSelectedId is: " + exerciseSelected);

        if (exerciseSelected < 0) {
            msg(v, "No exercise selected.");
            return;
        }

        int sets, reps, duration;
        sets = reps = duration = 0;

        Log.e("RoutineEditFragment", "addExerciseToRoutine editSets is:" + editSets);
        Log.e("RoutineEditFragment", "addExerciseToRoutine editSets is:" + editSets.getText());

        String s;
        s = editSets.getText().toString();
        if (!s.equals(""))
            sets = Integer.parseInt(s);

        s = editReps.getText().toString();
        if (!s.equals("")) reps = Integer.parseInt(s);

        s = editDuration.getText().toString();
        if (!s.equals("")) duration = Integer.parseInt(s);

        RoutineDetailObject temp = new RoutineDetailObject();
        RoutineDetailObject details = temp.createRoutineDetails(routineId, exerciseSelected, sets, reps, duration);
//                (routineId, exerciseSelected,
//                sets, reps, duration);
//                RoutineDetailObject(routineId, exerciseSelected);
//        details.setDuration(duration);
//        details.setReps(reps);
//        details.setSets(sets);
        msg(v, "Added exercise details.");
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
        void onFragmentInteraction(View v);
    }

    public void setExerciseSelected(int id) {
        exerciseSelected = id;
        Log.e("RoutineEditFragment", "exerciseSelected: "+id);
    }

    public void setDetailSelected(int id) {
        detailsSelected = id;
        Log.e("RoutineEditFragment", "detailSelected: "+id);

    }
}
