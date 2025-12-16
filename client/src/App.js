import React, { useState } from 'react';

/*
  Komponente u Reactu su funkcije (ili klase) koje vraćaju elemente
  za prikaz u korisničkom interfejsu (UI). Ovdje koristimo funkcijsku
  komponentu imena "App".
*/
function App() {
  /*
    useState je React Hook koji nam omogućava definisanje state-a
    unutar funkcijske komponente. State je objekt koji čuva
    podatke relevantne za prikaz i logiku.

    Ovdje kreiramo formData i setFormData. "formData" će sadržavati
    vrijednosti iz polja forme, a "setFormData" služi za ažuriranje tih vrijednosti.
  */
  const [formData, setFormData] = useState({
    ime: '',
    prezime: '',
    brojLicne: '',
    drzavljanstvo: '',
    brojTelefona: '',
    email: ''
  });

  /*
    handleChange se poziva svaki put kada se promijeni vrijednost
    u nekom polju unutar forme.

    e.target.name => ime polja (npr. "ime", "prezime", "brojLicne", itd.)
    e.target.value => nova vrijednost koju je korisnik unio.

    Koristimo sintaksu [e.target.name]: e.target.value da
    dinamički ažuriramo odgovarajuće polje u "formData" state-u.
  */
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  /*
    handleSubmit se poziva kad korisnik klikne na "Pošalji" (submit dugme).
    e.preventDefault() sprječava podrazumijevano ponašanje forme (osvježavanje stranice).

    Nakon toga, šaljemo POST zahtjev ka našem Express.js backendu (ruta '/api/submit')
    i u "body" postavljamo formData kao JSON.

    Kada dobijemo odgovor od servera, ili ispišemo poruku o uspjehu,
    ili resetujemo formu.
  */
  const handleSubmit = (e) => {
    e.preventDefault();

    fetch('/api/submit', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formData)
    })
        .then((res) => res.json())
        .then((data) => {
          alert(data.message);
          // Resetujemo formu nakon slanja
          setFormData({
            ime: '',
            prezime: '',
            brojLicne: '',
            drzavljanstvo: '',
            brojTelefona: '',
            email: ''
          });
        })
        .catch((err) => console.error('Greška prilikom slanja podataka:', err));
  };

  /*
    U return dijelu (JSX), definišemo strukturu i stil za našu formu.
    React koristi JSX (JavaScript XML), gdje pišemo kod koji izgleda
    kao HTML, ali se zapravo kompajlira u JavaScript.

    Svaki <input> polje je vezano na pripadajuće polje iz "formData"
    preko prop-a "value", i ima onChange koji poziva handleChange funkciju.

    onSubmit na <form> elementu povezan je sa handleSubmit funkcijom.
  */
  return (
      <div style={{ margin: '2rem auto', width: '300px', fontFamily: 'sans-serif' }}>
        <h1>Kontakt Forma</h1>
        {/* Sve što se nalazi unutar <form> se šalje handleSubmit funkciji kad se pritisne <button type="submit"> */}
        <form onSubmit={handleSubmit}>
          <div style={{ marginBottom: '1rem' }}>
            <label>Ime:</label><br/>
            <input
                type="text"
                name="ime"
                value={formData.ime}
                onChange={handleChange}
                required
            />
          </div>
          <div style={{ marginBottom: '1rem' }}>
            <label>Prezime:</label><br/>
            <input
                type="text"
                name="prezime"
                value={formData.prezime}
                onChange={handleChange}
                required
            />
          </div>
          <div style={{ marginBottom: '1rem' }}>
            <label>Broj lične karte:</label><br/>
            <input
                type="text"
                name="brojLicne"
                value={formData.brojLicne}
                onChange={handleChange}
                required
            />
          </div>
          <div style={{ marginBottom: '1rem' }}>
            <label>Državljanstvo:</label><br/>
            <input
                type="text"
                name="drzavljanstvo"
                value={formData.drzavljanstvo}
                onChange={handleChange}
                required
            />
          </div>
          <div style={{ marginBottom: '1rem' }}>
            <label>Broj telefona:</label><br/>
            <input
                type="text"
                name="brojTelefona"
                value={formData.brojTelefona}
                onChange={handleChange}
                required
            />
          </div>
          <div style={{ marginBottom: '1rem' }}>
            <label>Email:</label><br/>
            <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
            />
          </div>
          {/* Dugme koje šalje formu na server */}
          <button type="submit">Pošalji</button>
        </form>
      </div>
  );
}

export default App;
