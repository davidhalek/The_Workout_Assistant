package david.halek.theworkoutassistant;

public class ExerciseListElement {
    public int exerciseId = -1;
    public String exerciseName = "";

    // Getters & Setters
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}
