package exercise;

import android.app.ActionBar;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import david.halek.theworkoutassistant.R;

import static david.halek.theworkoutassistant.ConnectionClass.getExerciseDetail;
import static david.halek.theworkoutassistant.ConnectionClass.updateExerciseDetail;

/**
 * Activities that contain this fragment must implement the
 * {@link ExerciseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "userId";
    private static final String ARG_PARAM2 = "exerciseId";

    // TODO: Rename and change types of parameters
    private int exerciseId = -998;
    private int userId = 11;

    private OnFragmentInteractionListener mListener;
    ExerciseDetail exerciseDetail;

    EditText editName;
    EditText editDesc;
    EditText editInstructions;
    EditText editVideo;

    public ExerciseFragment() {
        // Required empty public constructor
    }

    public ExerciseFragment(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * //@param exerciseId Parameter 1.
     * //@param userId Parameter 2.
     * @return A new instance of fragment ExerciseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExerciseFragment newInstance(int exerciseID) {
//        this.exerciseId = exerciseID;
        ExerciseFragment fragment = new ExerciseFragment(exerciseID);
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, 12);
        args.putInt(ARG_PARAM2, exerciseID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getContext();
        if (getArguments() != null) {
            userId = getArguments().getInt(ARG_PARAM1);
            exerciseId = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        LayoutParams layoutParams = new LayoutParams( LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);

        return inflater.inflate(R.layout.fragment_exercise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View v = view;
        Log.e("Exercise", "Frag - exercise ID should be: " + exerciseId);
        editName = (EditText)v.findViewById(R.id.editExerciseName);
        editDesc = (EditText)v.findViewById(R.id.editDescription);
        editInstructions = (EditText)v.findViewById(R.id.editInstructions);
        editVideo = (EditText)v.findViewById(R.id.editVideo);
        updateScreen();
        editName.setOnFocusChangeListener(new MySetOnFocusChangeListener(editName));
        editVideo.setOnFocusChangeListener(new MySetOnFocusChangeListener(editVideo));
        editDesc.setOnFocusChangeListener(new MySetOnFocusChangeListener(editDesc));
        editInstructions.setOnFocusChangeListener(new MySetOnFocusChangeListener(editInstructions));
    }

    private class MySetOnFocusChangeListener implements View.OnFocusChangeListener {
        private View view;

        private MySetOnFocusChangeListener(View view) {
            this.view = view;
        }
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                int id = v.getId();
                String column = "";
                EditText box = (EditText) v;

                switch (id) {
                    case R.id.editExerciseName:
                        column = "ExerciseName";
                        break;
                    case R.id.editDescription:
                        column = "ExerciseDesc";
                        break;
                    case R.id.editInstructions:
                        column = "Instructions";
                        break;
                    case R.id.editVideo:
                        column = "VideoLink";
                        break;
                }
                Log.e("Exercise", "s is " + column + ", value is " + box.getText());
                updateExerciseDetail(exerciseId, column, box.getText().toString());
            }
        }
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
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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


    public void updateScreen() {
        exerciseDetail = (ExerciseDetail) getExerciseDetail(exerciseId);
        editInstructions.setText(exerciseDetail.getExerciseInstructions());
        editDesc.setText(exerciseDetail.getExerciseDesc());
        editName.setText(exerciseDetail.getExerciseName());
        editVideo.setText(exerciseDetail.getVideoId());
    }
}
