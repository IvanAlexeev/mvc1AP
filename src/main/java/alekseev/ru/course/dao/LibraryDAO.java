package alekseev.ru.course.dao;

import alekseev.ru.course.models.Book;
import alekseev.ru.course.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
public class LibraryDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LibraryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> indexBooks(){
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Person> indexStudents(){
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void saveBook(Book book){
        jdbcTemplate.update("insert into book(title, author, year) values(?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void saveStudent(Person person){
        jdbcTemplate.update("insert into person(fullname, birthday) values (?, ?)", person.getFullname(), person.getBirthday());
    }

    public void editBook(Book book, int id){
        jdbcTemplate.update("update book set person_id=?, title=?, author=?, year=? where id=?", book.getPerson_id(), book.getTitle(), book.getAuthor(), book.getYear(), id);
    }


    public Book showBook(int id){
        return jdbcTemplate.query("select * from book where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public Person showStudent(int id){
        return jdbcTemplate.query("select * from person where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Person showOwnerOfBook(int idBook){
        return jdbcTemplate.query("select * from person where id=?", new Object[]{showBook(idBook).getPerson_id()}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void specPersId(Person person, int id){
        jdbcTemplate.update("update book set person_id=? where id=?", person.getId(), id);
    }
}
