# Data Model

The structure of how each questionnaire is stored is the following <code>.json</code> format:

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
We tried to build a 1 to 1 mapping between the data model and the given questionnaire representation:   
- the entire questionnaire is between the curly brackets;  
- inside, each questionnaire has a certain number of "childrens" that match to the sections of the original questionnaire representation; 
- each child is identified by a string representig the name of the respective section (so section A1 is identified by <code>a1</code>, section A2 is identified by <code>a2</code> and so on.); 
- each section has a certain number of fields, where the left side is the identifier of the field that is in the given questionnaire model and the right side is the value that contains the answer to that specific field. We can have different type of values:  
   - <code>text</code> (see for example <code>a2.education</code>), 
   - <code>numeric</code> (see for example <code>a2.weight</code>) 
   - lists that we represent as child components (see for example <code>a2.residencies</code>).

To access a particular field it is simply needed to follow the key hierarchy; e.g., to access the field <code>occupationStartingTime</code> of the section <code>a2</code>, we need to type <code>a2.historyOfOccupations.occupationStartingTime</code>.


