<?php  include('headerfooter/header.php'); ?>
    <!-- Content -->
    <div class="content">
      <h2>NFL League Standings</h2>
      <hr>

      <select class="stat-filter" name="stat-filter">
        <option value="overall">Overall</option>
        <option value="op1">op1</option>
      </select>

      <div class="table table-hover">
        <table cellspacing="0" cellpadding="0">
          <tr>
            <th class="first-col" style='width: 100%;'>Team</th>
            <th>W</th>
            <th>L</th>
            <th>T</th>
            <th>Points</th>
            <th>Streak</th>
          </tr>
          <tr>
            <td class="first-col">New England Patriots</td>
            <td>8</td>
            <td>3</td>
            <td>0</td>
            <td>58</td>
            <th>1W</th>
          </tr>
          <tr>
            <td class="first-col">Miami Dolphins</td>
            <td>5</td>
            <td>6</td>
            <td>0</td>
            <td>-60</td>
            <th>2L</th>
          </tr>
          <tr>
            <td class="first-col">Buffalo Bills</td>
            <td>4</td>
            <td>7</td>
            <td>0</td>
            <td>-111</td>
            <th>2W</th>
          </tr>
        </table>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>
