const express = require("express")
const app = express()

app.use(express.urlencoded())
app.get("/",function (req, res){

    res.send("hello world")
})
app.get("/get",function (req, res){
    const content = req.query.content
    console.log(content)
    const { exec } = require("child_process");
    const state = "java -jar /Users/liuzeyu/Documents/JavaProject/cs622/target/search.jar "+content
    exec(state, (error, stdout, stderr) => {
        if (error) {
            console.log(`error: ${error.message}`);
            return;
        }
        return "Video is downloading..."
    });
})
app.listen(2000)
