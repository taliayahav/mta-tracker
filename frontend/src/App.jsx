import { useState, useEffect } from 'react'
import './App.css'

function App() {
  const [stopId, setStopId] = useState('')
  const [arrivals, setArrivals] = useState([])
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  const fetchArrivals = async (id) => {
    if (!id) return
    setLoading(true)
    setError('')
    try {
      const res = await fetch(`http://localhost:8080/api/arrivals/${id}`)
      if (!res.ok) throw new Error('Stop not found')
      const data = await res.json()
      setArrivals(data)
    } catch (e) {
      setError(e.message)
      setArrivals([])
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    if (!stopId) return
    fetchArrivals(stopId)
    const interval = setInterval(() => fetchArrivals(stopId), 15000)
    return () => clearInterval(interval)
  }, [stopId])

  return (
    <div className="app">
      <h1>NYC Subway Tracker</h1>
      <input
        type="text"
        placeholder="Enter stop ID (e.g. 127N)"
        onKeyDown={(e) => {
          if (e.key === 'Enter') setStopId(e.target.value.trim())
        }}
      />
      {loading && <p className="status">Loading...</p>}
      {error && <p className="status error">{error}</p>}
      {arrivals.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>Route</th>
              <th>Stop</th>
              <th>Arriving In</th>
            </tr>
          </thead>
          <tbody>
            {arrivals
              .sort((a, b) => a.minutesUntilArrival - b.minutesUntilArrival)
              .map((a, i) => (
                <tr key={i}>
                  <td>{a.routeId}</td>
                  <td>{a.stopId}</td>
                  <td>{a.minutesUntilArrival === 0 ? 'Now' : `${a.minutesUntilArrival} min`}</td>
                </tr>
              ))}
          </tbody>
        </table>
      )}
    </div>
  )
}

export default App