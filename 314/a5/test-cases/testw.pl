#!/usr/bin/perl
# a sample testw script
# written by Hyunyoung Lee for CSCE 314 Students
 
$tested = 0; $succeeded = 0; $failed = 0; $intentional_error = 0;
 
# test case 1 should succeed
$tested += 1;
$output = `./w factorial-example.w`;
if( $output eq "result is 120\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 1 failed\n";
}
# test case 2 should succeed
$tested += 1;
$output = `./w empty-example.w`;
if( $output eq "Testing...\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 2 failed\n";
}
# test case 3 is for intentional error
$tested += 1;
$output = `./w factorial-wrong.w 2>&1 1>/dev/null`;
@words = split " ", $output;
if( $words[1] eq "Unused" && $words[4] eq "while") {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 3 failed\n";
}
# test case 4 / fibonacci should succeed 
$tested += 1;
$output = `./w fibonacci.w`;
if( $output eq "12586269025\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 4 failed\n";
}
# test case 5 / if, variable declaration, assign, or, not should succeed
$tested += 1;
$output = `./w test1.w`;
if( $output eq "2\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 5 failed\n";
}
# test case 6 / comments in weird places test should succeed
$tested += 1;
$output = `./w test2.w`;
if( $output eq "2\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 6 failed\n";
}
# test case 7 / if inside while test should succeed
$tested += 1;
$output = `./w test3.w`;
if( $output eq "0\n2\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 7 failed\n";
}
# test case 8 / nested operations, greaterorequal, less test should succeed
$tested += 1;
$output = `./w test4.w`;
if( $output eq "False\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 8 failed\n";
}
# test case 9 / while inside if, lessorequal test should succeed
$tested += 1;
$output = `./w test5.w`;
if( $output eq "now DAIJOUBU\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 9 failed\n";
}
# test case 10 / random operations test should succeed
$tested += 1;
$output = `./w test6.w`;
if( $output eq "22 errors. compile again.\n11202 errors. compile again.\n78412 errors. compile again.\n425772 errors. compile again.\nlol\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 10 failed\n";
}
# test case 11 / notequal test should succeed
$tested += 1;
$output = `./w test10.w`;
if( $output eq "False\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 11 failed\n";
}
# test case 12 / bools where there's supposed to be ints test is for intentional error
$tested += 1;
$output = `./w test11.w 2>&1 1>/dev/null`;
@words = split " ", $output;
if( $words[1] eq "Expected" && $words[4] eq "got") {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 12 failed\n";
}
# test case 13 / out of scope test is for intentional error
$tested += 1;
$output = `./w test12.w 2>&1 1>/dev/null`;
@words = split " ", $output;
if( $words[1] eq "Unknown" && $words[3] eq "wut") {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 13 failed\n";
}
# test case 14 / not an actual comment test is for intentional error
$tested += 1;
$output = `./w test13.w 2>&1 1>/dev/null`;
@words = split " ", $output;
if( $words[1] eq "Unused" && $words[4] eq "{") {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 14 failed\n";
}
# test case 15 / incomplete block test is for intentional error
$tested += 1;
$output = `./w test14.w 2>&1 1>/dev/null`;
@words = split " ", $output;
if( $words[1] eq "Unused" && $words[4] eq "{") {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 15 failed\n";
}
# test case 16 / assign something that wasnt declared / out of scope test is for intentional error
$tested += 1;
$output = `./w test15.w 2>&1 1>/dev/null`;
@words = split " ", $output;
if( $words[1] eq "Cannot" && $words[3] eq "undefined") {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 16 failed\n";
}
# test case 17 / ints where there's supposed to be bools test is for intentional error
$tested += 1;
$output = `./w test16.w 2>&1 1>/dev/null`;
@words = split " ", $output;
if( $words[1] eq "Expected" && $words[4] eq "got") {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 17 failed\n";
}
# test case 18 / check if scope is correct and random spaces and stuff test should succeed
$tested += 1;
$output = `./w test10.w`;
if( $output eq "False\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 18 failed\n";
}

print "$tested tested\n";
print "$succeeded + $intentional_error passed and $failed failed\n";
 
# to run this script, type the following at the terminal command line prompt
# > perl testw.pl
# then the result should be the following two lines
# 3 tested
# 2 + 1 passed and 0 failed