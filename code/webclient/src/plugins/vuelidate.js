import Vue from 'vue'
import Vuelidate from 'vuelidate'

// custom validators

export const pregnancyHistoryNumberOfMiscarriagesValidator = (value, vm) => {
  if(vm.numberOfFullTermPregnancies != "" && vm.numberOfFullTermPregnancies != "0" && !isNaN(vm.numberOfFullTermPregnancies)){
      return (value >= 0 && value <= vm.numberOfFullTermPregnancies)
  }
  return true
}

export const dateOfBirthValidator = (value) => {
  var months = [
    "gen",
    "feb",
    "mar",
    "apr",
    "may",
    "jun",
    "jul",
    "aug",
    "sep",
    "oct",
    "nov",
    "dec",
    "mmm",
  ];

  try {
    var dd = value.substring(0, 2);
    var mmm = value.substring(3, 6);
    var yyyy = value.substring(7);

  } catch (error) {
    if (value === "UNK") {
      return true;
    } else {
      return false;
    }
  }

  if (dd > 31 || dd < 1) {
    return false;
  }
  if (!months.includes(mmm.toLowerCase())) {
    return false;
  }
  if (yyyy.toLowerCase() !== "yyyy" && isNaN(yyyy)) {
    return false;
  }
  return true;
}




Vue.use(Vuelidate)

export default {


}