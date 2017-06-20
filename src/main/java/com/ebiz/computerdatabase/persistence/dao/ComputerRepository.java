package com.ebiz.computerdatabase.persistence.dao;

import com.ebiz.computerdatabase.entities.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ebiz on 20/06/17.
 */
public interface ComputerRepository extends JpaRepository<Computer,Integer>{

    Page<Computer> findComputerByNameLikeOrCompanyName(String name, String companyName, Pageable page);

    Page<Computer> findComputerByNameLikeOrCompanyName(Pageable page);
}
