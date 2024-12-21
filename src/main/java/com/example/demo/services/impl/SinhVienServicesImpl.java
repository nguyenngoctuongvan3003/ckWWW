package com.example.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.LopHoc;
import com.example.demo.entities.SinhVien;
import com.example.demo.repositories.LopHocRepository;
import com.example.demo.repositories.SinhVienRepository;
import com.example.demo.services.SinhVienServices;

@Service
public class SinhVienServicesImpl implements SinhVienServices{
	@Autowired
	public SinhVienRepository sinhVienRepository;
	@Autowired
	public LopHocRepository lopHocRepository;
	@Override
	public List<SinhVien> findAll() {
		// TODO Auto-generated method stub
		return sinhVienRepository.findAll();
	}

	@Override
	public List<SinhVien> findSinhVienByMaLop(int maLop) {
		// TODO Auto-generated method stub
		return sinhVienRepository.findByLopHocMaLop(maLop);
	}

	@Override
	public List<LopHoc> findAllLopHoc() {
		// TODO Auto-generated method stub
		return lopHocRepository.findAll();
	}

	@Override
	public List<SinhVien> search(String keyword) {
		// TODO Auto-generated method stub
		return sinhVienRepository.search(keyword);
	}

	@Override
	public SinhVien save(SinhVien sinhVien) {
		// TODO Auto-generated method stub
		return sinhVienRepository.save(sinhVien);
	}

	@Override
	public LopHoc findLopHocById(int id) {
		// TODO Auto-generated method stub
		LopHoc lopHoc=new LopHoc();
		lopHoc=lopHocRepository.findById(id).get();
		return lopHoc;
	}

	@Override
	public SinhVien findByEmail(String email) {
		// TODO Auto-generated method stub
		return sinhVienRepository.findByEmail(email);
	}

	@Override
	public SinhVien findSinhVienById(int id) {
		// TODO Auto-generated method stub
		SinhVien sinhVien=sinhVienRepository.findById(id).get();
		return sinhVien;
	}
	
	@Override
	public void xoa(int id) {
		// TODO Auto-generated method stub
		sinhVienRepository.delete(findSinhVienById(id));
	}

}
