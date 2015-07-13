/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Trung
 */
@Entity
@Table(name = "Cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
    @NamedQuery(name = "Cart.findByRecordId", query = "SELECT c FROM Cart c WHERE c.recordId = :recordId"),
    @NamedQuery(name = "Cart.findByCartId", query = "SELECT c FROM Cart c WHERE c.cartId = :cartId"),
    @NamedQuery(name = "Cart.findByCount", query = "SELECT c FROM Cart c WHERE c.count = :count"),
    @NamedQuery(name = "Cart.findByDateCreated", query = "SELECT c FROM Cart c WHERE c.dateCreated = :dateCreated")})
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RecordId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;
    @Basic(optional = false)
    @Column(name = "CartId")
    private String cartId;
    @Basic(optional = false)
    @Column(name = "Count")
    private int count;
    @Basic(optional = false)
    @Column(name = "DateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "AlbumId", referencedColumnName = "AlbumId")
    @ManyToOne(optional = false)
    private Album albumId;

    public Cart() {
    }

    public Cart(Integer recordId) {
        this.recordId = recordId;
    }

    public Cart(Integer recordId, String cartId, int count, Date dateCreated) {
        this.recordId = recordId;
        this.cartId = cartId;
        this.count = count;
        this.dateCreated = dateCreated;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Album getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Album albumId) {
        this.albumId = albumId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.MVCMusicStore.Entities.Cart[ recordId=" + recordId + " ]";
    }
    
}
