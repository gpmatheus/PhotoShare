package com.sonego.PhotoShare.business.model;

import com.sonego.PhotoShare.business.exceptions.BusinessException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String content;

    private OffsetDateTime commentingDate;

    @ManyToOne
    private User commenter;

    @ManyToOne
    private Post post;

    public Comment(String content, User commenter, Post post) {
        this.commentingDate = OffsetDateTime.now();
        this.content = content;
        this.commenter = commenter;
        this.post = post;
    }
}
