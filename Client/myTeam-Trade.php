<?php  include('headerfooter/header.php'); ?>
    <div>
        <h2 class="ml-5 mt-5">Trading Players Now Are We?</h2>
        <hr>
        <!-- Trade Offer -->
        <div id="offerTrade">
            <div class="selectTradePartnerTeam text-center mb-4">
                <h5 class="h3 mb-3">Select Trade Partner Team</h5>
                <select class="stat-filter" name="stat-filter">
                    <option value="Team B">Team B</option>
                    <option value="Team C">Team C</option>
                    <option value="Team D">Team D</option>
                    <option value="Team E">Team E</option>
                    <option value="Team F">Team F</option>
                </select>
            </div>

            <div class="row">
                <div class="col-sm-6">
                    <div class="card card1">
                        <div class="card-body">
                            <h5 class="card-title">Select Player To Trade</h5>
                            <select class="stat-filter card-text mb-4" name="stat-filter">
                                <option value="QB">Drew Brees</option>
                                <option value="RB">Jamaal Charles</option>
                                <option value="RB">DeMarco Murray</option>
                                <option value="WR">DeSean Jackson</option>
                                <option value="Defense">Chicago Bears</option>
                            </select><br>
                            <a href="#" class="text-center btn btn-primary">Add Another Player</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="card card2">
                        <div class="card-body">
                            <h5 class="card-title">Select Player To Reveive</h5>
                            <select class="stat-filter card-text mb-4" name="stat-filter">
                                <option value="QB">Aaron Rodgers</option>
                                <option value="RB">LeSean McCoy</option>
                                <option value="RB">Ezekiel Elliot</option>
                                <option value="WR">Jordy Nelson</option>
                                <option value="Defense">New Orleans Saints</option>
                            </select><br>
                            <a href="#" class="text-center btn btn-primary">Add Another Player</a>
                        </div>
                    </div>
                </div>
                <div class="col-12 text-center">
                    <a href="#" class="text-center btn btn-primary w-25 mt-4 align-center">Submit Trade Offer</a>
                </div>
            </div>
        </div>

        <hr>
        <!-- Pending Trades -->
        <div id="pendingTrade mb-5">
            <div class="text-center mb-4">
                <h5 class="h3 mb-3">Pending Trades</h5>
            </div>
        
            <div class="row">
                <div class="col-sm-6">
                    <div class="card card3">
                        <div class="card-body">
                            <h5 class="card-title text-center">Players Received</h5>
                            <div class="table table-hover">
                                <table cellspacing="0" cellpadding="0" class="w-100">
                                    <tr>
                                        <th>Position</th>
                                        <th>Player Name</th>
                                        <th>Team</th>
                                    </tr>
                                    <tr>
                                        <td>RB</td>
                                        <td>Aaron Jones</td>
                                        <td>GB</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="card card4">
                        <div class="card-body">
                            <h5 class="card-title text-center">Players Sent</h5>
                            <div class="table table-hover">
                                <table cellspacing="0" cellpadding="0"  class="w-100">
                                    <tr>
                                        <th>Position</th>
                                        <th>Player Name</th>
                                        <th>Team</th>
                                    </tr>
                                    <tr>
                                        <td>RB</td>
                                        <td>Sqaquon Barkley</td>
                                        <td>NYG</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 text-center">
                    <a href="#" class="text-center btn btn-primary w-25 mt-4 align-center mr-4">Decline Trade Offer</a>
                    <a href="#" class="text-center btn btn-primary w-25 mt-4 align-center">Accept Trade Offer</a>
                </div>
            </div>
        </div>
        

    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
    
</html>