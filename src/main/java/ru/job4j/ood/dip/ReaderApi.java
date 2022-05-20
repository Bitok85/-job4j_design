package ru.job4j.ood.dip;

/**
 * Здесь имитируем библиотеку, которая содержит файлы книг обработанные для чтения для разных читалок.
 * Книг например не 2, а на несколько порядков больше. И по какой-то причине, в части книг надо поменять способ
 * обработки. Поэтому здесь нужна абстракция между библиотекой и различными способами обработки книг. Тогда библиотека
 * будет зависеть от конкретных реализаций.
 *
 */

public interface ReaderApi {

    ReaderApi getBook(Book book);

}

class LitresBook implements ReaderApi {

    @Override
    public LitresBook getBook(Book book) {
        return null;
    }
}

class ReadEraBook implements ReaderApi {

    @Override
    public ReadEraBook getBook(Book book) {
        return null;
    }
}

class Book {
}

class Library {

    ReadEraBook readEraBookGet = new ReadEraBook();
    LitresBook litresBookGet = new LitresBook();

    Book book1 = new Book();
    Book book2 = new Book();

    ReadEraBook readEraBook1 = readEraBookGet.getBook(book1);
    LitresBook getLitresBook2 = litresBookGet.getBook(book2);

}