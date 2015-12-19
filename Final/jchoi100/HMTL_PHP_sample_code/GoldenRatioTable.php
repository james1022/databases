<head>
  <title>Show Number of Artworks with Golden Ratio</title>
 </head>
 <body>
 
 <?php

ini_set('error_reporting', E_ALL);
ini_set('display_errors', true);

$db = mysql_connect("dbase.cs.jhu.edu", "cs41515_jchoi100", "ZCCSEBOC");

if (!$db) {
  echo "Connection failed!";

} else {
  // This says that the $cr_cnt variable should be assigned a value obtained as an
  // input to the PHP code. In this case, the input is called 'cr_cnt'.

  mysql_select_db("cs41515_jchoi100_db",$db);
  // ********* Remember to use the name of your database here ********* //

  $result = mysql_query("CALL GoldenRatio()",$db);
  // a simple query on the Course table. This should work fine, just like in
  // list_one_stu.php

  if (!$result) {

    echo "Query failed!\n";
    print mysql_error();

  } else {

    echo "<table border=1>\n";
    echo "<tr><td>Nationality</td><td>NumGoldenRatio</td><td>AverageSize</td><td>Percentage</td></tr>\n";

    while ($myrow = mysql_fetch_array($result)) {
      printf("<tr><td>%s</td><td>%d</td><td>%.2f</td><td>%.2f</td></tr>\n",
        $myrow["ArtistNationality"], $myrow["GoldenRatioCount"], $myrow["AvgSize"], $myrow["Percentage"]);
    }

    echo "</table>\n";

  }

}

// PHP code about to end

 ?>



 </body>