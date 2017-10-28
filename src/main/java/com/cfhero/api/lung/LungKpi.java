package com.cfhero.api.lung;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * XXX TODO create javadoc
 *
 */
@Entity
public class LungKpi {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String fce;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFce() {
		return fce;
	}

	public void setFce(String fce) {
		this.fce = fce;
	}

}
