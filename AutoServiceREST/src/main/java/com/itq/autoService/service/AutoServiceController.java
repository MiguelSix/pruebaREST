package com.itq.autoService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itq.autoService.dao.AutoDao;
import com.itq.autoService.dto.Ack;
import com.itq.autoService.dto.Auto;

@RestController
public class AutoServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AutoServiceController.class);
	
	@Autowired
	private AutoDao autoDao;
	
	@GetMapping("/auto")
	public Auto readAuto(@RequestParam(value =  "nombre") String nombre) {
		Auto auto = new Auto();
		auto.setColor("rojo - " + nombre);
		auto.setMarca("VW");
		auto.setModelo(2010);
		return auto;
	}

	@PostMapping(value = "/auto", consumes = "application/json", produces = "application/json")
	public Ack createAuto(@Valid @RequestBody Auto auto) {

		try{
			
			if(autoDao.insertAuto(auto) != 1){
				throw new Exception("Error al insertar el auto en la base de datos");
			} else {
				LOGGER.info("Auto insertado correctamente" + auto.toString());
				Ack ack = new Ack();
				ack.setCode(0);
				ack.setDescripcion("Auto insertado correctamente");
				return (ack);
			}
		}catch(Exception e){
			LOGGER.error("Error al insertar el auto en la base de datos", e);
			Ack ack = new Ack();
			ack.setCode(1);
			ack.setDescripcion("Error al insertar el auto en la base de datos");
			return (ack);
		}
	}

}
