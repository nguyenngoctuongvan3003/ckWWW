package com.example.demo.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SinhVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maSV;
	
	@NotNull(message = "buoc nhap tenSV")
	@NotEmpty(message = "khong nhap tenSV la khoang trang")
	@Size(max=50,message = "tenSV khong qua 50 ki tu")
	private String tenSV;
	
	@NotNull(message = "buoc nhap email")
	@NotEmpty(message = "khong nhap email la khoang trang")
	@Email(message = "nhap sai dinh dang email")
	private String email;
	
	@NotNull(message = "buoc nhap ngay sinh")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngaySinh;
	
	private String gioiTinh;
	
	@NotNull(message = "buoc nhap sdt")
	@NotEmpty(message = "khong nhap dienThoai la khoang trang")
	private String dienThoai;
	
	@ManyToOne
	@JoinColumn(name="maLop",referencedColumnName = "maLop")
	private LopHoc lopHoc;
	
	public SinhVien(String tenSV, String email, Date ngaySinh, String gioiTinh, String dienThoai, LopHoc lopHoc) {
		super();
		this.tenSV = tenSV;
		this.email = email;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.dienThoai = dienThoai;
		this.lopHoc = lopHoc;
	}
	
	
}
