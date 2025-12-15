import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDB {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:medicare.db");

            Statement st = con.createStatement();

            st.executeUpdate("CREATE TABLE IF NOT EXISTS test_table (id INTEGER PRIMARY KEY);");

            System.out.println("REAL medicare.db created successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
