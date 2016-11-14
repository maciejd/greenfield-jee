/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.ejb.endpoints;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.maciejd.ejb.facades.TestResultFacade;
import pl.maciejd.ejb.facades.TestRunFacade;
import pl.maciejd.exceptions.AppBaseException;
import pl.maciejd.interceptors.LoggingInterceptor;
import pl.maciejd.model.TestCase;
import pl.maciejd.model.TestResult;
import pl.maciejd.model.TestRun;
import pl.maciejd.model.TestStatus;
import pl.maciejd.model.TestSuite;

/**
 *
 * @author Maciej
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class RunEndpoint extends AbstractEndpoint implements SessionSynchronization {

    @EJB
    TestRunFacade runFacade;

    @EJB
    TestResultFacade resultFacade;

    public void createRun(TestSuite selectedSuite, String title) {
        TestRun run = new TestRun();
        run.setSuite(selectedSuite);
        run.setTitle(title);
        run.setCreatedDate(new Date());
        List<TestResult> results = new ArrayList<TestResult>();
        List<TestCase> cases = selectedSuite.getCases();
        for (TestCase c : cases) {
            TestResult result = new TestResult();
            result.setRun(run);
            result.setTestCase(c);
            result.setStatus(TestStatus.UNTESTED);
            results.add(result);
        }
        run.setResults(results);
        runFacade.create(run);
    }

    public List<TestRun> findRuns() {
        return runFacade.findAll();
    }

    @RolesAllowed("Admin")
    public void removeRun(TestRun run) {
        runFacade.remove(run);
    }

    public void editRun(TestRun run) throws AppBaseException {
        runFacade.edit(run);
    }

    public TestRun findRun(Long id) {
        return runFacade.find(id);
    }
}
