<?php  include('headerfooter/header.php'); ?>
    <!-- Content -->
    <div class="content">
      <div class="container">
        <div class="row">
          <div class="col-sm">
            <div class="col-sm-10"><h4 style="text-align: center; margin: 20px 5px;">Join Existing League</h4></div>
            <form>
              <div class="form-group row">
                <div class="col-sm-4">League Name</div>
                <div class="col-sm-10">
                  <select class="form-control form-control-sm" id="selectLeague">
                    <option value="Select A League">Select A League</option>
                  </select>
                </div>
              </div>
              <div class="form-group row">
                <div class="col-sm-4">Team Name</div>
                <div class="col-sm-10">
                  <input type="text" name="TeamName" value="" id="teamName">
                </div>
              </div>
              <div class="form-group row">
                <div class="col-sm-10">
                  <button type="submit" class="btn btn-primary" onclick="requestToJoinALeague();">Join League</button>
                </div>
              </div>
            </form>
          </div>
          <div class="col-sm">
            <div class="col-sm-10"><h4 style="text-align: center; margin: 20px 5px;">Create a League</h4></div>
            <form>
              <div class="form-group row">
                <label for="inputEmail3" class="col-sm-4 col-form-label">League Name</label>
                <div class="col-sm-10">
                  <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                </div>
              </div>
              <div class="form-group row">
                <div class="col-sm-4">Number of Teams</div>
                <div class="col-sm-10">
                  <select class="form-control form-control-sm">
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                  </select>
                </div>
              </div>
              <div class="form-group row">
                <div class="col-sm-4">Scoring Type</div>
                <div class="col-sm-10">
                  <select class="form-control form-control-sm">
                    <option>Standard</option>
                  </select>
                </div>
              </div>
              <div class="form-group row">
                <div class="col-sm-10">
                  <button type="submit" class="btn btn-primary" onclick="createALeague();">Create League</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>

    </div>
    <script>
      //set the table data by setting headers and getting the json string
      var reqBase = "http://serenity.ist.rit.edu:22222/footballapi/Leagues/"; //base header/endpoint for retriving data
      var leagueid;
      var leagueNamesAndIds = {};
      //on load set the drop down for avaliable leagues
      $(document).ready(function(){
        setAvaliableLeaguesToJoin();
      })

      //get league name
      function setAvaliableLeaguesToJoin(){
        $.getJSON(reqBase)
        .done(function( json ) {
          $.each( json.allLeagues, function( i, item ) {
            $('#selectLeague').append($('<option></option>').val(item.leaguename).html(item.leaguename));

            leagueNamesAndIds[item.leaguename] = item.leagueid;

          });
        });
      };

      function requestToJoinALeague(){
        if($('#selectLeague').val() == "Select A League" || $('#teamName').val() == "")
        {
          alert("Please Select An Actual League or Put a Team Name In");
          //console.log(leagueNamesAndIds["Test League"])
        } else{
          //console.log(leagueNamesAndIds[$('#selectLeague').val()])
          //send to the pending joins
          var user = GetCookie("session");
          console.log(user)
          //http://serenity.ist.rit.edu:22222/footballapi/User/leagueReq?userid=2&leagueid=1&teamname=test
          reqBase = "http://serenity.ist.rit.edu:22222/footballapi/User/leagueReq?userid="+ user +"&leagueid="+ leagueNamesAndIds[$('#selectLeague').val()] + "&teamname=" + $('#teamName').val();
          
          $.get(reqBase, function(){
            alert("You Have Requested To Join")
          });
        };
      };

      function createALeague(){
        
      }
    </script>
    <script src="js/cookies.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="include.js" charset="utf-8"></script>

  </body>
</html>
