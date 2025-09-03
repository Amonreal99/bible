import NavBar from "./components/NavBar";
import Home from "./pages/Home";
import SearchPage from "./pages/SearchPage";
import './App.css';
import { Routes, Route } from 'react-router-dom';
import VerseDay from './pages/VerseDay';

const App: React.FC = () => {


  return (
    <>
      {/*} <Home theBible={theBible} />*/}
      <NavBar />
      <Routes>
        <Route path='/home' element={<Home />} />
        <Route path='/VerseOfTheDay' element={<VerseDay />} />
        <Route path="/search-page" element={<SearchPage />} />
      </Routes>
    </>
  )
}

export default App
