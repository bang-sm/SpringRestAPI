package com.toggle.study.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.toggle.study.entity.Insuprd;

@Repository
public interface InsuprdRepository extends JpaRepository<Insuprd, String> {
  Insuprd findOneByInsuprdRegIdAndInsuprdNm(String insuprdRegId, String insuprdNm);
  List<Insuprd> findByInsuprdNm(String insuprdNm);
}
