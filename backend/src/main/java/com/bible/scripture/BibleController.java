package com.bible.scripture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bible")
public class BibleController {

    private final BibleService bibleService;

    @Autowired
    public BibleController(BibleService bibleService) {
        this.bibleService = bibleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bible> get(@PathVariable Integer id) {
        return ResponseEntity.of(bibleService.findById(id));
    }

    @GetMapping("/search")
    public List<Bible> search(@RequestParam(required = false) String bookName, @RequestParam(required = false) Integer chapter, @RequestParam(required = false) Integer verse) {
        if (bookName != null && chapter != null && verse != null) {
            return bibleService.getByBookChapterVerse(bookName, chapter, verse);
        } else if (bookName != null && chapter != null) {
            return bibleService.getByBookChapter(bookName, chapter);
        } else if (bookName != null && verse != null) {
            return bibleService.getByBookVerse(bookName, verse);
        } else if (chapter != null) {
            return bibleService.getByChapter(chapter);
        } else if (bookName != null) {
            return bibleService.getBook(bookName);
        } else {
            return bibleService.getByBookChapterVerse(bookName, chapter, verse);
        }
    }

    @GetMapping("/random")
    public Bible random(@RequestParam(required = false) String bookName, @RequestParam(required = false) Integer chapter) {
        if (bookName != null && chapter != null) {
            return bibleService.randomInBookChapter(bookName, chapter);
        }
        if (bookName != null) {
            return bibleService.randomInBook(bookName);
        }
        return bibleService.random();
    }
}
