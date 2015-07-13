/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Trung
 */
@Entity
@Table(name = "UsersRole")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersRole.findAll", query = "SELECT u FROM UsersRole u"),
    @NamedQuery(name = "UsersRole.findByRoleId", query = "SELECT u FROM UsersRole u WHERE u.roleId = :roleId"),
    @NamedQuery(name = "UsersRole.findByRoleName", query = "SELECT u FROM UsersRole u WHERE u.roleName = :roleName")})
public class UsersRole implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "RoleId")
    private Integer roleId;
    @Basic(optional = false)
    @Column(name = "RoleName")
    private String roleName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersRole")
    private Collection<Users> usersCollection;

    public UsersRole() {
    }

    public UsersRole(Integer roleId) {
        this.roleId = roleId;
    }

    public UsersRole(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersRole)) {
            return false;
        }
        UsersRole other = (UsersRole) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.MVCMusicStore.Entities.UsersRole[ roleId=" + roleId + " ]";
    }
    
}
