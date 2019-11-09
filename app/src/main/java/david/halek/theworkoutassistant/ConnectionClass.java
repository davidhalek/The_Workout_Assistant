package david.halek.theworkoutassistant;

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
import java.util.List;

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

    public static ArrayList<ExerciseListElement> getExerciseList(String searchFor, int sortBy) {
        ArrayList<ExerciseListElement> exerciseList = new ArrayList<ExerciseListElement>();
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
                ExerciseListElement ob = new ExerciseListElement();
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

    public static ArrayList<ExerciseListElement> getExerciseList() {
        return getExerciseList("", 0);
    }
}