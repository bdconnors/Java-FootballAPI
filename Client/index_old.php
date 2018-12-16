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
      </select>

      <div class="table table-hover">
        <table cellspacing="0" cellpadding="0">
          <tr>
            <th class="first-col">Player</th>
            <th>Team</th>
            <th>Bye Week</th>
            <th>Pass Attempts</th>
            <th>Pass Completions</th>
            <th>Pass Yards</th>
            <th>Interceptions</th>
            <th>Rushing Attempts</th>
            <th>Rushing Yards</th>
            <th>Recptions</th>
            <th>Recptions Yards</th>
            <th>TDs</th>
            <th>Fantasy Points</th>
          </tr>
          <tr>
            <td class="first-col">Patrick Mahomes</td>
            <td>KC</td>
            <td>12</td>
            <td>429</td>
            <td>287</td>
            <th>3923</th>
            <th>10</th>
            <th>51</th>
            <th>238</th>
            <th>0</th>
            <th>0</th>
            <th>43</th>
            <th>405</th>
          </tr>
          <tr>
            <td class="first-col">Todd Gurley</td>
            <td>LAR</td>
            <td>12</td>
            <td>0</td>
            <td>0</td>
            <th>0</th>
            <th>0</th>
            <th>233</th>
            <th>1175</th>
            <th>46</th>
            <th>474</th>
            <th>19</th>
            <th>273</th>
          </tr>
          <tr>
            <td class="first-col">Davante Adams</td>
            <td>GB</td>
            <td>7</td>
            <td>0</td>
            <td>0</td>
            <th>0</th>
            <th>0</th>
            <th>15</th>
            <th>118</th>
            <th>66</th>
            <th>1119</th>
            <th>11</th>
            <th>181</th>
          </tr>
        </table>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>
