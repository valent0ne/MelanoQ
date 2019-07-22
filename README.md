# Introduction

Many melanoma observational studies have been carried out across different countries and geographic
areas using heterogeneous assessments of epidemiologic risk factors and clinical variables.  
The aim is to develop a consensus questionnaire to standardize epidemiologic and clinical data collection for melanoma risk
assessment.  
[**Melanoq**](https://www.ncbi.nlm.nih.gov/pubmed/30098061) is the product of the work of the MelanoStrum Consortium.

This project's goal is to implement a digital version of the aforementioned questionnaire by providing a way to *compile* the questionnaire itself and to expose the physicians, researchers and authorized personnel a way to *query* the data stored in the questionnaires' database.

# Deployment

## Requirements
- CouchBase Server - [https://www.couchbase.com/](https://www.couchbase.com/)
- Apache Tomcat - [http://tomcat.apache.org/](http://tomcat.apache.org/)
- Apache Maven - [https://maven.apache.org/](https://maven.apache.org/)
- Yarn - [https://yarnpkg.com/en/](https://yarnpkg.com/en/) &nbsp;or&nbsp; NPM (Node Package Manager) - [https://www.npmjs.com/](https://www.npmjs.com/)
- VueJS CLI - [https://cli.vuejs.org/](https://cli.vuejs.org/)


## Database

Download and install CouchBase Server Community or Enterprise from here: [https://www.couchbase.com/downloads](https://www.couchbase.com/downloads).

After having installed and run it, its web interface will be available at the address [http://machine-ip:8091](http://machine-ip:8091), configure the server according to your preferences and then create a user and a bucket (remember that the bucket name and the user's credentials must then be provided to the MelanoQ api server), finally add all the permissions to the created user for the created bucket.

Go to the <code>Query</code> section of the web interface and run:
```sql
CREATE PRIMARY INDEX ON bucketname USING VIEW;
```
where <code>bucketname</code> is the name of the created bucket.

Now the database is empty, so you need to manually add an admin user for the MelanoQ api server: 
- generate a <code>bcrypt</code> password from this online tool: [https://www.browserling.com/tools/bcrypt](https://www.browserling.com/tools/bcrypt) using *12 rounds* (be sure to check that the generated encrypted password starts with the <code>$2a</code> prefix)
- execute the following query:  
   ```sql
   INSERT INTO bucketname (KEY, VALUE) 
    VALUES 
    (
       "k999", 
       {
           "role": "admin", 
           "username": "name", 
           "password": "generated-password", 
           "_class": "it.univaq.disim.bioinformatics.melanoq.model.User"
        }
    )
    ```

   where <code>k999</code> is any random alphanumeric string, <code>name</code> is a name of your choice and <code>generated-password</code> is the bcrypt password you previously generated.


If you need to backup the database content run: 
```console
> cbbackup http://machine-ip:8091 path-to-dest-folder -u username -p password -b bucket-name
```
if you need to restore a backup run: 
```console
> cbrestore path-to-source-folder http://machine-ip:8091 -u username -p password -b bucket-name -x rehash=1
```

where <code>cbbackup</code> and <code>cbrestore</code> are tools that are located in the installation directory of CouchBase Server.


## Server

```sh
> git clone https://gitlab.com/valent0ne/melanoq.git 
> cd ./melanoq/code/api 
> mvn clean package
```


Now inside the directory <code>./melanoq/code/api/target/</code> there will be a file named <code>melanoq-x.y.z.jar</code>, move this file inside your *Apache Tomcat* <code>webapp</code> directory.  
The MelanoQ api server will be available at the address [http://machine-ip:8080/melanoq/api](http://machine-ip:8080/melanoq/api). To change the application parameters (e.g., couchbase server credentials and address), before executing the command <code> mvn clean package</code> edit the file <code>application.properties</code> located in the directory <code>./melanoq/code/api/src/main/resources</code>.


## Webclient

```console
> git clone https://gitlab.com/valent0ne/melanoq.git
> cd ./melanoq/code/webclient
> yarn
> yarn build
```

or (if you are using <code>npm</code> instead of <code>yarn</code>):

```console
> git clone https://gitlab.com/valent0ne/melanoq.git
> cd ./melanoq/code/webclient
> npm
> npm run build
```


Before executing the <code>build</code> command, remember to edit the <code>config.js</code> file inside the directory <code>./melanoq/code/webclient/src/common</code> with the correct ip address of the MelanoQ api server.


Now you have two options:
- double-click <code>index.html</code> inside the directory <code>./melanoq/code/webclient/dist</code> to directly access the local instance of the client
- deploy the client (copy the content of the directory <code>./melanoq/code/webclient/dist</code>) into an http server (Apache Tomcat or others) to expose the client to the public.
