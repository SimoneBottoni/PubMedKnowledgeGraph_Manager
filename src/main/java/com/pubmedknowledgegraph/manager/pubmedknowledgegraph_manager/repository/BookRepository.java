package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.BookKey;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, BookKey> {

    @Query("SELECT b.bookKey.pmid FROM Book b")
    List<String> getAllPmids();

    @Query("SELECT b.bookKey.pmid FROM Book b WHERE b.bookKey.pmid=:pmid")
    List<String> getPmidByPmid(@Param("pmid") String pmid);

    @Query("SELECT b.bookKey.pmid FROM Book b WHERE b.bookKey.pmid=:pmid AND b.bookKey.version=:version")
    List<String> getPmidByPmidAndVersion(@Param("pmid") String pmid, @Param("version") String version);

    @Query("SELECT b FROM Book b WHERE b.bookKey.pmid=:pmid AND b.bookKey.version=:version")
    List<Book> getAllBooksByPmidANDVersion(@Param("pmid") String pmid, @Param("version") String version);

    @Transactional
    @Modifying
    @Query("UPDATE Book b set b.cancellationDate = :cancellationDate WHERE b.bookKey.pmid=:pmid AND b.bookKey.version=:version")
    void updateCancellationDate(@Param("cancellationDate") String cancellationDate, @Param("pmid") String pmid, @Param("version") String version);

    List<Book> getBooksByFileName(@Param("fileName") String fileName);

}
