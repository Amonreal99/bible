import { useEffect, useState } from "react";
import styles from "./NavBar.module.css"
import Search from "./Search";
import { Link, useLocation } from "react-router-dom";



export default function NavBar() {
    const [searchOpen, setSearchOpen] = useState(false);
    const location = useLocation();

    useEffect(() => {
        setSearchOpen(false);
    }, [location.pathname, location.search]);



    const handleSearchClick = () => {
        setSearchOpen(prev => !prev);
    };


    return (
        <div className={styles.container}>
            <div className={styles.innerContainer}>
                The Bible
            </div>
            <div className={styles.lists}>
                <Link to='/home' className={styles.innerLists}>
                    Home</Link>

                <Link to='VerseOfTheDay' className={styles.innerLists}>Verse of the Day</Link>
                <button onClick={handleSearchClick} className={styles.innerLists}>
                    Search
                </button>


            </div>

            {searchOpen && <Search />}
        </div >

    )
}
