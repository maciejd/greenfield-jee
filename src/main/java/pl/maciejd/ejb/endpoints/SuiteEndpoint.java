/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.ejb.endpoints;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.maciejd.ejb.facades.TestSuiteFacade;
import pl.maciejd.exceptions.AppBaseException;
import pl.maciejd.model.TestSuite;

/**
 *
 * @author Maciej
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(pl.maciejd.interceptors.LoggingInterceptor.class)
public class SuiteEndpoint extends AbstractEndpoint implements SessionSynchronization {

    @EJB
    private TestSuiteFacade testSuiteFacade;

    public List<TestSuite> findSuites() {
        return testSuiteFacade.findAll();
    }

    public TestSuite find(Long id) {
        return testSuiteFacade.find(id);
    }

    public void addSuite(TestSuite s) {
        testSuiteFacade.create(s);
    }

    @RolesAllowed("Admin")
    public void removeSuite(TestSuite s) {
        testSuiteFacade.remove(s);
    }

    public void editSuite(TestSuite s) throws AppBaseException {
        testSuiteFacade.edit(s);
    }

}
