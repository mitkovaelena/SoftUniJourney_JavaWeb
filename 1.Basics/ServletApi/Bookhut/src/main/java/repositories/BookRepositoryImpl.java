package repositories;

import entities.Book;

import java.util.*;

//ToDo implement db
public class BookRepositoryImpl implements BookRepository {
    private static BookRepository bookRepository;
    private List<Book> books;

    private BookRepositoryImpl() {
        this.books = new ArrayList<>();
    }

    public static BookRepository getInstance() {
        if (bookRepository == null) {
            bookRepository = new BookRepositoryImpl();
        }

        return bookRepository;
    }

    @Override
    public void saveBook(Book book) {
        if (this.findBookByTitle(book.getTitle()) == null) {
            this.books.add(book);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(this.books);
    }

    @Override
    public void deleteBookByTitle(String title) {
        this.books.removeIf(b -> b.getTitle().equals(title));
    }

    @Override
    public Book findBookByTitle(String title) {
        Optional<Book> bookOptional =  this.books.stream().filter(b -> b.getTitle().equals(title)).findFirst();
        return bookOptional.orElse(null);
    }
}
