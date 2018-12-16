<?php  include('headerfooter/header.php'); ?>
    <!-- Content -->
    <div class="content">
      <h2>League Name</h2>
      <hr>

      <div class="container">
        <div class="row">
          <div class="col-2">
            <div class="col-sm-2 label">Owner</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="owners">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
          <div class="col-4">
            <div class="col-sm-2 label">QB</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setQBs">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
          <div class="col-4">
            <div class="col-sm-2 label">WR</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setWRs1">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-2"></div>
          <div class="col-4">
            <div class="col-sm-2 label">Defense</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setDEF">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
          <div class="col-4">
            <div class="col-sm-2 label">WR</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setWRs2">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-2"></div>
          <div class="col-4">
            <div class="col-sm-2 label">Kicker</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setKICKER">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
          <div class="col-4">
            <div class="col-sm-2 label">RB</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setRBs1">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-2"></div>
          <div class="col-4">
            <div class="col-sm-2 label">TE</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setTEs">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
          <div class="col-4">
            <div class="col-sm-2 label">RB</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setRBs2">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-2"></div>
          <div class="col-4">
            <div class="col-sm-2 label">Flex</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setFLEX">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
          <div class="col-4"></div>
        </div>
        <div class="row">
          <div class="col-2"><button type="button" class="btn btn-primary btn-sm ml-2 mr-2" onclick="setTeamBtnClick()">Set Team</button></div>
        </div>
      </div>
    </div>
    <script>
      var reqBaseQB = "http://serenity.ist.rit.edu:22222/footballapi/Players/qb";
      var reqBaseRB = "http://serenity.ist.rit.edu:22222/footballapi/Players/rb";
      var reqBaseWR = "http://serenity.ist.rit.edu:22222/footballapi/Players/wr";
      var reqBaseKicker = "http://serenity.ist.rit.edu:22222/footballapi/Players/k";
      var reqBaseTE = "http://serenity.ist.rit.edu:22222/footballapi/Players/te";
      var reqBaseDefense = "http://serenity.ist.rit.edu:22222/footballapi/Players/Defenses";
      var reqBaseFlex = "http://serenity.ist.rit.edu:22222/footballapi/Players/flex";

      $(document).ready(function(){
        getOwners();
        setPlayers(reqBaseQB,'#setQBs');
        setPlayers(reqBaseRB,'#setRBs1');
        setPlayers(reqBaseRB,'#setRBs2');
        setPlayers(reqBaseWR,'#setWRs1');
        setPlayers(reqBaseWR,'#setWRs2');
        setPlayers(reqBaseTE,'#setTEs');
        setDef(reqBaseDefense,'#setDEF');
        setPlayers(reqBaseKicker,'#setKICKER');
        setPlayers(reqBaseFlex,'#setFLEX');
      })

      //get players name and populate the dropdowns
      function setPlayers(requestString, dropDownID){
        $.getJSON(requestString)
        .done(function( json ) {
          $.each( json.players, function( i, item ) {
            if(dropDownID == '#setDEF'){
              $(dropDownID).append($('<option></option>').val(item.team).html(item.team));
            } else {
              $(dropDownID).append($('<option></option>').val(item.playerid).html(item.fName + " " + item.lName));
            }
          });
        });
      };

      function setDef(requestString, dropDownID){
        $.getJSON(requestString)
        .done(function( json ) {
          $.each( json.allDefenses, function( i, item ) {
            if(dropDownID == '#setDEF'){
              $(dropDownID).append($('<option></option>').val(item.team).html(item.team));
            } else {
              $(dropDownID).append($('<option></option>').val(item.playerid).html(item.fName + " " + item.lName));
            }
          });
        });
      };

      //set team btn pressed check to see if all dropdowns have a value and if so create the team
      function setTeamBtnClick(){
        if($('#setQBs').val() == "negative" || $('#setRBs1').val() == "negative" || $('#setRBs2').val() == "negative" || $('#setWRs1').val() == "negative" || $('#setWRs2').val() == "negative" || $('#setTEs').val() == "negative" || $('#setDEF').val() == "negative" || $('#setKICKER').val() == "negative" || $('#setFLEX').val() == "negative" || $('#owners').val() == "negative")
        {
          alert("Please Select An Actual Player");
        } else{
          //create the team
          var teamid = $("#owners").val();
          var userid = GetCookie("session");
          //var createTeam = "http://serenity.ist.rit.edu:22222/footballapi/User/addPlayers?userid="+userid+"&teamid="+teamid+"&players="+$('#setQBs').val()+"&players="+$('#setRBs1').val()+"&players="+$('#setRBs2').val()+"&players="+$('#setWRs1').val()+"&players="+$('#setWRs2').val()+"&players="+$('#setTEs').val()+"&players="+$('#setDEF').val()+"&players="+$('#setKICKER').val()+"&players="+$('#setFLEX').val()
          var createTeam = "http://serenity.ist.rit.edu:22222/footballapi/User/addPlayers?userid="+userid+"&teamid="+teamid+"&players="+$('#setQBs').val()+"&players="+$('#setRBs1').val()+"&players="+$('#setRBs2').val()+"&players="+$('#setWRs1').val()+"&players="+$('#setWRs2').val()+"&players="+$('#setTEs').val()+"&players="+$('#setKICKER').val()+"&players="+$('#setFLEX').val()
          $.getJSON(createTeam).done(function( json ) {
            //alert user if worked or not
            if(json.successfullRosterSet){
              alert("You Have Set A Team! :)");
            } else{
              alert("Sorry the team could not be set! Please Try Again.");
            }
            location.reload();
          });
        }
      };

      //get owner names/ids and put them in the dropdown
      function getOwners(){
        var leaguemanagerid = GetCookie("leagueid");
        $.getJSON("http://serenity.ist.rit.edu:22222/footballapi/Leagues/unset/"+leaguemanagerid)
        .done(function( json ) {
          $.each( json.allLeagueRosters, function( i, item ) {
            console.log(json.allLeagueRosters);
            console.log("inside");
            $("#owners").append($('<option></option>').val(item.teamid).html(item.teamname));
          });
        });
      }
        
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>
