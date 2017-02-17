package ch.johannes.example.data.jooq;

import org.jooq.SQLDialect;

import javax.annotation.PostConstruct;
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

    private JooqExecutor jooqExecutor;

    public void setJooqExecutor(JooqExecutor jooqExecutor) {
        this.jooqExecutor = jooqExecutor;
    }

    @PostConstruct
    public void initializeJooqExectutor() {
        this.setJooqExecutor(new JooqDefaultExecutor(SQL_DIALECT, dataSource));
    }

    public <T> T execute(JOOQCallback<T> callback) {
        return this.jooqExecutor.execute(callback);
    }

    protected <T> T execute(JOOQCallback<T> callback, Connection connection) throws SQLException {
        return this.jooqExecutor.execute(callback, connection);
    }

    public void executeWithoutResult(JOOQVoidCallback callback) {
        this.jooqExecutor.executeWithoutResult(callback);
    }

    protected void executeWithoutResult(JOOQVoidCallback callback, Connection connection) throws SQLException {
        this.jooqExecutor.executeWithoutResult(callback, connection);
    }
}
