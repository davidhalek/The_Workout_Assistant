package david.halek.theworkoutassistant.Routine;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.FragmentTransaction;
import david.halek.theworkoutassistant.R;

import static david.halek.theworkoutassistant.Routine.RoutineObject.addExerciseRoutine;
import static david.halek.theworkoutassistant.Routine.RoutineObject.checkIfValidRoutineName;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddRoutineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddRoutineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRoutineFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USERID = "userId";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int userId;
    private String mParam2;

    EditText editName;
    EditText editDesc;

    private OnFragmentInteractionListener mListener;

    public AddRoutineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRoutineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRoutineFragment newInstance(int param1, String param2) {
        AddRoutineFragment fragment = new AddRoutineFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_USERID, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(ARG_USERID);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_routine, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }

    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        editName = (EditText)v.findViewById(R.id.editExerciseRoutineName);
        editDesc = (EditText)v.findViewById(R.id.editExerciseRoutineDesc);

        final Button btnAddExerciseRoutine = (Button) v.findViewById(R.id.btnSaveNewRoutine);

        btnAddExerciseRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String desc = editDesc.getText().toString();

                if (name.length() < 1) {
                    Snackbar.make(v, "Exercise routine name cannot be empty.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (!checkIfValidRoutineName(name)) {
                    Snackbar.make(v, "Exercise routine name must be unique - this routine already exists.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    boolean success = addExerciseRoutine(name, desc);
                    Snackbar.make(v, "Adding exercise routine "+name+".  Success: "+success, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    if (success) {
                        editDesc.setText("");
                        editName.setText("");
                        // TODO force recycler to update, and exit this fragment
                        Activity a = getActivity();
                    }
                }
            }
        });
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
