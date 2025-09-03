package com.bible.scripture;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BibleService {

    private final BibleRepository bibleRepository;

    public BibleService(BibleRepository bibleRepository) {
        this.bibleRepository = bibleRepository;
    }

    public Optional<Bible> findById(Integer id) {
        return bibleRepository.findById(id);
    }

    public List<Bible> getByBookChapterVerse(String book, Integer chap, Integer ver) {
        return bibleRepository.findByBookNameIgnoreCaseAndChapterAndVerse(book, chap, ver);
    }

    public List<Bible> getByBookChapter(String book, Integer chap) {
        return bibleRepository.findByBookNameIgnoreCaseAndChapterOrderByVerseAsc(book, chap);
    }

    public List<Bible> getByBookVerse(String book, Integer ver) {
        return bibleRepository.findByBookNameIgnoreCaseAndVerse(book, ver);
    }

    public List<Bible> getBook(String book) {
        return bibleRepository.findByBookNameContainingIgnoreCase(book);
    }

    public List<Bible> getByChapter(Integer chap) {
        return bibleRepository.findByChapter(chap);
    }

    public Bible random() {
        String book = bibleRepository.pickRandomBook();
        Integer chapter = bibleRepository.pickRandomChapter(book);
        return bibleRepository.pickRandomVerse(book, chapter);
    }

    public Bible randomInBook(String bookName) {
        Integer chapter = bibleRepository.pickRandomChapter(bookName);
        return bibleRepository.pickRandomVerse(bookName, chapter);
    }

    public Bible randomInBookChapter(String bookName, Integer chapter) {
        return bibleRepository.pickRandomVerse(bookName, chapter);
    }
}
