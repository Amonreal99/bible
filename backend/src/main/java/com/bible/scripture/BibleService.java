package com.bible.scripture;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BibleService {

    private final BibleRepository bibleRepository;

    @Autowired
    public BibleService(BibleRepository bibleRepository) {
        this.bibleRepository = bibleRepository;
    }

    public Optional<Bible> findById(Integer id) {
        return bibleRepository.findById(id);
    }

    public List<Bible> getByBookChapterVerse(String book, Integer chap, Integer ver) {
        return bibleRepository.findAll().stream().filter(bible -> bible.getBookName().toLowerCase().contains(book.toLowerCase()) && chap.equals(bible.getChapter()) && ver.equals(bible.getVerse())).collect(Collectors.toList());
    }

    public List<Bible> getByBookChapter(String book, Integer chap) {
        return bibleRepository.findAll().stream().filter(bible -> bible.getBookName().toLowerCase().contains(book.toLowerCase()) && chap.equals(bible.getChapter())).collect(Collectors.toList());
    }

    public List<Bible> getByBookVerse(String book, Integer ver) {
        return bibleRepository.findAll().stream().filter(bible -> bible.getBookName().toLowerCase().contains(book.toLowerCase()) && ver.equals(bible.getVerse())).collect(Collectors.toList());
    }

    public List<Bible> getBook(String book) {
        return bibleRepository.findAll().stream().filter(bible -> bible.getBookName().toLowerCase().contains(book.toLowerCase())).collect(Collectors.toList());
    }

    public List<Bible> getByChapter(Integer chap) {
        return bibleRepository.findAll().stream().filter(bible -> chap.equals(bible.getChapter())).collect(Collectors.toList());
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
