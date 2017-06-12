package com.ebiz.computerdatabase.persistence.dao;

import com.ebiz.computerdatabase.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    protected DataSource dataSource;

    protected interface ConnectionConsumer<T> {
        T consume(JdbcTemplate jdbcTemplate) throws SQLException, DAOException;
    }

    protected <T> T usingConnection(ConnectionConsumer<T> consumer) throws DAOException {

        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);

        try {
            return consumer.consume(jdbcTemplateObject);
        }
        catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        }

    }
}
