package ra.edu.business.model;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String categoty;
    private int quantityBook;
    private int publishYear;

    public Book() {

    }

    public Book(int bookId, String title, String author, String categoty, int quantityBook, int publishYear) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.categoty = categoty;
        this.quantityBook = quantityBook;
        this.publishYear = publishYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategoty() {
        return categoty;
    }

    public void setCategoty(String categoty) {
        this.categoty = categoty;
    }

    public int getQuantityBook() {
        return quantityBook;
    }

    public void setQuantityBook(int quantityBook) {
        this.quantityBook = quantityBook;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }
}
