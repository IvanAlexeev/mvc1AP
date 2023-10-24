package alekseev.ru.course.controllers;

import alekseev.ru.course.dao.LibraryDAO;
import alekseev.ru.course.models.Book;
import alekseev.ru.course.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/library")
public class LibraryController {

    private final LibraryDAO libraryDAO;


    @Autowired
    public LibraryController(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }


    @GetMapping()
    public String showMainPage(){
        return "mainPage";
    }

    @GetMapping("/books")
    public String showBooks(Model model){
        model.addAttribute("books", libraryDAO.indexBooks());
        System.out.println("Успешно");
        return "books";
    }

    @GetMapping("/students")
    public String showStudents(Model model){
        model.addAttribute("students", libraryDAO.indexStudents());
        return "students";
    }

    @GetMapping("/books/add")
    public String showAddBookPage(@ModelAttribute("book") Book book){
        return "addBook";
    }

    @GetMapping("/students/add")
    public String showAddStudentPage(@ModelAttribute("person") Person person){
        return "addStudent";
    }

    @PostMapping("/books")
    public String addBook(@ModelAttribute("book") Book book){
        libraryDAO.saveBook(book);
        return "redirect:/library/books";
    }

    @PostMapping("/students")
    public String addStudent(@ModelAttribute("person") Person person){
        libraryDAO.saveStudent(person);
        return "redirect:/library/students";
    }

    @PatchMapping("/books/{id}")
    public String editBook(@ModelAttribute("book") Book book,@PathVariable("id") int id){
        libraryDAO.editBook(book, id);
        return "editBook";
    }

    @GetMapping("/books/{id}")
    public String showBook(Model model,@PathVariable("id") int id){
        Book book = libraryDAO.showBook(id);
        model.addAttribute("book", book);
        boolean bool = false;
        if (!Objects.isNull(book.getPerson_id())){
            bool = true;
            model.addAttribute("person", libraryDAO.showOwnerOfBook(id));

        }
        else {
            model.addAttribute("people", libraryDAO.indexStudents());
        }
        model.addAttribute("bool", bool);
        return "book";
    }

    @GetMapping("/students/{id}")
    public String editStudent(Model model,@PathVariable("id") int id){
        model.addAttribute("person", libraryDAO.showStudent(id));
        return "student";
    }

    @PostMapping("/books/{id}")
    public void spicifyPersonId(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        libraryDAO.specPersId(person, id);

    }
}
