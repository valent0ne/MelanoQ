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

export default { removeByObjectKey }