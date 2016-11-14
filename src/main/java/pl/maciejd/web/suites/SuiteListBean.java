/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.web.suites;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import pl.maciejd.ejb.endpoints.SuiteEndpoint;
import pl.maciejd.model.TestSuite;

/**
 *
 * @author Maciej
 */
@RequestScoped
@Named
public class SuiteListBean {
    
    @Inject
    SuiteSession sc;
    
    @Inject
    SuiteEndpoint se;
    
    public String viewSuite() {
        return "suiteDetails";
    }
    
    private DataModel<TestSuite> suites;

    public DataModel<TestSuite> getSuites() {
        suites = new ListDataModel<>(se.findSuites());
        return suites;
    }
    
    public String showSelectedSuite() {
        sc.setSelectedSuite(suites.getRowData());
        return "suiteDetails";
    }
    
    public int getSuiteSize() {
        return suites.getRowData().getCases().size();
    }
    
}
