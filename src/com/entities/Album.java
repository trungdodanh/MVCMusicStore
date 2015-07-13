/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;

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
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Trung
 */
@Entity
@Table(name = "Album")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a"),
    @NamedQuery(name = "Album.findByAlbumId", query = "SELECT a FROM Album a WHERE a.albumId = :albumId"),
    @NamedQuery(name = "Album.findByTitle", query = "SELECT a FROM Album a WHERE a.title = :title"),
    @NamedQuery(name = "Album.findByPrice", query = "SELECT a FROM Album a WHERE a.price = :price"),
    @NamedQuery(name = "Album.findByAlbumArtUrl", query = "SELECT a FROM Album a WHERE a.albumArtUrl = :albumArtUrl")})

public class Album implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AlbumId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer albumId;
    @Basic(optional = false)
    @Column(name = "Title")
    private String title;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Price")
    @Range(min=(long) 0.01, max=(long) 100.00, message = "Price must be between 0.01 and 100.00")
    private Double price;
    @Column(name = "AlbumArtUrl")
    private String albumArtUrl;
    @JoinColumn(name = "ArtistId", referencedColumnName = "ArtistId")
    @ManyToOne(optional = false)
    private Artist artistId;
    @JoinColumn(name = "GenreId", referencedColumnName = "GenreId")
    @ManyToOne(optional = false)
    private Genre genreId;
    
    public Album() {
    }

    public Album(Integer albumId) {
        this.albumId = albumId;
    }

    public Album(Integer albumId, String title, Double price) {
        this.albumId = albumId;
        this.title = title;
        this.price = price;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAlbumArtUrl() {
        return albumArtUrl;
    }

    public void setAlbumArtUrl(String albumArtUrl) {
        this.albumArtUrl = albumArtUrl;
    }

    public Artist getArtistId() {
        return artistId;
    }

    public void setArtistId(Artist artistId) {
        this.artistId = artistId;
    }

    public Genre getGenreId() {
        return genreId;
    }

    public void setGenreId(Genre genreId) {
        this.genreId = genreId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (albumId != null ? albumId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.albumId == null && other.albumId != null) || (this.albumId != null && !this.albumId.equals(other.albumId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.MVCMusicStore.Entities.Album[ albumId=" + albumId + " ]";
    }
    
}
