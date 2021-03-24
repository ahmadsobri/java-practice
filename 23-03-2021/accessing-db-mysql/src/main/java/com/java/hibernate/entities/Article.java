package com.java.hibernate.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //If you want to use a table's identity column
    //Note : GenerationType.AUTO or GenerationType.SEQUENCE need table hibernate_sequence in your db
    private int id;
    private int author_id;
    private String title;
    private String description;
    private Date created;

    //@OneToMany(targetEntity=Comment.class, mappedBy="article", fetch=FetchType.EAGER)
    @OneToMany(mappedBy="article",cascade=CascadeType.ALL)
    List<Comment> commentList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    //@OneToMany(mappedBy="article",cascade=CascadeType.ALL)
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
