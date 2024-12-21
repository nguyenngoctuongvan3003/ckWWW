package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.LopHoc;
import com.example.demo.entities.SinhVien;


public interface SinhVienServices {
	public List<SinhVien> findAll();
	public List<LopHoc> findAllLopHoc();
	public List<SinhVien> findSinhVienByMaLop(int maLop);
	public List<SinhVien> search(String keyword);
	public SinhVien save(SinhVien sinhVien);
	public LopHoc findLopHocById(int id);
	public SinhVien findByEmail(String email);
	public SinhVien findSinhVienById(int id);
	public void xoa(int id);
}
