import entities.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

public class App {
    public static void main(String[] args) {

        //Add
//        var a = new Author();
//        a.setFullName("Sugeng Rahayu");
//        a.setAge(32);
//        addAuthor(a);

        //update
//        Author at = getAuthorById(3);
//        at.setFullName("Rindu");
//        updateAuthor(at);
//        getAllAuthor();

        //delete
//        Author ath = getLastAuthor();
//        deleteAuthor(ath);
        getAllAuthor();
    }

    public static void addAuthor(Author author) {
        Transaction tx = null;
        try (Session s = openSession()){
            tx = s.beginTransaction();
            s.save(author);
            tx.commit();
        } catch (Exception e) {
            finalizeTransaction(tx);
            e.printStackTrace();
        }
    }

    public static void updateAuthor(Author author) {
        Transaction tx = null;
        try (Session s = openSession()){
            tx = s.beginTransaction();
            s.update(author);
            tx.commit();
        } catch (Exception e) {
            finalizeTransaction(tx);
            e.printStackTrace();
        }
    }

    public static void deleteAuthor(Author author) {
        Transaction tx = null;
        try (Session s = openSession()){
            tx = s.beginTransaction();
            Query query = s.createQuery("delete from Author where id = :id");
            query.setParameter("id", author.getId());
            int exec = query.executeUpdate();
            //s.getTransaction().commit();
            System.out.println(exec);
            tx.commit();
        } catch (Exception e) {
            finalizeTransaction(tx);
            e.printStackTrace();
        }
    }

    public static void getAllAuthor() {
        try (Session s = openSession()){
            List<Author> students = s.createQuery("from Author", Author.class).list();
            students.forEach(c -> System.out.println(c.getFullName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Author getLastAuthor() {
        Author author = new Author();
        try (Session s = openSession()){
            author = s.createQuery("from Author  order by id desc", Author.class).setMaxResults(1).getSingleResult();
            System.out.println(author.getFullName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }

    public static Author getAuthorById(int id) {
        Author author = new Author();
        try (Session s = openSession()){
            author = s.find(Author.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }

    private static Session openSession(){
        Session s = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();
        return s;
    }

    private static void finalizeTransaction(Transaction transaction) {
        try {
            if(transaction != null || transaction.getStatus() == TransactionStatus.COMMITTED)
            {
                transaction.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
