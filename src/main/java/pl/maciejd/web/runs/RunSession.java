/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.web.runs;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import pl.maciejd.model.TestResult;
import pl.maciejd.model.TestRun;
import pl.maciejd.model.TestStatus;

/**
 *
 * @author Maciej
 */
@SessionScoped
public class RunSession implements Serializable {

    private TestRun selectedRun;

    private TestResult selectedResult;

    public TestResult getSelectedResult() {
        return selectedResult;
    }

    public void setSelectedResult(TestResult selectedResult) {
        this.selectedResult = selectedResult;
    }

    public TestRun getSelectedRun() {
        return selectedRun;
    }

    public void setSelectedRun(TestRun selectedRun) {
        this.selectedRun = selectedRun;
    }

}
