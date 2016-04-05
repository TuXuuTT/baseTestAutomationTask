# To run default configuration just run:
gradle clean test

# To run custom configuration specify sys.variables custom values:
gradle clean test -Purl="https://test1.futuresimple.com/sales/users/login" -PbrowserClient="ff"