package com.cfhero.api.kpis.lung;

import com.cfhero.versioning.ApiVersionRange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("kpis/lung")
public class LungKpiController {

	@Autowired
	private final LungService lungService;

    public LungKpiController(LungService lungService) {
		this.lungService = lungService;
	}

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiVersionRange(from = 1)
    public List<LungKpi> searchForMonth(@RequestParam("userid") Long userid, @RequestParam(value = "year", required = false) Integer year,
                                        @RequestParam(value = "month", required = false) Integer month) {
        return lungService.getLungKpi(userid, year, month);
    }

}
