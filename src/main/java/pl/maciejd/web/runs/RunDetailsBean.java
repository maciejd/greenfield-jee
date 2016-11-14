/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.web.runs;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import pl.maciejd.ejb.endpoints.ResultEndpoint;
import pl.maciejd.ejb.endpoints.RunEndpoint;
import pl.maciejd.exceptions.AppBaseException;
import pl.maciejd.model.TestResult;
import pl.maciejd.model.TestRun;
import pl.maciejd.model.TestStatus;

/**
 *
 * @author Maciej
 */
@RequestScoped
@Named
public class RunDetailsBean {

    @Inject
    RunSession rs;

    @Inject
    RunEndpoint re;
    
    @Inject
    ResultEndpoint resultEndpoint;

    private DataModel<TestResult> results;

    public DataModel<TestResult> getTestResults() {
        results = new ListDataModel<>(getTestRun().getResults());
        return results;
    }

    public TestRun getTestRun() {
        return rs.getSelectedRun();
    }

    @RolesAllowed("Admin")
    public String removeSelectedRun() {
        re.removeRun(getTestRun());
        return "runList";
    }

    public List<TestStatus> getStatuses() {
        return resultEndpoint.getStatuses();
    }

    public void resultHasChanged(ValueChangeEvent e) throws AppBaseException {
        TestStatus selectedStatus = (TestStatus) e.getNewValue();
        TestResult selectedTestResult = results.getRowData();
        resultEndpoint.changeStatus(selectedTestResult, selectedStatus);
    }

    public String viewSelectedResult() {
        rs.setSelectedResult(results.getRowData());
        return "/runs/testDetails.xhtml";
    }

}
