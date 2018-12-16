
<?php  include('headerfooter/header.php'); ?>
    <!-- Content -->
    <div class="content" onload="getTradeRequest()">
      <h2 id="leagueNameTitle">League Name</h2>
      <hr>

      <div class="table table-hover">
        <div><h4>Pending League Joins</h4></div>
        <table cellspacing="0" cellpadding="0"  id="trade-req"></table>
      </div>
    </div>
    <script>
      var reqID;

      $(document).ready(function(){
        getAllLeagueJoinRequests();
      })

      function getAllLeagueJoinRequests()
      {
        var leagueid = GetCookie("leagueid");
        var url = "http://serenity.ist.rit.edu:22222/footballapi/Leagues/requests/"+leagueid;
        $.getJSON(url).done(function( json ) {
          //console.log( "JSON Data: " + json.allPlayers[ 5 ].fName );
          var content = "";
          var button = "";

          $.each( json.allLeagueRequests, function( i, item ) {
            $("#leagueNameTitle").html(item.leagueName);
            reqID = item.requestid;
            $( "<tr>" ).attr("id", "trade-"+i).attr("class", "shadow p-4 mb-5 bg-white rounded").appendTo( "#trade-req" );
            $( "<td>" ).attr("id", "trade-info-"+i).appendTo( "#trade-"+i );

            content = "<div> League: "+item.leagueName+"<div> <div> User: "+item.userName+"<div>";

            $( "#trade-info-"+i ).append( content );

            $( "<td>" ).attr("id", "trade-button-"+i).appendTo( "#trade-"+i );

            var userId = item.userid;
            var leagueId = item.leagueid;

            console.log(userId+" "+leagueId);
            //button = "<button type='button' class='btn btn-primary btn-sm' id='btn-accept' onclick='authenticate2("+userId+","+leagueId+", true)'>Accept</button>"+"<button type='button' class='btn btn-secondary btn-sm' id='btn-decline' onclick='authenticate2("+userId+","+leagueId+", false)'>Decline</button>";

            button = "<button type='button' class='btn btn-primary btn-sm' id='btn-accept' onclick='RR(true,"+reqID+")'>Accept</button>"+"<button type='button' class='btn btn-secondary btn-sm' id='btn-decline' onclick='RR(false,"+reqID+")'>Decline</button>";

            $( "#trade-button-"+i ).append( button );
          //console.log( item.fName );
          });
        });
      };

      function RR(accOrdec,reqIDs) {
        var userid = GetCookie("session");
        console.log("userid = " + userid);
        $.getJSON("http://serenity.ist.rit.edu:22222/footballapi/User/respondToLeagueReq?userid="+userid+"&requestid="+reqIDs+"&accept="+accOrdec).done(function(json) {
          console.log("JSON Data: " + json.successfullLeagueRequestResponse);

          //checks JSON if successfulLogin == true
          if (accOrdec == true) {
            //makes session cookie and bind it to user
            alert("You've accepted the Join");
            location.reload();
          } else {
            alert("You've denied the Join");
            location.reload();
          }
        });
      };
      
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>
