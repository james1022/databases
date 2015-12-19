<head>
  <title>Show average size of artworks by decade</title>
 </head>
 <body>
 
 <?php

ini_set('error_reporting', E_ALL);
ini_set('display_errors', true);

$db = mysql_connect("dbase.cs.jhu.edu", "cs41515_jchoi100", "ZCCSEBOC");

if (!$db) {
  echo "Connection failed!";

} else {
  $nation = $_POST['nation'];
  // This says that the $cr_cnt variable should be assigned a value obtained as an
  // input to the PHP code. In this case, the input is called 'cr_cnt'.

  mysql_select_db("cs41515_jchoi100_db",$db);
  // ********* Remember to use the name of your database here ********* //

  $result = mysql_query("CALL AverageSizeFromNationByDecade('$nation')",$db);
  // a simple query on the Course table. This should work fine, just like in
  // list_one_stu.php

  if (!$result) {

    echo "Query failed!\n";
    print mysql_error();

  } else {

    echo "<table border=1>\n";
    echo "<tr><td>Decade</td><td>Average</td></tr>\n";

    while ($myrow = mysql_fetch_array($result)) {
      printf("<tr><td>%s</td><td>%s</td></tr>\n", $myrow["Decade"], $myrow["Average"]);
    }

    echo "</table>\n";

  }

}

// PHP code about to end

 ?>



 </body>