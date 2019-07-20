# DataModel

The structure of how each questionnaire is saved is the following format:

```json
{
    ...

    "a2": {
        "education": "text",
        "ethnicity": "text",
        "currentOccupationalStatus": "text",
        "sex": "text",
        "weight": "numeric",
        "dateOfBirth": "text",
        "historyOfOccupations": {
            "occupationStartingTime": "text",
            "occupationEndingTime": "text"
        },
        "cityOfBirth": "text",
        "provinceOfBirth": "text",
        "countryOfBirth": "text",
        "residencies": {
            "residencyEndingTime": "text",
            "residencyStartingTime": "text",
            "residencyCountry": "text",
            "residencyCity": "text"
        },
        "height": "numeric"
    },
    ...
}
```
We tried to make a 1 to 1 match between the data model and the given questionnaire representation:   
   * The entire questionnaire is collected between the curly brackets.  
   * Inside, each questionnaire has a certain number of "children" that match to the sections of the original questionnaire model. 
   * Each child is identified by a string representig the name of the respective section (so section A1 is identified by "a1", section A2 is identified by "a2" and so on...). 
   * Each section has a certain quantity of fields, where the left side is the identifier of the questions that are in the given questionnaire model and the right side is the value that contains the answering to that specific question. We can have different type of values: in most cases they are *text* type (see for example *education*), more rarely *numeric* type (see for example *weight*) and some times they are list that we represent as child (see for example *residencies*). In this last case the child is identified by the question identifier and contains some answering fields inside.


