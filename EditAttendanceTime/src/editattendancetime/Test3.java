/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editattendancetime;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author t.aljehani
 */
@Entity
@Table(name = "test3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Test3.findAll", query = "SELECT t FROM Test3 t")
    , @NamedQuery(name = "Test3.findById", query = "SELECT t FROM Test3 t WHERE t.id = :id")
    , @NamedQuery(name = "Test3.findByEmpid", query = "SELECT t FROM Test3 t WHERE t.empid = :empid")
    , @NamedQuery(name = "Test3.findByLogdate", query = "SELECT t FROM Test3 t WHERE t.logdate = :logdate")
    , @NamedQuery(name = "Test3.findByInout", query = "SELECT t FROM Test3 t WHERE t.inout = :inout")
    , @NamedQuery(name = "Test3.findByTime", query = "SELECT t FROM Test3 t WHERE t.time = :time")})
public class Test3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "EMPID")
    private String empid;
    @Column(name = "LOGDATE")
    @Temporal(TemporalType.DATE)
    private Date logdate;
    @Column(name = "INOUT")
    private Character inout;
    @Column(name = "TIME")
    private String time;

    public Test3() {
    }

    public Test3(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public Date getLogdate() {
        return logdate;
    }

    public void setLogdate(Date logdate) {
        this.logdate = logdate;
    }

    public Character getInout() {
        return inout;
    }

    public void setInout(Character inout) {
        this.inout = inout;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test3)) {
            return false;
        }
        Test3 other = (Test3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "editattendancetime.Test3[ id=" + id + " ]";
    }
    
}
