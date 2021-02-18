package com.toggle.study.repository;

import com.toggle.study.entity.CustQust;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustQustRepository extends JpaRepository<CustQust, String> {
    
}
