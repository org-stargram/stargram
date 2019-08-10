


$.ajax({
  dataType : 'jsonp',
  jsonpCallback: "callback",
  url: "https://code.jquery.com/jquery-latest.min.js",
  success: function(data) {
    console.log(data);
  }
});

