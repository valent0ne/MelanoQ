const ID_LOCALE_KEY = "id_locale";

export const getLocale = () => {
  var locale = window.localStorage.getItem(ID_LOCALE_KEY);
  return locale
};

export const saveLocale = locale => {
  window.localStorage.setItem(ID_LOCALE_KEY, locale);
};

export const destroyLocale = () => {
  window.localStorage.removeItem(ID_LOCALE_KEY);
};

export default { getLocale, saveLocale, destroyLocale };