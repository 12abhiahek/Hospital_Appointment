package com.hospital.hospital.repository;

import com.hospital.hospital.entity.DoctorList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorListRepository extends JpaRepository<DoctorList,Long> {

}
