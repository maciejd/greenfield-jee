/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.web.runs;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import pl.maciejd.ejb.endpoints.ResultEndpoint;
import pl.maciejd.ejb.endpoints.RunEndpoint;
import pl.maciejd.exceptions.AppBaseException;
import pl.maciejd.model.TestResult;
import pl.maciejd.model.TestStatus;
import pl.maciejd.web.suites.SuiteSession;

/**
 *
 * @author Maciej
 */
@RequestScoped
@Named
public class TestDetailsBean {

    @Inject
    RunSession rs;
    
    @Inject
    ResultEndpoint resultEndpoint;

    @Inject
    SuiteSession ss;

    public List<TestStatus> getStatuses() {
        return resultEndpoint.getStatuses();
    }

    public TestResult getTestResult() {
        return rs.getSelectedResult();
    }

    public void resultHasChanged(ValueChangeEvent e) throws AppBaseException {
        TestStatus selectedStatus = (TestStatus) e.getNewValue();
        resultEndpoint.changeStatus(getTestResult(), selectedStatus);
    }

    public String editSelectedTestCase() {
        ss.setSelectedCase(rs.getSelectedResult().getTestCase());
        return "/suites/caseDetails.xhtml";
    }

}
