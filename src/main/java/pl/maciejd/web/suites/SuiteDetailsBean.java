/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.web.suites;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import pl.maciejd.ejb.endpoints.SuiteEndpoint;
import pl.maciejd.exceptions.AppBaseException;
import pl.maciejd.model.TestCase;
import pl.maciejd.model.TestSuite;

/**
 *
 * @author Maciej
 */
@RequestScoped
@Named
public class SuiteDetailsBean {

    @Inject
    SuiteSession sc;
    
    @Inject
    SuiteEndpoint se;

    private DataModel<TestCase> cases;

    private TestCase testCase = new TestCase();

    public TestCase getTestCase() {
        return testCase;
    }

    public TestSuite getTestSuite() {
        return sc.getSelectedSuite();
    }

    public void edit() throws AppBaseException {
        se.editSuite(getTestSuite());
        sc.setSelectedSuite(se.find(getTestSuite().getId()));
    }

    public String addTestCase() throws AppBaseException {
        getTestSuite().addCase(testCase);
        edit();
        return "suiteDetails";
    }

    public DataModel<TestCase> getCases() {
        cases = new ListDataModel<>(getTestSuite().getCases());
        return cases;
    }

    @RolesAllowed("Admin")
    public String removeSelectedSuite() {
        se.removeSuite(getTestSuite());
        return "suiteList";
    }

    public String editSelectedTestCase() {
        sc.setSelectedCase(cases.getRowData());
        return "/suites/caseDetails.xhtml";
    }
}
