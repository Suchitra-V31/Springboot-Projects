package com.example.NetworkUtilization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class NWUtilizationController {
	@Autowired
	private NWUtilizationService utilizationService;
	
	@GetMapping("/city")
	public String getCity() {
		return utilizationService.getCity();
	}
	@GetMapping("/type")
	public String getType() {
		return utilizationService.getType();
	}
	@GetMapping("/value")
	public String getValue(@RequestParam(name="cityCode") int cityCode,@RequestParam(name="typeCode")
	int typeCode) {
		return utilizationService.getValue(cityCode,typeCode);
	}
	@GetMapping("/utilization")
	public String getUtilization(@RequestParam(name="city_code") int city_code, @RequestParam(name="type_code") int type_code
			,@RequestParam(name="value") String value) {
		return utilizationService.getUtilization(city_code,type_code,value);
	}
	

}
