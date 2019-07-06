const ID_DOCUMENT_ID_KEY = "id_document_id";

export const getDocumentId = () => {
  var documentId = window.localStorage.getItem(ID_DOCUMENT_ID_KEY);
  return documentId
};

export const saveDocumentId = documentId => {
  window.localStorage.setItem(ID_DOCUMENT_ID_KEY, documentId);
};

export const destroyDocumentId = () => {
  window.localStorage.removeItem(ID_DOCUMENT_ID_KEY);
};

export default { getDocumentId, saveDocumentId, destroyDocumentId };