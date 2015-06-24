# cassandratemplate

Project is intended to be used as a web project template, which includes persistent layer integration with mysql and cassandra.
Spring data and spring mvc is also used. Velocity is selected and configured as the template engine.

In order to run the program cassandra.properties and mysql.properties files should be configured according to the preffered 
settings of the user. At the very least mysql and cassandra should be up and running on the local machine.

There is also a non-reg test to populate both dbs for testing purposes. In order to run it "@Ignore" annotation has to be deleted 
from the "Populate" class. Results can be viewed from the index page to verify successful installation and integration.

Cheers !
