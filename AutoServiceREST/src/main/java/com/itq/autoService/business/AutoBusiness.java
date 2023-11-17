package com.itq.autoService.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itq.autoService.dao.AutoDao;
import com.itq.autoService.dto.Auto;
import com.itq.autoService.service.AutoServiceController;

@Repository
public class AutoBusiness {
	
	@Autowired 
	private AutoDao autoDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AutoServiceController.class);
	
	public boolean insertAuto(Auto auto) {
		try{
			if(autoDao.insertAuto(auto) != 1){
				throw new Exception("Error al insertar el auto en la base de datos");
			} else {
				LOGGER.info("Auto insertado correctamente" + auto.toString());
				return true;
			}
		}catch(Exception e){
			LOGGER.error("Error al insertar el auto en la base de datos", e);
			return false;
		}
	}

	public List<Auto> findAll() {
		return (List<Auto>) autoDao.findAll();
	}

	public Auto getAuto(int id) {
		LOGGER.info("Auto obtenido correctamente" + id);
		return autoDao.getAuto(id);
	}

	public boolean deleteAuto(int id) {
		try {
	        int deletionResult = autoDao.deleteAuto(id);

	        if (deletionResult != 1) {
	            LOGGER.warn("Auto with ID {} not found in the database. Unable to delete.", id);
	            return false; // Indicate that the deletion was not successful
	        } else {
	            LOGGER.info("Auto deleted successfully: {}", id);
	            return true; // Indicate successful deletion
	        }
	    } catch (Exception e) {
	        LOGGER.error("Error deleting auto with ID {} from the database", id, e);
	        return false; // Indicate that an error occurred during deletion
	    }
	}

}
