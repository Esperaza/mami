package com.mami.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mami.bean.Doctor;
import com.mami.service.DoctorService;
import com.mami.util.ResultMap;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private DoctorService doctorService;
	@RequestMapping(value = "/getAllDoctor" , method = RequestMethod.GET)
	@ResponseBody
	public ResultMap getAllDoctor(){	
		List<Doctor> doctors = doctorService.getAllDoctor();
		return ResultMap.success(doctors);
	}
	

	@RequestMapping(value = "/test" , method = RequestMethod.GET)
	@ResponseBody
	public ResultMap test(){	
		return ResultMap.success("hello word");
	}
}
