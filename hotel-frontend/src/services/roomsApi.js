import axios from 'axios'

export async function fetchAvailableRooms(checkIn, checkOut) {
  const response = await axios.get('/rooms/available', {
    params: {
      checkIn,
      checkOut,
    },
  })

  return response.data
}

export async function createBooking(payload) {
  const response = await axios.post('/bookings', payload)
  return response.data
}

export async function fetchUserBookings(userId) {
  const response = await axios.get(`/bookings/user/${userId}`)
  return response.data
}

export async function cancelBooking(bookingId) {
  const response = await axios.delete(`/bookings/${bookingId}`)
  return response.data
}



