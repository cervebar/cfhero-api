package com.cfhero.api.kpis.lung;

import com.cfhero.api.model.LocalDateTimeAttributeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * XXX TODO create javadoc
 *
 */
@Service
public class LungService {

    @Autowired
    private LungKpiRepository repository;

    public LungService(LungKpiRepository repository) {
        this.repository = repository;
    }

    public LungKpi getLungKpiForUser(Long userid) {
        return repository.findByUserId(userid);
	}

    public List<LungKpi> getLungKpi(Long userid, Integer year, Integer month) {
        // default values this month ---------------------------------------------------
        if (year == null) {
            year = Calendar.getInstance().get(Calendar.YEAR);
        }
        if (month == null) {
            month = Calendar.getInstance().get(Calendar.MONTH);
        }
        // create the first date of month-------------------------------------------------
        Calendar mycal = new GregorianCalendar(year, month, 1);
        long startDate = mycal.getTimeInMillis();

        // Get the number of days in that month which actually gives the last date.
        int lastDate = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        mycal = new GregorianCalendar(year, month, lastDate);
        long endDate = mycal.getTimeInMillis();

        List<LungKpi> kpis = repository.findByEffectiveTimestampBetween(new Timestamp(startDate), new Timestamp(endDate));
        // add human readable DateTime with Java 8
        kpis.forEach(kpi -> kpi.setEffectiveTimeDateTime(LocalDateTimeAttributeConverter.convertToLocalDateTime(kpi.getEffectiveTimestamp())));

        return kpis;
    }

}
