/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.web.suites;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import pl.maciejd.ejb.endpoints.CaseEndpoint;
import pl.maciejd.ejb.endpoints.SuiteEndpoint;
import pl.maciejd.exceptions.AppBaseException;
import pl.maciejd.exceptions.CaseUpdatedException;
import pl.maciejd.model.TestCase;

/**
 *
 * @author Maciej
 */
@RequestScoped
@Named
public class CaseDetailsBean {

    @Inject
    SuiteSession ss;

    @Inject
    SuiteEndpoint se;
    
    @Inject
    CaseEndpoint ce;
    
    public TestCase getTestCase() {
        return ss.getSelectedCase();
    }

    public String editSelectedCase() throws AppBaseException {
        try {
            ce.editCase(getTestCase());
        } catch (CaseUpdatedException ex) {
            Logger.getLogger(CaseDetailsBean.class.getName()).log(Level.SEVERE, null, ex.getTestCase());

            FacesMessage facesMassage = new FacesMessage("Brak możliwośći zapisu w bazie danych");
            if (ex.getCause() instanceof OptimisticLockException) {
                facesMassage.setSummary("Someone updated this test case before you.");
            }
            facesMassage.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, facesMassage);
            return "";
        } catch (AppBaseException ex) {
            Logger.getLogger(CaseDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        ss.setSelectedCase(ce.find(getTestCase().getId()));
        ss.setSelectedSuite(se.find(getTestCase().getSuite().getId()));
        return "/suites/suiteDetails.xhtml";
    }

    @RolesAllowed("Admin")
    public String removeSelectedCase() throws AppBaseException {
        ce.removeCase(getTestCase());
        ss.setSelectedSuite(getTestCase().getSuite());
        return "/suites/suiteDetails.xhtml";
    }

    public String cancelEdit() {
        ss.setSelectedSuite(getTestCase().getSuite());
        return "/suites/suiteDetails.xhtml";
    }

}
