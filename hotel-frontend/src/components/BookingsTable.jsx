function BookingsTable({ bookings, onCancel, cancellingBookingId }) {
  if (!bookings.length) {
    return <p className="empty-state">No bookings found for user 1.</p>
  }

  return (
    <table className="bookings-table">
      <thead>
        <tr>
          <th>Room Number</th>
          <th>Check-in</th>
          <th>Check-out</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        {bookings.map((booking) => {
          const isCancelled = booking.status === 'CANCELLED'
          const isLoading = cancellingBookingId === booking.id

          return (
            <tr key={booking.id}>
              <td>{booking.room?.roomNumber || '-'}</td>
              <td>{booking.checkInDate}</td>
              <td>{booking.checkOutDate}</td>
              <td>{booking.status}</td>
              <td>
                <button
                  type="button"
                  onClick={() => onCancel(booking.id)}
                  disabled={isCancelled || isLoading}
                >
                  {isLoading ? 'Cancelling...' : 'Cancel'}
                </button>
              </td>
            </tr>
          )
        })}
      </tbody>
    </table>
  )
}

export default BookingsTable

