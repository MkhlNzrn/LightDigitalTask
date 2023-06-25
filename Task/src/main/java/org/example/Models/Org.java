package org.example.Models;

import javax.persistence.*;

@Entity
@Table(name = "orgs")
public class Org {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orgName;
    private String inn;

    public Org(String orgName, String inn) {
        this.orgName = orgName;
        this.inn = inn;
    }

    public Org() {

    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

