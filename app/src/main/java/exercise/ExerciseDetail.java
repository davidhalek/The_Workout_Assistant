package exercise;

public class ExerciseDetail {
    int exerciseId = -999;
    String exerciseName = "";
    String exerciseDesc = "";
    String createDate = "";
    String exerciseInstructions = "";
    String videoId = "";

    public ExerciseDetail(int exerciseId, String exerciseName, String exerciseDesc, String createDate) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.exerciseDesc = exerciseDesc;
        this.createDate = createDate;
    }

    public ExerciseDetail(int id) {
        this.exerciseId = id;
        this.exerciseName = "";
        this.exerciseDesc = "";
        this.createDate = "";
    }

    public int getExerciseId() {
        return exerciseId;
    }
    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDesc() {
        return exerciseDesc;
    }

    public void setExerciseDesc(String exerciseDesc) {
        this.exerciseDesc = exerciseDesc;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getExerciseInstructions() {
        return exerciseInstructions;
    }

    public void setExerciseInstructions(String exerciseInstructions) {
        this.exerciseInstructions = exerciseInstructions;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
