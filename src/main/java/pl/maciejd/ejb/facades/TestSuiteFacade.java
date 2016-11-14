/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.ejb.facades;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.maciejd.model.TestSuite;

/**
 *
 * @author Maciej
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(pl.maciejd.interceptors.LoggingInterceptor.class)
public class TestSuiteFacade extends AbstractFacade<TestSuite> {
    @PersistenceContext(unitName = "gfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TestSuiteFacade() {
        super(TestSuite.class);
    }
    
}
