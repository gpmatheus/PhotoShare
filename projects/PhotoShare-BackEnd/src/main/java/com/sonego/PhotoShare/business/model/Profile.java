package com.sonego.PhotoShare.business.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String name;

    private String about;

    private boolean visible = true;

    @OneToOne
    private Image profileImage;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "profile")
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        List<Post> posts = this.getPosts();
        posts.add(post);
        post.setProfile(this);
        this.setPosts(posts);
    }
}
