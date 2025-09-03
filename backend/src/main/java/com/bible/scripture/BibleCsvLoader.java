package com.bible.scripture;

import java.io.InputStreamReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

@Component
public class BibleCsvLoader implements CommandLineRunner {

    private final BibleRepository bibleRepository;

    public BibleCsvLoader(BibleRepository bibleRepository) {
        this.bibleRepository = bibleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bibleRepository.count() > 0) {
            System.out.println("Bible table already has data. Skipping import.");
            return;
        }

        try (CSVReader reader = new CSVReader(
                new InputStreamReader(getClass().getResourceAsStream("/bible.csv")))) {

            // skip header
            String[] line = reader.readNext();

            while ((line = reader.readNext()) != null) {
                Bible bible = new Bible();
                bible.setIdNum(Integer.parseInt(line[0]));
                bible.setBookName(line[1]);
                bible.setBookNum(Integer.parseInt(line[2]));
                bible.setChapter(Integer.parseInt(line[3]));
                bible.setVerse(Integer.parseInt(line[4]));
                bible.setVerseText(line[5]);
                bible.setTranslation("ASV"); // or from CSV if you included it
                bibleRepository.save(bible);
            }
        }

    }
}
