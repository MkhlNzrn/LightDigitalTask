package org.example.Services;

import org.example.Repository.ContractRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractRequestService {

    private final ContractRequestRepository contractRequestRepository;


    public ContractRequestService(ContractRequestRepository contractRequestRepository) {
        this.contractRequestRepository = contractRequestRepository;
    }

}

