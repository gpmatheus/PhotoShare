package com.sonego.PhotoShare.business.model;

import com.sonego.PhotoShare.business.exceptions.BusinessException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.*;

@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToOne
    private Image image;
    private OffsetDateTime postingDate;
    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<Description> descriptions = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    private Profile profile;

    @Transient
    private final int MAX_DESCRIPTION_UPDATES = 3;
    @Transient
    private final Duration TIMEOUT_TO_UPDATE_DESCRIPTION = Duration.ofDays(3);

    @Transient
   private final Duration TIMEOUT_TO_COMMENT_AGAIN = Duration.ofMinutes(15);

    public Description setNewDescription(String content) throws BusinessException {
        List<Description> descriptions = getDescriptions();
        if (descriptions.size() == MAX_DESCRIPTION_UPDATES) {
            throw new BusinessException("There has already been made " + MAX_DESCRIPTION_UPDATES
                    + " changes to the description of this post");
        }
        OffsetDateTime currentTime = OffsetDateTime.now();
        if (!descriptions.isEmpty()) {
            descriptions.sort(Comparator.comparing(Description::getEditingDateTime));
            Description mostRecentDescription = descriptions.get(descriptions.size() - 1);
            if (Duration.between(mostRecentDescription.getEditingDateTime(), currentTime)
                    .compareTo(TIMEOUT_TO_UPDATE_DESCRIPTION) < 0) {
                throw new BusinessException("There has not been enough time (" + TIMEOUT_TO_UPDATE_DESCRIPTION.toHours()
                    + " hours) for you to make another modification");
            }
        }
        Description newDescription = new Description();
        newDescription.setPost(this);
        newDescription.setContent(content);
        newDescription.setEditingDateTime(currentTime);
        descriptions.add(newDescription);
        setDescriptions(descriptions);
        return newDescription;
    }

    public Comment addComment(String content, User commenter) {
        List<Comment> comments = getComments();
        Optional<Comment> mostRecentComment = comments.stream()
                .max(Comparator.comparing(Comment::getCommentingDate));
        if (mostRecentComment.isPresent() && isTimeoutNotOver(mostRecentComment.get())) {
            throw new BusinessException("There is a timeout of " + TIMEOUT_TO_COMMENT_AGAIN.toMinutes() + " for " +
                    "each new comment");
        }

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCommenter(commenter);
        comment.setPost(this);
        comment.setCommentingDate(OffsetDateTime.now());

        comments.add(comment);
        setComments(comments);
        return comment;
    }

    private boolean isTimeoutNotOver(Comment mostRecentComment) {
        return Duration.between(mostRecentComment.getCommentingDate(),
                OffsetDateTime.now()).compareTo(TIMEOUT_TO_COMMENT_AGAIN) < 0;
    }
}
