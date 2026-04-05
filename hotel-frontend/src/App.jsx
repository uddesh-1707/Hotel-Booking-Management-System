import { useState } from 'react'
import RoomSearchPage from './pages/RoomSearchPage'
import BookingsPage from './pages/BookingsPage'
import './App.css'

function App() {
  const [activePage, setActivePage] = useState('search')

  return (
    <>
      <header className="top-nav">
        <button
          type="button"
          className={activePage === 'search' ? 'active' : ''}
          onClick={() => setActivePage('search')}
        >
          Search Rooms
        </button>
        <button
          type="button"
          className={activePage === 'bookings' ? 'active' : ''}
          onClick={() => setActivePage('bookings')}
        >
          My Bookings
        </button>
      </header>

      {activePage === 'search' ? <RoomSearchPage /> : <BookingsPage />}
    </>
  )
}

export default App
