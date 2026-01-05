let trips = [];

class Trip {
    constructor(id, destination, duration, travelerId) {
        this.id = id;
        this.destination = destination;
        this.duration = duration;
        this.travelerId = travelerId;
    }
}

module.exports = { Trip, trips };
