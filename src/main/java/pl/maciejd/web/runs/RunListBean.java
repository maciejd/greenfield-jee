/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.web.runs;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import pl.maciejd.ejb.endpoints.RunEndpoint;
import pl.maciejd.ejb.endpoints.SuiteEndpoint;
import pl.maciejd.model.TestRun;
import pl.maciejd.model.TestSuite;

/**
 *
 * @author Maciej
 */
@RequestScoped
@Named
public class RunListBean {

    @Inject
    SuiteEndpoint se;

    @Inject
    RunEndpoint re;

    @Inject
    RunSession rs;

    private DataModel<TestRun> runs;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DataModel<TestRun> getTestRuns() {
        runs = new ListDataModel<>(re.findRuns());
        return runs;
    }

    private TestSuite selectedSuite;

    public TestSuite getSelectedSuite() {
        return selectedSuite;
    }

    public void setSelectedSuite(TestSuite selectedSuite) {
        this.selectedSuite = selectedSuite;
    }

    public List<TestSuite> getSuites() {
        return se.findSuites();
    }

    public String createTestRun() {
        if (selectedSuite != null) {
            re.createRun(selectedSuite, title);
            return "runDetails";
        } else {
            return "";
        }
    }

    public String viewRunDetails() {
        rs.setSelectedRun(runs.getRowData());
        return "runDetails.xhtml";
    }
    
}
