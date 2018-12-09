<?php  include('headerfooter/header.php'); ?>
    <!-- Content -->
    <div class="content">
      <h2>Player Stats</h2>
      <hr>

      <select class="stat-filter" name="stat-filter">
        <h5>View Stats By: </h5><option value="overall">Season Ranking</option>
        <option value="QB">QB</option>
        <option value="WR">WR</option>
        <option value="RB">RB</option>
        <option value="TE">TE</option>
        <option value="Defense">Defense</option>
        <option value="Kicker">Kicker</option>
      </select>

      <div class="table table-hover">
        <table id="player-table" cellspacing="0" cellpadding="0">
          <tr>
            <th class="first-col">Player</th>
            <th>Team</th>
            <!-- <th>Bye Week</th> -->
            <th>Pass Attempts</th>
            <th>Pass Completions</th>
            <th>Pass Yards</th>
            <!-- <th>INTs</th> -->
            <th>Rushing Attempts</th>
            <th>Rushing Yards</th>
            <th>Recptions</th>
            <th>Recptions Yards</th>
            <th>TDs</th>
            <!-- <th>Fantasy Points</th> -->
          </tr>
          <script>
            //stat sort by value
            var statSortBy = "overall"; //base value

            //call the setData right away with base data
            setData(statSortBy);
            //check if sort by value update/change
            $(".stat-filter").change(function(){
              statSortBy = $('.stat-filter').val();
              setData();
            });
            function setData(){
              //check value of statSortBy to know which data to load
              var reqBase = "http://serenity.ist.rit.edu:22222/footballapi/Players/"; //base header/endpoint for retriving data
              var endpoint;
              switch (statSortBy) {
                case statSortBy = "overall":
                  removeTable();
                  setTableWithData(reqBase);
                  break;
                case statSortBy = "QB":
                  removeTable();
                  endpoint = statSortBy.toLowerCase();
                  reqBase += endpoint;
                  setTableWithData(reqBase);
                  break;
                case statSortBy = "RB":
                  removeTable();
                  endpoint = statSortBy.toLowerCase();
                  reqBase += endpoint;
                  setTableWithData(reqBase);
                  break;
                case statSortBy = "WR":
                  removeTable();
                  endpoint = statSortBy.toLowerCase();
                  reqBase += endpoint;
                  setTableWithData(reqBase);
                  break;
                case statSortBy = "TE":
                  removeTable();
                  endpoint = statSortBy.toLowerCase();
                  reqBase += endpoint;
                  setTableWithData(reqBase);
                  break;
                case statSortBy = "Defense":
                  removeTable();
                  endpoint = statSortBy.toLowerCase();
                  reqBase = "http://serenity.ist.rit.edu:22222/footballapi/";
                  setTableWithData(reqBase);
                  break;
                case statSortBy = "Kicker":
                  removeTable();
                  endpoint = "k";
                  reqBase += endpoint;
                  setTableWithData(reqBase);
                  break;
              }
            };

            function setTableWithData(newRequestHeader){
              //console.log("set table");
              //console.log("remove the table before setting new data");
              //console.log(newRequestHeader);
              $.getJSON(newRequestHeader)
              .done(function( json ) {
                
                $("<tr>").attr("id", "row-head").appendTo("#player-table");
                $("<th>").appendTo("#row-head").attr("class", "first-col").text("Player");
                $("<th>").appendTo("#row-head").text("Team");
                $("<th>").appendTo("#row-head").text("Pass Attempts");
                $("<th>").appendTo("#row-head").text("Pass Completions");
                $("<th>").appendTo("#row-head").text("Pass Yards");
                $("<th>").appendTo("#row-head").text("Rushing Attempts");
                $("<th>").appendTo("#row-head").text("Rushing Yards");
                $("<th>").appendTo("#row-head").text("Receptions");
                $("<th>").appendTo("#row-head").text("Receptions Yards");
                $("<th>").appendTo("#row-head").text("TDs");

                $.each( json.allPlayers, function( i, item ) {
                  $( "<tr>" ).attr("id", "row-"+i).appendTo( "#player-table" );
                  $( "<td>" ).appendTo( "#row-"+i ).attr("class","first-col").text( item.fName + " " + item.lName);
                  $( "<td>" ).appendTo( "#row-"+i ).text( item.team);
                  $( "<td>" ).appendTo( "#row-"+i ).text( item.passatt );
                  $( "<td>" ).appendTo( "#row-"+i ).text( item.passcomp);
                  $( "<td>" ).appendTo( "#row-"+i ).text( item.passyds);
                  $( "<td>" ).appendTo( "#row-"+i ).text( item.rushatt);
                  $( "<td>" ).appendTo( "#row-"+i ).text( item.rushyds);
                  $( "<td>" ).appendTo( "#row-"+i ).text( item.rec);
                  $( "<td>" ).appendTo( "#row-"+i ).text( item.recyds);
                  $( "<td>" ).appendTo( "#row-"+i ).text( item.rectds);
                //console.log( item.fName );
                });
              });
            };

            function removeTable(){
              //console.log("remove");
              $("#player-table tr").remove();
            };
          </script>
        </table>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>
