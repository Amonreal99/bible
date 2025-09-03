package com.bible.scripture;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bible")
public class BibleController {

    private final BibleService bibleService;

    public BibleController(BibleService bibleService) {
        this.bibleService = bibleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bible> get(@PathVariable Integer id) {
        return ResponseEntity.of(bibleService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(required = false) String bookName,
            @RequestParam(required = false) Integer chapter,
            @RequestParam(required = false) Integer verse) {

        String book = (bookName == null || bookName.isBlank()) ? null : bookName.trim();

        if (book != null && chapter != null && verse != null) {
            return ResponseEntity.ok(bibleService.getByBookChapterVerse(book, chapter, verse));
        } else if (book != null && chapter != null) {
            return ResponseEntity.ok(bibleService.getByBookChapter(book, chapter));
        } else if (book != null && verse != null) {
            return ResponseEntity.ok(bibleService.getByBookVerse(book, verse));
        } else if (chapter != null) {
            return ResponseEntity.ok(bibleService.getByChapter(chapter));
        } else if (book != null) {
            return ResponseEntity.ok(bibleService.getBook(book));
        } else {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Provide at least one of: bookName, chapter, verse"));
        }
    }

    @GetMapping("/random")
    public ResponseEntity<Bible> random(
            @RequestParam(required = false) String bookName,
            @RequestParam(required = false) Integer chapter) {

        Bible result;
        if (bookName != null && chapter != null) {
            result = bibleService.randomInBookChapter(bookName.trim(), chapter);
        } else if (bookName != null) {
            result = bibleService.randomInBook(bookName.trim());
        } else {
            result = bibleService.random();
        }
        return ResponseEntity.of(Optional.ofNullable(result));
    }
}
