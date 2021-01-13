package com.bunker.authservice.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bunker.authservice.domain.AuthClientDetails;

@Repository
public interface AuthClientRepository extends JpaRepository<AuthClientDetails, String> {
    @Cacheable("clients")
    Optional<AuthClientDetails> findByClientId(String clientId);
}
