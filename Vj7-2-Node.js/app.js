const express = require('express');
const app = express();

let osobe = [
    {id: 1, ime: 'Narcisa', email: 'narcisa.hadzajlic@unze.ba'},
    {id: 2, ime: 'Samir', email: 'samir.lemes@unze.ba'},
    {id: 3, ime: 'Nevzudin', email: 'nevzudin.buzadjija@unze.ba'}
];

app.use(express.json()); //sucelje za parsiranje JSON

app.get('/osobe',(req,res)=>{
    res.json(osobe);
})

app.post('/osobe',(req,res)=>{
    const novaOsoba = req.body;
    tasks.push(novaOsoba);
    res.status(201).json(novaOsoba);
})

app.put('/osobe/:id', (req,res)=>{
    const id = parseInt(req.params.id);
    const updateOsoba = req.body;

    const IndeksOsobe = osobe.findIndex((osoba)=> osoba.id === id);

    if(IndeksOsobe !== -1){
        osobe[IndeksOsobe] = {id, ...updateOsoba};
        res.json(osobe[IndeksOsobe]);
    }else{
        res.status(404).json({error: 'Osoba nije pronađena.'});
    }
})

app.listen(3000,()=>{
    console.log("Express server je pokrenut na http://localhost:3000");
});