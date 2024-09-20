package model.db.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import model.db.Database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class GradesDatabase implements Database {
    private DataSource dataSource;

    public GradesDatabase() {
        dataSource = initDataSource();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private static DataSource initDataSource() {
        String url = "jdbc:mysql://localhost:3306/grades";
        String name = "webapp";
        String password = "password";

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(name);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "50");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }
}

