package eci.arsw.covidanalyzer;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class CovidAggregateController {
     @Autowired
    @Qualifier("CovidAggregateServiceStub")
   

    ICovidAggregateService covidAggregateService;

    //TODO: Implemente todos los metodos POST que hacen falta.

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.POST)
    public ResponseEntity  addTruePositiveResult(Result result) {
        //TODO
       
            covidAggregateService.aggregateResult(result, ResultType.TRUE_POSITIVE);
            return new ResponseEntity<>("Created",HttpStatus.CREATED);
       
            
        
        
    }

    //TODO: Implemente todos los metodos GET que hacen falta.

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.GET)
    public ResponseEntity getTruePositiveResult() {
        //TODO
        
        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.TRUE_POSITIVE),HttpStatus.ACCEPTED);
    }


    //TODO: Implemente el método.

    @RequestMapping(value = "/covid/result/persona/{id}", method = RequestMethod.PUT)
    public ResponseEntity savePersonaWithMultipleTests(UUID id, ResultType type) {
      
        
            covidAggregateService.upsertPersonWithMultipleTests(id, type);
            return new ResponseEntity<>(type, HttpStatus.ACCEPTED);
       
        
    }
    
    
    
}