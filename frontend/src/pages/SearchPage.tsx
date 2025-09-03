import { useSearchParams } from 'react-router-dom';
import axios from 'axios';
import { useEffect, useState } from 'react';
import type { Bible } from '../types/types';
import styles from './SearchPage.module.css';

const SearchPage: React.FC = () => {
    const [sp, setSp] = useSearchParams();
    const bookName = sp.get('q') || undefined;
    const chapter = sp.get('a') ? Number(sp.get('a')) : undefined;
    const verse = sp.get('b') ? Number(sp.get('b')) : undefined;
    const [results, setResults] = useState<Bible[]>([]);
    const show = !!(bookName || chapter !== undefined || verse !== undefined);




    const chap = sp.get('a') ? Number(sp.get('a')) : undefined;
    const ver = sp.get('b') ? Number(sp.get('b')) : undefined;
    const [buttonResults, setButtonResults] = useState<Bible[]>([]);

    const [searchOpen, setSearchOpen] = useState(false);

    // Canonical ASV order
    const BOOKS = [
        "Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy", "Joshua", "Judges", "Ruth",
        "1 Samuel", "2 Samuel", "1 Kings", "2 Kings", "1 Chronicles", "2 Chronicles", "Ezra", "Nehemiah",
        "Esther", "Job", "Psalms", "Proverbs", "Ecclesiastes", "Song of Solomon", "Isaiah", "Jeremiah",
        "Lamentations", "Ezekiel", "Daniel", "Hosea", "Joel", "Amos", "Obadiah", "Jonah", "Micah",
        "Nahum", "Habakkuk", "Zephaniah", "Haggai", "Zechariah", "Malachi", "Matthew", "Mark", "Luke",
        "John", "Acts", "Romans", "1 Corinthians", "2 Corinthians", "Galatians", "Ephesians", "Philippians",
        "Colossians", "1 Thessalonians", "2 Thessalonians", "1 Timothy", "2 Timothy", "Titus", "Philemon",
        "Hebrews", "James", "1 Peter", "2 Peter", "1 John", "2 John", "3 John", "Jude", "Revelation"
    ];


    const normalize = (s: string) => s.replace(/\s+/g, ' ').trim().toLowerCase();

    const bookIndex = (name?: string) => {
        if (!name) return -1;
        const target = normalize(name);
        return BOOKS.findIndex(b => normalize(b) === target);
    };

    const nextBook = (name?: string) => {
        const i = bookIndex(name);
        if (i < 0) return undefined;
        return BOOKS[Math.min(i + 1, BOOKS.length - 1)]; // clamp at Revelation
    };

    const prevBook = (name?: string) => {
        const i = bookIndex(name);
        if (i < 0) return undefined;
        return BOOKS[Math.max(i - 1, 0)]; // clamp at Genesis
    };




    useEffect(() => {
        if (!bookName && !chapter && !verse) {
            setResults([]);

            return;
        }
        const params: Record<string, string | number> = {};
        if (bookName) params.bookName = bookName;
        if (chapter !== undefined) params.chapter = chapter;
        if (verse !== undefined) params.verse = verse;


        axios.get<Bible[]>('/api/bible/search', { params })
            .then(res => setResults(res.data))
            .catch(err => console.error('Search error:', err));
    }, [bookName, chapter, verse]);

    useEffect(() => {
        if (bookName === undefined && chap === undefined && ver === undefined) {
            setButtonResults([]);
            setSearchOpen(false);
            return;
        }

        const params: Record<string, string | number> = {};
        if (bookName) params.bookName = bookName;
        if (chap !== undefined) params.chapter = chap;
        if (ver !== undefined) params.verse = ver;

        axios.get<Bible[]>('/api/bible/search', { params })
            .then(res => setButtonResults(res.data))
            .catch(err => console.error("Next error:", err));
    }, [bookName, chap, ver]);

    const setCDE = (q?: string, a?: number, b?: number) => {
        const next = new URLSearchParams(sp);
        if (q) next.set('q', String(q));
        if (a !== undefined) next.set('a', String(a));
        if (b !== undefined) next.set('b', String(b));
        setSp(next); // pushes a new entry; use { replace:true } if you don't want history entries
    };



    const handleNext = () => {
        // default if missing: book 1, chapter 1, start at verse 1
        if (chap === undefined && ver === undefined) {
            const nb = nextBook(bookName);
            if (!nb) return;
            setCDE(nb)
        }
        else if (ver === undefined) {
            const nb = bookName;
            const nd = (chap ?? 1) + 1;
            setCDE(nb, nd);
        }
        else if (ver !== undefined) {
            const nb = bookName;
            const nd = chap;
            const ne = (ver ?? 1) + 1;
            setCDE(nb, nd, ne);
        }
        setSearchOpen(true);
    }


    const handleBack = () => {
        if (chap === undefined && ver === undefined) {
            const pb = prevBook(bookName);
            if (!pb) return;
            setCDE(pb)
        }
        else if (ver === undefined) {
            const nb = bookName;
            const current = chap ?? 1;
            const nd = current > 1 ? current - 1 : 1;
            setCDE(nb, nd);
        }
        else if (ver !== undefined) {
            const nb = bookName;
            const nd = chap;
            const current = ver ?? 1;
            const ne = current > 1 ? current - 1 : 1;
            setCDE(nb, nd, ne);
        }


        setSearchOpen(true);
    };


    return (
        <div style={{ color: 'black', fontSize: '16px', fontWeight: '500' }}>

            {show && <h2>Search Results</h2>}
            <p>{bookName ? bookName.toUpperCase() : ''} {chapter !== undefined ? `${chapter}` : ''}{chapter !== undefined && verse !== undefined ? `:${verse}` : ''}</p>
            {!searchOpen && (
                <ul>
                    {results.map((v, i) => (
                        <li key={i}>
                            {v.bookName} {v.chapter}:{v.verse} — {v.verseText}
                        </li>
                    ))}
                </ul>)}
            {searchOpen && (
                <ul>
                    {buttonResults.map((v, i) => (
                        <li key={i}>
                            {v.bookName} {v.chapter}: {v.verse}-{v.verseText}
                        </li>
                    ))}
                </ul>)}
            <div className={styles.buttonOuterContainer}>
                <div className={styles.buttonInnerContainer}>
                    <button onClick={handleBack} className={styles.backNext}>back</button>

                    <button onClick={handleNext} className={styles.backNext}>next</button>
                </div>

            </div>

        </div>
    );
};

export default SearchPage;
