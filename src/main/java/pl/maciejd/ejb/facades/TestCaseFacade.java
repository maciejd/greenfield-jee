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
import javax.persistence.PersistenceException;
import pl.maciejd.exceptions.AppBaseException;
import pl.maciejd.exceptions.CaseUpdatedException;
import pl.maciejd.model.TestCase;

/**
 *
 * @author Maciej
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(pl.maciejd.interceptors.LoggingInterceptor.class)
public class TestCaseFacade extends AbstractFacade<TestCase> {
    @PersistenceContext(unitName = "gfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TestCaseFacade() {
        super(TestCase.class);
    }

    @Override
    public void edit(TestCase entity) throws AppBaseException {
        try {
        super.edit(entity);
        em.flush();
        } catch (PersistenceException ole) {
            throw new CaseUpdatedException(ole, entity);
        }
    }
    
    
    
}
