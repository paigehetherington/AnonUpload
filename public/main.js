function getFiles(filesData) { //filesData call it anything you want but specifiy data type
       for (var i in filesData) {
       var elem = $("<a>"); //elem is the link that's why a href
       elem.attr("href", "files/" + filesData[i].filename);  //in an AL
       elem.text(filesData[i].comment); //what the link says
       $("#fileList").append(elem);
       var elem2 = $("<br>");
       $("#fileList").append(elem2);

       }
}

$.get("/files", getFiles); //callback fxn