
import styles from './Home.module.css'


export default function Home() {




    return (
        <div className={styles.container}>
            <div className={styles.innerContainer}>
                <section className={styles.section}>
                    <h3>Old Testament</h3>
                    <ul className={styles.cols3}>
                        <li>Genesis</li><li>Exodus</li><li>Leviticus</li><li>Numbers</li><li>Deuteronomy</li>
                        <li>Joshua</li><li>Judges</li><li>Ruth</li><li>1 Samuel</li><li>2 Samuel</li>
                        <li>1 Kings</li><li>2 Kings</li><li>1 Chronicles</li><li>2 Chronicles</li><li>Ezra</li>
                        <li>Nehemiah</li><li>Esther</li><li>Job</li><li>Psalms</li><li>Proverbs</li>

                    </ul>
                </section>
                <section className={styles.section}>
                    <h3>Old Testament</h3>
                    <ul className={styles.cols3}>
                        <li>Ecclesiastes</li><li>Song of Solomon</li><li>Isaiah</li><li>Jeremiah</li>
                        <li>Lamentations</li><li>Ezekiel</li><li>Daniel</li><li>Hosea</li><li>Joel</li>
                        <li>Amos</li><li>Obadiah</li><li>Jonah</li><li>Micah</li><li>Nahum</li>
                        <li>Habakkuk</li><li>Zephaniah</li><li>Haggai</li><li>Zechariah</li><li>Malachi</li>
                    </ul>
                </section>


                <section className={styles.section}>
                    <h3>New Testament</h3>
                    <ul className={styles.cols3}>
                        <li>Matthew</li><li>Mark</li><li>Luke</li><li>John</li><li>Acts</li><li>Romans</li>
                        <li>1 Corinthians</li><li>2 Corinthians</li><li>Galatians</li><li>Ephesians</li>
                        <li>Philippians</li><li>Colossians</li><li>1 Thessalonians</li><li>2 Thessalonians</li>
                        <li>1 Timothy</li><li>2 Timothy</li><li>Titus</li><li>Philemon</li><li>Hebrews</li>
                        <li>James</li><li>1 Peter</li><li>2 Peter</li><li>1 John</li><li>2 John</li>
                        <li>3 John</li><li>Jude</li><li>Revelation</li>
                    </ul>
                </section>
            </div>
        </div>


    )
}; 