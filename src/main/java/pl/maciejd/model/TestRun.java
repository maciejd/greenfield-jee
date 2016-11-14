/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Maciej
 */
@Entity
public class TestRun implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    TestSuite suite;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<TestResult> results;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private String title;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setResults(List<TestResult> results) {
        this.results = results;
    }

    public List<TestResult> getResults() {
        return results;
    }

    public TestSuite getSuite() {
        return suite;
    }

    public void setSuite(TestSuite suite) {
        this.suite = suite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassed() {
        return getNumber(TestStatus.PASSED);
    }

    public String getFailed() {
        return getNumber(TestStatus.FAILED);
    }

    public String getRetest() {
        return getNumber(TestStatus.RETEST);
    }

    public String getBlocked() {
        return getNumber(TestStatus.BLOCKED);
    }

    private String getNumber(TestStatus status) {
        if (results.isEmpty()) {
            return "0";
        }
        int sum = 0;
        for (TestResult result : results) {
            if (result.getStatus().equals(status)) {
                sum++;
            }
        }
        if (sum == 0) {
            return "0";
        } else {
            return String.format("%.1f", sum * 100.0 / results.size()).replace(",", ".");
        }
    }

    @Override
    public String toString() {
        return "id=" + id + ", suiteId=" + suite.getId();
    }
    
    

}
