package com.bible.scripture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bible")
public class Bible {

    @Id
    @Column(name = "id", unique = true)
    private Integer idNum;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_number")
    private Integer bookNum;

    @Column(name = "chapter")
    private Integer chapter;

    @Column(name = "verse")
    private Integer verse;

    @Column(name = "text", columnDefinition = "TEXT")
    private String verseText;

    @Column(name = "translation")
    private String translation;

    public Bible() {
    }

    public Bible(Integer idNum) {
        this.idNum = idNum;
    }

    public Integer getIdNum() {
        return idNum;
    }

    public void setIdNum(Integer idNum) {
        this.idNum = idNum;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public Integer getChapter() {
        return chapter;
    }

    public void setChapter(Integer chapter) {
        this.chapter = chapter;
    }

    public Integer getVerse() {
        return verse;
    }

    public void setVerse(Integer verse) {
        this.verse = verse;
    }

    public String getVerseText() {
        return verseText;
    }

    public void setVerseText(String verseText) {
        this.verseText = verseText;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
