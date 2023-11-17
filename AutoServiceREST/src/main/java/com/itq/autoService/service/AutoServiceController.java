package com.itq.autoService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itq.autoService.business.AutoBusiness;
import com.itq.autoService.dto.Ack;
import com.itq.autoService.dto.Auto;

@RestController
public class AutoServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AutoServiceController.class);
	
	@Autowired
	private AutoBusiness autoBusiness;
	
	/* 
	@GetMapping("/auto")
	public List<Auto> getAll() {
		return (List<Auto>) autoBusiness.findAll();
	}
	*/

	@GetMapping("/auto")
	public Auto readAuto(@RequestParam(value =  "id") int id) {
		Auto auto = new Auto();
		auto = autoBusiness.getAuto(id);
		return auto;
	}

	@PostMapping(value = "/auto", consumes = "application/json", produces = "application/json")
	public Ack createAuto(@Valid @RequestBody Auto auto) {
		Ack ack = new Ack();
		if(autoBusiness.insertAuto(auto) == true) {
			LOGGER.info("Auto insertado correctamente" + auto.toString());
			ack.setCode(0);
			ack.setDescripcion("Auto insertado correctamente");
			return (ack);
		} else {
			LOGGER.error("Error al insertar el auto en la base de datos");
			ack.setCode(1);
			ack.setDescripcion("Error al insertar el auto en la base de datos");
			return (ack);
		}
	}

	@DeleteMapping("/auto")
	public Ack deleteAuto(@RequestParam(value =  "id") int id) {
		Ack ack = new Ack();
		if(autoBusiness.deleteAuto(id) == true) {
			LOGGER.info("Auto eliminado correctamente");
			ack.setCode(0);
			ack.setDescripcion("Auto eliminado correctamente");
			return (ack);
		} else {
			LOGGER.error("Error al eliminar el auto en la base de datos");
			ack.setCode(1);
			ack.setDescripcion("Error al eliminar el auto en la base de datos");
			return (ack);
		}
	}

	

}
