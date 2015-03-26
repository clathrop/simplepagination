# simplepagination

A prototype for simple pagination functionality to view users in a database. 

Built with Struts2 framework and jTable.

Steps to install:

1. Clone this repo to your local dev environment: 
    `git clone https://github.com/clathrop/simplepagination.git` 
2. `mvn clean package` to build the project and generate the simplepagination.war
3. Within the simplepagination/ directory, run `sudo cp target/simplepagination.war /var/lib/tomcat7/webapps/` -- if you've installed tomcat in a non default directory then cp it to whichever directory is appropriate
4. Create a new directory within your tomcat7 installation: `sudo mkdir /var/lib/tomcat7/dbs`
5. Copy the hero.db into the newly made directory: `sudo cp hero.db /var/lib/tomcat7/dbs/`
6. Restart tomcat7: `sudo service tomcat7 restart`
7. All done, if everything went smoothly you should be able to access the application via localhost:8080/simplepagination


Additionally:
The data within hero.db is generated via a python script, parseHeroData.py which parses the herodata.csv and inserts it into the hero.db database. This means if you wish to add or remove your own data you may do so by simply editing the herdodata.csv and running the parseHeroData.py with: `python parseHeroData.py`. Once the new hero.db is created you must move it to the correct location stated above, /var/lib/tomcat7/dbs, for it to take affect within the application.
