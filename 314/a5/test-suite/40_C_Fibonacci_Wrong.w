var n = 10;

var fibonacci1 = 0;

var fibonacci2 = 1;

var fibonaccin = 0;

if (n == 1)
fibonaccin = 0;

if (n == 2)
fibonaccin = 1;

if n >=2 {
	while (n > 2) {
		fibonaccin = fibonacci1 + fibonacci2;
		fibonacci1 = fibonacci2;
		fibonacci2 = fibonaccin;
		n = n - 1;
	}
}

print "Result is ";
print fibonaccin;
print "\n";