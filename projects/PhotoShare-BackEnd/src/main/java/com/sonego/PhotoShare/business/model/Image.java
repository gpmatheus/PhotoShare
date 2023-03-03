package com.sonego.PhotoShare.business.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Image {

    @Id
    private UUID id;
    @Column(unique = true)
    private String url;

    private String fileName;

    private String contentType;

    @Transient
    private String extension;

    @Transient
    byte[] bytes;
}
