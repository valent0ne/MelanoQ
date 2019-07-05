const ID_USER_USERNAME_KEY = "id_user_username";
const ID_USER_ROLE_KEY = "id_user_role";
const ID_USER_TYPE_KEY = "id_user_type";
const ID_USER_TOKEN_KEY = "id_user_token";

export const getUser = () => {
  var user = {}

  user.username = window.localStorage.getItem(ID_USER_USERNAME_KEY);
  user.role = window.localStorage.getItem(ID_USER_ROLE_KEY);
  user.type = window.localStorage.getItem(ID_USER_TYPE_KEY);
  user.token = window.localStorage.getItem(ID_USER_TOKEN_KEY);

  return user
};

export const saveUser = user => {

  window.localStorage.setItem(ID_USER_USERNAME_KEY, user.username);
  window.localStorage.setItem(ID_USER_ROLE_KEY, user.role);
  window.localStorage.setItem(ID_USER_TYPE_KEY, user.type);
  window.localStorage.setItem(ID_USER_TOKEN_KEY, user.token);

};

export const destroyUser = () => {
  window.localStorage.removeItem(ID_USER_USERNAME_KEY);
  window.localStorage.removeItem(ID_USER_ROLE_KEY);
  window.localStorage.removeItem(ID_USER_TYPE_KEY);
  window.localStorage.removeItem(ID_USER_TOKEN_KEY);
};

export default { getUser, saveUser, destroyUser };