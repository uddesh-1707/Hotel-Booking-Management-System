function RoomTable({ rooms, onBook, bookingRoomId }) {
  if (!rooms.length) {
    return <p className="empty-state">No rooms available for selected dates.</p>
  }

  return (
    <table className="rooms-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Room Number</th>
          <th>Type</th>
          <th>Price</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        {rooms.map((room) => (
          <tr key={room.id}>
            <td>{room.id}</td>
            <td>{room.roomNumber}</td>
            <td>{room.type}</td>
            <td>{room.price}</td>
            <td>
              <button
                type="button"
                onClick={() => onBook(room.id)}
                disabled={bookingRoomId === room.id}
              >
                {bookingRoomId === room.id ? 'Booking...' : 'Book'}
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  )
}

export default RoomTable


