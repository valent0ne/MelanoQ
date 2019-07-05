<template>
  <div>
    <div id="main-container" class="home container my-4">
      <Message />
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>
            <span class="float-left mt-1">{{$t('section_c')+'.I'}}</span>
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
              <!-- solarLentigines -->
              <label for="solarLentigines" class="mt-4">
                <h6>{{'1. '+$t('solarLentigines_label')}}</h6>
              </label>
              <b-form-select
                v-model="$v.form.solarLentigines.howMany.$model"
                :state="$v.form.solarLentigines.howMany.$dirty ? !$v.form.solarLentigines.howMany.$error : null"
                :options="solarLentiginesHowManyOptions"
              ></b-form-select>
              <b-form-select
                class="mt-2"
                v-model="$v.form.solarLentigines.atSiteOfMelanoma.$model"
                :state="$v.form.solarLentigines.atSiteOfMelanoma.$dirty ? !$v.form.solarLentigines.atSiteOfMelanoma.$error : null"
                :options="solarLentiginesAtSiteOfMelanomaOptions"
              ></b-form-select>
              <b-form-text>{{$t('solarLentigines_desc')}}</b-form-text>

              <!-- neviCountGreatherThan2mm -->
              <label class="mt-4">
                <h6>{{'2. '+$t('neviCountGreatherThan2mm_label')}}</h6>
              </label>
              <b-form-text>{{$t('neviCountGreatherThan2mm_desc')}}</b-form-text>

              <b-form class="mt-2">
                <span
                  class="w-100"
                  v-for="(nct, index) in $v.form.neviCountGreatherThan2mm.$each.$iter"
                  :key="index"
                >
                  <p>{{$t(nct.site.$model.toLowerCase().replace(/\ /g, "_"))}}</p>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="nct.leftZoneNumber.$model"
                    :state="nct.leftZoneNumber.$dirty ? !nct.leftZoneNumber.$error : null"
                    required
                    type="text"
                    :placeholder="$t('neviCountGreatherThan2mmLeftZoneNumber_desc')"
                  ></b-form-input>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="nct.rightZoneNumber.$model"
                    :state="nct.rightZoneNumber.$dirty ? !nct.rightZoneNumber.$error : null"
                    required
                    type="text"
                    :placeholder="$t('neviCountGreatherThan2mmRightZoneNumber_desc')"
                  ></b-form-input>
                  <hr />
                </span>
              </b-form>

              <!-- neviCountLessThan2mm -->
              <label for="neviCountLessThan2mm" class="mt-4">
                <h6>{{'3. '+$t('neviCountLessThan2mm_label')}}</h6>
              </label>
              <b-form-select
                v-model="$v.form.neviCountLessThan2mm.$model"
                :state="$v.form.neviCountLessThan2mm.$dirty ? !$v.form.neviCountLessThan2mm.$error : null"
                :options="neviCountLessThan2mmOptions"
              ></b-form-select>
              <b-form-text>{{$t('neviCountLessThan2mm_desc')}}</b-form-text>

              <!-- clinicallyAtypicalNevi -->
              <label for="clinicallyAtypicalNevi" class="mt-4">
                <h6>{{'4. '+$t('clinicallyAtypicalNevi_label')}}</h6>
              </label>
              <b-form-input
                class="mr-2 mb-2"
                v-model="$v.form.clinicallyAtypicalNevi.howMany.$model"
                :state="$v.form.clinicallyAtypicalNevi.howMany.$dirty ? !$v.form.solarLentigines.howMany.$error : null"
                type="text"
                :placeholder="$t('clinicallyAtypicalNeviHowMany_desc')"
              ></b-form-input>

              <b-form-select
                v-model="$v.form.clinicallyAtypicalNevi.atSiteOfMelanoma.$model"
                :state="$v.form.clinicallyAtypicalNevi.atSiteOfMelanoma.$dirty ? !$v.form.solarLentigines.atSiteOfMelanoma.$error : null"
                :options="clinicallyAtypicalNeviAtSiteOfMelanomaOptions"
              ></b-form-select>
              <b-form-text>{{$t('clinicallyAtypicalNevi_desc')}}</b-form-text>

              <!-- congenitalNeviMedium -->
              <span class="form-inline mt-4">
                <label>
                  <h6>{{'5.a. '+$t('congenitalNeviMedium_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="congenitalNeviMediumToggle"
                    @click="congenitalNeviMediumToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!congenitalNeviMediumToggle"
                    @click="congenitalNeviMediumToggle = false; $v.form.congenitalNevi.numberOfMediumSizedNevi.$model = ''; $v.form.congenitalNevi.siteOfMediumSizedNevi.$model = ''"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>
              <span v-if="congenitalNeviMediumToggle">
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.congenitalNevi.numberOfMediumSizedNevi.$model"
                  :state="$v.form.congenitalNevi.numberOfMediumSizedNevi.$dirty ? !$v.form.congenitalNevi.numberOfMediumSizedNevi.$error : null"
                  type="text"
                  :placeholder="$t('numberOfMediumSizedNevi_desc')"
                ></b-form-input>
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.congenitalNevi.siteOfMediumSizedNevi.$model"
                  :state="$v.form.congenitalNevi.siteOfMediumSizedNevi.$dirty ? !$v.form.congenitalNevi.siteOfMediumSizedNevi.$error : null"
                  type="text"
                  :placeholder="$t('siteOfMediumSizedNevi_desc')"
                ></b-form-input>
                <b-form-text>{{$t('congenitalNeviMedium_desc')}}</b-form-text>
              </span>

              <!-- congenitalNeviLarge -->
              <span class="form-inline mt-4">
                <label>
                  <h6>{{'5.b. '+$t('congenitalNeviLarge_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="congenitalNeviLargeToggle"
                    @click="congenitalNeviLargeToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!congenitalNeviLargeToggle"
                    @click="congenitalNeviLargeToggle = false; $v.form.congenitalNevi.numberOfLargeSizedNevi.$model = ''; $v.form.congenitalNevi.siteOfLargeSizedNevi.$model = ''"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>
              <span v-if="congenitalNeviLargeToggle">
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.congenitalNevi.numberOfLargeSizedNevi.$model"
                  :state="$v.form.congenitalNevi.numberOfLargeSizedNevi.$dirty ? !$v.form.congenitalNevi.numberOfLargeSizedNevi.$error : null"
                  type="text"
                  :placeholder="$t('numberOfLargeSizedNevi_desc')"
                ></b-form-input>
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.congenitalNevi.siteOfLargeSizedNevi.$model"
                  :state="$v.form.congenitalNevi.siteOfLargeSizedNevi.$dirty ? !$v.form.congenitalNevi.siteOfLargeSizedNevi.$error : null"
                  type="text"
                  :placeholder="$t('siteOfLargeSizedNevi_desc')"
                ></b-form-input>
                <b-form-text>{{$t('congenitalNeviLarge_desc')}}</b-form-text>
              </span>

              <!-- congenitalNeviGiant -->
              <span class="form-inline mt-4">
                <label>
                  <h6>{{'5.c. '+$t('congenitalNeviGiant_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="congenitalNeviGiantToggle"
                    @click="congenitalNeviGiantToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!congenitalNeviGiantToggle"
                    @click="congenitalNeviGiantToggle = false; $v.form.congenitalNevi.numberOfGiantSizedNevi.$model = ''; $v.form.congenitalNevi.siteOfGiantSizedNevi.$model = ''"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>
              <span v-if="congenitalNeviGiantToggle">
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.congenitalNevi.numberOfGiantSizedNevi.$model"
                  :state="$v.form.congenitalNevi.numberOfGiantSizedNevi.$dirty ? !$v.form.congenitalNevi.numberOfGiantSizedNevi.$error : null"
                  type="text"
                  :placeholder="$t('numberOfGiantSizedNevi_desc')"
                ></b-form-input>
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.congenitalNevi.siteOfGiantSizedNevi.$model"
                  :state="$v.form.congenitalNevi.siteOfGiantSizedNevi.$dirty ? !$v.form.congenitalNevi.siteOfGiantSizedNevi.$error : null"
                  type="text"
                  :placeholder="$t('siteOfGiantSizedNevi_desc')"
                ></b-form-input>
                <b-form-text>{{$t('congenitalNeviGiant_desc')}}</b-form-text>
              </span>

              <!-- blueNevi -->
              <span class="form-inline mt-4">
                <label>
                  <h6>{{'6. '+$t('blueNevi_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="blueNeviToggle"
                    @click="blueNeviToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!blueNeviToggle"
                    @click="blueNeviToggle = false; $v.form.blueNevi.$model = '';"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>
              <span v-if="blueNeviToggle">
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.blueNevi.$model"
                  :state="$v.form.blueNevi.$dirty ? !$v.form.blueNevi.$error : null"
                  type="text"
                  :placeholder="$t('blueNeviNumber_desc')"
                ></b-form-input>
              </span>

              <!-- actinicKeratoses -->
              <span class="form-inline mt-4">
                <label>
                  <h6>{{'7. '+$t('actinicKeratoses_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="actinicKeratosesToggle"
                    @click="actinicKeratosesToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!actinicKeratosesToggle"
                    @click="actinicKeratosesToggle = false; $v.form.actinicKeratoses.site.$model = null; $v.form.actinicKeratoses.typeOfDistribution.$model = null;"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>
              <span v-if="actinicKeratosesToggle">
                <b-form-select
                  class="mt-2 mr-2"
                  v-model="$v.form.actinicKeratoses.site.$model"
                  :state="$v.form.actinicKeratoses.site.$dirty ? !$v.form.actinicKeratoses.site.$error : null"
                  :options="actinicKeratosesSiteOptions"
                ></b-form-select>
                <b-form-select
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.actinicKeratoses.typeOfDistribution.$model"
                  :state="$v.form.actinicKeratoses.typeOfDistribution.$dirty ? !$v.form.actinicKeratoses.typeOfDistribution.$error : null"
                  :options="actinicKeratosesTypeOfDistributionOptions"
                ></b-form-select>
              </span>

              <!-- currentNonMelanomaSkinCancersBCC -->
              <span class="form-inline mt-4">
                <label>
                  <h6>{{'8.a. '+$t('currentNonMelanomaSkinCancersBCC_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="currentNonMelanomaSkinCancersBCCToggle"
                    @click="currentNonMelanomaSkinCancersBCCToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!currentNonMelanomaSkinCancersBCCToggle"
                    @click="currentNonMelanomaSkinCancersBCCToggle = false; $v.form.currentNonMelanomaSkinCancers.$each[0].number.$model = ''; $v.form.currentNonMelanomaSkinCancers.$each[0].site.$model = '';"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>
              <span v-if="currentNonMelanomaSkinCancersBCCToggle">
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.currentNonMelanomaSkinCancers.$each[0].number.$model"
                  :state="$v.form.currentNonMelanomaSkinCancers.$each[0].number.$dirty ? !$v.form.currentNonMelanomaSkinCancers.$each[0].number.$error : null"
                  type="text"
                  :placeholder="$t('currentNonMelanomaSkinCancersNumber_desc')"
                ></b-form-input>
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.currentNonMelanomaSkinCancers.$each[0].site.$model"
                  :state="$v.form.currentNonMelanomaSkinCancers.$each[0].site.$dirty ? !$v.form.currentNonMelanomaSkinCancers.$each[0].site.$error : null"
                  type="text"
                  :placeholder="$t('currentNonMelanomaSkinCancersSite_desc')"
                ></b-form-input>
              </span>

              <!-- currentNonMelanomaSkinCancersInvasiveSCC -->
              <span class="form-inline mt-4">
                <label>
                  <h6>{{'8.b. '+$t('currentNonMelanomaSkinCancersInvasiveSCC_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="currentNonMelanomaSkinCancersInvasiveSCCToggle"
                    @click="currentNonMelanomaSkinCancersInvasiveSCCToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!currentNonMelanomaSkinCancersInvasiveSCCToggle"
                    @click="currentNonMelanomaSkinCancersInvasiveSCCToggle = false; $v.form.currentNonMelanomaSkinCancers.$each[1].number.$model = ''; $v.form.currentNonMelanomaSkinCancers.$each[1].site.$model = '';"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>
              <span v-if="currentNonMelanomaSkinCancersInvasiveSCCToggle">
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.currentNonMelanomaSkinCancers.$each[1].number.$model"
                  :state="$v.form.currentNonMelanomaSkinCancers.$each[1].number.$dirty ? !$v.form.currentNonMelanomaSkinCancers.$each[1].number.$error : null"
                  type="text"
                  :placeholder="$t('currentNonMelanomaSkinCancersNumber_desc')"
                ></b-form-input>
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.currentNonMelanomaSkinCancers.$each[1].site.$model"
                  :state="$v.form.currentNonMelanomaSkinCancers.$each[1].site.$dirty ? !$v.form.currentNonMelanomaSkinCancers.$each[1].site.$error : null"
                  type="text"
                  :placeholder="$t('currentNonMelanomaSkinCancersSite_desc')"
                ></b-form-input>
              </span>

              <!-- currentNonMelanomaSkinCancersInsituSCC -->
              <span class="form-inline mt-4">
                <label>
                  <h6>{{'8.c. '+$t('currentNonMelanomaSkinCancersInsituSCC_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="currentNonMelanomaSkinCancersInsituSCCToggle"
                    @click="currentNonMelanomaSkinCancersInsituSCCToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!currentNonMelanomaSkinCancersInsituSCCToggle"
                    @click="currentNonMelanomaSkinCancersInsituSCCToggle = false; $v.form.currentNonMelanomaSkinCancers.$each[2].number.$model = ''; $v.form.currentNonMelanomaSkinCancers.$each[2].site.$model = '';"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>
              <span v-if="currentNonMelanomaSkinCancersInsituSCCToggle">
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.currentNonMelanomaSkinCancers.$each[2].number.$model"
                  :state="$v.form.currentNonMelanomaSkinCancers.$each[2].number.$dirty ? !$v.form.currentNonMelanomaSkinCancers.$each[2].number.$error : null"
                  type="text"
                  :placeholder="$t('currentNonMelanomaSkinCancersNumber_desc')"
                ></b-form-input>
                <b-form-input
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.currentNonMelanomaSkinCancers.$each[2].site.$model"
                  :state="$v.form.currentNonMelanomaSkinCancers.$each[2].site.$dirty ? !$v.form.currentNonMelanomaSkinCancers.$each[2].site.$error : null"
                  type="text"
                  :placeholder="$t('currentNonMelanomaSkinCancersSite_desc')"
                ></b-form-input>
              </span>
              <b-form-text>{{$t('currentNonMelanomaSkinCancers_desc')}}</b-form-text>

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
import { ADD_MESSAGE, ADD_ERROR, INSERT_C1 } from "@/store/actions.type";
import { required, numeric, requiredIf } from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "c1",
  data() {
    return {
      form: {
        solarLentigines: {
          howMany: null,
          atSiteOfMelanoma: null
        },
        neviCountGreatherThan2mm: [
          {
            site: "Scalp",
            leftZoneNumber: "",
            rightZoneNumber: ""
          },
          {
            site: "Face (including ears)",
            leftZoneNumber: "",
            rightZoneNumber: ""
          },
          {
            site: "Neck",
            leftZoneNumber: "",
            rightZoneNumber: ""
          },
          {
            site: "Anterior thorax + abdomen",
            leftZoneNumber: "",
            rightZoneNumber: ""
          },
          {
            site: "Back",
            leftZoneNumber: "",
            rightZoneNumber: ""
          },
          {
            site: "Upper extremities including deltoid",
            leftZoneNumber: "",
            rightZoneNumber: ""
          },
          {
            site: "Lower extremities including gluteus",
            leftZoneNumber: "",
            rightZoneNumber: ""
          },
          {
            site: "Palms",
            leftZoneNumber: "",
            rightZoneNumber: ""
          },
          {
            site: "Soles",
            leftZoneNumber: "",
            rightZoneNumber: ""
          }
        ],
        neviCountLessThan2mm: null,
        clinicallyAtypicalNevi: {
          howMany: "",
          atSiteOfMelanoma: null
        },
        congenitalNevi: {
          numberOfMediumSizedNevi: "",
          siteOfMediumSizedNevi: "",
          numberOfLargeSizedNevi: "",
          siteOfLargeSizedNevi: "",
          numberOfGiantNevi: "",
          siteOfGiantSizedNevi: ""
        },
        blueNevi: "",
        actinicKeratoses: {
          site: null,
          typeOfDistribution: null
        },
        currentNonMelanomaSkinCancers: [
          {
            type: "BCC",
            number: "",
            site: ""
          },
          {
            type: "Invasive SCC",
            number: "",
            site: ""
          },
          {
            type: "In situ SCC",
            number: "",
            site: ""
          }
        ]
      },

      congenitalNeviMediumToggle: false,
      congenitalNeviLargeToggle: false,
      congenitalNeviGiantToggle: false,
      blueNeviToggle: false,
      actinicKeratosesToggle: false,
      currentNonMelanomaSkinCancersBCCToggle: false,
      currentNonMelanomaSkinCancersInvasiveSCCToggle: false,
      currentNonMelanomaSkinCancersInsituSCCToggle: false,

      solarLentiginesHowManyOptions: [
        { value: null, text: this.$t("solarLentiginesHowMany_desc") },
        { value: "None", text: this.$t("none") },
        { value: "Few", text: this.$t("few") },
        { value: "Many", text: this.$t("many") }
      ],
      solarLentiginesAtSiteOfMelanomaOptions: [
        { value: null, text: this.$t("solarLentiginesAtSiteOfMelanoma_desc") },
        { value: "Yes", text: this.$t("yes") },
        { value: "No", text: this.$t("no") }
      ],
      neviCountLessThan2mmOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "0", text: "0" },
        { value: "1-50", text: "1-50" },
        { value: "51-100", text: "51-100" },
        { value: "101-200", text: "101-200" },
        { value: ">200", text: ">200" }
      ],
      clinicallyAtypicalNeviAtSiteOfMelanomaOptions: [
        {
          value: null,
          text: this.$t("clinicallyAtypicalNeviAtSiteOfMelanoma_desc")
        },
        { value: "Yes", text: this.$t("yes") },
        { value: "No", text: this.$t("no") }
      ],
      actinicKeratosesSiteOptions: [
        {
          value: null,
          text: this.$t("actinicKeratosesSite_desc")
        },
        { value: "Scalp,", text: this.$t("scalp") },
        { value: "Face", text: this.$t("face") },
        { value: "Hands,", text: this.$t("hands") },
        { value: "Arms", text: this.$t("arms") },
        { value: "Trunk", text: this.$t("trunk") },
        { value: "Legs", text: this.$t("legs") }
      ],
      actinicKeratosesTypeOfDistributionOptions: [
        {
          value: null,
          text: this.$t("actinicKeratosesTypeOfDistribution_desc")
        },
        { value: "Isolated/Scattered", text: this.$t("isolated/scattered") },
        { value: "Clustered", text: this.$t("clustered") },
        { value: "Confluent", text: this.$t("confluent") }
      ],
      show: true,
      canProceed: false
    };
  },
  created: function() {
    if (this.user.type !== "physician") {
      this.$store.dispatch(ADD_ERROR, "unauthorized");
      this.$router.push({ name: "home" });
    }
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
        this.$store.dispatch(INSERT_C1, this.form).then(() => {
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

      this.form.solarLentigines = {
        howMany: null,
        atSiteOfMelanoma: null
      };

      this.form.neviCountGreatherThan2mm = [
        {
          site: "Scalp",
          leftZoneNumber: "",
          rightZoneNumber: ""
        },
        {
          site: "Face (including ears)",
          leftZoneNumber: "",
          rightZoneNumber: ""
        },
        {
          site: "Neck",
          leftZoneNumber: "",
          rightZoneNumber: ""
        },
        {
          site: "Anterior thorax + abdomen",
          leftZoneNumber: "",
          rightZoneNumber: ""
        },
        {
          site: "Back",
          leftZoneNumber: "",
          rightZoneNumber: ""
        },
        {
          site: "Upper extremities including deltoid",
          leftZoneNumber: "",
          rightZoneNumber: ""
        },
        {
          site: "Lower extremities including gluteus",
          leftZoneNumber: "",
          rightZoneNumber: ""
        },
        {
          site: "Palms",
          leftZoneNumber: "",
          rightZoneNumber: ""
        },
        {
          site: "Soles",
          leftZoneNumber: "",
          rightZoneNumber: ""
        }
      ];

      this.form.neviCountLessThan2mm = null;

      this.form.clinicallyAtypicalNevi = {
        howMany: "",
        atSiteOfMelanoma: null
      };

      this.form.congenitalNevi = {
        numberOfMediumSizedNevi: "",
        siteOfMediumSizedNevi: "",
        numberOfLargeSizedNevi: "",
        siteOfLargeSizedNevi: "",
        numberOfGiantSizedNevi: "",
        siteOfGiantSizedNevi: ""
      };

      this.form.blueNevi = "";

      this.form.actinicKeratoses = {
        site: null,
        typeOfDistribution: null
      };

      this.actinicKeratosesToggle = false;

      this.congenitalNeviMediumToggle = false;
      this.congenitalNeviLargeToggle = false;
      this.congenitalNeviGiantToggle = false;
      this.blueNeviToggle = false;
      this.actinicKeratosesToggle = false;

      this.currentNonMelanomaSkinCancersBCCToggle = false;
      this.currentNonMelanomaSkinCancersInvasiveSCCToggle = false;
      this.currentNonMelanomaSkinCancersInsituSCCToggle = false;

      this.$v.$reset();
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
      solarLentigines: {
        howMany: { required },
        atSiteOfMelanoma: { required }
      },
      neviCountGreatherThan2mm: {
        $each: {
          site: { required },
          leftZoneNumber: { required, numeric },
          rightZoneNumber: { required, numeric }
        }
      },
      neviCountLessThan2mm: {},
      clinicallyAtypicalNevi: {
        howMany: { numeric },
        atSiteOfMelanoma: {
          required: requiredIf(function() {
            return this.form.clinicallyAtypicalNevi.howMany != "";
          })
        }
      },
      congenitalNevi: {
        numberOfMediumSizedNevi: { numeric },
        siteOfMediumSizedNevi: {
          required: requiredIf(function() {
            return this.form.congenitalNevi.numberOfMediumSizedNevi != "";
          })
        },
        numberOfLargeSizedNevi: { numeric },
        siteOfLargeSizedNevi: {
          required: requiredIf(function() {
            return this.form.congenitalNevi.numberOfLargeSizedNevi != "";
          })
        },
        numberOfGiantSizedNevi: { numeric },
        siteOfGiantSizedNevi: {
          required: requiredIf(function() {
            return this.form.congenitalNevi.numberOfGiantSizedNevi != "";
          })
        }
      },
      blueNevi: {
        numeric,
        required: requiredIf(function() {
          return this.blueNeviToggle;
        })
      },

      actinicKeratoses: {
        site: {
          required: requiredIf(function() {
            return this.actinicKeratosesToggle;
          })
        },
        typeOfDistribution: {
          required: requiredIf(function() {
            return this.actinicKeratosesToggle;
          })
        }
      },

      currentNonMelanomaSkinCancers: {
        $each: {
          type: { required },
          number: {
            numeric,
            required: requiredIf(function(object) {
              if (
                object.type == "BCC" &&
                this.currentNonMelanomaSkinCancersBCCToggle
              ) {
                return true;
              }
              if (
                object.type == "Invasive SCC" &&
                this.currentNonMelanomaSkinCancersInvasiveSCCToggle
              ) {
                return true;
              }
              if (
                object.type == "In situ SCC" &&
                this.currentNonMelanomaSkinCancersInsituSCCToggle
              ) {
                return true;
              }
              return false;
            })
          },
          site: {
            required: requiredIf(function(object) {
              if (
                object.type == "BCC" &&
                this.currentNonMelanomaSkinCancersBCCToggle
              ) {
                return true;
              }
              if (
                object.type == "Invasive SCC" &&
                this.currentNonMelanomaSkinCancersInvasiveSCCToggle
              ) {
                return true;
              }
              if (
                object.type == "In situ SCC" &&
                this.currentNonMelanomaSkinCancersInsituSCCToggle
              ) {
                return true;
              }
              return false;
            })
          }
        }
      }
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