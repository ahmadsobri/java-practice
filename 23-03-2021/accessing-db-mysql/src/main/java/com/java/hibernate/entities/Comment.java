package com.java.hibernate.entities;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //If you want to use a table's identity column
    //Note : GenerationType.AUTO or GenerationType.SEQUENCE need table hibernate_sequence in your db
    private int id;

    private String name;
    private String message;
    private Date created;
    @Column(name = "is_show")
    private boolean isShow;
//    @Column(name = "article_id")
//    private int articleId;

    @ManyToOne
    @JoinColumn(name = "article_id")
    Article article;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

//    public int getArticleId() {
//        return articleId;
//    }
//
//    public void setArticleId(int articleId) {
//        this.articleId = articleId;
//    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
