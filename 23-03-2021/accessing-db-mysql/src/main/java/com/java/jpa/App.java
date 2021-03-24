package com.java.jpa;

import com.java.jpa.entities.Article;
import com.java.jpa.entities.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class App {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("java-jpa");
    static EntityManager entityManager = emf.createEntityManager();

    public static void main(String[] args) {
        //findAllArticle();
        //addAuthor();
        addArticle();
        //deleteArticle(6);
        findAllArticle();
        //updateArticle(8,"Cara Menyemai Cabe Rawit", "sekumpulan tips Menyemai Cabe Rawit");

    }

    public static int addAuthor() {
        Author author = new Author();
        if (!emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("java-jpa");
        }
        if (!entityManager.isOpen()) {
            entityManager = emf.createEntityManager();
        }
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        author.setFullname("Dewa Kipas");
        author.setAge(23);

        entityManager.persist(author);
        entityManager.getTransaction().commit();

        entityManager.close();
        emf.close();
        return author.getId();
    }

    public static void addArticle() {
        if (!emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("java-jpa");
        }
        if (!entityManager.isOpen()) {
            entityManager = emf.createEntityManager();
        }
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT st FROM Author st order by st.id desc").setMaxResults(1);
        Author author = (Author) query.getResultList().get(0);
        int author_id = (author.getId() > 0) ? author.getId() : addAuthor();

        Article article = new Article();
        article.setTitle("Tips Merawat Tanaman Cabe Rawit");
        article.setDescription("bebrapa tips untuk merawat cabe rawit");
        article.setCreated(new Date());
        article.setAuthor_id(author_id);

        entityManager.persist(article);
        entityManager.getTransaction().commit();

        entityManager.close();
        emf.close();
    }

    public static void updateArticle(int id, String title, String desc) {
        if (!emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("java-jpa");
        }
        if (!entityManager.isOpen()) {
            entityManager = emf.createEntityManager();
        }

        entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Article article = entityManager.find(Article.class, id);

        article.setTitle(title);
        article.setDescription(desc);

        entityManager.persist(article);
        entityManager.getTransaction().commit();
        System.out.println(article);

        entityManager.close();
        emf.close();
    }

    public static void findAllArticle() {
        if (!emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("java-jpa");
        }
        if (!entityManager.isOpen()) {
            entityManager = emf.createEntityManager();
        }
        entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        List<Article> articles = entityManager.createQuery("Select a from Article a").getResultList();

        for (Article article : articles) {
            String output = "%s - %s - %s - %s";
            System.out.println(String.format(output, article.getId(), article.getTitle(), article.getDescription(), article.getCreated()));
        }

        entityManager.close();
        emf.close();
    }

    public static void deleteArticle(int id) {
        if (!emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("java-jpa");
        }
        if (!entityManager.isOpen()) {
            entityManager = emf.createEntityManager();
        }
        entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Article article = entityManager.find(Article.class, id);
        entityManager.remove(article);
        entityManager.getTransaction().commit();
        System.out.println("deleted article '" + article.getTitle() + "' is successful");

        entityManager.close();
        emf.close();
    }
}
