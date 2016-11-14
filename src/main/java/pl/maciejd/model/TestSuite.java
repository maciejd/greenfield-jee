/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maciejd.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 *
 * @author Maciej
 */
@TableGenerator(initialValue = 101, allocationSize = 10,
        name = "SuiteGen", table = "GENERATOR",
        pkColumnName = "ENTITY", pkColumnValue = "TestSuite",
        valueColumnName = "VALUE")

@Entity
public class TestSuite implements Serializable {

    @Id
    @GeneratedValue(generator = "SuiteGen")
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "suite")
    private List<TestCase> cases;

    public List<TestCase> getCases() {
        return cases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TestSuite() {
    }

    public void addCase(TestCase testCase){
        if (testCase.getSuite() != this) {
            testCase.setSuite(this);
        }
        this.cases.add(testCase);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TestSuite other = (TestSuite) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
    
    
    
}
