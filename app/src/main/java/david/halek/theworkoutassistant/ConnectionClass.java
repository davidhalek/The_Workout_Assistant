package david.halek.theworkoutassistant;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}