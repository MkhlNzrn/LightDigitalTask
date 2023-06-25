package org.example.Repository;

import org.example.Models.ContractRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRequestRepository extends JpaRepository<ContractRequest, Long> {
}

