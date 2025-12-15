package dao;

import java.io.File;
import java.sql.*;

public class DBConnection {

    private static final String DB_NAME = "medicare.db";
    private static final String URL = "jdbc:sqlite:" + DB_NAME;

    static {
        try {
            Class.forName("org.sqlite.JDBC");

            File db = new File(DB_NAME);

            if (db.exists() && !db.isFile()) {
                deleteInvalid(db);
            }

            if (!db.exists()) {
                db.createNewFile();
                System.out.println("Created medicare.db");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void deleteInvalid(File f) {
        if (f.isDirectory()) {
            for (File x : f.listFiles()) deleteInvalid(x);
        }
        f.delete();
    }
}
