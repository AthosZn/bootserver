package com.zn.biz.dao;

import com.zn.aop.CacheableMem;
import com.zn.biz.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SimpleBookRepository implements BookRepository {

    @Override
    @Cacheable(value = "cache_space_name", key = "'ass'+#a0",
     condition = "!T(org.springframework.transaction.support.TransactionSynchronizationManager).synchronizationActive")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    @Override
//    @Cacheable(value = "cache_space_name", key = "'test'+#a0")
    @CacheableMem(key= "'" + "zn" + "'+#isbn",expiredTime = 0)
    public String test(String isbn) {
        if(isbn.equals("1")){
            return "hello";
        }else{
            return "fuck";
        }
    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }




}
