<head>
  <title>Compare average size of artworks from nations</title>
 </head>
 <body>
 
 <?php

ini_set('error_reporting', E_ALL);
ini_set('display_errors', true);

$db = mysql_connect("dbase.cs.jhu.edu", "cs41515_jchoi100", "ZCCSEBOC");

if (!$db) {
  echo "Connection failed!";

} else {
  $nation1 = $_POST['nation1'];
  $nation2 = $_POST['nation2'];
  // This says that the $cr_cnt variable should be assigned a value obtained as an
  // input to the PHP code. In this case, the input is called 'cr_cnt'.

  mysql_select_db("cs41515_jchoi100_db",$db);
  // ********* Remember to use the name of your database here ********* //

  $compare_query = mysql_query("CALL CompareAverageSizeFromNationByDecade('$nation1', '$nation2')",$db);
  // a simple query on the Course table. This should work fine, just like in
  // list_one_stu.php

  if (!$compare_query) {

    echo "Query failed!\n";
    print mysql_error();

  } else {

    echo "<table border=1>\n";
    echo "<tr><td>Decade</td><td>$nation1</td><td>$nation2</td></tr>\n";

    while ($myrow = mysql_fetch_array($compare_query)) {
      printf("<tr><td>%s</td><td>%s</td><td>%s</td></tr>\n", $myrow["Decade"], $myrow["Nation1Avg"], $myrow["Nation2Avg"]);
    }

    echo "</table>\n";

  }

}

// PHP code about to end

 ?>



 </body>