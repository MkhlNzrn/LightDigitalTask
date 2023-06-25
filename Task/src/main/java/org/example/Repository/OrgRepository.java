package org.example.Repository;

import org.example.Models.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OrgRepository extends JpaRepository<Org, Long> {
    Optional<Org> findById(Long id);
}


