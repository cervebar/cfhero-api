package com.cfhero.api.lung;

import org.springframework.stereotype.Service;

/**
 * XXX TODO create javadoc
 *
 */
@Service
public class LunService {

	public LungKpi getLungKpiForUser(String user) {
		// TODO Auto-generated method st;
		LungKpi kpi=new LungKpi();
		kpi.setFce("test");
		return kpi;
	}

}
