const express = require('express');
const app = express();

app.get('/',(req,res)=>{
    res.send('Dobrodošli studenti na Express aplikaciju!');
})

app.listen(3000,()=>{
    console.log("Express server je pokrenut na http://localhost:3000");
});