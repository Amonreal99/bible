// Search.tsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './Search.module.css';


const Search: React.FC = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const navigate = useNavigate();

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSearchQuery(event.target.value); // Update the search query state
    };

    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        const input = searchQuery.trim();
        if (!input) return;
        const m = input.match(/^\s*([1-3]?\s*[A-Za-z]+(?:\s+[A-Za-z]+)*)\s+(\d+)(?::(\d+))?\s*$/);
        if (m) {
            const book = m[1].replace(/\s+/g, ' ').trim();
            const chap = m[2];
            const ver = m[3];
            const url = ver
                ? `/search-page?q=${encodeURIComponent(book)}&a=${chap}&b=${ver}`
                : `/search-page?q=${encodeURIComponent(book)}&a=${chap}`;
            navigate(url);
        } else {
            navigate(`/search-page?q=${encodeURIComponent(input)}`);
        }
    };

    return (
        <div className={styles.searchContainer}>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Book Chapter:Verse"
                    value={searchQuery}
                    onChange={handleChange}
                    className={styles.searchInput}
                />
                <button type="submit" className={styles.searchButton}>Go
                </button>
            </form>
        </div>
    );
};

export default Search;