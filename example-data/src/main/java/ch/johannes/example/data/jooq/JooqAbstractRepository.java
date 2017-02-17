package ch.johannes.example.data.jooq;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class JooqAbstractRepository {

    public static final SQLDialect SQL_DIALECT = SQLDialect.POSTGRES_9_5;

    private DataSource dataSource;

    @Resource(lookup = "java:/JohannesDS")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T execute(JOOQCallback<T> callback) {
        final Connection connection = DatabaseUtil.createConnection(dataSource);
        try {
            return execute(callback, connection);
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage(), e);
        } finally {
            DatabaseUtil.close(connection);
        }
    }

    protected <T> T execute(JOOQCallback<T> callback, Connection connection) throws SQLException {
        try (DSLContext dslContext = DSL.using(connection, SQL_DIALECT)) {
            return callback.execute(dslContext);
        } catch (DataAccessException e) {
            throw new RepositoryException(e.getMessage(), unwrapRootCause(e));
        }
    }

    public void executeWithoutResult(JOOQVoidCallback callback) {
        final Connection connection = DatabaseUtil.createConnection(dataSource);
        try {
            executeWithoutResult(callback, connection);
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage(), e);
        } finally {
            DatabaseUtil.close(connection);
        }
    }

    protected void executeWithoutResult(JOOQVoidCallback callback, Connection connection) throws SQLException {
        try (DSLContext dslContext = DSL.using(connection, SQL_DIALECT)) {
            callback.execute(dslContext);
        } catch (DataAccessException e) {
            throw new RepositoryException(e.getMessage(), unwrapRootCause(e));
        }
    }

    private Exception unwrapRootCause(Exception e) {
        if (e.getCause() instanceof SQLException && ((SQLException) e.getCause()).getNextException() != null) {
            return ((SQLException) e.getCause()).getNextException();
        }
        return e;
    }
}
