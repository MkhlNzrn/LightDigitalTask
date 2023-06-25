package org.example.Controllers;

import org.example.Models.ContractRequest;
import org.example.Repository.ContractRequestRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/adminRequestPanel")
public class AdminRequestController {

    private final ContractRequestRepository contractRequestRepository;


    public AdminRequestController(ContractRequestRepository contractRequestRepository) {
        this.contractRequestRepository = contractRequestRepository;
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PRINCIPAL')")
    @GetMapping
    public List<ContractRequest> findAllContractRequests() {
        return contractRequestRepository.findAll();
    }

    @GetMapping("/{id}")
    public ContractRequest findContractRequestById(@PathVariable Long id) {
        Optional<ContractRequest> contractRequest = contractRequestRepository.findById(id);
        return contractRequest.orElse(null);
    }

    @PostMapping
    public String createContractRequest(@RequestBody ContractRequest contractRequest) {
        if (!contractRequest.getStatus().equals("ok")) {
            contractRequestRepository.save(contractRequest);
            return "Заявка успешно подана";
        }
        else return "Вы не можете сам одобрить свою заявку, дождитесь ответа организации";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PRINCIPAL')")
    @PutMapping("/{id}")
    public ContractRequest updateContractRequest(@PathVariable Long id, @RequestBody ContractRequest updatedContractRequest) {
        Optional<ContractRequest> contractRequest = contractRequestRepository.findById(id);
        if (contractRequest.isPresent()) {
            ContractRequest existingContractRequest = contractRequest.get();
            existingContractRequest.setRequesterName(updatedContractRequest.getRequesterName());
            existingContractRequest.setRequestDescription(updatedContractRequest.getRequestDescription());
            existingContractRequest.setStatus(updatedContractRequest.getStatus());
            return contractRequestRepository.save(existingContractRequest);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteContractRequest(@PathVariable Long id) {
        contractRequestRepository.deleteById(id);
    }
}

