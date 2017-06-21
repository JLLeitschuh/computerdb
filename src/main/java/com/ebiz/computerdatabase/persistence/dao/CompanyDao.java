package com.ebiz.computerdatabase.persistence.dao;

import com.ebiz.computerdatabase.entities.Company;

import org.springframework.data.jpa.repository.JpaRepository;


import javax.persistence.CascadeType;
import javax.persistence.OneToMany;




public interface  CompanyDao extends JpaRepository<Company,Integer>{

	@OneToMany( cascade ={CascadeType.REMOVE })
	void deleteById(Integer id);

}
