/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.web.suites;

import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import pl.maciejd.ejb.endpoints.SuiteEndpoint;
import pl.maciejd.exceptions.AppBaseException;
import pl.maciejd.model.TestCase;
import pl.maciejd.model.TestSuite;

/**
 *
 * @author Maciej
 */
@SessionScoped
public class SuiteSession implements Serializable {
    
    private TestSuite selectedSuite;
    
    private TestCase selectedCase;

    public TestCase getSelectedCase() {
        return selectedCase;
    }

    public void setSelectedCase(TestCase selectedCase) {
        this.selectedCase = selectedCase;
    }

    public TestSuite getSelectedSuite() {
        return selectedSuite;
    }

    public void setSelectedSuite(TestSuite selectedSuite) {
        this.selectedSuite = selectedSuite;
    }
    
}