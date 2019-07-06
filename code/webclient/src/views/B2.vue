<template>
  <div>
    <div class="home container my-4">
      <Message />
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>
            <span class="float-left mt-1">{{$t('section_b')+'.II'}}</span>
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
              <font-awesome-icon icon="arrow-right" />&nbsp;
            </b-button>
            <b-button
              type="button"
              to="/choice"
              variant="outline-info"
              class="fa-button-outline mr-2 float-right"
            >
              <font-awesome-icon icon="arrow-right" flip="horizontal" />
              &nbsp;
              {{$t('back_to_section_choice')}}
            </b-button>
          </b-card-title>
          <div>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <!-- occupationalSunExposure -->
              <span class="form-inline mt-4">
                <label>
                  <h6>{{'6. '+$t('occupationalSunExposure_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="occupationalSunExposureToggle"
                    @click="occupationalSunExposureToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!occupationalSunExposureToggle"
                    @click="occupationalSunExposureToggle = false; $v.form.occupationalSunExposure.occupation.$model=''; $v.form.occupationalSunExposure.hoursPerDay.$model=''; $v.form.occupationalSunExposure.daysPerMonth.$model=''; $v.form.occupationalSunExposure.monthsPerYear.$model=''; $v.form.occupationalSunExposure.years.$model=''"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>

              <span v-if="occupationalSunExposureToggle">
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.occupationalSunExposure.occupation.$model"
                  :state="$v.form.occupationalSunExposure.occupation.$dirty ? !$v.form.occupationalSunExposure.occupation.$error : null"
                  type="text"
                  :placeholder="$t('occupation_desc')"
                ></b-form-input>

                <b-form-input
                  class="mr-2 mb-2"
                  v-model="$v.form.occupationalSunExposure.hoursPerDay.$model"
                  :state="$v.form.occupationalSunExposure.hoursPerDay.$dirty ? !$v.form.occupationalSunExposure.hoursPerDay.$error : null"
                  type="text"
                  :placeholder="$t('hoursPerDay_desc')"
                ></b-form-input>

                <b-form-input
                  class="mr-2 mb-2"
                  v-model="$v.form.occupationalSunExposure.daysPerMonth.$model"
                  :state="$v.form.occupationalSunExposure.daysPerMonth.$dirty ? !$v.form.occupationalSunExposure.daysPerMonth.$error : null"
                  type="text"
                  :placeholder="$t('daysPerMonth_desc')"
                ></b-form-input>

                <b-form-input
                  class="mr-2 mb-2"
                  v-model="$v.form.occupationalSunExposure.monthsPerYear.$model"
                  :state="$v.form.occupationalSunExposure.monthsPerYear.$dirty ? !$v.form.occupationalSunExposure.monthsPerYear.$error : null"
                  type="text"
                  :placeholder="$t('monthsPerYear_desc')"
                ></b-form-input>

                <b-form-input
                  class="mr-2 mb-2"
                  v-model="$v.form.occupationalSunExposure.years.$model"
                  :state="$v.form.occupationalSunExposure.years.$dirty ? !$v.form.occupationalSunExposure.years.$error : null"
                  type="text"
                  :placeholder="$t('years_desc')"
                ></b-form-input>
              </span>
              <b-form-text>{{$t('occupationalSunExposure_desc')}}</b-form-text>

              <!-- recreationalSunExposure -->
              <span class="form-inline mt-4">
                <label>
                  <h6>{{'7. '+$t('recreationalSunExposure_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="recreationalSunExposureToggle"
                    @click="recreationalSunExposureToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!recreationalSunExposureToggle"
                    @click="recreationalSunExposureToggle = false; $v.form.recreationalSunExposure.activity.$model = ''; $v.form.recreationalSunExposure.hoursPerDay.$model=''; $v.form.recreationalSunExposure.daysPerMonth.$model=''; $v.form.recreationalSunExposure.monthsPerYear.$model=''"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>

              <span v-if="recreationalSunExposureToggle">
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.recreationalSunExposure.activity.$model"
                  :state="$v.form.recreationalSunExposure.activity.$dirty ? !$v.form.recreationalSunExposure.activity.$error : null"
                  type="text"
                  :placeholder="$t('occupation_desc')"
                ></b-form-input>

                <b-form-input
                  class="mr-2 mb-2"
                  v-model="$v.form.recreationalSunExposure.hoursPerDay.$model"
                  :state="$v.form.recreationalSunExposure.hoursPerDay.$dirty ? !$v.form.recreationalSunExposure.hoursPerDay.$error : null"
                  type="text"
                  :placeholder="$t('hoursPerDay_desc')"
                ></b-form-input>

                <b-form-input
                  class="mr-2 mb-2"
                  v-model="$v.form.recreationalSunExposure.daysPerMonth.$model"
                  :state="$v.form.recreationalSunExposure.daysPerMonth.$dirty ? !$v.form.recreationalSunExposure.daysPerMonth.$error : null"
                  type="text"
                  :placeholder="$t('daysPerMonth_desc')"
                ></b-form-input>

                <b-form-input
                  class="mr-2 mb-2"
                  v-model="$v.form.recreationalSunExposure.monthsPerYear.$model"
                  :state="$v.form.recreationalSunExposure.monthsPerYear.$dirty ? !$v.form.recreationalSunExposure.monthsPerYear.$error : null"
                  type="text"
                  :placeholder="$t('monthsPerYear_desc')"
                ></b-form-input>

                <b-form-input
                  class="mr-2 mb-2"
                  v-model="$v.form.recreationalSunExposure.years.$model"
                  :state="$v.form.recreationalSunExposure.years.$dirty ? !$v.form.recreationalSunExposure.years.$error : null"
                  type="text"
                  :placeholder="$t('years_desc')"
                ></b-form-input>
              </span>
              <b-form-text>{{$t('recreationalSunExposure_desc')}}</b-form-text>

              <!-- intermittentSunExposure -->
              <label class="mt-4">
                <h6>{{'8. '+$t('intermittentSunExposure_label')}}</h6>
              </label>
              <b-form-text>{{$t('intermittentSunExposure_desc')}}</b-form-text>

              <b-form class="mt-2">
                <span
                  class="w-100"
                  v-for="(ise, index) in $v.form.intermittentSunExposure.$each.$iter"
                  :key="index"
                >
                  <p>{{$t(ise.agePeriod.$model.toLowerCase().replace(/\ /g, "_"))}}</p>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="ise.weeksOfVacation.$model"
                    :state="ise.weeksOfVacation.$dirty ? !ise.weeksOfVacation.$error : null"
                    type="text"
                    required
                    :placeholder="$t('intermittentSunExposureWeeksOfVacation_desc')"
                  ></b-form-input>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="ise.hoursSpentBetween11AMAnd4PM.$model"
                    :state="ise.hoursSpentBetween11AMAnd4PM.$dirty ? !ise.hoursSpentBetween11AMAnd4PM.$error : null"
                    type="text"
                    required
                    :placeholder="$t('intermittentSunExposureHoursSpentBetween11AMAnd4PM_desc')"
                  ></b-form-input>
                  <hr />
                </span>
              </b-form>

              <!-- mostRecentIntermittentSunExposure -->
              <label for="mostRecentIntermittentSunExposure" class="mt-4">
                <h6>{{'9. '+$t('mostRecentIntermittentSunExposure_label')}}</h6>
              </label>
              <b-form-input
                id="mostRecentIntermittentSunExposure"
                v-model="$v.form.mostRecentIntermittentSunExposure.$model"
                :state="$v.form.mostRecentIntermittentSunExposure.$dirty ? !$v.form.mostRecentIntermittentSunExposure.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('mostRecentIntermittentSunExposure_desc')}}</b-form-text>

              <!-- severeSunBurns -->
              <label class="mt-4">
                <h6>{{'10. '+$t('severeSunBurns_label')}}</h6>
              </label>

              <span
                class="form-inline mt-2"
                v-for="(ssb, index) in $v.form.severeSunBurns.$each.$iter"
                :key="index"
              >
                <label>{{$t(ssb.period.$model.toLowerCase().replace(/\ /g, "_"))}}</label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="ssb.presence.$model == 'yes'"
                    @click="ssb.presence.$model = 'yes'"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="ssb.presence.$model == 'no'"
                    @click="ssb.presence.$model = 'no'; ssb.number.$model = ''"
                  >{{$t('no')}}</b-button>
                  <b-button
                    variant="outline-secondary"
                    :pressed="ssb.presence.$model == 'not known'"
                    @click="ssb.presence.$model = 'not known'; ssb.number.$model = ''"
                  >{{$t('not_known')}}</b-button>
                </b-button-group>
                <span v-if="ssb.presence.$model == 'yes'" class="w-100">
                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="ssb.number.$model"
                    :state="ssb.number.$dirty ? !ssb.number.$error : null"
                    type="text"
                    :placeholder="$t('severeSunBurnsNumber_desc')"
                  ></b-form-input>
                </span>
              </span>
              <b-form-text>{{$t('severeSunBurns_desc')}}</b-form-text>

              <!-- sunscreenUse -->
              <label class="mt-4">
                <h6>{{'11. '+$t('sunscreenUse_label')+' '+$t('sunscreenUse_desc')}}</h6>
              </label>

              <b-form class="mt-2">
                <span
                  class="w-100"
                  v-for="(ssu, index) in $v.form.sunscreenUses.$each.$iter"
                  :key="index"
                >
                  <p>{{$t(ssu.agePeriod.$model.toLowerCase().replace(/\ /g, "_"))}}</p>

                  <b-form-select
                    class="mr-2 mb-2"
                    v-model="ssu.howOften.$model"
                    :state="ssu.howOften.$dirty ? !ssu.howOften.$error : null"
                    required
                    :options="sunscreenUseHowOftenOptions"
                  ></b-form-select>

                  <b-form-select
                    class="mr-2 mb-2"
                    v-model="ssu.type.$model"
                    :state="ssu.type.$dirty ? !ssu.type.$error : null"
                    required
                    :options="sunscreenUseTypeOptions"
                  ></b-form-select>
                  <hr />
                </span>
              </b-form>

              <!-- sunProtectionOtherThanSunscreenUseHat -->
              <label for="sunProtectionOtherThanSunscreenUseHat" class="mt-4">
                <h6>{{'12. '+$t('sunProtectionOtherThanSunscreenUseHat_label')}}</h6>
              </label>
              <b-form-select
                v-model="$v.form.sunProtectionOtherThanSunscreenUseHat.$model"
                :state="$v.form.sunProtectionOtherThanSunscreenUseHat.$dirty ? !$v.form.sunProtectionOtherThanSunscreenUseHat.$error : null"
                type="text"
                required
                :options="sunProtectionOtherThanSunscreenUseHatOptions"
              ></b-form-select>

              <!-- sunProtectionOtherThanSunscreenUseClothing -->

              <b-form-select
                class="mt-2"
                v-model="$v.form.sunProtectionOtherThanSunscreenUseClothing.$model"
                :state="$v.form.sunProtectionOtherThanSunscreenUseClothing.$dirty ? !$v.form.sunProtectionOtherThanSunscreenUseClothing.$error : null"
                type="text"
                required
                :options="sunProtectionOtherThanSunscreenUseClothingOptions"
              ></b-form-select>

              <!-- seekTheShadeDuringUVRHours -->

              <b-form-select
                class="mt-2"
                v-model="$v.form.seekTheShadeDuringUVRHours.$model"
                :state="$v.form.seekTheShadeDuringUVRHours.$dirty ? !$v.form.seekTheShadeDuringUVRHours.$error : null"
                type="text"
                required
                :options="seekTheShadeDuringUVRHoursOptions"
              ></b-form-select>

              <!-- sunlampsSunbeds -->
              <span class="form-inline mt-5">
                <label>
                  <h6>{{'13. '+$t('sunlampsSunbeds_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="sunlampsSunbedsToggle"
                    @click="sunlampsSunbedsToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!sunlampsSunbedsToggle"
                    @click="sunlampsSunbedsToggle = false; $v.form.sunlampsSunbeds.lifetimeNumberOfSession.$model = ''; $v.form.sunlampsSunbeds.ageAtFirstExposure.$model = ''; $v.form.sunlampsSunbeds.ageAtLastExposure.$model=''"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>
              <span v-if="sunlampsSunbedsToggle">
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.sunlampsSunbeds.lifetimeNumberOfSession.$model"
                  :state="$v.form.sunlampsSunbeds.lifetimeNumberOfSession.$dirty ? !$v.form.sunlampsSunbeds.lifetimeNumberOfSession.$error : null"
                  type="text"
                  :placeholder="$t('lifetimeNumberOfSession_desc')"
                ></b-form-input>

                <b-form-input
                  class="mr-2 mb-2"
                  v-model="$v.form.sunlampsSunbeds.ageAtFirstExposure.$model"
                  :state="$v.form.sunlampsSunbeds.ageAtFirstExposure.$dirty ? !$v.form.sunlampsSunbeds.ageAtFirstExposure.$error : null"
                  type="text"
                  :placeholder="$t('ageAtFirstExposure_desc')"
                ></b-form-input>

                <b-form-input
                  class="mr-2 mb-2"
                  v-model="$v.form.sunlampsSunbeds.ageAtLastExposure.$model"
                  :state="$v.form.sunlampsSunbeds.ageAtLastExposure.$dirty ? !$v.form.sunlampsSunbeds.ageAtLastExposure.$error : null"
                  type="text"
                  :placeholder="$t('ageAtLastExposure_desc')"
                ></b-form-input>
              </span>
              <b-form-text>{{$t('sunlampsSunbeds_desc')}}</b-form-text>

              <!-- phototherapyUVBPUVA -->
              <label for="phototherapyUVBPUVA" class="mt-4">
                <h6>{{'14. '+$t('phototherapyUVBPUVA_label')}}</h6>
              </label>
              <b-form-select
                v-model="$v.form.phototherapyUVBPUVA.$model"
                :state="$v.form.phototherapyUVBPUVA.$dirty ? !$v.form.phototherapyUVBPUVA.$error : null"
                type="text"
                required
                :options="phototherapyUVBPUVAOptions"
              ></b-form-select>
              <b-form-text>{{$t('phototherapyUVBPUVA_desc')}}</b-form-text>

              <!-- buttons -->
              <hr class="mt-5" />
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
    <br />
    <br />
  </div>
</template>
<script>
import { mapState } from "vuex";
import { ADD_MESSAGE, ADD_ERROR, INSERT_B2 } from "@/store/actions.type";
import {
  required,
  between,
  requiredIf,
  numeric
} from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "b2",
  data() {
    return {
      form: {
        occupationalSunExposure: {
          occupation: "",
          hoursPerDay: "",
          daysPerMonth: "",
          monthsPerYear: "",
          years: ""
        },
        recreationalSunExposure: {
          activity: "",
          hoursPerDay: "",
          daysPerMonth: "",
          monthsPerYear: "",
          years: ""
        },
        intermittentSunExposure: [
          {
            agePeriod: "Childhood (up to age 10 yrs)",
            weeksOfVacation: "",
            hoursSpentBetween11AMAnd4PM: ""
          },
          {
            agePeriod: "Adolescence (11-18 yrs)",
            weeksOfVacation: "",
            hoursSpentBetween11AMAnd4PM: ""
          },
          {
            agePeriod: "Adulthood (≥18 yrs)",
            weeksOfVacation: "",
            hoursSpentBetween11AMAnd4PM: ""
          },
          {
            agePeriod:
              "10 years before melanoma diagnosis (for cases) or last 10 years (for controls)",
            weeksOfVacation: "",
            hoursSpentBetween11AMAnd4PM: ""
          }
        ],
        mostRecentIntermittentSunExposure: "",
        severeSunBurns: [
          {
            period: "At age <18 yrs",
            presence: "no",
            number: ""
          },
          {
            period: "At age ≥18 yrs",
            presence: "no",
            number: ""
          },
          {
            period: "At site of melanoma?",
            presence: "no",
            number: ""
          },
          {
            period: "In the last 5 yrs",
            presence: "no",
            number: ""
          }
        ],
        sunscreenUses: [
          {
            agePeriod: "Childhood (up to age 10 yrs)",
            howOften: null,
            type: null
          },
          {
            agePeriod: "Adolescence (11-18 yrs)",
            howOften: null,
            type: null
          },
          {
            agePeriod: "Adulthood (≥18 yrs)",
            howOften: null,
            type: null
          },
          {
            agePeriod:
              "10 years before melanoma diagnosis (for cases) or last 10 years (for controls)",
            howOften: null,
            type: null
          }
        ],
        sunProtectionOtherThanSunscreenUseHat: null,
        sunProtectionOtherThanSunscreenUseClothing: null,
        seekTheShadeDuringUVRHours: null,

        sunlampsSunbeds: [
          {
            lifetimeNumberOfSession: "",
            ageAtFirstExposure: "",
            ageAtLastExposure: ""
          }
        ],
        phototherapyUVBPUVA: null
      },

      occupationalSunExposureToggle: false,
      recreationalSunExposureToggle: false,
      sunlampsSunbedsToggle: false,

      sunscreenUseHowOftenOptions: [
        { value: null, text: this.$t("sunscreenUseHowOften") },
        { value: "Never", text: this.$t("never") },
        {
          value: "<50% of time exposure",
          text: this.$t("<50%_of_time_exposure")
        },
        {
          value: ">50% of time exposure",
          text: this.$t(">50%_of_time_exposure")
        },
        { value: "Always", text: this.$t("always") },
        { value: "Not known", text: this.$t("not_known") }
      ],

      sunscreenUseTypeOptions: [
        { value: null, text: this.$t("sunscreenUseType") },
        { value: "SPF<20", text: "SPF<20" },
        { value: "SPF>20", text: "SPF>20" },
        { value: "Not known", text: this.$t("not_known") }
      ],

      sunProtectionOtherThanSunscreenUseHatOptions: [
        {
          value: null,
          text: this.$t("sunProtectionOtherThanSunscreenUseHat_desc")
        },
        { value: "Never", text: this.$t("never") },
        {
          value: "<50% of time exposure",
          text: this.$t("<50%_of_time_exposure")
        },
        {
          value: ">50% of time exposure",
          text: this.$t(">50%_of_time_exposure")
        },
        { value: "Always", text: this.$t("always") },
        { value: "Not known", text: this.$t("not_known") }
      ],

      sunProtectionOtherThanSunscreenUseClothingOptions: [
        {
          value: null,
          text: this.$t("sunProtectionOtherThanSunscreenUseClothing_desc")
        },
        { value: "Never", text: this.$t("never") },
        {
          value: "<50% of time exposure",
          text: this.$t("<50%_of_time_exposure")
        },
        {
          value: ">50% of time exposure",
          text: this.$t(">50%_of_time_exposure")
        },
        { value: "Always", text: this.$t("always") },
        { value: "Not known", text: this.$t("not_known") }
      ],

      seekTheShadeDuringUVRHoursOptions: [
        { value: null, text: this.$t("seekTheShadeDuringUVRHours_desc") },
        { value: "Rarely", text: this.$t("rarely") },
        { value: "Sometimes", text: this.$t("sometimes") },
        { value: "always", text: this.$t("always") },
        { value: "Not known", text: this.$t("not_known") }
      ],

      phototherapyUVBPUVAOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Ever", text: this.$t("ever") },
        { value: "Never", text: this.$t("never") }
      ],

      show: true,
      canProceed: false
    };
  },
  created: function() {
    /*
    if (this.user.type !== "physician") {
      this.$store.dispatch(ADD_ERROR, "unauthorized");
      this.$router.push({ name: "home" });
    }
    */
    if (!this.isAuthenticated) {
      this.$store.dispatch(ADD_ERROR, "not_authenticated");
      this.$router.push({ name: "home" });
    }
  },
  methods: {
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
        this.$store.dispatch(INSERT_B2, this.form).then(() => {
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
      this.occupationalSunExposureToggle = false;
      this.recreationalSunExposureToggle = false;
      this.sunlampsSunbedsToggle = false;

      this.form.occupationalSunExposure = {
        occupation: "",
        hoursPerDay: "",
        daysPerMonth: "",
        monthsPerYear: "",
        years: ""
      };

      this.form.recreationalSunExposure = {
        activity: "",
        hoursPerDay: "",
        daysPerMonth: "",
        monthsPerYear: "",
        years: ""
      };

      this.form.intermittentSunExposure = [
        {
          agePeriod: "Childhood (up to age 10 yrs)",
          weeksOfVacation: "",
          hoursSpentBetween11AMAnd4PM: ""
        },
        {
          agePeriod: "Adolescence (11-18 yrs)",
          weeksOfVacation: "",
          hoursSpentBetween11AMAnd4PM: ""
        },
        {
          agePeriod: "Adulthood (≥18 yrs)",
          weeksOfVacation: "",
          hoursSpentBetween11AMAnd4PM: ""
        },
        {
          agePeriod:
            "10 years before melanoma diagnosis (for cases) or last 10 years (for controls)",
          weeksOfVacation: "",
          hoursSpentBetween11AMAnd4PM: ""
        }
      ];
      this.form.mostRecentIntermittentSunExposure = "";

      this.form.severeSunBurns = [
        {
          period: "At age <18 yrs",
          presence: "no",
          number: ""
        },
        {
          period: "At age ≥18 yrs",
          presence: "no",
          number: ""
        },
        {
          period: "At site of melanoma?",
          presence: "no",
          number: ""
        },
        {
          period: "In the last 5 yrs",
          presence: "no",
          number: ""
        }
      ];

      this.form.sunscreenUses = [
        {
          agePeriod: "Childhood (up to age 10 yrs)",
          howOften: null,
          type: null
        },
        {
          agePeriod: "Adolescence (11-18 yrs)",
          howOften: null,
          type: null
        },
        {
          agePeriod: "Adulthood (≥18 yrs)",
          howOften: null,
          type: null
        },
        {
          agePeriod:
            "10 years before melanoma diagnosis (for cases) or last 10 years (for controls)",
          howOften: null,
          type: null
        }
      ];
      this.form.sunProtectionOtherThanSunscreenUseHat = null;
      this.form.sunProtectionOtherThanSunscreenUseClothing = null;
      this.form.seekTheShadeDuringUVRHours = null;

      this.form.sunlampsSunbeds = {
        lifetimeNumberOfSession: "",
        ageAtFirstExposure: "",
        ageAtLastExposure: ""
      };

      this.form.phototherapyUVBPUVA = null;
      this.$v.$reset();
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    proceed() {
      if (this.canProceed) {
        this.$router.push({ name: "b3" });
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
      occupationalSunExposure: {
        occupation: {
          required: requiredIf(function() {
            return this.occupationalSunExposureToggle;
          })
        },
        hoursPerDay: {
          required: requiredIf(function() {
            return this.occupationalSunExposureToggle;
          }),
          between: between(0, 24)
        },
        daysPerMonth: {
          required: requiredIf(function() {
            return this.occupationalSunExposureToggle;
          }),
          between: between(0, 31)
        },
        monthsPerYear: {
          required: requiredIf(function() {
            return this.occupationalSunExposureToggle;
          }),
          between: between(0, 12)
        },
        years: {
          required: requiredIf(function() {
            return this.occupationalSunExposureToggle;
          }),
          between: between(0, 100)
        }
      },
      recreationalSunExposure: {
        activity: {
          required: requiredIf(function() {
            return this.recreationalSunExposureToggle;
          })
        },
        hoursPerDay: {
          required: requiredIf(function() {
            return this.recreationalSunExposureToggle;
          }),
          between: between(0, 24)
        },
        daysPerMonth: {
          required: requiredIf(function() {
            return this.recreationalSunExposureToggle;
          }),
          between: between(0, 31)
        },
        monthsPerYear: {
          required: requiredIf(function() {
            return this.recreationalSunExposureToggle;
          }),
          between: between(0, 12)
        },
        years: {
          required: requiredIf(function() {
            return this.recreationalSunExposureToggle;
          }),
          between: between(0, 100)
        }
      },
      intermittentSunExposure: {
        $each: {
          agePeriod: { required },
          weeksOfVacation: { numeric, required },
          hoursSpentBetween11AMAnd4PM: { between: between(0, 4) }
        }
      },
      mostRecentIntermittentSunExposure: { required },
      severeSunBurns: {
        $each: {
          period: { required },
          presence: { required },
          number: { numeric }
        }
      },
      sunscreenUses: {
        $each: {
          agePeriod: { required },
          howOften: { required },
          type: { required }
        }
      },
      sunProtectionOtherThanSunscreenUseHat: {},
      sunProtectionOtherThanSunscreenUseClothing: {},
      seekTheShadeDuringUVRHours: {},

      sunlampsSunbeds: {
        lifetimeNumberOfSession: {
          numeric,
          required: requiredIf(function() {
            return this.sunlampsSunbedsToggle;
          })
        },
        ageAtFirstExposure: {
          numeric,
          required: requiredIf(function() {
            return this.sunlampsSunbedsToggle;
          })
        },
        ageAtLastExposure: {
          numeric,
          required: requiredIf(function() {
            return this.sunlampsSunbedsToggle;
          })
        }
      },
      phototherapyUVBPUVA: {}
    }
  },
  computed: {
    ...mapState({
      user: state => state.auth.user,
      isAuthenticated: state => state.auth.isAuthenticated
    })
  }
};
</script>