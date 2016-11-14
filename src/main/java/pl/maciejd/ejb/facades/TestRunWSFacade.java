/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.ejb.facades;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import pl.maciejd.ejb.endpoints.RunEndpoint;
import pl.maciejd.ejb.endpoints.SuiteEndpoint;
import pl.maciejd.model.TestSuite;

/**
 *
 * @author Maciej
 */
@Stateless
@Interceptors(pl.maciejd.interceptors.LoggingInterceptor.class)
public class TestRunWSFacade {

    @Inject
    RunEndpoint re;

    @Inject
    SuiteEndpoint se;

    public void createRun(TestSuite suite, String name) {
        re.createRun(suite, name);
    }

    public TestSuite findSuite(Long id) {
        return se.find(id);
    }

}
