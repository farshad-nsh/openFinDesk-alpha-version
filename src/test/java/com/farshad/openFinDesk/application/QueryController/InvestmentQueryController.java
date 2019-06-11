package com.farshad.openFinDesk.application.QueryController;


import com.farshad.openFinDesk.domain.aggregates.Instrument;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvestmentQueryController {
    @RequestMapping("/instruments/list")
    public Instrument list(@RequestParam(value = "name",
            defaultValue = "bond") String name) {
        Instrument instrument = new Instrument();
        instrument.setDescription("some derivative");
        return instrument;
    }
}
