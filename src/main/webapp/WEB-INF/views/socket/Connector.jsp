<%--
  Created by IntelliJ IDEA.
  User: Tristan
  Date: 16/6/30
  Time: 下午10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="width: 500px;height: 800px;border: 1px solid black;" id="content">

</div>
<input type="text" name="file" id="file">
<button id="send" onclick="sendMsg()">send</button>


<script>
    var socket = new WebSocket('ws://localhost:8080/moondust/socketserver/core/connect/action');

    // 打开Socket
    socket.onopen = function (event) {

        // 监听消息
        socket.onmessage = function (event) {
            document.getElementById("content").innerHTML=document.getElementById("content").innerHTML+'<br>'+event.data
        };

        // 监听Socket的关闭
        socket.onclose = function (event) {
            console.log('Client notified socket has closed', event);
        };

        // 关闭Socket....
        //socket.close()
    };


    function sendMsg() {
        var str = document.getElementById("file").value;
        if (str.length > 0) {
            socket.send(str)
        }
    }


</script>


</body>
</html>
