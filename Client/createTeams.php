<?php  include('headerfooter/header.php'); ?>
    <!-- Content -->
    <div class="content">
      <h2>League Name</h2>
      <hr>

      <div class="container">
        <div class="row">
          <div class="col-2">
            Team 1
          </div>
          <div class="col-4">
            <div class="col-sm-2 label">QB</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setQBs-1">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
          <div class="col-4">
            <div class="col-sm-2 label">WR</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setWRs1-1">
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
              <select class="form-control form-control-sm" id="setDEF-1">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
          <div class="col-4">
            <div class="col-sm-2 label">WR</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setWRs2-1">
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
              <select class="form-control form-control-sm" id="setKICKER-1">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
          <div class="col-4">
            <div class="col-sm-2 label">RB</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setRBs1-1">
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
              <select class="form-control form-control-sm" id="setTEs-1">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
          <div class="col-4">
            <div class="col-sm-2 label">RB</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm" id="setRBs2-1">
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
              <select class="form-control form-control-sm" id="setFLEX-1">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
          <div class="col-4"></div>
        </div>
        <div class="row">
          <div class="col-2"><button type="button" class="btn btn-secondary btn-sm ml-2 mr-2" onclick="setTeamBtnClick(1)">Set Team</button></div>
          <div class="col-4"></div>
          <div class="col-4">
            <div class="col-sm-2 label">Owner</div>
            <div class="col-sm-10">
              <select class="form-control form-control-sm">
                <option value="negative">--</option>
              </select>
            </div>
          </div>
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
      var reqBaseFlex = "http://serenity.ist.rit.edu:22222/footballapi/Players/wr";

      $(document).ready(function(){
        setPlayers(reqBaseQB,'#setQBs-1');
        setPlayers(reqBaseRB,'#setRBs1-1');
        setPlayers(reqBaseRB,'#setRBs2-1');
        setPlayers(reqBaseWR,'#setWRs1-1');
        setPlayers(reqBaseWR,'#setWRs2-1');
        setPlayers(reqBaseTE,'#setTEs-1');
        setDef(reqBaseDefense,'#setDEF-1');
        setPlayers(reqBaseKicker,'#setKICKER-1');
        setPlayers(reqBaseFlex,'#setFLEX-1');
      })

      //get league name
      function setPlayers(requestString, dropDownID){
        $.getJSON(requestString)
        .done(function( json ) {
          $.each( json.players, function( i, item ) {
            if(dropDownID == '#setDEF-1'){
              $(dropDownID).append($('<option></option>').val(item.team).html(item.team));
            } else {
              $(dropDownID).append($('<option></option>').val(item.fName + " " + item.lName).html(item.fName + " " + item.lName));
            }
          });
        });
      };

      function setDef(requestString, dropDownID){
        $.getJSON(requestString)
        .done(function( json ) {
          $.each( json.allDefenses, function( i, item ) {
            if(dropDownID == '#setDEF-1'){
              $(dropDownID).append($('<option></option>').val(item.team).html(item.team));
            } else {
              $(dropDownID).append($('<option></option>').val(item.fName + " " + item.lName).html(item.fName + " " + item.lName));
            }
          });
        });
      };

      function setTeamBtnClick(teamNum){
        for(var i = 1; i < 2; i++){
          if($('#setQBs-' + i).val() == "negative" || $('#setRBs1-' + i).val() == "negative" || $('#setRBs2-' + i).val() == "negative" || $('#setWRs1-' + i).val() == "negative" || $('#setWRs2-' + i).val() == "negative" || $('#setTEs-' + i).val() == "negative" || $('#setDEF-' + i).val() == "negative" || $('#setKICKER-' + i).val() == "negative" || $('#setFLEX-' + i).val() == "negative")
          {
            alert("Please Select An Actual Player for team #" + teamNum);
            //console.log(leagueNamesAndIds["Test League"])
          } else{
          }
        }
      }
        
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>
