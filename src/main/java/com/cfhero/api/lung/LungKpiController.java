package com.cfhero.api.lung;

import com.cfhero.versioning.ApiVersionRange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kpis/lung")
public class LungKpiController {
	
	@Autowired
	private final LunService lungService;
	
    public LungKpiController(LunService lungService) {
		this.lungService = lungService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiVersionRange(from = 1)
    public LungKpi search(@RequestParam("user") String user) {
        return lungService.getLungKpiForUser(user);
    }

}
