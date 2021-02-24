package eci.arsw.covidanalyzer.service;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Component("ICovidAggregateServiceStub")
public class ICovidAggregateServiceStub implements ICovidAggregateService {
    private List<Result> resultList = new CopyOnWriteArrayList<>();

    @Override
    public void aggregateResult(Result result, ResultType type) {
        int testNumber;
        for (Result r:resultList) {
            if (r.equals(result)) {
                r.setResultType(type);

                testNumber = r.getTestRealize();
                testNumber += 1;
                r.setTestRealize(testNumber);
            }
        }
    }

    @Override
    public List<Result> getResult(ResultType type) {
        List<Result> resultsByType = new CopyOnWriteArrayList<>();

        for (Result r:resultList) {
            if (r.getResultType().equals(type)) {
                resultsByType.add(r);
            }
        }

        return resultsByType;
    }


    @Override
    public void upsertPersonWithMultipleTests(UUID id, ResultType type) {
        
        
    }
}