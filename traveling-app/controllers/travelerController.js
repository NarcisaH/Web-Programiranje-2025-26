const { Traveler, travelers } = require('../models/traveler');

exports.getAllTravelers = (req, res) => {
    res.json(travelers);
};

exports.addTraveler = (req, res) => {
    const { id, name, age } = req.body;
    travelers.push(new Traveler(id, name, age));
    res.status(201).json({ message: 'Traveler added successfully.' });
};

exports.updateTraveler = (req, res) => {
    const { id } = req.params;
    const { name, age } = req.body;

    const traveler = travelers.find(t => t.id === id);
    if (traveler) {
        traveler.name = name;
        traveler.age = age;
        res.json({ message: 'Traveler updated successfully.' });
    } else {
        res.status(404).json({ message: 'Traveler not found.' });
    }
};

exports.deleteTraveler = (req, res) => {
    const { id } = req.params;

    const index = travelers.findIndex(t => t.id === id);
    if (index !== -1) {
        travelers.splice(index, 1);
        res.json({ message: 'Traveler deleted successfully.' });
    } else {
        res.status(404).json({ message: 'Traveler not found.' });
    }
};

