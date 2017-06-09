package com.ebiz.computerdatabase.persistence.dao;

import com.ebiz.computerdatabase.exception.DTOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ebiz on 09/06/17.
 */
@Repository
public class IDao {

    @Autowired
    protected DataSource ds;
    protected interface ConnectionConsumer<T> {
        T consume(Connection conn) throws SQLException, DTOException;
    }

    protected <T> T usingConnection(ConnectionConsumer<T> consumer) throws DTOException {
        Connection connection = DataSourceUtils.getConnection(ds);

        try {
            return consumer.consume(connection);
        }
        catch (SQLException ex) {
            throw new DTOException(ex.getMessage());
        }
        finally {
            DataSourceUtils.releaseConnection(connection, ds);
        }
    }
}
