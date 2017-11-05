package com.cfhero.api.kpis.lung;

import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface LungKpiRepository extends CrudRepository<LungKpi, Long> {

    LungKpi findByUserId(Long userid);

    List<LungKpi> findByEffectiveTimestampBetween(Timestamp start, Timestamp end);
    // findTop2ByOrderByIdDesc

}