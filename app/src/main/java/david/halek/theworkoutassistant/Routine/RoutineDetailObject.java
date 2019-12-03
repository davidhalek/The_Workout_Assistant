package david.halek.theworkoutassistant.Routine;

import android.util.Log;

import java.sql.ResultSet;

import david.halek.theworkoutassistant.R;

public class RoutineDetailObject {
    public static final String DETAILS_TABLE_NAME = " [WorkoutAssistant].[dbo].[ExerciseRoutineDetails] ";

    public static final String DETAILS_ID_COLUMN = " [ExerciseRoutineDetailsID ";
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
