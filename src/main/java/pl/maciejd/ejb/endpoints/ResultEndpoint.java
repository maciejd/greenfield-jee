/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.ejb.endpoints;

import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;
import pl.maciejd.ejb.facades.TestResultFacade;
import pl.maciejd.exceptions.AppBaseException;
import pl.maciejd.model.TestResult;
import pl.maciejd.model.TestStatus;
import pl.maciejd.web.runs.RunSession;

/**
 *
 * @author Maciej
 */
@Stateful
public class ResultEndpoint {

    @Inject
    RunEndpoint re;

    @Inject
    RunSession rs;

    @Inject
    TestResultFacade trf;

    public void changeStatus(TestResult selectedTestResult, TestStatus selectedStatus) throws AppBaseException {
        selectedTestResult.setStatus(selectedStatus);
        editResult(selectedTestResult);
        rs.setSelectedResult(findResult(selectedTestResult.getId()));
        rs.setSelectedRun(re.findRun(rs.getSelectedRun().getId()));
    }

    public List<TestStatus> getStatuses() {
        return Arrays.asList(TestStatus.values());
    }

    public void editResult(TestResult result) throws AppBaseException {
        trf.edit(result);
    }

    public TestResult findResult(Long id) {
        return trf.find(id);
    }
}
