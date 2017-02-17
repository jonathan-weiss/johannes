package ch.johannes.example.data.jooq;

import java.sql.Connection;
import java.sql.SQLException;

public interface JooqExecutor {

    <T> T execute(JOOQCallback<T> callback);

    <T> T execute(JOOQCallback<T> callback, Connection connection) throws SQLException;

    void executeWithoutResult(JOOQVoidCallback callback);

    void executeWithoutResult(JOOQVoidCallback callback, Connection connection) throws SQLException;

}
