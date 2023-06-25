package org.example.Models;

import javax.persistence.*;

@Entity
@Table(name = "contract_requests")
public class ContractRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "requester_name")
    private String requesterName;

    @Column(name = "request_description")
    private String requestDescription;

    @Column(name = "status")
    private String status;

    public ContractRequest() {

    }

    public ContractRequest(String requesterName, String requestDescription, String status) {
        this.requesterName = requesterName;
        this.requestDescription = requestDescription;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }
}

