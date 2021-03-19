const express= require('express');
const app = express();
const env = require('dotenv');
const mongoose= require('mongoose');
const router= require('./routes');
const path = require('path');
const cors= require('cors');
const { request } = require('http');

env.config();
app.use(express.json());


const mongoURL= `mongodb+srv://${process.env.MONGO_DB_USER}:${process.env.MONGO_DB_PASSWORD}@cluster0.6yyqf.mongodb.net/${process.env.MONGO_DB_DATABASE}?retryWrites=true&w=majority`;
const localMongo= `mongodb://localhost/${process.env.MONGO_DB_DATABASE}`;
mongoose.connect(localMongo,
 {
     useNewUrlParser: true,
     useUnifiedTopology: true
    }
).then(() => {
    console.log('Database Connected');
});

app.use(cors());

app.use('/',router);
app.get('/', (req, res) => {
    res.send('The Server is working awesome, Welcome to AILights');
});

app.listen(process.env.PORT, () => {
    console.log(`App listening on port ${process.env.PORT} `);
});