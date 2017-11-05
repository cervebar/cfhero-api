package com.cfhero.api.kpis.lung;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * XXX TODO create javadoc
 *
 */
@Entity
@Table(name = "lung_kpi")
public class LungKpi {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    // idenitfication props --------------
    private Long userId;

    // private LocalDate createdDate;

    // bbeacuse of Java 8 and Hibernate 5 not compatibility, for now we have to have it doubled, check setters
    private LocalDateTime effectiveTimeDateTime;
    private Timestamp effectiveTimestamp;

    // lung functions --------------------
    private Double fce;

    public LocalDateTime getEffectiveTimeDateTime() {
        return effectiveTimeDateTime;
    }

    public void setEffectiveTimeDateTime(LocalDateTime effectiveTimeDateTime) {
        this.effectiveTimeDateTime = effectiveTimeDateTime;
    }

    public void setEffectiveTimestamp(Timestamp effectiveTimestamp) {
        this.effectiveTimestamp = effectiveTimestamp;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getEffectiveTimestamp() {
        return effectiveTimestamp;
    }

    public void setId(Long id) {
		this.id = id;
	}

    public Double getFce() {
		return fce;
	}

    public void setFce(Double fce) {
		this.fce = fce;
	}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
