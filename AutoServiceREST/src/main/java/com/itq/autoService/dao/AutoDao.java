package com.itq.autoService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.itq.autoService.dto.Auto;

@Repository
public class AutoDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertAuto(final Auto autoDto) {
        StringBuffer sql = new StringBuffer("");
        sql.append("INSERT INTO auto (idauto, marca, modelo, color, fecha) ");
        sql.append("VALUES (?,?,?,?,?)");
        final String query = sql.toString();

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        //System.out.println("keyHolder: " + keyHolder.getKey().intValue());
        
        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, autoDto.getIdAuto());
                    ps.setString(2, autoDto.getMarca());
                    ps.setInt(3, autoDto.getModelo());
                    ps.setString(4, autoDto.getColor());
                    ps.setString(5, null);
                    return ps;
                }
            }, keyHolder);

            //return keyHolder.getKey().intValue();
            return 1;
        } catch (Exception e) {
            LOGGER.error("Error al insertar el auto en la base de datos", e);
            return 0;
        }
    }
}
