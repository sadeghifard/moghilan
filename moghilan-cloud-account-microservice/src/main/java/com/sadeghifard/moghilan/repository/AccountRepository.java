package com.sadeghifard.moghilan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sadeghifard.moghilan.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
