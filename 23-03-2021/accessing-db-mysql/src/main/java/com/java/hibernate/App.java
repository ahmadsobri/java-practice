package com.java.hibernate;

import com.java.hibernate.entities.Article;
import com.java.hibernate.entities.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class App {
    static Transaction transaction = null;
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); //auto load hibernate.cfg.xml from sources
    //if above not work you can use bellow
    //SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Comment.class).buildSessionFactory();

    public static void main(String[] args) {
        showAllComment();
    }

    public static void addComment(){
        try (Session session = sessionFactory.openSession()) {
            Article article = getArticle("Cara Menyemai Cabe Rawit");
            int article_id = article.getId() > 0 ? article.getId() : 1;

            Comment comment = new Comment();
            comment.setName("Rendi");
            comment.setMessage("Good article");
            comment.setCreated(new Date());
            //comment.setArticleId(article_id);

            // start a transaction
            transaction = session.beginTransaction();

            // save objects
            session.save(comment);

            // commit transaction
            transaction.commit();

            System.out.println("comment id "+comment.getId()+" was added on article "+article_id);
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public static Article getArticle(String title){
        Article article = new Article();
        try (Session session = sessionFactory.openSession()) {

            Query query = session.createQuery("select a from Article a where a.title = :title order by a.id desc", Article.class);//.setFirstResult(1).getSingleResult();
            query.setParameter("title", title);
            article = ((Article)query.setFirstResult(0).getSingleResult());
            //int article_id = article.getId() > 0 ? article.getId() : 1;
            //Article article1 = session.find(Article.class,0); //if you need find by id (no need query)
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    public static void showAllComment(){
        try (Session session = sessionFactory.openSession()) {

            Query query = session.createQuery("select a from Article a join a.commentList");

            List<Article> list=query.list();

            list.stream().forEach((p)->{
                System.out.println("article title: "+p.getTitle());
                System.out.println("comment :");
                p.getCommentList().forEach((c)->{
                    System.out.println("\t\t\t"+c.getMessage());
                });
                System.out.println("==============================================\n");
            });
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
