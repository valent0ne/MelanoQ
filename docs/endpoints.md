# Rest Endpoints
We have used the following endpoints:

- GET <code>/questionnaire/{dbCodeNumber}</code>:  
    retrives the questionnaire identified by <code>dbCodeNumber</code>.

- POST <code>/questionnaire/query</code>:  
    retrives all the questionnaires in the database according to the filter setted in the query. 

- GET <code>/questionnaire/</code>:    
    retrives all the questionnaires.

- GET <code>/questionnaire/validate/{dbCodeNumber}</code>:  
    verify if the given <code>dbCodeNumbe</code> exist or not.

- POST <code>/questionnaire/{dbCodeNumber}/{section}</code>:  
    insert the section indicate in the <code>section</code> parameter to he questionnaire indentified by the <code>dbCodeNumber</code>.

- GET <code>/questionnaire/{dbCodeNumber}/{section}</code>:  
    return the section indicate in the <code>section</code> parameter of the questionnaire indentified by the <code>dbCodeNumber</code>.

- PATCH <code>/questionnaire/{dbCodeNumber}/{section}</code>:  
    update the section indicate in the <code>section</code> parameter to he questionnaire indentified by the <code>dbCodeNumber</code>.

- DELETE <code>/questionnaire/{dbCodeNumber}/{section}</code>:  
    delete the section indicate in the <code>section</code> parameter to he questionnaire indentified by the <code>dbCodeNumber</code>.

- POST <code>/user</code>:  
    insert an user in the database.

- GET <code>/user/{username}</code>:  
    return the user whose username match with the given <code>username</code>.

-  POST <code>/authentication</code>:  
    perform the authentication of an user and assign it a token.
