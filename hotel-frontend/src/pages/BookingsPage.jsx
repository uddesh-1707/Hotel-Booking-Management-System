import { useEffect, useState } from 'react'
import BookingsTable from '../components/BookingsTable'
import { cancelBooking, fetchUserBookings } from '../services/roomsApi'

function BookingsPage() {
  const [bookings, setBookings] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [cancellingBookingId, setCancellingBookingId] = useState(null)

  const loadBookings = async () => {
    try {
      setLoading(true)
      setError('')
      const data = await fetchUserBookings(1)
      setBookings(data)
    } catch (apiError) {
      setError(apiError.response?.data?.message || 'Failed to load bookings.')
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    loadBookings()
  }, [])

  const handleCancelBooking = async (bookingId) => {
    try {
      setCancellingBookingId(bookingId)
      const response = await cancelBooking(bookingId)
      alert(response?.message || 'Booking cancelled successfully.')
      await loadBookings()
    } catch (apiError) {
      const message = apiError.response?.data?.message || 'Failed to cancel booking.'
      alert(message)
    } finally {
      setCancellingBookingId(null)
    }
  }

  return (
    <main className="page-container">
      <h1>My Bookings</h1>

      {loading && <p>Loading bookings...</p>}
      {error && <p className="error-message">{error}</p>}

      {!loading && !error && (
        <BookingsTable
          bookings={bookings}
          onCancel={handleCancelBooking}
          cancellingBookingId={cancellingBookingId}
        />
      )}
    </main>
  )
}

export default BookingsPage

