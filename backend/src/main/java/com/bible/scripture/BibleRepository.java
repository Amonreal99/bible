package com.bible.scripture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BibleRepository extends JpaRepository<Bible, Integer> {

    List<Bible> findByBookNameIgnoreCaseAndChapterAndVerse(String bookName, Integer chapter, Integer verse);

    List<Bible> findByBookNameIgnoreCaseAndChapterOrderByVerseAsc(String bookName, Integer chapter);

    List<Bible> findByBookNameIgnoreCaseAndVerse(String bookName, Integer verse);

    List<Bible> findByBookNameContainingIgnoreCase(String bookName);

    List<Bible> findByChapter(Integer chapter);

    @Query(value = """
      SELECT book_name
      FROM bible
      GROUP BY book_name
      ORDER BY RANDOM()          -- use RAND() on MySQL/H2
      LIMIT 1
      """, nativeQuery = true)
    String pickRandomBook();

    // 2) Random chapter within a book
    @Query(value = """
      SELECT chapter
      FROM bible
      WHERE LOWER(book_name) = LOWER(:bookName)
      GROUP BY chapter
      ORDER BY RANDOM()          -- use RAND() on MySQL/H2
      LIMIT 1
      """, nativeQuery = true)
    Integer pickRandomChapter(@Param("bookName") String bookName);

    // 3) Random verse within a book+chapter
    @Query(value = """
      SELECT *
      FROM bible
      WHERE LOWER(book_name) = LOWER(:bookName)
        AND chapter = :chapter
      ORDER BY RANDOM()          -- use RAND() on MySQL/H2
      LIMIT 1
      """, nativeQuery = true)
    Bible pickRandomVerse(@Param("bookName") String bookName,
            @Param("chapter") Integer chapter);

}
