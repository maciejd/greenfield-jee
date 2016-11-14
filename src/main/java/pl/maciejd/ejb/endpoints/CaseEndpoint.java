/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.ejb.endpoints;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.maciejd.ejb.facades.TestCaseFacade;
import pl.maciejd.ejb.facades.TestSuiteFacade;
import pl.maciejd.exceptions.AppBaseException;
import pl.maciejd.model.TestCase;

/**
 *
 * @author Maciej
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(pl.maciejd.interceptors.LoggingInterceptor.class)
public class CaseEndpoint {

    @EJB
    private TestCaseFacade testCaseFacade;

    @EJB
    private TestSuiteFacade testSuiteFacade;

    public void editCase(TestCase c) throws AppBaseException {
        testCaseFacade.edit(c);
    }

    @RolesAllowed("Admin")
    public void removeCase(TestCase c) throws AppBaseException {
        c.getSuite().getCases().remove(c);
        testCaseFacade.remove(c);
        testSuiteFacade.edit(c.getSuite());
    }

    public TestCase find(Long id) {
        return testCaseFacade.find(id);
    }

}
