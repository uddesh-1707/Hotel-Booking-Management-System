import { useState } from 'react'
import RoomTable from '../components/RoomTable'
import { createBooking, fetchAvailableRooms } from '../services/roomsApi'

function RoomSearchPage() {
  const [checkIn, setCheckIn] = useState('')
  const [checkOut, setCheckOut] = useState('')
  const [rooms, setRooms] = useState([])
  const [searched, setSearched] = useState(false)
  const [loading, setLoading] = useState(false)
  const [bookingRoomId, setBookingRoomId] = useState(null)
  const [error, setError] = useState('')

  const handleSearch = async (event) => {
    event.preventDefault()
    setError('')

    if (!checkIn || !checkOut) {
      setError('Please select both check-in and check-out dates.')
      return
    }

    if (checkIn >= checkOut) {
      setError('Check-out date must be after check-in date.')
      return
    }

    try {
      setLoading(true)
      const availableRooms = await fetchAvailableRooms(checkIn, checkOut)
      setRooms(availableRooms)
      setSearched(true)
    } catch (apiError) {
      setError(apiError.response?.data?.message || 'Failed to fetch available rooms.')
      setRooms([])
      setSearched(true)
    } finally {
      setLoading(false)
    }
  }

  const handleBookRoom = async (roomId) => {
    if (!checkIn || !checkOut) {
      alert('Please search with check-in and check-out dates first.')
      return
    }

    try {
      setBookingRoomId(roomId)
      const response = await createBooking({
        userId: 1,
        roomId,
        checkIn,
        checkOut,
      })

      alert(response?.message || 'Booking created successfully.')
    } catch (apiError) {
      const message = apiError.response?.data?.message || 'Booking failed.'
      alert(message)
    } finally {
      setBookingRoomId(null)
    }
  }

  return (
    <main className="page-container">
      <h1>Find Available Rooms</h1>

      <form className="search-form" onSubmit={handleSearch}>
        <div className="field-group">
          <label htmlFor="checkIn">Check-in date</label>
          <input
            id="checkIn"
            type="date"
            value={checkIn}
            onChange={(event) => setCheckIn(event.target.value)}
          />
        </div>

        <div className="field-group">
          <label htmlFor="checkOut">Check-out date</label>
          <input
            id="checkOut"
            type="date"
            value={checkOut}
            onChange={(event) => setCheckOut(event.target.value)}
          />
        </div>

        <button type="submit" disabled={loading}>
          {loading ? 'Searching...' : 'Search'}
        </button>
      </form>

      {error && <p className="error-message">{error}</p>}

      {searched && !error && (
        <RoomTable
          rooms={rooms}
          onBook={handleBookRoom}
          bookingRoomId={bookingRoomId}
        />
      )}
    </main>
  )
}

export default RoomSearchPage


