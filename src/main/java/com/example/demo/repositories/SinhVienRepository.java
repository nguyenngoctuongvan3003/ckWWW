package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.SinhVien;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Integer>{
	public List<SinhVien> findByLopHocMaLop(int maLop);
	public SinhVien findByEmail(String email);
	@Query(value = "Select s from SinhVien s where s.lopHoc.tenLop like %:keyword% or s.tenSV like %:keyword%")
	List<SinhVien>search(@Param("keyword")String keyword);
}
