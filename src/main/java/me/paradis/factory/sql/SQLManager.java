package me.paradis.factory.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLManager {
    private Connection conn;
    private String url;

    public SQLManager(String url) throws Exception {
        this.url = url;
        setup();
    }

    public void setup() throws Exception {
        conn = DriverManager.getConnection(url);
        System.out.println("Connected to the database!");


        Statement statement = getConnection().createStatement();
        String createLinesTable = "CREATE TABLE IF NOT EXISTS lines (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "enabled INTEGER NOT NULL" +
                ");";
        statement.execute(createLinesTable);

        String createHoppersTable = "CREATE TABLE IF NOT EXISTS hoppers (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "target_x INTEGER," +
                "target_y INTEGER," +
                "target_z INTEGER," +
                "target_world VARCHAR," +
                "target_id INTEGER," +
                "target_name VARCHAR," +
                "location_x INTEGER NOT NULL," +
                "location_y INTEGER NOT NULL," +
                "location_z INTEGER NOT NULL," +
                "location_world TEXT NOT NULL," +
                "enabled INTEGER NOT NULL," +
                "line_id INTEGER," +
                "FOREIGN KEY(line_id) REFERENCES lines(id)" +
                ");";
        statement.execute(createHoppersTable);

        statement.close();
    }

    /*
    *  This method will save a new hopper in the database
    * @return true if the hopper was saved successfully
     */
    public boolean saveNewHopper(){
        return true;
    }

    public Connection getConnection() throws SQLException {
        if (conn != null) return conn;

        //Try to connect to my MySQL database running locally
        Connection conn = DriverManager.getConnection(url);

        this.conn = conn;

        return conn;
    }
}

