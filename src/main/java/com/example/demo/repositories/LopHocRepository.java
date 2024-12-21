package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.LopHoc;
@Repository
public interface LopHocRepository extends JpaRepository<LopHoc, Integer>{

}
