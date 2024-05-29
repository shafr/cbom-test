import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlInjectionExample {
    public static void main(String[] args) {
        String userName = args[0];
        String password = args[1];

        try {
            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/mydb";
            Connection conn = DriverManager.getConnection(url, "root", "password");

            // Construct the SQL query with user input
            String query = "SELECT * FROM users WHERE username = '" + userName + "' AND password = '" + password + "'";

            // Execute the query
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            if (rs.next()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password.");
            }

            // Close the resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}