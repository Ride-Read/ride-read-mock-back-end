package qi.yue.entity;

import java.util.Date;

public class ThumbsUp {
    private Integer thumbsUpId;

    private Integer uid;

    private Integer commentId;

    private String nickname;

    private Date createdAt;

    public Integer getThumbsUpId() {
        return thumbsUpId;
    }

    public void setThumbsUpId(Integer thumbsUpId) {
        this.thumbsUpId = thumbsUpId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}