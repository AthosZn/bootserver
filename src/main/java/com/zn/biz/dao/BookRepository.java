package com.zn.biz.dao;

import com.zn.biz.entity.Book;

public interface BookRepository {
    Book getByIsbn(String isbn);

    String test(String isbn);
}
