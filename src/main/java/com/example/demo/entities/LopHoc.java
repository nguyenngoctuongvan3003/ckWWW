package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LopHoc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maLop;
	private String tenLop;
	
	@OneToMany(mappedBy = "lopHoc")
	private List<SinhVien> dsSinhVien;
	
	public LopHoc(String tenLop) {
		super();
		this.tenLop = tenLop;
	}
	
	
}
