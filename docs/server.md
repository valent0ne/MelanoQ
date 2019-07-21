 # REST Server

 The server side of our project is structured by following the MVC pattern, exploit in 4 principal packeges: model, controller, repository and service.  
 In the *model* package there is the "descriprtion" of the entities used by the application:
 - *User* that is the representation of  user's types that can log in in the application and add the questionnaires or consult them.
 - *Questionnaire* where is explained of which sections a questionnaire is composed. 
 - *Query* is the model of how the application can be questioned.
 - *ErrorMessage* is the structure that will have any error message in the application.
 - *section* folder contains all the models of the all sections of which a questionnaire is composed (i.e., section A1, A2,B1,B2,B3,C1,C2,C3,D and Evaluation section).
 - *nested* folder contains all the complex objects that are in the various section.

The *controller* indicatesorchestrate the data flow of the entire application:
- Trough *QuestionnaireController* and *UserController* we indicate the CRUD operations to perform on data in the database.
- *Response* defines the format of how a general response from the server to the client must be.
- *JwtAuthenticationController* is the controller that manage the authentication of the users in the application.
- *ExceptionControllerAdvice* deals with the management of the exceptions throw, eventually, by the functioning of the application.
  
The *repository* package contains all the interfaces needed to communicate with the database of the principal entities: User and Questionnaire.

The *service* package contains the business logic of the application: it contains all the sections Services and the user service, where are specified the CRUD operation to perform on the database.

In addition to the packeges characterising the MVC pattern, there are:
- The *configuration* package whose contains all the classes necessarely to configure the application to the use of the additionig modules such as SpringSecurity and Json Web Token-based authentication.
- The *BusinessException* class that handle the exeptions of the application.
- The *application* class that is the starting point of the entire application.

I