package com.sonego.PhotoShare.business.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String content;
    private OffsetDateTime editingDateTime;
    @ManyToOne
    private Post post;
}
