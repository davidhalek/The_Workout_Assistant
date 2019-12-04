package david.halek.theworkoutassistant.Routine;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import david.halek.theworkoutassistant.Database.ConnectionClass;
import david.halek.theworkoutassistant.R;

public class RoutineDetailObject {
    public static final String DETAILS_TABLE_NAME = " [WorkoutAssistant].[dbo].[ExerciseRoutineDetails] ";

    public static final String DETAILS_ID_COLUMN = " [ExerciseRoutineDetailsID] ";
    public static final String DETAILS_ROUTINE_ID_COLUMN = " [ExerciseRoutineID] ";
    public static final String DETAILS_EXERCISE_ID_COLUMN = " [ExerciseID] ";
    public static final String DETAILS_SETS_COLUMN = " [Sets] ";
    public static final String DETAILS_REPS_COLUMN = " [Reps] ";
    public static final String DETAILS_WEIGHT_COLUMN = " [Weight] ";
    public static final String DETAILS_DURATION_COLUMN = " [Duration] ";
    public static final String DETAILS_CREATE_DATE_COLUMN = " [CreateDate] ";

    public static final int DETAILS_ID_COLUMN_NUM = 1;
    public static final int DETAILS_ROUTINE_ID_COLUMN_NUM = 2;
    public static final int DETAILS_EXERCISE_ID_COLUMN_NUM = 3;
    public static final int DETAILS_SETS_COLUMN_NUM = 4;
    public static final int DETAILS_REPS_COLUMN_NUM = 5;
    public static final int DETAILS_WEIGHT_COLUMN_NUM = 6;
    public static final int DETAILS_DURATION_COLUMN_NUM = 7;
    public static final int DETAILS_CREATE_DATE_COLUMN_NUM = 8;

    public int detailsId = -2;
    public int routineId = -2;
    public int exerciseId = -2;
    public int sets = 0;
    public int reps = 0;
    public int weight = 0;
    public  int duration = 0;
    public String createDate = "";
    public String routineName = "";
    public String routineDesc = "";

    //
    // Constructors
    //

    public RoutineDetailObject() {
    }

    public RoutineDetailObject(int id) {
        String query = "SELECT * " +
                " FROM "+ DETAILS_TABLE_NAME +
                " WHERE " + DETAILS_ID_COLUMN + " = " + id;

        loadDetails(query);
    }

    public RoutineDetailObject(int routineId, int exerciseId) {
        String query = "SELECT * " +
                " FROM "+ DETAILS_TABLE_NAME +
                " WHERE " + DETAILS_ROUTINE_ID_COLUMN + " = " + routineId +
                " AND " + DETAILS_EXERCISE_ID_COLUMN + " = " + exerciseId;

        loadDetails(query);
    }

    void loadDetails(String query) {
        // do the query, get the results, load them into the variables
        Log.e("DETAILS", "Query is: " + query);

        RoutineObject ob = new RoutineObject(routineId);
    }

    RoutineDetailObject createRoutineDetails(int routineId, int exerciseId, int sets, int reps, int duration) {
        this.routineId = routineId;
        this.exerciseId = exerciseId;
        this.sets = sets;
        this.reps = reps;
        this.duration = duration;

        ConnectionClass connectionClass = new ConnectionClass();
        Connection con = connectionClass.CONN();

        String query = "INSERT INTO " + DETAILS_TABLE_NAME + "( " +
                DETAILS_ROUTINE_ID_COLUMN + ", " +
                DETAILS_EXERCISE_ID_COLUMN + ", " +
                DETAILS_SETS_COLUMN + ", " +
                DETAILS_REPS_COLUMN + ", " +
                DETAILS_DURATION_COLUMN + ") " +
                "VALUES (?, ?, ?, ?, ? )";
        int result = -1;
        int newId = -1;

        try {
            PreparedStatement preparedQuery = con.prepareStatement(query);
            preparedQuery.setInt( 1, routineId);
            preparedQuery.setInt( 2, exerciseId);
            preparedQuery.setInt( 3, sets);
            preparedQuery.setInt( 4, reps);
            preparedQuery.setInt( 5, duration);
            Log.e("PREPARED", preparedQuery.toString());
            result = preparedQuery.executeUpdate();

            // Get new key
            query = "SELECT [ExerciseRoutineDetailsID] FROM" + DETAILS_TABLE_NAME +
                    " WHERE " + DETAILS_EXERCISE_ID_COLUMN + " = ? AND "
                    + DETAILS_ROUTINE_ID_COLUMN + " = ? ORDER BY" + DETAILS_CREATE_DATE_COLUMN;

            PreparedStatement query2 = con.prepareStatement(query);
            query2.setInt(1, exerciseId);
            query2.setInt(2, routineId);

            Log.e("PREPARED", query2.toString());
            ResultSet rs = query2.executeQuery();

            while (rs.next()) {
                this.detailsId = (int) rs.getInt(1);
                Log.e("RoutineDetailObject", "detailsId is: " + this.detailsId);
            }
//
//            ResultSet keys = preparedQuery.getGeneratedKeys();
//            if (keys.next()) {
//                this.detailsId = keys.getInt(1);
//                Log.e("RoutineDetailObject", "NEW KEY GENERATED: " + detailsId);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Log.e("RoutineDetailObject", "Update result for "+routineId+" is: "+result);
//        return (result > 0 ? true : false);

        return this;
    }

    public static ArrayList<RoutineDetailObject> getDetailsList() {
        ArrayList details = new ArrayList();
        String query = "SELECT * FROM " + DETAILS_TABLE_NAME +
                "ORDER BY " + DETAILS_ID_COLUMN;

        ConnectionClass connectionClass = new ConnectionClass();
        Connection con = connectionClass.CONN();

        try {
            PreparedStatement preparedQuery = con.prepareStatement(query);
            Log.e("Prepared", preparedQuery.toString());
            ResultSet rs = preparedQuery.executeQuery();

            while (rs.next()) {
                RoutineDetailObject ob = new RoutineDetailObject();

                ob.setDetailsId(rs.getInt(DETAILS_ID_COLUMN_NUM));
                ob.setRoutineId(rs.getInt(DETAILS_ROUTINE_ID_COLUMN_NUM));
                ob.setExerciseId(rs.getInt(DETAILS_EXERCISE_ID_COLUMN_NUM));
                ob.setSets(rs.getInt(DETAILS_SETS_COLUMN_NUM));
                ob.setReps(rs.getInt(DETAILS_REPS_COLUMN_NUM));
                ob.setDuration(rs.getInt(DETAILS_DURATION_COLUMN_NUM));

//                Log.e("Routine", ob.getDetailsId() + "\t loaded."); // TODO remove
                details.add(ob);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }










    //
    // Setters and Getters
    //
    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public int getRoutineId() {
        return routineId;
    }

    public void setRoutineId(int routineId) {
        this.routineId = routineId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public String getRoutineDesc() {
        return routineDesc;
    }

    public void setRoutineDesc(String routineDesc) {
        this.routineDesc = routineDesc;
    }

}
