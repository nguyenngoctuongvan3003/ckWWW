package com.example.demo.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.SinhVien;
import com.example.demo.services.SinhVienServices;

import jakarta.validation.Valid;

@Controller
public class SinhVienController {
	@Autowired
	private SinhVienServices sinhVienServices;
	
	@GetMapping({"","sinhvien"})
	public String showIndex(Model model) {
		model.addAttribute("dsSinhVien", sinhVienServices.findAll());
		model.addAttribute("dsLopHoc", sinhVienServices.findAllLopHoc());
		return "index";
	}
	
	@GetMapping("/sinhvien/filter")
	public String showFilter(Model model, @RequestParam int maLop) {
		model.addAttribute("dsSinhVien", sinhVienServices.findSinhVienByMaLop(maLop));
		model.addAttribute("dsLopHoc", sinhVienServices.findAllLopHoc());
		return "index";
	}
	
	@GetMapping("/sinhvien/search")
	public String showSearch(Model model, @RequestParam String keyword) {
		model.addAttribute("dsSinhVien", sinhVienServices.search(keyword));
		model.addAttribute("dsLopHoc", sinhVienServices.findAllLopHoc());
		return "index";
	}
	
	@GetMapping("/sinhvien/add")
	public String showAdd(Model model) {
		SinhVien sinhVien=new SinhVien();
		model.addAttribute("sinhVien", sinhVien);
		model.addAttribute("dsLopHoc", sinhVienServices.findAllLopHoc());
		return "add";
	}
	
	@PostMapping("/sinhvien/add")
	public String addSV(Model model,@Valid @ModelAttribute SinhVien sinhVien, BindingResult bindingResult) {
		SinhVien svTheoEmail= sinhVienServices.findByEmail(sinhVien.getEmail());
		if(bindingResult.hasErrors()) {
			model.addAttribute("sinhVien", sinhVien);
			model.addAttribute("dsLopHoc", sinhVienServices.findAllLopHoc());
			return "add";
		}
		if(svTheoEmail!=null) {
			bindingResult.rejectValue("email", null, "email trung");
			model.addAttribute("sinhVien", sinhVien);
			model.addAttribute("dsLopHoc", sinhVienServices.findAllLopHoc());
			return "add";
		}
		if( !tren18t(sinhVien.getNgaySinh())) {
			bindingResult.rejectValue("ngaySinh", null, "phai tu 18t");
			model.addAttribute("sinhVien", sinhVien);
			model.addAttribute("dsLopHoc", sinhVienServices.findAllLopHoc());
			return "add";
		}
		sinhVien.setLopHoc(sinhVienServices.findLopHocById(sinhVien.getLopHoc().getMaLop()));
		sinhVienServices.save(sinhVien);
		return "redirect:/sinhvien";
	}
	
	@GetMapping("/sinhvien/edit/{maSV}")
	public String showEdit(Model model, @PathVariable int maSV) {
		SinhVien sinhVien=sinhVienServices.findSinhVienById(maSV);
		model.addAttribute("dsLopHoc", sinhVienServices.findAllLopHoc());
		model.addAttribute("sinhVien", sinhVien);
		return "update";
	}
	
	@PostMapping("/sinhvien/edit/{maSV}")
	public String edit(Model model, @PathVariable int maSV,@Valid @ModelAttribute SinhVien sinhVien,BindingResult bindingResult) {
		SinhVien svById= sinhVienServices.findSinhVienById(sinhVien.getMaSV());
		SinhVien svTheoEmail= sinhVienServices.findByEmail(sinhVien.getEmail());
		if(bindingResult.hasErrors()) {
			model.addAttribute("sinhVien", sinhVien);
			model.addAttribute("dsLopHoc", sinhVienServices.findAllLopHoc());
			return "update";
		}
		if( !sinhVien.getEmail().equals(svById.getEmail()) && svTheoEmail!=null) {
			bindingResult.rejectValue("email", null, "email trung");
			model.addAttribute("sinhVien", sinhVien);
			model.addAttribute("dsLopHoc", sinhVienServices.findAllLopHoc());
			return "update";
		}
		if( !tren18t(sinhVien.getNgaySinh())) {
			bindingResult.rejectValue("ngaySinh", null, "phai tu 18t");
			model.addAttribute("sinhVien", sinhVien);
			model.addAttribute("dsLopHoc", sinhVienServices.findAllLopHoc());
			return "update";
		}
		sinhVien.setLopHoc(sinhVienServices.findLopHocById(sinhVien.getLopHoc().getMaLop()));
		sinhVienServices.save(sinhVien);
		return "redirect:/sinhvien";
	}
	
	@GetMapping("/sinhvien/delete/{maSV}")
	public String delete(@PathVariable int maSV) {
		sinhVienServices.xoa(maSV);
		return "redirect:/sinhvien";
	}
	
	private boolean tren18t(Date ngaySinh) {
		Calendar dob= Calendar.getInstance();
		dob.setTime(ngaySinh);
		
		Calendar now= Calendar.getInstance();
		int age= now.get(Calendar.YEAR)-dob.get(Calendar.YEAR);
		return age>=18;
		
	}

}
