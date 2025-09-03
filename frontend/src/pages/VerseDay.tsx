
import styles from '../pages/VerseDay.module.css';
import { useEffect, useState } from 'react';
import type { Bible } from '../types/types';
import axios from 'axios';

const VerseDay: React.FC = () => {
    const [verse, setVerse] = useState<Bible | null>(null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        let cancelled = false;
        (async () => {
            try {
                console.log("API BASE URL:", import.meta.env.VITE_API_BASE_URL);
                const { data } = await axios.get<Bible>(`${import.meta.env.VITE_API_BASE_URL}/bible/random`); // or pass bookName/chapter
                if (!cancelled) {
                    setVerse(data);
                    setError(null);
                }
            } catch (e) {
                if (!cancelled) setError("Could not load verse");
                console.error("Verse of the Day error", e);
            }
        })();
        return () => { cancelled = true; };
    }, []);

    if (error) return <p>{error}</p>;
    if (!verse) return <p>Loading…</p>;
    return (
        <div className={styles.outerContainer}>
            <p style={{ fontWeight: "bolder", fontSize: "30px" }}> {verse.bookName} {verse.chapter}:{verse.verse}</p>
            <p style={{ fontWeight: "bold", fontSize: "18px" }}> — {verse.verseText}</p>

        </div>
    )

}; export default VerseDay;