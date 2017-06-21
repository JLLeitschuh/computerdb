package com.ebiz.computerdatabase.persistence.dao;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.entities.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ebiz on 20/06/17.
 */
public interface ComputerDao extends JpaRepository<Computer,Integer>{


    Page<Computer> findComputerByNameContainingOrCompanyNameContaining(String name, String companyName, Pageable page);

    void deleteByIdIn(List<Integer> ids);

    Computer findComputerById(Integer id);




}
