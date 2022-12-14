package me.kornyshev.WebCalculator.utils;

import lombok.SneakyThrows;
import me.kornyshev.WebCalculator.properties.Properties;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DbUtils {

    private static DbUtils instance = null;
    private final DataSource dataSource;

    private DbUtils() {
        this.dataSource = createDataSource();
    }

    private DataSource createDataSource() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUser(Properties.DB_USER);
        ds.setPassword(Properties.DB_PASSWORD);
        ds.setServerName(Properties.DB_SERVER_NAME);
        ds.setPortNumber(Integer.parseInt(Properties.DB_SERVER_PORT));
        ds.setDatabaseName(Properties.DB_SERVER_DATABASE);
        return ds;
    }

    public static DbUtils getInstance() {
        if (instance == null) {
            instance = new DbUtils();
        }
        return instance;
    }

    @SneakyThrows
    public <T> T getQueryResults(String query, ResultSetHandler<T> handler, Object... params) {
        QueryRunner run = new QueryRunner(dataSource);
        return run.query(query, handler, params);
    }

    @SneakyThrows
    public int executeUpdate(String query, Object... params) {
        QueryRunner run = new QueryRunner(dataSource);
        return run.update(query, params);
    }

}
