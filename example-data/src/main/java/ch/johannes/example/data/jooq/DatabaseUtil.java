package ch.johannes.example.data.jooq;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class DatabaseUtil {

    private DatabaseUtil() {
        // hidden
    }

    public static Connection createConnection(DataSource dataSource) {
        try {
            final Connection connection = dataSource.getConnection();

            return connection;
        } catch (SQLException e) {
            throw new RepositoryException("Failed to obtain a connection from the DataSource", e);
        }
    }

    public static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RepositoryException("Failed to rollback transaction", e);
        }
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RepositoryException("Failed to close connection", e);
        }
    }
}
