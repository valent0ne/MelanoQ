<template>
  <div>
    <div class="home container my-4">
      <Message/>
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>
            <span class="float-left mt-1">{{$t('section_c')+'.III'}}</span>
            <!-- buttons -->
            <b-button
              type="button"
              @click="proceed()"
              :disabled="(!canProceed)"
              variant="outline-info"
              class="fa-button-outline float-right"
            >
              {{$t('proceed')}}
              &nbsp;
              <font-awesome-icon icon="arrow-right"/>&nbsp;
            </b-button>
            <b-button
              type="button"
              to="/choice"
              variant="outline-info"
              class="fa-button-outline mr-2 float-right"
            >
              <font-awesome-icon icon="arrow-right" flip="horizontal"/>
              &nbsp;
              {{$t('back_to_section_choice')}}
            </b-button>
          </b-card-title>
          <div>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <!-- familyHistoryOfMelanomaList -->
              <span class="form-inline mt-4">
                <label for="familyHistoryOfMelanomaList" class="mt-4">
                  <h6>{{'14. '+$t('familyHistoryOfMelanomaList_label')}}</h6>
                </label>
                <span class="ml-auto mt-auto">
                  <b-button
                    variant="outline-success"
                    @click="addFamilyHistoryOfMelanomaListField()"
                  >
                    <font-awesome-icon icon="plus"/>
                  </b-button>
                  <b-button
                    variant="outline-danger"
                    @click="removeFamilyHistoryOfMelanomaListField()"
                    class="ml-2"
                  >
                    <font-awesome-icon icon="minus"/>
                  </b-button>
                </span>
              </span>
              <b-form-text>{{$t('familyHistoryOfMelanomaList_desc')}}</b-form-text>

              <b-form class="mt-2">
                <span
                  v-for="(fhml, index) in $v.form.familyHistoryOfMelanomaList.$each.$iter"
                  :key="index"
                >
                  <b-form-select
                    class="mr-2 mb-2"
                    v-model="fhml.presence.$model"
                    :state="fhml.presence.$dirty ? !fhml.presence.$error : null"
                    :options="familyHistoryOfMelanomaListPresenceOptions"
                  ></b-form-select>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="fhml.type.$model"
                    :state="fhml.type.$dirty ? !fhml.type.$error : null"
                    type="text"
                    required
                    :placeholder="$t('familyHistoryOfMelanomaListType_desc')"
                  ></b-form-input>

                  <b-form-select
                    class="mr-2 mb-2"
                    v-model="fhml.sideOfAffectedRelative.$model"
                    :state="fhml.sideOfAffectedRelative.$dirty ? !fhml.sideOfAffectedRelative.$error : null"
                    :options="familyHistoryOfMelanomaListSideOfAffectedRelativeOptions"
                  ></b-form-select>

                  <b-form-select
                    class="mr-2 mb-2"
                    v-model="fhml.degreeOfRelative.$model"
                    :state="fhml.degreeOfRelative.$dirty ? !fhml.degreeOfRelative.$error : null"
                    :options="familyHistoryOfMelanomaListDegreeOfRelativeOptions"
                  ></b-form-select>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="fhml.ageAtDiagnosis.$model"
                    :state="fhml.ageAtDiagnosis.$dirty ? !fhml.ageAtDiagnosis.$error : null"
                    type="text"
                    required
                    :placeholder="$t('familyHistoryOfMelanomaListAgeAtDiagnosis_desc')"
                  ></b-form-input>

                  <hr v-if="familyHistoryOfMelanomaListHrVisible">
                </span>
              </b-form>

              <label for="germlineStatus" class="mt-4">
                <h6>{{'15. '+$t('germlineStatus_label')}}</h6>
              </label>
              <b-form-select
                class="mr-2 mb-2"
                v-model="$v.form.germlineStatus.$model"
                :state="$v.form.germlineStatus.$dirty ? !$v.form.germlineStatus.$error : null"
                :options="germlineStatusOptions"
              ></b-form-select>
              <b-form-text>{{$t('germlineStatus_desc')}}</b-form-text>

              <!-- familyHistoryOfOtherCancer -->
              <span class="form-inline mt-4">
                <label for="familyHistoryOfOtherCancer" class="mt-4">
                  <h6>{{'16. '+$t('familyHistoryOfOtherCancer_label')}}</h6>
                </label>
                <span class="ml-auto mt-auto">
                  <b-button variant="outline-success" @click="addFamilyHistoryOfOtherCancerField()">
                    <font-awesome-icon icon="plus"/>
                  </b-button>
                  <b-button
                    variant="outline-danger"
                    @click="removeFamilyHistoryOfOtherCancerField()"
                    class="ml-2"
                  >
                    <font-awesome-icon icon="minus"/>
                  </b-button>
                </span>
              </span>
              <b-form-text>{{$t('familyHistoryOfOtherCancer_desc')}}</b-form-text>

              <b-form class="mt-2">
                <span
                  v-for="(fhc, index) in $v.form.familyHistoryOfOtherCancer.$each.$iter"
                  :key="index"
                >
                  <b-form-select
                    class="mr-2 mb-2"
                    v-model="fhc.presence.$model"
                    :state="fhc.presence.$dirty ? !fhc.presence.$error : null"
                    :options="familyHistoryOfOtherCancerPresenceOptions"
                  ></b-form-select>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="fhc.type.$model"
                    :state="fhc.type.$dirty ? !fhc.type.$error : null"
                    type="text"
                    required
                    :placeholder="$t('familyHistoryOfOtherCancerType_desc')"
                  ></b-form-input>

                  <b-form-select
                    class="mr-2 mb-2"
                    v-model="fhc.sideOfAffectedRelative.$model"
                    :state="fhc.sideOfAffectedRelative.$dirty ? !fhc.sideOfAffectedRelative.$error : null"
                    :options="familyHistoryOfOtherCancerSideOfAffectedRelativeOptions"
                  ></b-form-select>

                  <b-form-select
                    class="mr-2 mb-2"
                    v-model="fhc.degreeOfRelative.$model"
                    :state="fhc.degreeOfRelative.$dirty ? !fhc.degreeOfRelative.$error : null"
                    :options="familyHistoryOfOtherCancerDegreeOfRelativeOptions"
                  ></b-form-select>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="fhc.ageAtDiagnosis.$model"
                    :state="fhc.ageAtDiagnosis.$dirty ? !fhc.ageAtDiagnosis.$error : null"
                    type="text"
                    required
                    :placeholder="$t('familyHistoryOfOtherCancerAgeAtDiagnosis_desc')"
                  ></b-form-input>

                  <hr v-if="familyHistoryOfOtherCancerHrVisible">
                </span>
              </b-form>

              <!-- buttons -->
              <hr class="mt-5">
              <b-button
                type="reset"
                variant="outline-danger"
                class="float-right mb-1"
              >{{$t('reset')}}</b-button>
              <b-button
                type="submit"
                variant="outline-success"
                class="float-right mr-2 mb-1"
              >{{$t('submit')}}</b-button>
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
import { ADD_MESSAGE, ADD_ERROR, INSERT_C3 } from "@/store/actions.type";
import { required, numeric, between } from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "c3",
  data() {
    return {
      form: {
        familyHistoryOfMelanomaList: [],
        germlineStatus: null,
        familyHistoryOfOtherCancer: []
      },

      familyHistoryOfOtherCancerPresenceOptions: [
        {
          value: null,
          text: this.$t("familyHistoryOfOtherCancerPresence_desc")
        },
        { value: "Yes", text: this.$t("yes") },
        { value: "No", text: this.$t("no") },
        { value: "Not known", text: this.$t("not_known") },
        { value: "Other", text: this.$t("other") }
      ],
      familyHistoryOfOtherCancerSideOfAffectedRelativeOptions: [
        {
          value: null,
          text: this.$t("familyHistoryOfOtherCancerSideOfAffectedRelative_desc")
        },
        { value: "Maternal side", text: this.$t("maternal_side") },
        { value: "Paternal side", text: this.$t("paternal_side") }
      ],
      familyHistoryOfOtherCancerDegreeOfRelativeOptions: [
        {
          value: null,
          text: this.$t("familyHistoryOfOtherCancerDegreeOfRelative_desc")
        },
        { value: "1st", text: this.$t("1st") },
        { value: "2nd", text: this.$t("2nd") },
        { value: "3rd", text: this.$t("3rd") }
      ],

      germlineStatusOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Not tested", text: this.$t("not_tested") },
        { value: "CDKN2A", text: "CDKN2A" },
        { value: "CDK4", text: "CDK4" },
        { value: "BAP-1", text: "BAP-1" },
        { value: "MC1R", text: "MC1R" },
        { value: "TERT", text: "TERT" },
        { value: "MITF", text: "MITF" },
        { value: "POT1", text: "POT1" },
        { value: "Other", text: this.$t("other") }
      ],

      familyHistoryOfMelanomaListPresenceOptions: [
        {
          value: null,
          text: this.$t("familyHistoryOfMelanomaListPresence_desc")
        },
        { value: "Yes", text: this.$t("yes") },
        { value: "No", text: this.$t("no") },
        { value: "Not known", text: this.$t("not_known") },
        { value: "Other", text: this.$t("other") }
      ],
      familyHistoryOfMelanomaListSideOfAffectedRelativeOptions: [
        {
          value: null,
          text: this.$t(
            "familyHistoryOfMelanomaListSideOfAffectedRelative_desc"
          )
        },
        { value: "Maternal side", text: this.$t("maternal_side") },
        { value: "Paternal side", text: this.$t("paternal_side") }
      ],
      familyHistoryOfMelanomaListDegreeOfRelativeOptions: [
        {
          value: null,
          text: this.$t("familyHistoryOfMelanomaListDegreeOfRelative_desc")
        },
        { value: "1st", text: this.$t("1st") },
        { value: "2nd", text: this.$t("2nd") },
        { value: "3rd", text: this.$t("3rd") }
      ],
      familyHistoryOfMelanomaListHrVisible: false,
      familyHistoryOfOtherCancerHrVisible: false,
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
    addFamilyHistoryOfOtherCancerField() {
      this.form.familyHistoryOfOtherCancer.push({
        presence: null,
        type: "",
        sideOfAffectedRelative: null,
        degreeOfRelative: null,
        ageAtDiagnosis: ""
      });
      if (this.form.familyHistoryOfOtherCancer.length > 1) {
        this.familyHistoryOfOtherCancertHrVisible = true;
      }
    },
    removeFamilyHistoryOfOtherCancerField() {
      if (this.form.familyHistoryOfOtherCancer.length > 0) {
        this.form.familyHistoryOfOtherCancer.pop();
      }
      if (this.form.familyHistoryOfOtherCancer.length == 1) {
        this.familyHistoryOfOtherCancerHrVisible = false;
      }
    },
    addFamilyHistoryOfMelanomaListField() {
      this.form.familyHistoryOfMelanomaList.push({
        presence: null,
        type: "",
        sideOfAffectedRelative: null,
        degreeOfRelative: null,
        ageAtDiagnosis: ""
      });
      if (this.form.familyHistoryOfMelanomaList.length > 1) {
        this.familyHistoryOfMelanomaListHrVisible = true;
      }
    },
    removeFamilyHistoryOfMelanomaListField() {
      if (this.form.familyHistoryOfMelanomaList.length > 0) {
        this.form.familyHistoryOfMelanomaList.pop();
      }
      if (this.form.familyHistoryOfMelanomaList.length == 1) {
        this.familyHistoryOfMelanomaListHrVisible = false;
      }
    },
    onSubmit(evt) {
      evt.preventDefault();
      document.body.scroll({
        top: 0,
        left: 0,
        behavior: "smooth"
      });
      this.$v.$touch();
      // eslint-disable-next-line
      console.log(JSON.stringify(this.form));
      if (this.$v.$invalid) {
        this.$store.dispatch(ADD_ERROR, "form_dirty");
      } else {
        var that = this;
        this.$store.dispatch(INSERT_C3, this.form).then(() => {
          this.$store.dispatch(ADD_MESSAGE, "form_success");
          that.canProceed = true;
        });
      }
    },
    onReset(evt) {
      evt.preventDefault();
      document.body.scroll({
        top: 0,
        left: 0,
        behavior: "smooth"
      });
      this.form.familyHistoryOfMelanomaList = [];
      this.familyHistoryOfMelanomaListHrVisible = false;
      this.form.germlineStatus = null;

      this.form.familyHistoryOfOtherCancer = [];
      this.familyHistoryOfOtherCancerHrVisible = false;

      this.$v.$reset();

      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    proceed() {
      if (this.canProceed) {
        this.$router.push({ name: "c3" });
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
      familyHistoryOfMelanomaList: {
        $each: {
          presence: { required },
          type: { required },
          sideOfAffectedRelative: { required },
          degreeOfRelative: { required },
          ageAtDiagnosis: { required, numeric, between: between(0, 120) }
        }
      },
      germlineStatus: { required },
      familyHistoryOfOtherCancer: {
        $each: {
          presence: { required },
          type: { required },
          sideOfAffectedRelative: { required },
          degreeOfRelative: { required },
          ageAtDiagnosis: { required, numeric, between: between(0, 120) }
        }
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