const { Agency, agencies } = require('../models/agency');

exports.getAllAgencies = (req, res) => {
    res.json(agencies);
};

exports.addAgency = (req, res) => {
    const { id, name, location } = req.body;
    agencies.push(new Agency(id, name, location));
    res.status(201).json({ message: 'Agency added successfully.' });
};

exports.updateAgency = (req, res) => {
    const { id } = req.params;
    const { name, location } = req.body;

    const agency = agencies.find(a => a.id === id);
    if (agency) {
        agency.name = name;
        agency.location = location;
        res.json({ message: 'Agency updated successfully.' });
    } else {
        res.status(404).json({ message: 'Agency not found.' });
    }
};

exports.deleteAgency = (req, res) => {
    const { id } = req.params;

    const index = agencies.findIndex(a => a.id === id);
    if (index !== -1) {
        agencies.splice(index, 1);
        res.json({ message: 'Agency deleted successfully.' });
    } else {
        res.status(404).json({ message: 'Agency not found.' });
    }
};

