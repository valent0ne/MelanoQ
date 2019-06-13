<template>
  <div>
    <div class="home container my-4">
      <Message/>
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>{{$t('section_c')+'.II'}}</b-card-title>
          <div>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <!-- medicalDiagnoses -->
              <span class="form-inline mt-4">
                <label for="medicalDiagnoses" class="mt-4">
                  <h6>{{'9. '+$t('medicalDiagnoses_label')}}</h6>
                </label>
                <span class="ml-auto mt-auto">
                  <b-button variant="outline-success" @click="addMedicalDiagnosesField()">
                    <font-awesome-icon icon="plus"/>
                  </b-button>
                  <b-button
                    variant="outline-danger"
                    @click="removeMedicalDiagnosesField()"
                    class="ml-2"
                  >
                    <font-awesome-icon icon="minus"/>
                  </b-button>
                </span>
              </span>
              <b-form-text>{{$t('medicalDiagnoses_desc')}}</b-form-text>

              <b-form class="mt-2">
                <span v-for="(md, index) in $v.form.medicalDiagnoses.$each.$iter" :key="index">
                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="md.diagnosisName.$model"
                    :state="md.diagnosisName.$dirty ? !md.diagnosisName.$error : null"
                    type="text"
                    required
                    :placeholder="$t('medicalDiagnosesDiagnosisName_desc')"
                  ></b-form-input>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="md.ICD10Code.$model"
                    :state="md.ICD10Code.$dirty ? !md.ICD10Code.$error : null"
                    type="text"
                    required
                    :placeholder="$t('medicalDiagnosesDiagnosisICD10Code_desc')"
                  ></b-form-input>

                  <hr v-if="medicalDiagnosesHrVisible">
                </span>
              </b-form>

              <!-- previousAndConcomitantTreatments -->
              <span class="form-inline mt-4">
                <label for="previousAndConcomitantTreatments" class="mt-4">
                  <h6>{{'10. '+$t('previousAndConcomitantTreatments_label')}}</h6>
                </label>
                <span class="ml-auto mt-auto">
                  <b-button
                    variant="outline-success"
                    @click="addPreviousAndConcomitantTreatmentsField()"
                  >
                    <font-awesome-icon icon="plus"/>
                  </b-button>
                  <b-button
                    variant="outline-danger"
                    @click="removePreviousAndConcomitantTreatmentsField()"
                    class="ml-2"
                  >
                    <font-awesome-icon icon="minus"/>
                  </b-button>
                </span>
              </span>
              <b-form-text>{{$t('previousAndConcomitantTreatments_desc')}}</b-form-text>

              <b-form class="mt-2">
                <span
                  v-for="(pct, index) in $v.form.previousAndConcomitantTreatments.$each.$iter"
                  :key="index"
                >
                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="pct.treatmentName.$model"
                    :state="pct.treatmentName.$dirty ? !pct.treatmentName.$error : null"
                    type="text"
                    required
                    :placeholder="$t('previousAndConcomitantTreatmentsTreatmentName_desc')"
                  ></b-form-input>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="pct.treatmentStartingTime.$model"
                    :state="pct.treatmentStartingTime.$dirty ? !pct.treatmentStartingTime.$error : null"
                    type="text"
                    required
                    :placeholder="$t('previousAndConcomitantTreatmentsTreatmentStartingTime_desc')"
                  ></b-form-input>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="pct.treatmentEndingTime.$model"
                    :state="pct.treatmentEndingTime.$dirty ? !pct.treatmentEndingTime.$error : null"
                    type="text"
                    required
                    :placeholder="$t('previousAndConcomitantTreatmentsTreatmentEndingTime_desc')"
                  ></b-form-input>

                  <hr v-if="previousAndConcomitantTreatmentsHrVisible">
                </span>
              </b-form>

              <!-- pregnancyHistoryNumberOfFullTermPregnancies -->
              <label for="pregnancyHistoryNumberOfFullTermPregnancies" class="mt-4">
                <h6>{{'11a. '+$t('pregnancyHistoryNumberOfFullTermPregnancies_label')}}</h6>
              </label>
              <b-form-input
                v-model="$v.form.pregnancyHistory.numberOfFullTermPregnancies.$model"
                :state="$v.form.pregnancyHistory.numberOfFullTermPregnancies.$dirty ? !$v.form.pregnancyHistory.numberOfFullTermPregnancies.$error : null"
                v-on:input="fillPregnancyHistoryDateOfBirthOfChildren()"
                maxlength="2"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('pregnancyHistoryNumberOfFullTermPregnancies_desc')}}</b-form-text>

              <span v-if="$v.form.pregnancyHistory.dateOfBirthOfChildren.$model.length > 0">
                <label for="pregnancyHistoryDateOfBirthOfChildren" class="mt-4">
                  <h6>{{'11b. '+$t('pregnancyHistoryDateOfBirthOfChildren_label')}}</h6>
                </label>
                <span
                  v-for="(dbc, index) in $v.form.pregnancyHistory.dateOfBirthOfChildren.$each.$iter"
                  :key="index"
                >
                  <!-- pregnancyHistoryDateOfBirthOfChildren -->
                  <b-form-input
                    v-model="form.pregnancyHistory.dateOfBirthOfChildren[index]"
                    v-on:input="dbc.$model = form.pregnancyHistory.dateOfBirthOfChildren[index]"
                    :state="dbc.$dirty ? !dbc.$error : null"
                    type="text"
                    required
                  ></b-form-input>
                  <b-form-text
                    class="mb-2"
                  >{{$t('pregnancy')+' '+(parseInt(index) + 1)+'/'+$v.form.pregnancyHistory.dateOfBirthOfChildren.$model.length}}</b-form-text>
                </span>

                <!-- pregnancyHistoryNumberOfMiscarriages -->
                <label for="pregnancyHistoryNumberOfMiscarriages" class="mt-4">
                  <h6>{{'11c. '+$t('pregnancyHistoryNumberOfMiscarriages_label')}}</h6>
                </label>

                <b-form-input
                  v-model="$v.form.pregnancyHistory.numberOfMiscarriages.$model"
                  :state="$v.form.pregnancyHistory.numberOfMiscarriages.$dirty ? !$v.form.pregnancyHistory.numberOfMiscarriages.$error : null"
                  type="text"
                  required
                ></b-form-input>
                <b-form-text>{{$t('pregnancyHistoryNumberOfMiscarriages_desc')}}</b-form-text>

                <!-- pregnancyHistoryMelanomaOccurDuringPregnancy -->
                <label for="pregnancyHistoryMelanomaOccurDuringPregnancy" class="mt-4">
                  <h6>{{'11d. '+$t('pregnancyHistoryMelanomaOccurDuringPregnancy_label')}}</h6>
                </label>

                <b-form-checkbox
                  v-model="$v.form.pregnancyHistory.melanomaOccurDuringPregnancy.$model"
                  :state="$v.form.pregnancyHistory.melanomaOccurDuringPregnancy.$dirty ? !$v.form.pregnancyHistory.melanomaOccurDuringPregnancy.$error : null"
                >{{$t('pregnancyHistoryMelanomaOccurDuringPregnancy_desc')}}</b-form-checkbox>

                <!-- pregnancyHistoryMelanomaOccurBeforePregnancy -->
                <label for="pregnancyHistoryMelanomaOccurBeforePregnancy" class="mt-4">
                  <h6>{{'11e. '+$t('pregnancyHistoryMelanomaOccurBeforePregnancy_label')}}</h6>
                </label>

                <b-form-checkbox
                  v-model="$v.form.pregnancyHistory.melanomaOccurBeforePregnancy.$model"
                  :state="$v.form.pregnancyHistory.melanomaOccurBeforePregnancy.$dirty ? !$v.form.pregnancyHistory.melanomaOccurBeforePregnancy.$error : null"
                >{{$t('pregnancyHistoryMelanomaOccurBeforePregnancy_desc')}}</b-form-checkbox>

                <span v-if="$v.form.pregnancyHistory.melanomaOccurBeforePregnancy.$model">
                  <!-- pregnancyHistoryHowManyYearsBeforePregnancy -->
                  <label for="pregnancyHistoryHowManyYearsBeforePregnancy" class="mt-4">
                    <h6>{{'11e.I. '+$t('pregnancyHistoryHowManyYearsBeforePregnancy_label')}}</h6>
                  </label>

                  <b-form-input
                    v-model="$v.form.pregnancyHistory.howManyYearsBeforePregnancy.$model"
                    :state="$v.form.pregnancyHistory.howManyYearsBeforePregnancy.$dirty ? !$v.form.pregnancyHistory.howManyYearsBeforePregnancy.$error : null"
                    type="text"
                    required
                    :placeholder="$t('pregnancyHistoryHowManyYearsBeforePregnancy_desc')"
                  ></b-form-input>
                </span>

                <!-- pregnancyHistoryMelanomaOccurAfterPregnancy -->
                <label for="pregnancyHistoryMelanomaOccurAfterPregnancy" class="mt-4">
                  <h6>{{'11f. '+$t('pregnancyHistoryMelanomaOccurAfterPregnancy_label')}}</h6>
                </label>

                <b-form-checkbox
                  v-model="$v.form.pregnancyHistory.melanomaOccurAfterPregnancy.$model"
                  :state="$v.form.pregnancyHistory.melanomaOccurAfterPregnancy.$dirty ? !$v.form.pregnancyHistory.melanomaOccurAfterPregnancy.$error : null"
                >{{$t('pregnancyHistoryMelanomaOccurAfterPregnancy_desc')}}</b-form-checkbox>

                <span v-if="$v.form.pregnancyHistory.melanomaOccurAfterPregnancy.$model">
                  <!-- pregnancyHistoryHowManyYearsAfterPregnancy -->
                  <label for="pregnancyHistoryHowManyYearsAfterPregnancy" class="mt-4">
                    <h6>{{'11f.I. '+$t('pregnancyHistoryHowManyYearsAfterPregnancy_label')}}</h6>
                  </label>

                  <b-form-input
                    v-model="$v.form.pregnancyHistory.howManyYearsAfterPregnancy.$model"
                    :state="$v.form.pregnancyHistory.howManyYearsAfterPregnancy.$dirty ? !$v.form.pregnancyHistory.howManyYearsAfterPregnancy.$error : null"
                    type="text"
                    required
                    :placeholder="$t('pregnancyHistoryHowManyYearsAfterPregnancy_desc')"
                  ></b-form-input>
                </span>
              </span>

              <!-- pregnancyHistoryIVFBeforeDiagnosis -->
              <label for="pregnancyHistoryIVFBeforeDiagnosisy" class="mt-4">
                <h6>{{'11g '+$t('pregnancyHistoryIVFBeforeDiagnosis_label')}}</h6>
              </label>

              <b-form-checkbox
                v-model="$v.form.pregnancyHistory.IVFBeforeDiagnosis.$model"
                :state="$v.form.pregnancyHistory.IVFBeforeDiagnosis.$dirty ? !$v.form.pregnancyHistory.IVFBeforeDiagnosis.$error : null"
              >{{$t('pregnancyHistoryIVFBeforeDiagnosis_desc')}}</b-form-checkbox>
              <!-- buttons -->
              <b-button
                type="submit"
                variant="outline-success"
                class="mr-2 mb-1 mt-5"
              >{{$t('submit')}}</b-button>
              <b-button type="reset" variant="outline-danger" class="mr-2 mb-1 mt-5">{{$t('reset')}}</b-button>

              <b-button
                type="button"
                @click="proceed()"
                :disabled="(!canProceed)"
                variant="outline-info"
                class="fa-button-outline mr-2 mb-1 mt-5 float-right"
              >
                {{$t('proceed')}}
                &nbsp;
                <font-awesome-icon icon="arrow-right"/>&nbsp;
              </b-button>
              <b-button
                type="button"
                to="/choice"
                variant="outline-info"
                class="fa-button-outline mr-2 mb-1 mt-5 float-right"
              >
                <font-awesome-icon icon="arrow-right" flip="horizontal"/>
                &nbsp;
                {{$t('back_to_section_choice')}}
              </b-button>
            </b-form>
          </div>
        </div>
      </b-card>
    </div>
    <br>
    <br>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { ADD_MESSAGE, ADD_ERROR, INSERT_C2 } from "@/store/actions.type";
import {
  required,
  minLength,
  maxLength,
  numeric,
  requiredIf
} from "vuelidate/lib/validators";
import { pregnancyHistoryNumberOfMiscarriagesValidator } from "@/plugins/vuelidate";

import Message from "@/components/Message.vue";

export default {
  name: "c2",
  data() {
    return {
      form: {
        medicalDiagnoses: [],
        previousAndConcomitantTreatments: [],
        pregnancyHistory: {
          numberOfFullTermPregnancies: "",
          dateOfBirthOfChildren: [],
          numberOfMiscarriages: "",
          melanomaOccurDuringPregnancy: false,
          melanomaOccurBeforePregnancy: false,
          howManyYearsBeforePregnancy: "",
          melanomaOccurAfterPregnancy: false,
          howManyYearsAfterPregnancy: "",
          IVFBeforeDiagnosis: false
        }
      },

      medicalDiagnosesHrVisible: false,
      previousAndConcomitantTreatmentsHrVisible: false,

      show: true,
      canProceed: false
    };
  },
  created: function() {
    if (this.type !== "physician") {
      this.$store.dispatch(ADD_ERROR, "unauthorized");
      this.$router.push({ name: "home" });
    }
    if (!this.isAuthenticated) {
      this.$store.dispatch(ADD_ERROR, "not_authenticated");
      this.$router.push({ name: "home" });
    }
  },
  methods: {
    fillPregnancyHistoryDateOfBirthOfChildren() {
      this.form.pregnancyHistory.dateOfBirthOfChildren = [];

      if (
        this.form.pregnancyHistory.numberOfFullTermPregnancies != "" &&
        !isNaN(this.form.pregnancyHistory.numberOfFullTermPregnancies)
      ) {
        for (
          let i = 0;
          i < this.form.pregnancyHistory.numberOfFullTermPregnancies;
          i++
        ) {
          this.form.pregnancyHistory.dateOfBirthOfChildren.push("");
        }
      }
    },
    addPreviousAndConcomitantTreatmentsField() {
      this.form.previousAndConcomitantTreatments.push({
        treatmentName: "",
        treatmentStartingTime: "",
        treatmentEndingTime: ""
      });
      if (this.form.previousAndConcomitantTreatments.length > 1) {
        this.previousAndConcomitantTreatmentsHrVisible = true;
      }
    },
    removePreviousAndConcomitantTreatmentsField() {
      if (this.form.previousAndConcomitantTreatments.length > 0) {
        this.form.previousAndConcomitantTreatments.pop();
      }
      if (this.form.previousAndConcomitantTreatments.length == 1) {
        this.previousAndConcomitantTreatmentsHrVisible = false;
      }
    },
    addMedicalDiagnosesField() {
      this.form.medicalDiagnoses.push({
        diagnosisName: "",
        ICD10Code: ""
      });
      if (this.form.medicalDiagnoses.length > 1) {
        this.medicalDiagnosesHrVisible = true;
      }
    },
    removeMedicalDiagnosesField() {
      if (this.form.medicalDiagnoses.length > 0) {
        this.form.medicalDiagnoses.pop();
      }
      if (this.form.medicalDiagnoses.length == 1) {
        this.medicalDiagnosesHrVisible = false;
      }
    },
    onSubmit(evt) {
      evt.preventDefault();

      this.$v.$touch();
      // eslint-disable-next-line
      console.log(JSON.stringify(this.form));
      if (this.$v.$invalid) {
        this.$store.dispatch(ADD_ERROR, "form_dirty");
      } else {
        var that = this;
        this.$store.dispatch(INSERT_C2, this.form).then(() => {
          this.$store.dispatch(ADD_MESSAGE, "form_success");
          that.canProceed = true;
        });
      }
    },
    onReset(evt) {
      evt.preventDefault();

      this.form.medicalDiagnoses = [];
      this.form.previousAndConcomitantTreatments = [];

      this.form.pregnancyHistory = {
        numberOfFullTermPregnancies: "",
        dateOfBirthOfChildren: [],
        numberOfMiscarriages: "",
        melanomaOccurDuringPregnancy: false,
        melanomaOccurBeforePregnancy: false,
        howManyYearsBeforePregnancy: "",
        melanomaOccurAfterPregnancy: false,
        howManyYearsAfterPregnancy: "",
        IVFBeforeDiagnosis: false
      };

      this.$v.$touch();

      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    proceed() {
      if (this.canProceed) {
        this.$router.push({ name: "c2" });
      }
    }
  },
  components: {
    Message
  },
  props: {
    action: String
  },
  validations: {
    form: {
      medicalDiagnoses: {
        $each: {
          diagnosisName: { required },
          ICD10Code: {
            required,
            minLength: minLength(3),
            maxLength: maxLength(5)
          }
        }
      },
      previousAndConcomitantTreatments: {
        $each: {
          treatmentName: { required },
          treatmentStartingTime: {
            required,
            numeric,
            minLength: minLength(4),
            maxLength: maxLength(4)
          },
          treatmentEndingTime: {
            required,
            numeric,
            minLength: minLength(4),
            maxLength: maxLength(4)
          }
        }
      },
      pregnancyHistory: {
        numberOfFullTermPregnancies: { numeric, required },
        dateOfBirthOfChildren: {
          $each: {
            required,
            numeric,
            minLength: minLength(4),
            maxLength: maxLength(4)
          }
        },
        numberOfMiscarriages: {
          numeric,
          required: requiredIf(function() {
            return this.form.pregnancyHistory.numberOfFullTermPregnancies != "";
          }),
          pregnancyHistoryNumberOfMiscarriagesValidator
        },
        melanomaOccurDuringPregnancy: { required },
        melanomaOccurBeforePregnancy: { required },
        howManyYearsBeforePregnancy: {
          numeric,
          required: requiredIf(function() {
            return this.form.pregnancyHistory.melanomaOccurBeforePregnancy;
          })
        },
        melanomaOccurAfterPregnancy: { required },
        howManyYearsAfterPregnancy: {
          numeric,
          required: requiredIf(function() {
            return this.form.pregnancyHistory.melanomaOccurAfterPregnancy;
          })
        },
        IVFBeforeDiagnosis: { required }
      }
    }
  },
  computed: {
    ...mapState({
      user: state => state.auth.user,
      isAuthenticated: state => state.auth.isAuthenticated,
      type: state => state.auth.type
    })
  }
};
</script>