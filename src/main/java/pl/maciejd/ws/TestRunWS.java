/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.ws;

import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.ws.rs.NotFoundException;
import pl.maciejd.ejb.facades.TestRunWSFacade;
import pl.maciejd.model.TestSuite;

/**
 *
 * @author Maciej
 */
@WebService(endpointInterface = "pl.maciejd.ws.TestRunWsSEI")
public class TestRunWS implements TestRunWsSEI {

    @Inject
    TestRunWSFacade trfa;

    @Override
    public void createTestRun(Long suiteId, String name) throws Exception {
        TestSuite suite = null;
        try {
            suite = trfa.findSuite(suiteId);
            if (suite == null) {
                throw new NotFoundException("Suite with given ID does not exist");
            }
        } catch (EJBTransactionRolledbackException e2) {
            throw new IllegalArgumentException("Suite ID must be an integer");
        }
        trfa.createRun(suite, name);
    }
}
