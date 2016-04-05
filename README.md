### It should be possible to run the solution in CI

_To run default configuration just run:_

`gradle clean test`

_To run custom configuration specify sys.variables custom values.
Example:_

`gradle clean test -Purl="https://someAnotherTestHost.com" -PbrowserClient="ff"`

### It should bootstrap itself (install dependencies)

As gradle project, it has all required dependencies and drivers, and should be able to run default configuration without additional manipulations. All you need to run this is installed gradle app and Java.

### Publish the code as a GitHub or a private Bitbucket repo
I guess you read this README exactly on Github :)

### You can use Base API Client -https://developers.getbase.com/docs/rest/articles/libraries
//TODO

### Adding a new test scenario should be easy.
Test code is self-documented, trying to follow best practices and using PageObject pattern. Usage of 'Selenide' library makes it even more simple. It should be easy to read existing code and extend it to increase test coverage.
To add new test, just extend(inherit from) BaseTest. To add new PageObject just extend(inherit from BasicPage).

If you want to run existing scenario but with custom user, you will need to add his credentials to 'config.groovy' file to 'users' section and add specific login method in LoginPage, as now it is set to 'loginAsAdmin' with specified admin credentials for everyday usage on CI(for example).
If it will be needed, to often run tests as different users, this behaviour can be easily rewritten by some changes in the way how to login and how env. variables are being set.
