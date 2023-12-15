package com.projectboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectboard.domain.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{

}
