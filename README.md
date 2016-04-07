##TASK
### Preconditions:
A Base account preconfigured with 3 custom fields (each with different name and specific types: a phone, an email, a date) set up on Leads (you can set it up going to 'Settings -> Leads -> Custom Field')
### Test steps:

- Log into the Base web application (using the account).
- Write a helper that will lists the names and types of Custom Fields that are visible on Settings page.
- Create a new Lead with all Custom Fields populated.

### Expected result:
On a new Lead form, the Custom Fields (name and type) correspond to the ones available on Settings page.

---

### Points to focus on:
#### Q: It should be possible to run the solution in CI
**A:**
_To run default configuration just run:_

`gradle clean test`

_To run custom configuration specify sys.variables custom values.
Example:_

`gradle clean test -Purl="https://someAnotherTestHost.com" -PbrowserClient="ff"`

#### Q: It should bootstrap itself (install dependencies)
**A:**
As gradle project, it has all required dependencies and drivers, and should be able to run default configuration without additional manipulations. All you need to run this is installed gradle app and Java.

#### Q: Publish the code as a GitHub or a private Bitbucket repo
**A:**
I guess you read this README exactly on Github :)

#### Q: You can use Base API Client -https://developers.getbase.com/docs/rest/articles/libraries
**A:**
Base API v2 Java client library has been used to create new Lead entity, and then it was verified that it is displayed on the Web.

#### Q: Adding a new test scenario should be easy.
**A:**
Test code is self-documented, trying to follow best practices and using PageObject pattern. Usage of 'Selenide' library makes it even more simple. It should be easy to read existing code and extend it to increase test coverage.
To add new test, just extend(inherit from) BaseTest. To add new PageObject just extend(inherit from) BasicPage or follow existing hierarchy.

If you want to run existing scenario but with custom user, you will need to add his credentials to 'config.groovy' file to 'users' section and add specific login method in LoginPage, as now it is set to 'loginAsAdmin' with specified admin credentials for everyday usage on CI(for example).
If it will be needed, to often run tests as different users, this behaviour can be easily rewritten by some changes in the way how to login and how env. variables are being set.

#### Notes:
This implementation was tested on Windows OS: chromedriver 2.21 and Firefox 43. Newer versions of browsers may cause issues with WebDriver.
