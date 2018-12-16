<?php  include('headerfooter/header.php'); ?>
    <!-- Content -->
    <div class="content">
      <h2>Player Stats</h2>
      <hr>
	  <form id ="playersearch">
      <select class="stat-filter" name="stat-filter">
        <h5>View Stats By: </h5>
        <option value="QB">QB</option>
        <option value="WR">WR</option>
        <option value="RB">RB</option>
        <option value="TE">TE</option>
        <option value="Defenses">D/ST</option>
        <option value="K">K</option>
      </select>
	  <input type="text" name = "Name" id="searchName">
	  <input type="submit" name="Search" value="Search" id="searchBtn" onclick="searchByName()">
	  </form>
      <div class="table table-hover">
        <table id="player-table" cellspacing="0" cellpadding="0">
          <tr>
            <th class="first-col">Player</th>
            <!-- <th>Fantasy Points</th> -->
          </tr>
        </table>
      </div>
    </div>
    <script>
      //stat sort by value
      var statSortBy = "QB"; //base value

      //call the setData right away with base data
      setData("http://serenity.ist.rit.edu:22222/footballapi/Players/");
      //check if sort by value update/change
      $(".stat-filter").change(function(){
        statSortBy = $('.stat-filter').val();
        setData("http://serenity.ist.rit.edu:22222/footballapi/Players/");
      });

      function setData(head){
        //check value of statSortBy to know which data to load
        var reqBase = head; //base header/endpoint for retriving data
        var endpoint;
        switch (statSortBy) {
          case statSortBy = "QB":
            removeTable();
            endpoint = statSortBy;
            reqBase += endpoint;
            setTableWithDataQB(reqBase);
            break;
          case statSortBy = "RB":
            removeTable();
            endpoint = statSortBy;
            reqBase += endpoint;
            setTableWithDataRB(reqBase);
            break;
          case statSortBy = "WR":
            removeTable();
            endpoint = statSortBy;
            reqBase += endpoint;
            setTableWithDataWR(reqBase);
            break;
          case statSortBy = "TE":
            removeTable();
            endpoint = statSortBy;
            reqBase += endpoint;
            //need TE
            setTableWithDataWR(reqBase);
            break;
          case statSortBy = "Defenses":
            removeTable();
            endpoint = statSortBy;
            reqBase += endpoint;
            setTableWithDataDef(reqBase);
            break;
          case statSortBy = "K":
            removeTable();
            endpoint = statSortBy;
            reqBase += endpoint;
            setTableWithDataK(reqBase);
            break;
        }
      };

      function setTableWithDataQB(newRequestHeader){
        //console.log("set table");
        //console.log("remove the table before setting new data");
        //console.log(newRequestHeader);
        $.getJSON(newRequestHeader)
        .done(function( json ) {
          
          $("<tr>").attr("id", "row-head").appendTo("#player-table");
          $("<th>").appendTo("#row-head").attr("class", "first-col").text("Player");
          $("<th>").appendTo("#row-head").text("Games");
          $("<th>").appendTo("#row-head").text("Team");
          $("<th>").appendTo("#row-head").text("Position");
          $("<th>").appendTo("#row-head").text("#");
          $("<th>").appendTo("#row-head").text("Pass Attempts");
          $("<th>").appendTo("#row-head").text("Pass Completions");
          $("<th>").appendTo("#row-head").text("Pass Yards");
          $("<th>").appendTo("#row-head").text("TD");
          $("<th>").appendTo("#row-head").text("INT");
          $("<th>").appendTo("#row-head").text("Rushing Attempts");
          $("<th>").appendTo("#row-head").text("Rushing Yards");
          $("<th>").appendTo("#row-head").text("TD");
          $("<th>").appendTo("#row-head").text("FUM");

          $.each( json.players, function( i, item ) {
            $( "<tr>" ).attr("id", "row-"+i).appendTo( "#player-table" );
            $( "<td>" ).appendTo( "#row-"+i ).attr("class","first-col").text( item.fName + " " + item.lName);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.gPlayed);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.team);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.pos);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.jerseyNumber);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.passatt );
            $( "<td>" ).appendTo( "#row-"+i ).text( item.passcomp);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.passyds);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.passtds);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.intthr);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rushatt);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rushyds);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rushtd);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.fum);
          //console.log( item.fName );
          });
        });
      };

      function setTableWithDataRB(newRequestHeader){
        //console.log("set table");
        //console.log("remove the table before setting new data");
        //console.log(newRequestHeader);
        $.getJSON(newRequestHeader)
        .done(function( json ) {
          
          $("<tr>").attr("id", "row-head").appendTo("#player-table");
          $("<th>").appendTo("#row-head").attr("class", "first-col").text("Player");
          $("<th>").appendTo("#row-head").text("Games");
          $("<th>").appendTo("#row-head").text("Team");
          $("<th>").appendTo("#row-head").text("Position");
          $("<th>").appendTo("#row-head").text("#");
          $("<th>").appendTo("#row-head").text("Rush Attempts");
          $("<th>").appendTo("#row-head").text("Rush Yards");
          $("<th>").appendTo("#row-head").text("TD");
          $("<th>").appendTo("#row-head").text("Receptions");
          $("<th>").appendTo("#row-head").text("Receiving Yards");
          $("<th>").appendTo("#row-head").text("TD");
          $("<th>").appendTo("#row-head").text("FUM");

          $.each( json.players, function( i, item ) {
            $( "<tr>" ).attr("id", "row-"+i).appendTo( "#player-table" );
            $( "<td>" ).appendTo( "#row-"+i ).attr("class","first-col").text( item.fName + " " + item.lName);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.gPlayed);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.team);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.pos);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.jerseyNumber);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rushatt );
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rushyds);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rushtd);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rec);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.recyds);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rectd);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.fum);
          //console.log( item.fName );
          });
        });
      };

      function setTableWithDataWR(newRequestHeader){
        //console.log("set table");
        //console.log("remove the table before setting new data");
        //console.log(newRequestHeader);
        $.getJSON(newRequestHeader)
        .done(function( json ) {
          
          $("<tr>").attr("id", "row-head").appendTo("#player-table");
          $("<th>").appendTo("#row-head").attr("class", "first-col").text("Player");
          $("<th>").appendTo("#row-head").text("Games");
          $("<th>").appendTo("#row-head").text("Team");
          $("<th>").appendTo("#row-head").text("Position");
          $("<th>").appendTo("#row-head").text("#");
          $("<th>").appendTo("#row-head").text("Receptions");
          $("<th>").appendTo("#row-head").text("Receiving Yards");
          $("<th>").appendTo("#row-head").text("TD");
          $("<th>").appendTo("#row-head").text("Rushing Attempts");
          $("<th>").appendTo("#row-head").text("Rushing Yards");
          $("<th>").appendTo("#row-head").text("TD");
          $("<th>").appendTo("#row-head").text("FUM");

          $.each( json.players, function( i, item ) {
            $( "<tr>" ).attr("id", "row-"+i).appendTo( "#player-table" );
            $( "<td>" ).appendTo( "#row-"+i ).attr("class","first-col").text( item.fName + " " + item.lName);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.gPlayed);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.team);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.pos);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.jerseyNumber);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rec);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.recyds);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rectd);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rushatt );
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rushyds);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.rushtd);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.fum);
          //console.log( item.fName );
          });
        });
      };

      function setTableWithDataDef(newRequestHeader){
        //console.log("set table");
        //console.log("remove the table before setting new data");
        //console.log(newRequestHeader);
        $.getJSON(newRequestHeader)
        .done(function( json ) {
          
          $("<tr>").attr("id", "row-head").appendTo("#player-table");
          $("<th>").appendTo("#row-head").text("Team");
          $("<th>").appendTo("#row-head").text("Points Allowed");
          $("<th>").appendTo("#row-head").text("Sacks");
          $("<th>").appendTo("#row-head").text("INT");
          $("<th>").appendTo("#row-head").text("FUM");
          $("<th>").appendTo("#row-head").text("Safety");
          $("<th>").appendTo("#row-head").text("INT TD");
          $("<th>").appendTo("#row-head").text("FUM TD");
          $("<th>").appendTo("#row-head").text("Kick Return TD");
          $("<th>").appendTo("#row-head").text("Punt Return TD");
          $("<th>").appendTo("#row-head").text("Field Goal Block");
          $("<th>").appendTo("#row-head").text("Extra Point Block");

          $.each( json.allDefenses, function( i, item ) {
            $( "<tr>" ).attr("id", "row-"+i).appendTo( "#player-table" );
            $( "<td>" ).appendTo( "#row-"+i ).text( item.team);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.pa);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.sck);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.intc);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.fum);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.sfty);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.inttd);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.fumtd);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.krtd);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.prtd);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.kblk);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.xpblk);
          //console.log( item.fName );
          });
        });
      };
      function setTableWithDataK(newRequestHeader){
        //console.log("set table");
        //console.log("remove the table before setting new data");
        //console.log(newRequestHeader);
        $.getJSON(newRequestHeader)
        .done(function( json ) {
          
          $("<tr>").attr("id", "row-head").appendTo("#player-table");
          $("<th>").appendTo("#row-head").attr("class", "first-col").text("Player");
          $("<th>").appendTo("#row-head").text("Games");
          $("<th>").appendTo("#row-head").text("Team");
          $("<th>").appendTo("#row-head").text("Position");
          $("<th>").appendTo("#row-head").text("#");
          $("<th>").appendTo("#row-head").text("Field Goal Attempts");
          $("<th>").appendTo("#row-head").text("Field Goals Made");
          $("<th>").appendTo("#row-head").text("Extra Point Attempts");
          $("<th>").appendTo("#row-head").text("Extra Points Made");

          $.each( json.players, function( i, item ) {
            $( "<tr>" ).attr("id", "row-"+i).appendTo( "#player-table" );
            $( "<td>" ).appendTo( "#row-"+i ).attr("class","first-col").text( item.fName + " " + item.lName);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.gPlayed);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.team);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.pos);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.jerseyNumber);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.fgatt );
            $( "<td>" ).appendTo( "#row-"+i ).text( item.fgmd);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.xpatt);
            $( "<td>" ).appendTo( "#row-"+i ).text( item.xpmd);

          //console.log( item.fName );
          });
        });
      };

      function removeTable(){
        //delete table
        $("#player-table tr").remove();
      };

      function searchByName(){
        //search by the users entered name
        var st = $("#searchName").val();
        
        var res = st.split(" ");
        var fname = res[0];
        var lname = res[1];
        var newEndPoint = "http://serenity.ist.rit.edu:22222/footballapi/Players/" + statSortBy + "/" + fname + "/" + lname + "/"

        alert(newEndPoint);
        if(statSortBy == "QB"){
          removeTable();
          setTableWithDataQB(newEndPoint);      
        }
        if(statSortBy == "WR"){
          removeTable();
          setTableWithDataWR(newEndPoint);      
        }
        if(statSortBy == "RB"){
          removeTable();
          setTableWithDataRB(newEndPoint);      
        }
        if(statSortBy == "TE"){
          removeTable();
          setTableWithDataWR(newEndPoint);
        }
        if(statSortBy == "Defenses"){
          removeTable();
          setTableWithDataDef(newEndPoint);
        }
        if(statSortBy == "K"){
          removeTable();
          setTableWithDataK(newEndPoint);
        }

      };
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>
