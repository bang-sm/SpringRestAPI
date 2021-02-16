package com.toggle.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.toggle.sutdy.entity.Insuprd;

@Repository
public interface InsuprdRepository extends JpaRepository<Insuprd, String> {
}
