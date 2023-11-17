package com.itq.autoService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

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
        
        try {
        	GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, autoDto.getIdAuto());
                    ps.setString(2, autoDto.getMarca());
                    ps.setInt(3, autoDto.getModelo());
                    ps.setString(4, autoDto.getColor());
                    ps.setString(5, null);
                    //System.out.println("keyHolder: " + keyHolder.getKey().intValue());
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
    
    public List<Auto> findAll() {
		return jdbcTemplate.query("SELECT * FROM auto", new BeanPropertyRowMapper<Auto>(Auto.class));
	}	

    public class AutoRowMapper implements RowMapper<Auto> {
        @Override
        public Auto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Auto auto = new Auto();
            auto.setIdAuto(rs.getInt("idauto"));
            auto.setMarca(rs.getString("marca"));
            auto.setModelo(rs.getInt("modelo"));
            auto.setColor(rs.getString("color"));
            return auto;
        }
    }

    @SuppressWarnings("deprecation")
	public Auto getAuto(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM auto WHERE idauto=?", new Object[]{id}, new AutoRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Auto with ID {} not found in the database", id);
            return null;
        } catch (DataAccessException e) {
            LOGGER.error("Error accessing database to get auto with ID {}", id, e);
            throw e;
        }
    }

    public int deleteAuto(int id) {
    	
        try {
            Auto existingAuto = getAuto(id);

            if (existingAuto == null) {
                LOGGER.warn("Auto with ID {} not found in the database. Unable to delete.", id);
                return 0; // Indicate that the deletion was not successful
            }

            String sql = "DELETE FROM auto WHERE idauto = ?";
            jdbcTemplate.update(sql, id);
            return 1; // Indicate successful deletion

        } catch (Exception e) {
            LOGGER.error("Error deleting auto with ID {} from the database", id, e);
            return 0; // Indicate that an error occurred during deletion
        }

    }

}
