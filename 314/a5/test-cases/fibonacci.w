var prev = 0-1;
var result = 1;
var counter = 0;
while (counter <= 50) {
    var sum = result + prev;
    prev = result;
    result = sum;
    counter = counter + 1;
}

print result;
print "\n";