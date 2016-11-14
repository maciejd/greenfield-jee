/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.exceptions;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import pl.maciejd.model.TestCase;

/**
 *
 * @author Maciej
 */
public class CaseUpdatedException extends AppBaseException {
    
    private TestCase testCase;
    
    public CaseUpdatedException(PersistenceException ole, TestCase tc) {
        super(ole);
        this.testCase = tc;
    }

    public TestCase getTestCase() {
        return testCase;
    }
    
}
