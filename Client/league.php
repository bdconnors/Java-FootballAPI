
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
                  <select class="form-control form-control-sm">
                    <option>--</option>
                  </select>
                </div>
              </div>
              <div class="form-group row">
                <div class="col-sm-10">
                  <button type="submit" class="btn btn-primary">Join League</button>
                </div>
              </div>
            </form>

          </div>
          <div class="col-sm">
            <div class="col-sm-10"><h4 style="text-align: center; margin: 20px 5px;">Create a League</h4></div>
            <form>
              <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label">League Name</label>
                <div class="col-sm-10">
                  <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                </div>
              </div>
              <div class="form-group row">
                <div class="col-sm-4">Number of Teams</div>
                <div class="col-sm-10">
                  <select class="form-control form-control-sm">
                    <option>0</option>
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
                  <button type="submit" class="btn btn-primary">Create League</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>

    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="include.js" charset="utf-8"></script>

  </body>
</html>
