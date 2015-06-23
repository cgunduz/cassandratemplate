NOTES & COMMENTS

------

Make sure the @Ignore annotation is removed under Populate non reg class
if you want to populate the dbs by non reg tests

------

cassandra.properties and mysql.properties files should be changed to match the local configurations
or at the very least requires these dbs to be present without default configurations locally

------

Takes very long to persist all data, especially site3.xml

for cassandra all write operations approximately take 4 mins ( With more than half the time spent at site1.xml)
for mysql all write operations approximately take 20 mins (With %75 of the time spent at site3.xml)