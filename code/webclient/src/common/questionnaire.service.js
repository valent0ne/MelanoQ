const ID_DB_CODE_NUMBER_KEY = "id_db_code_number";

export const getDbCodeNumber = () => {
  var documentId = window.localStorage.getItem(ID_DB_CODE_NUMBER_KEY);
  return documentId
};

export const saveDbCodeNumber = documentId => {
  window.localStorage.setItem(ID_DB_CODE_NUMBER_KEY, documentId);
};

export const destroyDbCodeNumber = () => {
  window.localStorage.removeItem(ID_DB_CODE_NUMBER_KEY);
};

export default { getDbCodeNumber, saveDbCodeNumber, destroyDbCodeNumber };