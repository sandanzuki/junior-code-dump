{
    var a = 10;
    var b = 5;
    var c = a * b;
    var d = c / b + 1;
    var e = 0;
    var counter = 0;
    while(counter < b){
        counter = counter + 1;
        if(d == a + 1){
            e = e + counter;
        }else{
            e = 2;
        }
        a = 0;
    }

    print a;
    print "\n";
    print e;
    print "\n";
}