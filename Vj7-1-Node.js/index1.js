const express = require('express');
const app = express();

let tasks = [
    {id: 1, naslov: 'Prvi task', zavrsen: false},
    {id: 2, naslov: 'Drugi task', zavrsen: true},
    {id: 3, naslov: 'Treci task', zavrsen: true}
];

app.use(express.json()); //sucelje za parsiranje JSON

app.get('/tasks',(req,res)=>{
    res.json(tasks);
})

app.post('/tasks',(req,res)=>{
    const newTask = req.body;
    tasks.push(newTask);
    res.status(201).json(newTask);
})

app.listen(3000,()=>{
    console.log("Express server je pokrenut na http://localhost:3000");
});