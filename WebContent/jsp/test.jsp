<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <script type="text/javascript">
    function convert()
    {
	  var text=document.getElementById("url").value;
	  var exp = /(\b(https?|ftp|file):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/ig;
	  var text1=text.replace(exp, "<a href='$1'>$1</a>");
	  var exp2 =/(^|[^\/])(www\.[\S]+(\b|$))/gim;
	  document.getElementById("converted_url").innerHTML=text1.replace(exp2, '$1<a target="_blank" href="http://$2">$2</a>');
    }
  </script>
</head>

<body>
  
  <h1>Convert URL Text Into Clickable HTML Link Using JavaScript</h1>
  <textarea id="url" Placeholder="Enter Some Text With Links">
  </textarea>
  <input type="button" value="Convert" onclick="convert();">
  <p id="converted_url"></p>

</body>
</html>