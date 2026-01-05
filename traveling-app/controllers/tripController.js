const { Trip, trips } = require('../models/trip');

exports.getAllTrips = (req, res) => {
    res.json(trips);
};

exports.addTrip = (req, res) => {
    const { id, destination, duration, travelerId } = req.body;
    trips.push(new Trip(id, destination, duration, travelerId));
    res.status(201).json({ message: 'Trip added successfully.' });
};

exports.updateTrip = (req, res) => {
    const { id } = req.params;
    const { destination, duration, travelerId } = req.body;

    const trip = trips.find(t => t.id === id);
    if (trip) {
        trip.destination = destination;
        trip.duration = duration;
        trip.travelerId = travelerId;
        res.json({ message: 'Trip updated successfully.' });
    } else {
        res.status(404).json({ message: 'Trip not found.' });
    }
};

exports.deleteTrip = (req, res) => {
    const { id } = req.params;

    const index = trips.findIndex(t => t.id === id);
    if (index !== -1) {
        trips.splice(index, 1);
        res.json({ message: 'Trip deleted successfully.' });
    } else {
        res.status(404).json({ message: 'Trip not found.' });
    }
};
