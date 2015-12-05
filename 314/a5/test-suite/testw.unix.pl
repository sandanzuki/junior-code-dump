#!/usr/bin/perl
# a sample testw script
# written by Hyunyoung Lee for CSCE 314 Students
#
# to run this script, type the following at the terminal command line prompt
# > perl testw.pl
# then the result should be the following two lines
# 40 tested
# 34 + 6 passed and 0 failed

 
$tested = 0; $succeeded = 0; $failed = 0; $intentional_error = 0;
 
# test case 1 should succeed
$tested += 1;
$output = `./w 1_S_VarDeclareStat_ValVar.w`;
if( $output eq "x equals to 5 \n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 1 failed\n";
}
# test case 2 should succeed
$tested += 1;
$output = `./w 2_S_VarDeclareStat_Arithmetric.w`;
if( $output eq "3122\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 2 failed\n";
}
# test case 3 should succeed
$tested += 1;
$output = `./w 3_S_VarDeclareStat_Compare.w`;
if( $output eq "True,False,False,True,False,True\n") {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 3 failed\n";
}
# test case 4 should succeed
$tested += 1;
$output = `./w 4_S_VarDeclareStat_Logical.w`;
if( $output eq "True,False,False,True,False,True,False,True,True\n") {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 4 failed\n";
}
# test case 5 should succeed
$tested += 1;
$output = `./w 5_S_AssignStat_ValVar.w`;
if( $output eq "6\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 5 failed\n";
}
# test case 6 should succeed
$tested += 1;
$output = `./w 6_S_AssignStat_Arithmetric.w`;
if( $output eq "3\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 6 failed\n";
}
# test case 7 should succeed
$tested += 1;
$output = `./w 7_S_AssignStat_Compare.w`;
if( $output eq "False\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 7 failed\n";
}
# test case 8 should succeed
$tested += 1;
$output = `./w 8_S_AssignStat_Logical.w`;
if( $output eq "True\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 8 failed\n";
}
# test case 9 should succeed
$tested += 1;
$output = `./w 9_S_IfElseStat_Compare.w`;
if( $output eq "positive\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 9 failed\n";
}
# test case 10 should succeed
$tested += 1;
$output = `./w 10_S_IfElseStat_Logical.w`;
if( $output eq "positive\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 10 failed\n";
}
# test case 11 should succeed
$tested += 1;
$output = `./w 11_S_IfStat_Compare.w`;
if( $output eq "positive\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 11 failed\n";
}
# test case 12 should succeed
$tested += 1;
$output = `./w 12_S_IfStat_Logical.w`;
if( $output eq "positive\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 12 failed\n";
}
# test case 13 should succeed
$tested += 1;
$output = `./w 13_S_IfStatBlock_Compare.w`;
if( $output eq "positive\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 13 failed\n";
}
# test case 14 should succeed
$tested += 1;
$output = `./w 14_S_IfStatBlock_Logical.w`;
if( $output eq "positive\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 14 failed\n";
}
# test case 15 should succeed
$tested += 1;
$output = `./w 15_S_WhileStat_Compare.w`;
if( $output eq "10\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 15failed\n";
}
# test case 16 should succeed
$tested += 1;
$output = `./w 16_S_WhileStat_Logical.w`;
if( $output eq "9\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 16 failed\n";
}
# test case 17 should succeed
$tested += 1;
$output = `./w 17_S_PrintStat_ValVar.w`;
if( $output eq "5\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 17 failed\n";
}
# test case 18 should succeed
$tested += 1;
$output = `./w 18_S_PrintStat_Arithmetric.w`;
if( $output eq "3\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 18 failed\n";
}
# test case 19 should succeed
$tested += 1;
$output = `./w 19_S_PrintStat_Compare.w`;
if( $output eq "True\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 19 failed\n";
}
# test case 20 should succeed
$tested += 1;
$output = `./w 20_S_PrintStat_Logical.w`;
if( $output eq "False\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 20 failed\n";
}
# test case 21 should succeed
$tested += 1;
$output = `./w 21_S_BlockStat_EmptyStat.w`;
if( $output eq "blockEmpty\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 21 failed\n";
}
# test case 22 should succeed
$tested += 1;
$output = `./w 22_S_BlockStat_VarDecalreStat.w`;
if( $output eq "blockDeclare\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 22 failed\n";
}
# test case 23 should succeed
$tested += 1;
$output = `./w 23_S_BlockStat_AssignStat.w`;
if( $output eq "blockAssign\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 23 failed\n";
}
# test case 24 should succeed
$tested += 1;
$output = `./w 24_S_BlockStat_IfStat.w`;
if( $output eq "blockIf\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 24 failed\n";
}
# test case 25 should succeed
$tested += 1;
$output = `./w 25_S_BlockStat_WhileStat.w`;
if( $output eq "blockWhile\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 25 failed\n";
}
# test case 26 should succeed
$tested += 1;
$output = `./w 26_S_BlockStat_BlockStat.w`;
if( $output eq "blockBlock\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 26 failed\n";
}
# test case 27 should succeed
$tested += 1;
$output = `./w 27_S_BlockStat_PrintStat.w`;
if( $output eq "blockPrint\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 27 failed\n";
}
# test case 28 should succeed
$tested += 1;
$output = `./w 28_S_EmptyStat.w`;
if( $output eq "HelloHaskell\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 28 failed\n";
}
# test case 29 should succeed
$tested += 1;
$output = `./w 29_C_IfInWhile.w`;
if( $output eq "10\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 29 failed\n";
}
# test case 30 is for intentional error
$tested += 1;
$output = `./w 30_C_IfInWhile_Wrong.w 2>&1`;
@words = split " ", $output;
if( ($words[1] eq "Unused") && ($words[4] eq "while")) {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 30 failed\n";
}
# test case 31 should succeed
$tested += 1;
$output = `./w 31_C_WhileInIf.w`;
if( $output eq "10\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 31 failed\n";
}
# test case 32 is for intentional error
$tested += 1;
$output = `./w 32_C_WhileInIf_Wrong.w 2>&1`;
@words = split " ", $output;
if( ($words[1] eq "Unused") && ($words[4] eq "if")) {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 32 failed\n";
}
# test case 33 should succeed
$tested += 1;
$output = `./w 33_C_Factorial.w`;
if( $output eq "result is 120\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 33 failed\n";
}
# test case 34 is for intentional error
$tested += 1;
$output = `./w 34_C_Factorial_Wrong.w 2>&1`;
@words = split " ", $output;
if( ($words[1] eq "Unused") && ($words[4] eq "while")) {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 34 failed\n";
}
# test case 35 should succeed
$tested += 1;
$output = `./w 35_C_SumOfOneToHundred.w`;
if( $output eq "Sum is 5050\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 35 failed\n";
}
# test case 36 is for intentional error
$tested += 1;
$output = `./w 36_C_SumOfOneToHundred_Wrong.w 2>&1`;
@words = split " ", $output;
if( ($words[1] eq "Unused") && ($words[4] eq "while")) {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 36 failed\n";
}
# test case 37 should succeed
$tested += 1;
$output = `./w 37_C_PowerOfTwo.w`;
if( $output eq "Power is 1024\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 37 failed\n";
}
# test case 38 is for intentional error
$tested += 1;
$output = `./w 38_C_PowerOfTwo_Wrong.w 2>&1`;
@words = split " ", $output;
if( ($words[1] eq "Unused") && ($words[4] eq "while")) {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 38 failed\n";
}
# test case 39 should succeed
$tested += 1;
$output = `./w 39_C_Fibonacci.w`;
if( $output eq "Result is 34\n" ) {
   $succeeded += 1;
}else{
   $failed += 1;
   print "test 39 failed\n";
}
# test case 40 is for intentional error
$tested += 1;
$output = `./w 40_C_Fibonacci_Wrong.w 2>&1`;
@words = split " ", $output;
if( ($words[1] eq "Unused") && ($words[4] eq "if")) {
   $intentional_error += 1;
}else{
   $failed += 1;
   print "test 40 failed\n";
}

# add more test cases of yours ...
 
print "$tested tested\n";
print "$succeeded + $intentional_error passed and $failed failed\n";
 
