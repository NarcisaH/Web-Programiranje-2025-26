const express = require('express');
const cors = require('cors');
const fs = require('fs');

const app = express();
app.use(cors());
app.use(express.json());

// POST ruta za prijavu
app.post('/api/submit', (req, res) => {
  const { ime, prezime, brojLicne, drzavljanstvo, brojTelefona, email } = req.body;

  // Pripremamo red za csv fajl
  const noviRed = `${ime},${prezime},${brojLicne},${drzavljanstvo},${brojTelefona},${email}\n`;

  // Dodavanje u fajl 'prijavljeni.csv'
  fs.appendFile('prijavljeni.csv', noviRed, (err) => {
    if (err) {
      console.error('Greška pri upisu u fajl:', err);
      return res.status(500).json({ success: false, message: 'Došlo je do greške pri spremanju podataka.' });
    }
    return res.json({ success: true, message: 'Podaci uspješno sačuvani u CSV fajl.' });
  });
});

// Pokretanje servera
const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
  console.log(`Server je pokrenut na portu ${PORT}`);
});

