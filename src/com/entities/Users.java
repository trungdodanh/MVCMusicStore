/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Trung
 */
@Entity
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUsersId", query = "SELECT u FROM Users u WHERE u.usersId = :usersId"),
    @NamedQuery(name = "Users.findByUsersName", query = "SELECT u FROM Users u WHERE u.usersName = :usersName"),
    @NamedQuery(name = "Users.findByPassWord", query = "SELECT u FROM Users u WHERE u.passWord = :passWord"),
    @NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UsersId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usersId;
    @Basic(optional = false)
    @Column(name = "UsersName")
    private String usersName;
    @Basic(optional = false)
    @Column(name = "PassWord")
    private String passWord;
    @Basic(optional = false)
    @Column(name = "FirstName")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LastName")
    private String lastName;
    @JoinColumn(name = "UsersRole", referencedColumnName = "RoleId")
    @ManyToOne(optional = false)
    private UsersRole usersRole;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersId")
    private List<Orders> ordersList;

	public Users() {
		
    }

    public Users(Integer usersId) {
        this.usersId = usersId;
    }

    public Users(Integer usersId, String usersName, String passWord, String firstName, String lastName) {
        this.usersId = usersId;
        this.usersName = usersName;
        this.passWord = passWord;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UsersRole getUsersRole() {
        return usersRole;
    }

    public void setUsersRole(UsersRole usersRole) {
        this.usersRole = usersRole;
    }
    
    public List<Orders> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usersId != null ? usersId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.usersId == null && other.usersId != null) || (this.usersId != null && !this.usersId.equals(other.usersId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.MVCMusicStore.Entities.Users[ usersId=" + usersId + " ]";
    }
    
}
