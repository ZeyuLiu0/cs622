const button = document.getElementById("button")
const text = document.getElementById("text")

button.addEventListener("click",()=>{
    const content = text.innerText;
    $.ajax({
        url: 'http://localhost:2000',
        type: 'get',
        data: {
            email: 'email@example.com',
            message: 'hello world!'
        }
    });
})