//Jednostavan server koji posluzuje applikacije 
//na zadanom portu

const http = require('http');

const server = http.createServer((req,res)=>{
    res.statusCode = 200; //uspjesno kreiran server
    res.setHeader('Content-Type', 'text/plain');
    res.end('Pozdrav Node.js!');
})

server.listen(3000, ()=> {
    console.log('Server pokrenut na http://localhost:3000/');
});