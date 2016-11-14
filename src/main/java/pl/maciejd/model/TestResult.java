/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Maciej
 */
@Entity
public class TestResult implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private TestRun run;

    @ManyToOne
    private TestCase testCase;

    private TestStatus status;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date resultDate;

    public Date getResultDate() {
        return resultDate;
    }

    public TestRun getRun() {
        return run;
    }

    public void setRun(TestRun run) {
        this.run = run;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public TestStatus getStatus() {
        return status;
    }

    public void setStatus(TestStatus status) {
        this.status = status;
    }
    
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @PrePersist
    @PreUpdate
    public void updateLastModified() {
        this.resultDate = new Date();
    }

    @Override
    public String toString() {
        return "caseId=" + testCase.getId() + ", result=" + status;
    }
    
    
    
}
