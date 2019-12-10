package exercise;

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

import com.google.android.material.snackbar.Snackbar;

import david.halek.theworkoutassistant.R;

import static david.halek.theworkoutassistant.ConnectionClass.addExercise;
import static david.halek.theworkoutassistant.ConnectionClass.checkIfValidExerciseName;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddExerciseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddExerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddExerciseFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText editName;
    EditText editDesc;
    EditText editInstructions;
    EditText editVideo;
    Button btnAddExercise;

    public AddExerciseFragment() {
        // Required empty public constructor
        // TODO: remove
//        Log.e("Exercise", "AddExerciseFragment() Constructor called");
//        checkIfValidExerciseName("blah");
//        checkIfValidExerciseName("Run");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddExerciseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddExerciseFragment newInstance(String param1, String param2) {
        AddExerciseFragment fragment = new AddExerciseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_exercise, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(ExerciseObject ob) {
        if (mListener != null) {
            mListener.onFragmentInteraction(ob);
        }
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        editName = (EditText)v.findViewById(R.id.editExerciseName);
        editDesc = (EditText)v.findViewById(R.id.editDescription);
        editInstructions = (EditText)v.findViewById(R.id.editInstructions);
        editVideo = (EditText)v.findViewById(R.id.editVideo);
        btnAddExercise = (Button)v.findViewById(R.id.btnAddExercise);
        btnAddExercise.setOnClickListener(this);


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

    public void msg(View v, String text) {
        Snackbar.make(v, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onClick(View v) {
        String name, desc, instructions, video;
        boolean flag = true;

        name = editName.getText().toString();
        desc = editDesc.getText().toString();
        instructions = editInstructions.getText().toString();
        video = editVideo.getText().toString();

        if (name.length() < 1) {
            msg(v, "Exercise name cannot be empty.");
            flag = false;
        } else if (!checkIfValidExerciseName(name)) {
            msg(v, "Exercise name must be unique.");
//            Log.e("Exercise", "Check for valid exercise name returned false.");
            flag = false;
        } else {
            boolean r = addExercise(name, desc, instructions, video);
            msg(v, "Returned "+r);
        }
        ((ExerciseActivity)getActivity()).updateList();
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
        void onFragmentInteraction(ExerciseObject ob);
    }
}
