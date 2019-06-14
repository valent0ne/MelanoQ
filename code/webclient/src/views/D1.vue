<template>
  <div>
    <div class="home container my-4">
      <Message/>
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>{{$t('section_d')+'.I'}}</b-card-title>
          <div>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <!-- identifier -->
              <label for="identifier" class="mt-4">
                <h6>{{'0. '+$t('identifier_label')}}</h6>
              </label>
              <b-form-input
                v-model="$v.form.identifier.$model"
                :state="$v.form.identifier.$dirty ? !$v.form.identifier.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('identifier_desc')}}</b-form-text>

              <!-- preExistingPigmentedLesionAtTheSameSiteOfMelanoma -->
              <span class="form-inline">
                <label for="preExistingPigmentedLesionAtTheSameSiteOfMelanoma" class="mt-4">
                  <h6>{{'1. '+$t('preExistingPigmentedLesionAtTheSameSiteOfMelanoma_label')}}</h6>
                </label>

                <b-button-group class="ml-auto mt-2">
                  <b-button
                    :pressed="preExistingPigmentedLesionAtTheSameSiteOfMelanomaToggle == 'yes'"
                    @click="preExistingPigmentedLesionAtTheSameSiteOfMelanomaToggle = 'yes'"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="preExistingPigmentedLesionAtTheSameSiteOfMelanomaToggle == 'no'"
                    @click="preExistingPigmentedLesionAtTheSameSiteOfMelanomaToggle = 'no'; form.preExistingPigmentedLesionAtTheSameSiteOfMelanoma = ''"
                  >{{$t('no')}}</b-button>
                  <b-button
                    variant="outline-secondary"
                    :pressed="preExistingPigmentedLesionAtTheSameSiteOfMelanomaToggle == 'not known'"
                    @click="preExistingPigmentedLesionAtTheSameSiteOfMelanomaToggle = 'not known'; form.preExistingPigmentedLesionAtTheSameSiteOfMelanoma = ''"
                  >{{$t('not_known')}}</b-button>
                </b-button-group>
              </span>

              <b-form-text>{{$t('preExistingPigmentedLesionAtTheSameSiteOfMelanoma_desc')}}</b-form-text>
              <span
                v-if="preExistingPigmentedLesionAtTheSameSiteOfMelanomaToggle == 'yes'"
                class="w-100"
              >
                <b-form-input
                  class="mb-2 mt-2"
                  v-model="$v.form.preExistingPigmentedLesionAtTheSameSiteOfMelanoma.$model"
                  :state="$v.form.preExistingPigmentedLesionAtTheSameSiteOfMelanoma.$dirty ? !$v.form.preExistingPigmentedLesionAtTheSameSiteOfMelanoma.$error : null"
                  type="text"
                  required
                  :placeholder="$t('preExistingPigmentedLesionAtTheSameSiteOfMelanomaHowLong')"
                ></b-form-input>
              </span>

              <!-- detectionOfMelanoma -->
              <label for="detectionOfMelanoma" class="mt-4">
                <h6>{{'2. '+$t('detectionOfMelanoma_label')}}</h6>
              </label>
              <b-form-select
                v-model="$v.form.detectionOfMelanoma.$model"
                :state="$v.form.detectionOfMelanoma.$dirty ? !$v.form.detectionOfMelanoma.$error : null"
                :options="detectionOfMelanomaOptions"
              ></b-form-select>
              <b-form-text>{{$t('detectionOfMelanoma_desc')}}</b-form-text>

              <!-- selfSkinExam -->
              <label for="selfSkinExam" class="mt-4">
                <h6>{{'3. '+$t('selfSkinExam_label')}}</h6>
              </label>
              <b-form-select
                v-model="$v.form.selfSkinExam.$model"
                :state="$v.form.selfSkinExam.$dirty ? !$v.form.selfSkinExam.$error : null"
                :options="selfSkinExamOptions"
              ></b-form-select>
              <b-form-text>{{$t('selfSkinExam_desc')}}</b-form-text>

              <!-- skinExamByPhysician -->
              <label for="skinExamByPhysician" class="mt-4">
                <h6>{{'4. '+$t('skinExamByPhysician_label')}}</h6>
              </label>
              <b-form-select
                v-model="$v.form.skinExamByPhysician.$model"
                :state="$v.form.skinExamByPhysician.$dirty ? !$v.form.skinExamByPhysician.$error : null"
                :options="skinExamByPhysicianOptions"
              ></b-form-select>
              <b-form-text>{{$t('skinExamByPhysician_desc')}}</b-form-text>

              <!-- numberOfMPM -->
              <span class="form-inline mt-4">
                <label>
                  <h6>{{'5. '+$t('numberOfMPM_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="numberOfMPMToggle"
                    @click="numberOfMPMToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!numberOfMPMToggle"
                    @click="numberOfMPMToggle = false; $v.form.numberOfMPM.$model=''; "
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>

              <span v-if="numberOfMPMToggle">
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.numberOfMPM.$model"
                  :state="$v.form.numberOfMPM.$dirty ? !$v.form.numberOfMPM.$error : null"
                  type="text"
                ></b-form-input>
              </span>
              <b-form-text>{{$t('numberOfMPM_desc')}}</b-form-text>

              <!-- dateOfDiagnosis -->
              <label for="dateOfDiagnosis" class="mt-4">
                <h6>{{'6. '+$t('dateOfDiagnosis_label')}}</h6>
              </label>
              <date-picker
                name="dateOfDiagnosis"
                v-model="form.dateOfDiagnosis"
                :config="datePickerOptions"
                required
              ></date-picker>
              <b-form-text>{{$t('dateOfDiagnosis_desc')}}</b-form-text>

              <!-- primaryTumorKnown -->
              <span class="form-inline mt-4">
                <label>
                  <h6>{{'7. '+$t('primaryTumorKnown_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="form.primaryTumorKnown"
                    @click="form.primaryTumorKnown = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!form.primaryTumorKnown"
                    @click="form.primaryTumorKnown = false; /* TODO */"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>
              <b-form-text>{{$t('primaryTumorKnown_desc')}}</b-form-text>

              <span v-if="form.primaryTumorKnown">
                <!--TODO-->
              </span>

              <!-- buttons -->
              <b-button
                type="submit"
                variant="outline-success"
                class="mr-2 mb-1 mt-5"
              >{{$t('submit')}}</b-button>
              <b-button type="reset" variant="outline-danger" class="mr-2 mb-1 mt-5">{{$t('reset')}}</b-button>

              <b-button
                type="button"
                @click="addNewD1()"
                :disabled="(!canProceed)"
                variant="outline-info"
                class="fa-button-outline mb-1 mt-5 float-right"
              >
                {{$t('add_new_d1')}}
                &nbsp;
                <font-awesome-icon icon="plus"/>&nbsp;
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
import { ADD_MESSAGE, ADD_ERROR, INSERT_D1 } from "@/store/actions.type";
import { required, requiredIf, numeric } from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "d1",
  data() {
    return {
      form: {
        identifier: "",
        preExistingPigmentedLesionAtTheSameSiteOfMelanoma: "",
        detectionOfMelanoma: null,
        selfSkinExam: null,
        skinExamByPhysician: null,
        numberOfMPM: "",
        dateOfDiagnosis: this.$moment().format("DD/MMM/YYYY"),
        primaryTumorKnown: false
      },
      datePickerOptions: {
        // https://momentjs.com/docs/#/displaying/
        format: "DD/MMM/YYYY",
        useCurrent: true,
        showClear: true,
        showClose: true
      },
      detectionOfMelanomaOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Patient", text: this.$t("patient") },
        {
          value: "Relative/Spouse/Friend",
          text: this.$t("relative/spouse/friend")
        },
        { value: "Physician", text: this.$t("physician") },
        { value: "Other", text: this.$t("other") }
      ],
      selfSkinExamOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Never", text: this.$t("never") },
        {
          value: "Once",
          text: this.$t("once")
        },
        { value: "Once/year", text: this.$t("once/year") },
        { value: "More than once/year", text: this.$t("more_than_once/year") }
      ],
      skinExamByPhysicianOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Never", text: this.$t("never") },
        {
          value: "Once",
          text: this.$t("once")
        },
        { value: "Once/year", text: this.$t("once/year") },
        { value: "More than once/year", text: this.$t("more_than_once/year") },
        {
          value: "Do not recall a physician ever examining my skin",
          text: this.$t("do_not_recall_a_physician_ever_examining_my_skin")
        }
      ],
      preExistingPigmentedLesionAtTheSameSiteOfMelanomaToggle: "no",
      numberOfMPMToggle: false,

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
    addNewD1() {
      if (this.canProceed) {
        this.$router.push({ name: "d1" });
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
        this.$store.dispatch(INSERT_D1, this.form).then(() => {
          this.$store.dispatch(ADD_MESSAGE, "form_success");
          that.canProceed = true;
        });
      }
    },
    onReset(evt) {
      evt.preventDefault();

      this.form.identifier = "";
      this.form.preExistingPigmentedLesionAtTheSameSiteOfMelanoma = "";
      this.preExistingPigmentedLesionAtTheSameSiteOfMelanomaToggle = "no";
      this.form.detectionOfMelanoma = null;
      this.form.selfSkinExam = null;
      this.form.skinExamByPhysician = null;
      this.numberOfMPMToggle = false;
      this.dateOfDiagnosis = this.$moment().format("DD/MMM/YYYY");
      this.primaryTumorKnown = false;

      this.$v.$reset();

      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
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
      identifier: { required },
      preExistingPigmentedLesionAtTheSameSiteOfMelanoma: {
        required: requiredIf(function() {
          return this.preExistingPigmentedLesionAtTheSameSiteOfMelanomaToggle;
        })
      },
      detectionOfMelanoma: {},
      selfSkinExam: {},
      skinExamByPhysician: {},
      numberOfMPM: {
        numeric,
        required: requiredIf(function() {
          return this.numberOfMPMToggle;
        })
      },
      dateOfDiagnosis: { required },
      primaryTumorKnown: { required }
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