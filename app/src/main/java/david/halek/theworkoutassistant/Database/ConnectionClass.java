package david.halek.theworkoutassistant.Database;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exercise.ExerciseDetail;
import exercise.ExerciseObject;

public class ConnectionClass {
    String ip = "exercise.ckiipompt8fh.us-east-2.rds.amazonaws.com";
    String db = "WorkoutAssistant";
    String port = "1433";
    String username = "bigmoney";
    String password = "andnowhammies";
    String cl = "net.sourceforge.jtds.jdbc.Driver";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Log.e("ERROR", "Attempting Connection");
            Class.forName(cl);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + username + ";password=" + password + ";";
            conn = DriverManager.getConnection(ConnURL);
            System.out.println("Connection Successful.");
        } catch (SQLException se) {
            Log.e("-->SQLException-->", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("-->ClassNotFound-->", e.getMessage());
        } catch (Exception e) {
            Log.e("-->Generic-->", e.getMessage());
        }
        return conn;
    }

    public static String getFirstName(int id) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection con = connectionClass.CONN();
        String query = "select FirstName from [User] where UserId='" + id + "'";
        String firstName = "";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                firstName = rs.getString("FirstName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Log.e("-->", "getFirstName returning: " + firstName);
        return firstName;
    }

    public static ArrayList<ExerciseObject> getExerciseList(String searchFor, int sortBy) {
        ArrayList<ExerciseObject> exerciseList = new ArrayList<ExerciseObject>();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection con = connectionClass.CONN();

        // Other sorts just concatenate here
        String query = "SELECT [ExerciseID], [ExerciseName] FROM [WorkoutAssistant].[dbo].[Exercise] WHERE [ExerciseName] LIKE ? ORDER BY [ExerciseName];";
        searchFor = "%" + searchFor + "%";


        try {
            PreparedStatement preparedQuery = con.prepareStatement( query);
            preparedQuery.setString(1, searchFor);
            Log.e("PREPARED", preparedQuery.toString());
            ResultSet rs = preparedQuery.executeQuery();

            while (rs.next()) {
                ExerciseObject ob = new ExerciseObject();
                ob.setExerciseId(rs.getInt(1));
                ob.setExerciseName(rs.getString(2));
                exerciseList.add (ob);
            }

        } catch (SQLException e) {
            Log.e("CONN", "Error in getExerciseList");
            e.printStackTrace();
        }

        return exerciseList;
    }

    public static ArrayList<ExerciseObject> getExerciseList() {
        return getExerciseList("", 0);
    }

    public static ExerciseDetail getExerciseDetail(int id) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection con = connectionClass.CONN();
        String query = "select * from [Exercise] where ExerciseID='" + id + "'";
        ExerciseDetail exerciseDetail = new ExerciseDetail(id);

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                exerciseDetail.setExerciseDesc(rs.getString("ExerciseDesc"));
                exerciseDetail.setExerciseName(rs.getString("ExerciseName"));
                exerciseDetail.setCreateDate(rs.getString("CreateDate"));
                exerciseDetail.setExerciseInstructions(rs.getString("Instructions"));
                exerciseDetail.setVideoId(rs.getString("VideoLink"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Log.e("ConnectionClass", "ExerciseName is " + exerciseDetail.getExerciseName());
        return exerciseDetail;
    }


    public static void updateExerciseDetail(int id, String column, String value) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection con = connectionClass.CONN();

        ExerciseDetail exerciseDetail = new ExerciseDetail(id);
        String query = "UPDATE [Exercise] SET [?] = '?' WHERE [ExerciseID] = " + id ;
        String badQuery = "UPDATE [Exercise] SET [" + column + "] = ? WHERE [ExerciseID] = " + id ;
//        searchFor = "%" + searchFor + "%";
        try {
            Log.e("PREPARED-", badQuery);
            PreparedStatement preparedQuery = con.prepareStatement( badQuery);
            preparedQuery.setString( 1, value);
            int rs = preparedQuery.executeUpdate();
            Log.e ("PREPARED", "rs is: "+ (rs >= 1 ? "true" : "false"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Log.e("ConnectionClass", "ID is " + id +
                ", ColumnName is " + column + ", value is " + value);
//        return exerciseDetail;

    }

    public static boolean checkIfValidExerciseName(String name) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection con = connectionClass.CONN();
        String query = "SELECT * FROM [Exercise] WHERE [ExerciseName] = ?";
        ResultSet rs = null;
        try {
            PreparedStatement preparedQuery = con.prepareStatement(query);
            preparedQuery.setString( 1, name);
            Log.e("PREPARED", preparedQuery.toString());
            rs = preparedQuery.executeQuery();
            if (!rs.isBeforeFirst() ) {
                Log.e("Result", "Name is "+name+", ResultSet is: false");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.e("Result", "Name is "+name+", ResultSet is: true");
        return false;

    }

    public static boolean addExercise(String name, String desc, String instruct, String video) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection con = connectionClass.CONN();

        String query = "INSERT INTO [Exercise] ([ExerciseName], [ExerciseDesc], [Instructions], [VideoLink]) VALUES (?, ?, ?, ?)";
        int result = -1;

        try {
            PreparedStatement preparedQuery = con.prepareStatement(query);
            preparedQuery.setString( 1, name);
            preparedQuery.setString( 2, desc);
            preparedQuery.setString( 3, instruct);
            preparedQuery.setString( 4, video);
            Log.e("PREPARED", preparedQuery.toString());
            result = preparedQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Log.e("ConnectionClass", "Update result for "+name+" is: "+result);
        return (result > 0 ? true : false);
    }


}