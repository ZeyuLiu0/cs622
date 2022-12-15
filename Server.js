const express = require("express")
const {exec} = require("child_process");
const app = express()

app.use(express.urlencoded())
app.get("/",function (req, res){

    res.send("hello world")
})
app.get("/getyoutube",function (req, res){
    const content = req.query.content
    console.log(content)
    const type = "youtube"
    const { exec } = require("child_process");
    const state = "java -jar /Users/liuzeyu/Documents/JavaProject/cs622/target/search.jar "+type+" "+content
    exec(state, (error, stdout, stderr) => {
        if (error) {
            console.log(`error: ${error.message}`);
            return;
        }
        return "Video is downloading..."
    });
})

app.get("/getig",function (req, res){
    const content = req.query.content
    const username = req.query.user
    const password = req.query.password
    const type = "ig"
    console.log(content)
    const { exec } = require("child_process");
    const state = "java -jar /Users/liuzeyu/Documents/JavaProject/cs622/target/search.jar "+type+" "+username+" "+password+" "+content
    exec(state, (error, stdout, stderr) => {
        if (error) {
            console.log(`error: ${error.message}`);
            return;
        }
        return "Video is downloading..."
    });
})

app.get("/gettiktok",function (req, res){
    const content = req.query.content
    const type = "tt"
    console.log(content)
    const { exec } = require("child_process");
    const state = "java -jar /Users/liuzeyu/Documents/JavaProject/cs622/target/search.jar "+type+" "+content
    exec(state, (error, stdout, stderr) => {
        if (error) {
            console.log(`error: ${error.message}`);
            return;
        }
        return "Video is downloading..."
    });
})
app.listen(2000)
