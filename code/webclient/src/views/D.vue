<template>
  <div>
    <div class="home container my-4">
      <Message />
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>
            <span class="float-left mt-1">{{$t('section_d')}}</span>
            <!-- buttons -->
            <b-button
              type="button"
              @click="addNewD()"
              :disabled="(!canProceed)"
              variant="outline-info"
              class="fa-button-outline float-right"
            >
              {{$t('add_melanoma')}}
              &nbsp;
              <font-awesome-icon icon="plus" />&nbsp;
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
              <!-- identifier -->
              <label for="identifier" class="mt-4">
                <h6>{{'0. '+$t('identifier_label')}}</h6>
              </label>
              <b-form-input
                disabled
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
                <b-form-text>{{$t('numberOfMPM_desc')}}</b-form-text>
              </span>

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
                    @click="form.primaryTumorKnown = false; primaryTumorKnownFalseTrigger();"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>
              <b-form-text>{{$t('primaryTumorKnown_desc')}}</b-form-text>

              <span v-if="form.primaryTumorKnown">
                <!-- site -->
                <label for="site" class="mt-4">
                  <h6>{{'8. '+$t('site_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.site.$model"
                  :state="$v.form.site.$dirty ? !$v.form.site.$error : null"
                  :options="siteOptions"
                ></b-form-select>
                <b-form-text>{{$t('site_desc')}}</b-form-text>

                <!-- breslowThinkness -->
                <label for="breslowThinkness" class="mt-4">
                  <h6>{{'9. '+$t('breslowThinkness_label')}}</h6>
                </label>
                <b-form-input
                  v-model="$v.form.breslowThinkness.$model"
                  :state="$v.form.breslowThinkness.$dirty ? !$v.form.breslowThinkness.$error : null"
                  type="text"
                  required
                ></b-form-input>
                <b-form-text>{{$t('breslowThinkness_desc')}}</b-form-text>

                <!-- otherMainHistopatologicFeatures -->
                <label class="mt-4">
                  <h6>{{'10. '+$t('otherMainHistopatologicFeatures_label')}}</h6>
                </label>
                <b-form-text>{{$t('otherMainHistopatologicFeatures_desc')}}</b-form-text>

                <!-- subtype -->
                <label for="subtype" class="mt-4">
                  <h6>{{'10.a. '+$t('subtype_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.otherMainHistopatologicFeatures.subtype.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.subtype.$dirty ? !$v.form.otherMainHistopatologicFeatures.subtype.$error : null"
                  :options="subtypeOptions"
                ></b-form-select>
                <b-form-text>{{$t('subtype_desc')}}</b-form-text>

                <!-- mitoticRate -->
                <label for="mitoticRate" class="mt-4">
                  <h6>{{'10.b. '+$t('mitoticRate_label')}}</h6>
                </label>
                <b-form-input
                  v-model="$v.form.otherMainHistopatologicFeatures.mitoticRate.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.mitoticRate.$dirty ? !$v.form.otherMainHistopatologicFeatures.mitoticRate.$error : null"
                  type="text"
                  required
                ></b-form-input>
                <b-form-text>{{$t('mitoticRate_desc')}}</b-form-text>

                <!-- ulceration -->
                <label for="ulceration" class="mt-4">
                  <h6>{{'10.c. '+$t('ulceration_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.otherMainHistopatologicFeatures.ulceration.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.ulceration.$dirty ? !$v.form.otherMainHistopatologicFeatures.ulceration.$error : null"
                  :options="ulcerationOptions"
                ></b-form-select>
                <b-form-text>{{$t('ulceration_desc')}}</b-form-text>

                <!-- tumorGrowthPhase -->
                <label for="tumorGrowthPhase" class="mt-4">
                  <h6>{{'10.d. '+$t('tumorGrowthPhase_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.otherMainHistopatologicFeatures.tumorGrowthPhase.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.tumorGrowthPhase.$dirty ? !$v.form.otherMainHistopatologicFeatures.tumorGrowthPhase.$error : null"
                  :options="tumorGrowthPhaseOptions"
                ></b-form-select>
                <b-form-text>{{$t('tumorGrowthPhase_desc')}}</b-form-text>

                <!-- regression -->
                <label for="regression" class="mt-4">
                  <h6>{{'10.e. '+$t('regression_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.otherMainHistopatologicFeatures.regression.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.regression.$dirty ? !$v.form.otherMainHistopatologicFeatures.regression.$error : null"
                  :options="regressionOptions"
                ></b-form-select>
                <b-form-text>{{$t('regression_desc')}}</b-form-text>

                <!-- tumorInfiltratingLymphocytes -->
                <label for="tumorInfiltratingLymphocytes" class="mt-4">
                  <h6>{{'10.f. '+$t('tumorInfiltratingLymphocytes_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.otherMainHistopatologicFeatures.tumorInfiltratingLymphocytes.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.tumorInfiltratingLymphocytes.$dirty ? !$v.form.otherMainHistopatologicFeatures.tumorInfiltratingLymphocytes.$error : null"
                  :options="tumorInfiltratingLymphocytesOptions"
                ></b-form-select>
                <b-form-text>{{$t('tumorInfiltratingLymphocytes_desc')}}</b-form-text>

                <!-- associatedNevus -->
                <label for="associatedNevus" class="mt-4">
                  <h6>{{'10.g. '+$t('associatedNevus_label')}}</h6>
                </label>
                <b-form-input
                  v-model="$v.form.otherMainHistopatologicFeatures.associatedNevus.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.associatedNevus.$dirty ? !$v.form.otherMainHistopatologicFeatures.$error : null"
                  type="text"
                ></b-form-input>
                <b-form-text>{{$t('associatedNevus_desc')}}</b-form-text>

                <!-- vascularInvasion -->
                <label for="vascularInvasion" class="mt-4">
                  <h6>{{'10.h. '+$t('vascularInvasion_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.otherMainHistopatologicFeatures.vascularInvasion.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.vascularInvasion.$dirty ? !$v.form.otherMainHistopatologicFeatures.vascularInvasion.$error : null"
                  :options="vascularInvasionOptions"
                ></b-form-select>
                <b-form-text>{{$t('vascularInvasion_desc')}}</b-form-text>

                <!-- microsatellitosis -->
                <label for="microsatellitosis" class="mt-4">
                  <h6>{{'10.i. '+$t('microsatellitosis_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.otherMainHistopatologicFeatures.microsatellitosis.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.microsatellitosis.$dirty ? !$v.form.otherMainHistopatologicFeatures.microsatellitosis.$error : null"
                  :options="microsatellitosisOptions"
                ></b-form-select>
                <b-form-text>{{$t('microsatellitosis_desc')}}</b-form-text>

                <!-- pigmentation -->
                <label for="pigmentation" class="mt-4">
                  <h6>{{'10.j. '+$t('pigmentation_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.otherMainHistopatologicFeatures.pigmentation.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.pigmentation.$dirty ? !$v.form.otherMainHistopatologicFeatures.pigmentation.$error : null"
                  :options="pigmentationOptions"
                ></b-form-select>
                <b-form-text>{{$t('pigmentation_desc')}}</b-form-text>

                <!-- solarElastosis -->
                <label for="solarElastosis" class="mt-4">
                  <h6>{{'10.k. '+$t('solarElastosis_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.otherMainHistopatologicFeatures.solarElastosis.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.solarElastosis.$dirty ? !$v.form.otherMainHistopatologicFeatures.solarElastosis.$error : null"
                  :options="solarElastosisOptions"
                ></b-form-select>
                <b-form-text>{{$t('solarElastosis_desc')}}</b-form-text>

                <!-- lateralMarginStatus -->
                <label for="lateralMarginStatus" class="mt-4">
                  <h6>{{'10.l. '+$t('lateralMarginStatus_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.otherMainHistopatologicFeatures.lateralMarginStatus.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.lateralMarginStatus.$dirty ? !$v.form.otherMainHistopatologicFeatures.lateralMarginStatus.$error : null"
                  :options="lateralMarginStatusOptions"
                ></b-form-select>
                <b-form-text>{{$t('lateralMarginStatus_desc')}}</b-form-text>

                <!-- deepMarginStatus -->
                <label for="deepMarginStatus" class="mt-4">
                  <h6>{{'10.m. '+$t('deepMarginStatus_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.otherMainHistopatologicFeatures.deepMarginStatus.$model"
                  :state="$v.form.otherMainHistopatologicFeatures.deepMarginStatus.$dirty ? !$v.form.otherMainHistopatologicFeatures.deepMarginStatus.$error : null"
                  :options="deepMarginStatusOptions"
                ></b-form-select>
                <b-form-text>{{$t('deepMarginStatus_desc')}}</b-form-text>

                <!-- sentinelLymphNodebiopsyDone -->
                <label for="sentinelLymphNodebiopsyDone" class="mt-4">
                  <h6>{{'11. '+$t('sentinelLymphNodebiopsyDone_label')}}</h6>
                </label>
                <b-form-select
                  @input="$v.form.sentinelLymphNodebiopsyResult.$model = null"
                  v-model="$v.form.sentinelLymphNodebiopsyDone.$model"
                  :state="$v.form.sentinelLymphNodebiopsyDone.$dirty ? !$v.form.sentinelLymphNodebiopsyDone.$error : null"
                  :options="sentinelLymphNodebiopsyDoneOptions"
                ></b-form-select>
                <b-form-text>{{$t('sentinelLymphNodebiopsyDone_desc')}}</b-form-text>

                <span v-if="form.sentinelLymphNodebiopsyDone == 'Yes'">
                  <!-- sentinelLymphNodebiopsyResult -->
                  <label for="sentinelLymphNodebiopsyResult" class="mt-4">
                    <h6>{{'11.a. '+$t('sentinelLymphNodebiopsyResult_label')}}</h6>
                  </label>
                  <b-form-select
                    v-model="$v.form.sentinelLymphNodebiopsyResult.$model"
                    :state="$v.form.sentinelLymphNodebiopsyResult.$dirty ? !$v.form.sentinelLymphNodebiopsyResult.$error : null"
                    :options="sentinelLymphNodebiopsyResultOptions"
                  ></b-form-select>
                  <b-form-text>{{$t('sentinelLymphNodebiopsyResult_desc')}}</b-form-text>
                </span>

                <!-- ajccStageAtDiagnosis -->
                <label for="ajccStageAtDiagnosis" class="mt-4">
                  <h6>{{'12. '+$t('ajccStageAtDiagnosis_label')}}</h6>
                </label>
                <b-form-input
                  v-model="$v.form.ajccStageAtDiagnosis.$model"
                  :state="$v.form.ajccStageAtDiagnosis.$dirty ? !$v.form.ajccStageAtDiagnosis.$error : null"
                  type="text"
                  required
                ></b-form-input>
                <b-form-text>{{$t('ajccStageAtDiagnosis_desc')}}</b-form-text>

                <!-- mutationalData -->
                <label class="mt-4">
                  <h6>{{'13. '+$t('mutationalData_label')}}</h6>
                </label>
                <b-form-text>{{$t('mutationalData_desc')}}</b-form-text>

                <!-- gene -->
                <label for="gene" class="mt-4">
                  <h6>{{'13.a. '+$t('gene_label')}}</h6>
                </label>
                <b-form-select
                  v-model="$v.form.mutationalData.gene.$model"
                  :state="$v.form.mutationalData.gene.$dirty ? !$v.form.mutationalData.gene.$error : null"
                  :options="geneOptions"
                ></b-form-select>
                <b-form-text>{{$t('gene_desc')}}</b-form-text>

                <!-- sourceOfData -->
                <label for="sourceOfData" class="mt-4">
                  <h6>{{'13.b. '+$t('sourceOfData_label')}}</h6>
                </label>
                <b-form-select
                  @input="$v.form.mutationalData.site.$model = null"
                  v-model="$v.form.mutationalData.sourceOfData.$model"
                  :state="$v.form.mutationalData.sourceOfData.$dirty ? !$v.form.mutationalData.sourceOfData.$error : null"
                  :options="sourceOfDataOptions"
                ></b-form-select>
                <b-form-text>{{$t('sourceOfData_desc')}}</b-form-text>

                <span v-if="$v.form.mutationalData.sourceOfData.$model == 'Metastatic tissue'">
                  <!-- site -->
                  <label for="site" class="mt-4">
                    <h6>{{'13.c. '+$t('.mutationalDataSite_label')}}</h6>
                  </label>
                  <b-form-select
                    v-model="$v.form.mutationalData.site.$model"
                    :state="$v.form.mutationalData.site.$dirty ? !$v.form.mutationalData.site.$error : null"
                    :options="mutationalDataSiteOptions"
                  ></b-form-select>
                  <b-form-text>{{$t('.mutationalDataSite_desc')}}</b-form-text>
                </span>
              </span>

              <!-- buttons -->
              <hr class="mt-5" />
              <b-button
                type="reset"
                variant="outline-danger"
                class="float-right mb-1"
              >{{$t('reset')}}</b-button>
              <b-button
                type="submit"
                :disabled="!canSubmit"
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
import {
  ADD_REST_ERROR,
  ADD_MESSAGE,
  ADD_ERROR,
  INSERT_D,
  GET_QUESTIONNAIRE
} from "@/store/actions.type";
import { required, requiredIf, numeric } from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "d",
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
        primaryTumorKnown: false,
        site: null,
        breslowThinkness: "",
        otherMainHistopatologicFeatures: {
          subtype: null,
          mitoticRate: "",
          ulceration: null,
          tumorGrowthPhase: null,
          regression: null,
          tumorInfiltratingLymphocytes: null,
          associatedNevus: "",
          vascularInvasion: null,
          microsatellitosis: null,
          pigmentation: null,
          solarElastosis: null,
          lateralMarginStatus: null,
          deepMarginStatus: null
        },
        sentinelLymphNodebiopsyDone: null,
        sentinelLymphNodebiopsyResult: null,
        mutationalData: {
          gene: null,
          sourceOfData: null,
          site: null
        }
      },

      mutationalDataSiteOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Skin", text: this.$t("skin") },
        { value: "Lymph node", text: this.$t("lymph_node") },
        { value: "Brain", text: this.$t("brain") },
        { value: "Lung", text: this.$t("lung") },
        { value: "Other", text: this.$t("other") }
      ],
      sourceOfDataOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Primary tissue", text: this.$t("primary_tissue") },
        { value: "Metastatic tissue", text: this.$t("metastatic_tissue") }
      ],
      geneOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "BRAF", text: "BRAF" },
        { value: "NRAS", text: "NRAS" },
        { value: "KIT", text: "KIT" },
        { value: "Others", text: this.$t("others") }
      ],
      sentinelLymphNodebiopsyResultOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Positive", text: this.$t("positive") },
        { value: "Negative", text: this.$t("negative") }
      ],
      sentinelLymphNodebiopsyDoneOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Yes", text: this.$t("yes") },
        { value: "No", text: this.$t("no") },
        { value: "Not done", text: this.$t("not_done") },
        { value: "Not known", text: this.$t("not_known") }
      ],
      deepMarginStatusOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Disease-free", text: this.$t("absent") },
        { value: "Not disease-free", text: this.$t("not_disease-free") }
      ],
      lateralMarginStatusOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Disease-free", text: this.$t("absent") },
        { value: "Not disease-free", text: this.$t("not_disease-free") }
      ],
      solarElastosisOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Absent", text: this.$t("absent") },
        { value: "Mild", text: this.$t("mild") },
        { value: "Moderate", text: this.$t("moderate") },
        { value: "Severe", text: this.$t("severe") }
      ],
      pigmentationOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Absent", text: this.$t("absent") },
        { value: "Partially pigmented", text: this.$t("partially_pigmented") },
        { value: "Fully pigmented", text: this.$t("fully_pigmented") }
      ],
      microsatellitosisOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Absent", text: this.$t("absent") },
        { value: "Present", text: this.$t("present") }
      ],
      vascularInvasionOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Absent", text: this.$t("absent") },
        { value: "Present", text: this.$t("present") }
      ],
      tumorInfiltratingLymphocytesOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Absent", text: this.$t("absent") },
        { value: "Non-brisk", text: this.$t("non-brisk") },
        { value: "Brisk", text: this.$t("brisk") }
      ],
      regressionOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Absent", text: this.$t("absent") },
        { value: "<50%", text: "<50%" },
        { value: ">50%", text: ">50%" }
      ],
      tumorGrowthPhaseOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Radial", text: this.$t("radial") },
        { value: "Vertical", text: this.$t("vertical") }
      ],
      ulcerationOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Absent", text: this.$t("absent") },
        { value: "Present", text: this.$t("present") }
      ],
      subtypeOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "SSM", text: "SSM" },
        { value: "NM", text: "NM" },
        { value: "LMM", text: "LMM" },
        { value: "ALM", text: "ALM" },
        { value: "Desmoplastic", text: this.$t("desmoplastic") },
        { value: "Mucosal", text: this.$t("mucosal") },
        { value: "Uveal", text: this.$t("uveal") },
        { value: "Other", text: this.$t("other") },
        { value: "NOS", text: "NOS" }
      ],
      siteOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "abdomen_left", text: "abdomen_left" },
        { value: "abdomen_middle", text: "abdomen_middle" },
        { value: "abdomen_right", text: "abdomen_right" },
        { value: "anus", text: "anus" },
        { value: "arm_left_anterior", text: "arm_left_anterior" },
        { value: "arm_left_posterior", text: "arm_left_posterior" },
        { value: "arm_right_anterior", text: "arm_right_anterior" },
        { value: "arm_right_posterior", text: "arm_right_posterior" },
        { value: "axilla_left", text: "axilla_left" },
        { value: "axilla_right", text: "axilla_right" },
        { value: "back_left", text: "back_left" },
        { value: "back_middle", text: "back_middle" },
        { value: "back_righ", text: "back_righ" },
        { value: "buttock_left", text: "buttock_left" },
        { value: "buttock_right", text: "buttock_right" },
        { value: "cheek_left", text: "cheek_left" },
        { value: "cheek_right", text: "cheek_right" },
        { value: "chest_left", text: "chest_left" },
        { value: "chest_middle", text: "chest_middle" },
        { value: "chest_right", text: "chest_right" },
        { value: "chin", text: "chin" },
        { value: "ear_left", text: "ear_left" },
        { value: "ear_right", text: "ear_right" },
        { value: "eye_left", text: "eye_left" },
        { value: "eye_right", text: "eye_right" },
        { value: "eyelid_left", text: "eyelid_left" },
        { value: "eyelid_right", text: "eyelid_right" },
        { value: "finger_left", text: "finger_left" },
        { value: "finger_right", text: "finger_right" },
        { value: "foot_left_dorsal", text: "foot_left_dorsal" },
        { value: "foot_left_plantar", text: "foot_left_plantar" },
        { value: "foot_right_dorsal", text: "foot_right_dorsal" },
        { value: "foot_right_plantar", text: "foot_right_plantar" },
        {
          value: "forearm_left_anterior",
          text: "forearm_left_anterior"
        },
        {
          value: "forearm_left_posterior",
          text: "forearm_left_posterior"
        },
        {
          value: "forearm_right_anterior",
          text: "forearm_right_anterior"
        },
        {
          value: "forearm_right_posterior",
          text: "forearm_right_posterior"
        },
        { value: "forehead", text: "forehead" },
        { value: "hand_left_dorsal", text: "hand_left_dorsal" },
        { value: "hand_left_palmar", text: "hand_left_palmar" },
        { value: "hand_right_dorsal", text: "hand_right_dorsal" },
        { value: "hand_right_palmar", text: "hand_right_palmar" },
        { value: "head", text: "head" },
        { value: "laterocervical_left", text: "laterocervical_left" },
        {
          value: "laterocervical_right",
          text: "laterocervical_right"
        },
        { value: "leg_left_anterior", text: "leg_left_anterior" },
        { value: "leg_left_posterior", text: "leg_left_posterior" },
        { value: "leg_right_anterior", text: "leg_right_anterior" },
        { value: "leg_right_posterior", text: "leg_right_posterior" },
        { value: "lip", text: "lip" },
        { value: "lumbar", text: "lumbar" },
        { value: "nail_finger_left", text: "nail_finger_left" },
        { value: "nail_finger_right", text: "nail_finger_right" },
        { value: "nail_toe_left", text: "nail_toe_left" },
        { value: "nail_toe_right", text: "nail_toe_right" },
        { value: "nose", text: "nose" },
        { value: "penis_scrotum", text: "penis_scrotum" },
        { value: "scalp", text: "scalp" },
        { value: "shoulder_left", text: "shoulder_left" },
        { value: "shoulder_right", text: "shoulder_right" },
        { value: "thigh_left_anterior", text: "thigh_left_anterior" },
        {
          value: "thigh_left_posterior",
          text: "thigh_left_posterior"
        },
        {
          value: "thigh_right_anterior",
          text: "thigh_right_anterior"
        },
        {
          value: "thigh_right_posterior",
          text: "thigh_right_posterior"
        },
        { value: "toe_left", text: "toe_left" },
        { value: "toe_right", text: "toe_right" },
        { value: "vagina", text: "vagina" },
        { value: "vulva", text: "vulva" },
        { value: "other", text: "other" }
      ],
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
      canProceed: false,
      canSubmit: true
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
    if (!this.dbCodeNumber) {
      this.$store.dispatch(ADD_ERROR, "no_db_code_number");
      this.$router.push({ name: "home" });
    }
    this.$store
      .dispatch(GET_QUESTIONNAIRE, this.dbCodeNumber)
      .then(data => {
        if (data.data.d) {
          this.form.identifier =
            this.dbCodeNumber + "D" + (data.data.d.length + 1);
        } else {
          this.form.identifier = this.dbCodeNumber + "D1";
        }
      })
      .catch(() => {
        this.$store.dispatch(ADD_ERROR, "cannot_retrieve_questionnaire");
      });
  },
  methods: {
    // resets fields 8-13
    primaryTumorKnownFalseTrigger() {
      this.form.site = null;
      this.form.breslowThinkness = "";
      this.form.otherMainHistopatologicFeatures = {
        subtype: null,
        mitoticRate: "",
        ulceration: null,
        tumorGrowthPhase: null,
        regression: null,
        tumorInfiltratingLymphocytes: null,
        associatedNevus: "",
        vascularInvasion: null,
        microsatellitosis: null,
        pigmentation: null,
        solarElastosis: null,
        lateralMarginStatus: null,
        deepMarginStatus: null
      };
      this.form.sentinelLymphNodebiopsyDone = null;
      this.form.sentinelLymphNodebiopsyResult = null;
      this.form.ajccStageAtDiagnosis = "";
      this.form.mutationalData = {
        gene: null,
        sourceOfData: null,
        site: null
      };
    },
    addNewD() {
      if (this.canProceed) {
        window.location.reload();

        //this.$router.push({ name: "d" });
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
        this.$store
          .dispatch(INSERT_D, this.form)
          .then(() => {
            this.$store.dispatch(ADD_MESSAGE, "form_success");
            that.canProceed = true;
            that.canSubmit = false;
          })
          .catch(response => {
            this.$store.dispatch(ADD_REST_ERROR, response);
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

      this.form.preExistingPigmentedLesionAtTheSameSiteOfMelanoma = "";
      this.preExistingPigmentedLesionAtTheSameSiteOfMelanomaToggle = "no";
      this.form.detectionOfMelanoma = null;
      this.form.selfSkinExam = null;
      this.form.skinExamByPhysician = null;
      this.form.numberOfMPMToggle = false;
      this.form.dateOfDiagnosis = this.$moment().format("DD/MMM/YYYY");
      this.form.primaryTumorKnown = false;
      this.form.site = null;
      this.form.breslowThinkness = "";
      this.form.otherMainHistopatologicFeatures = {
        subtype: null,
        mitoticRate: "",
        ulceration: null,
        tumorGrowthPhase: null,
        regression: null,
        tumorInfiltratingLymphocytes: null,
        associatedNevus: "",
        vascularInvasion: null,
        microsatellitosis: null,
        pigmentation: null,
        solarElastosis: null,
        lateralMarginStatus: null,
        deepMarginStatus: null
      };
      this.form.sentinelLymphNodebiopsyDone = null;
      this.form.sentinelLymphNodebiopsyResult = null;
      this.form.ajccStageAtDiagnosis = "";
      this.form.mutationalData = {
        gene: null,
        sourceOfData: null,
        site: null
      };
      this.$v.$reset();

      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });

      window.scrollTo(0, 0);
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
          return (
            this.preExistingPigmentedLesionAtTheSameSiteOfMelanomaToggle ==
            "yes"
          );
        })
      },
      detectionOfMelanoma: { required },
      selfSkinExam: { required },
      skinExamByPhysician: { required },
      numberOfMPM: {
        numeric,
        required: requiredIf(function() {
          return this.numberOfMPMToggle;
        })
      },
      dateOfDiagnosis: { required },
      primaryTumorKnown: { required },
      site: {
        required: requiredIf(function() {
          return this.form.primaryTumorKnown;
        })
      },
      breslowThinkness: {
        required: requiredIf(function() {
          return this.form.primaryTumorKnown;
        }),

        numeric
      },
      otherMainHistopatologicFeatures: {
        subtype: {
          required: requiredIf(function() {
            return this.form.primaryTumorKnown;
          })
        },
        mitoticRate: {
          required: requiredIf(function() {
            return this.form.primaryTumorKnown;
          }),
          numeric
        },
        ulceration: {
          required: requiredIf(function() {
            return this.form.primaryTumorKnown;
          })
        },
        tumorGrowthPhase: {
          required: requiredIf(function() {
            return this.form.primaryTumorKnown;
          })
        },
        regression: {
          required: requiredIf(function() {
            return this.form.primaryTumorKnown;
          })
        },
        tumorInfiltratingLymphocytes: {
          required: requiredIf(function() {
            return this.form.primaryTumorKnown;
          })
        },
        associatedNevus: {},
        vascularInvasion: {
          required: requiredIf(function() {
            return this.form.primaryTumorKnown;
          })
        },
        microsatellitosis: {
          required: requiredIf(function() {
            return this.form.primaryTumorKnown;
          })
        },
        pigmentation: {
          required: requiredIf(function() {
            return this.form.primaryTumorKnown;
          })
        },
        solarElastosis: {
          required: requiredIf(function() {
            return this.form.primaryTumorKnown;
          })
        },
        lateralMarginStatus: {
          required: requiredIf(function() {
            return this.form.primaryTumorKnown;
          })
        },
        deepMarginStatus: {
          required: requiredIf(function() {
            return this.form.primaryTumorKnown;
          })
        }
      },
      sentinelLymphNodebiopsyDone: {
        required: requiredIf(function() {
          return this.form.primaryTumorKnown;
        })
      },
      sentinelLymphNodebiopsyResult: {
        required: requiredIf(function() {
          return this.form.sentinelLymphNodebiopsyDone == "Yes";
        })
      },
      ajccStageAtDiagnosis: {
        required: requiredIf(function() {
          return this.form.primaryTumorKnown;
        })
      },
      mutationalData: {
        gene: {},
        sourceOfData: {},
        site: {
          required: requiredIf(function() {
            return this.form.mutationalData.sourceOfData == "Metastatic tissue";
          })
        }
      }
    }
  },
  computed: {
    ...mapState({
      user: state => state.auth.user,
      isAuthenticated: state => state.auth.isAuthenticated,
      dbCodeNumber: state => state.questionnaire.dbCodeNumber
    })
  }
};
</script>