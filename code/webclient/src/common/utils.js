/**
* removeByIndex
* @param {Array} array
* @param {Number} index
*/
export const removeByObjectKey = (array, key) => {
  for (var i = array.length - 1; i >= 0; --i) {
    if (array[i].key == key) {
      array.splice(i, 1);
    }
  }
}

/**
* removeByIndex
* @param {Object} model object
* @param {Array} rules empty array
*/
export const buildRuleList = (model, rules) => {
  var textOperators = ['contains', 'does not contain', 'equals', 'does not equal',]
  var numericOperators = ['=', '<>', '>', '<', '>=', '<=']
  for (var key in model) {
    if (model.hasOwnProperty(key) && key != "_class") {
      var secondLevelObject = model[key];
      // console.log(key);
      // console.log(secondLevelObject);
      // inspecting inner keys (second level, e.g. a1.subject, etc.)
      for (var secondLevelKey in secondLevelObject) {
        if (secondLevelObject.hasOwnProperty(secondLevelKey)) {
          // console.log("   " + secondLevelKey);
          // console.log(typeof secondLevelObject[secondLevelKey]);
          // if the type is string then we are finished, push the entry to the ruleset
          if (secondLevelObject[secondLevelKey] == "text" || secondLevelObject[secondLevelKey] == "numeric") {
            rules.push({
              type: secondLevelObject[secondLevelKey],
              id: key + "." + secondLevelKey,
              label: key + "." + secondLevelKey,
              operators: (secondLevelObject[secondLevelKey] == "text") ? textOperators : numericOperators
            });
          } // if the type is object then we have to go deeper
          else if (typeof secondLevelObject[secondLevelKey] == "object") {
            var thirdLevelObject = secondLevelObject[secondLevelKey];

            for (var thirdLevelKey in thirdLevelObject) {
              if (thirdLevelObject.hasOwnProperty(thirdLevelKey)) {
                //console.log("      " + thirdLevelKey);

                if (thirdLevelObject[thirdLevelKey] == "text" || thirdLevelObject[thirdLevelKey] == "numeric") {
                  rules.push({
                    type: thirdLevelObject[thirdLevelKey],
                    id: key + "." + secondLevelKey + "." + thirdLevelKey,
                    label: key + "." + secondLevelKey + "." + thirdLevelKey,
                    operators: (thirdLevelObject[thirdLevelKey] == "text") ? textOperators : numericOperators
                  });
                }
              }
            }
          }
        }
      }
    }
  }
  return rules
}

export default { removeByObjectKey, buildRuleList }