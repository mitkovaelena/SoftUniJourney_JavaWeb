package services;

import entities.Book;
import models.bindingModels.AddBookModel;
import models.viewModels.ViewBookModel;
import repositories.BookRepository;
import repositories.BookRepositoryImpl;
import utils.MappingUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BookServiceImpl implements BookService {
    private BookRepository repository;

    public BookServiceImpl() {
        this.repository = BookRepositoryImpl.getInstance();
    }

    @Override
    public void saveBook(AddBookModel addBookModel) {
        Book book = MappingUtil.convert(addBookModel, Book.class);
        book.setCreationDate(new Date());
        book.setId(UUID.randomUUID());
        this.repository.saveBook(book);
    }

    @Override
    public List<ViewBookModel> getAllBooks() {
        List<Book> books = repository.getAllBooks();
        return MappingUtil.convert(books, ViewBookModel.class);
    }

    @Override
    public ViewBookModel findBookByTitle(String title) {
        Book book = repository.findBookByTitle(title);
        return MappingUtil.convert(book, ViewBookModel.class);
    }

    @Override
    public void deleteBookByTitle(String title) {
        repository.deleteBookByTitle(title);
    }
}
