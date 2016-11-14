/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.web.suites;

import javax.enterprise.context.RequestScoped;
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
public class CreateSuiteBean {
    
    @Inject
    SuiteEndpoint se;

    private TestSuite testSuite = new TestSuite();

    public TestSuite getTestSuite() {
        return testSuite;
    }
    
    public String create() {
        se.addSuite(testSuite);
        return "suiteList";
    }
    
}
