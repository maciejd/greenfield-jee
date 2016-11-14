/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.converters;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import pl.maciejd.ejb.endpoints.SuiteEndpoint;
import pl.maciejd.model.TestSuite;

/**
 *
 * @author Maciej
 */
@ManagedBean
@Named
@FacesConverter(value = "suiteConverter")
public class TestSuiteConverter implements Converter {

    @EJB
    SuiteEndpoint se;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Long id = Long.valueOf(value);
        System.out.println("ID is  " + id);
        TestSuite suite = se.find(id);
        System.out.println("suite is " + suite.getTitle());
        return suite;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        TestSuite suite = (TestSuite) value;
        Long id = suite.getId();
        return String.valueOf(id);
    }

}
