package org.example.Services;

import org.example.Models.ContractRequest;
import org.example.Repository.ContractRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractRequestService {

    private final ContractRequestRepository contractRequestRepository;

    @Autowired
    public ContractRequestService(ContractRequestRepository contractRequestRepository) {
        this.contractRequestRepository = contractRequestRepository;
    }

    public void submitContractRequest(ContractRequest request) {
        contractRequestRepository.save(request);
    }

    public List<ContractRequest> getAllContractRequests() {
        return contractRequestRepository.findAll();
    }

    public ContractRequest getContractRequestById(Long id) {
        Optional<ContractRequest> request = contractRequestRepository.findById(id);
        return request.orElse(null);
    }

    public void deleteContractRequest(Long id) {
        contractRequestRepository.deleteById(id);
    }
}

