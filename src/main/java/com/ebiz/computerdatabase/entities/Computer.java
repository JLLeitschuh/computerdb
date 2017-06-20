package com.ebiz.computerdatabase.entities;

import com.ebiz.computerdatabase.model.CompanyEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ebiz on 20/06/17.
 */
@Entity
@Table(name ="computer")
public class Computer implements Serializable {

    @Id
    private int id;

    @Column(name="name")
    private String name;


    @Column(name="introduced")
    private String introduced;


    @Column(name="discontinued")
    private String discontinued;


    @ManyToOne
    Company company;



    //private String companyName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
