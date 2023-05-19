package me.paradis.factory.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import me.paradis.factory.Factory;
import me.paradis.factory.tools.Logging;

public class SQLManager implements HopperSqlManager{
    private Connection conn;
    private String url;

    public SQLManager(String url) throws Exception {
        this.url = url;
        setup();
    }

    public void setup() throws Exception {
        conn = DriverManager.getConnection(url);
        Logging.Info("connected to database");


        Statement statement = getConnection().createStatement();
        String createLinesTable = "CREATE TABLE IF NOT EXISTS lines (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "enabled INTEGER NOT NULL" +
                ");";
        statement.execute(createLinesTable);

        statement.execute(createHoppersTable);

        statement.close();
    }

    public Connection getConnection() throws SQLException {
        if (conn != null) return conn;

        //Try to connect to my MySQL database running locally
        Connection conn = DriverManager.getConnection(url);

        this.conn = conn;

        return conn;
    }
}

