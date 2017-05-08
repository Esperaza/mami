package com.mami.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mami.bean.Doctor;
import com.mami.mapper.DoctorMapper;

@Service
public class DoctorService {
	@Autowired
	private DoctorMapper doctorMapper;
	public List<Doctor> getAllDoctor(){
		return doctorMapper.getAllDoctor();
	}
}
