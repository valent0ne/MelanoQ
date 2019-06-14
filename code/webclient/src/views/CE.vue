<template>
  <div>
    <div class="home container my-4">
      <Message/>
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>{{$t('section_c')+' - '+$t('evaluation')}}</b-card-title>

          <div>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <!-- complexityOfTheQuestionnaire -->
              <label for="complexityOfTheQuestionnaire" class="mt-4">
                <h6>{{'17. '+$t('complexityOfTheQuestionnaire_label')}}</h6>
              </label>
              <b-form-select
                id="complexityOfTheQuestionnaire"
                v-model="$v.form.complexityOfTheQuestionnaire.$model"
                :state="$v.form.complexityOfTheQuestionnaire.$dirty ? !$v.form.complexityOfTheQuestionnaire.$error : null"
                :options="complexityOfTheQuestionnaireOptions"
              ></b-form-select>
              <b-form-text>{{$t('complexityOfTheQuestionnaire_desc')}}</b-form-text>

              <!-- difficultQuestions -->
              <span class="form-inline mt-4">
                <label for="difficultQuestions" class="mt-4">
                  <h6>{{'18. '+$t('difficultQuestions_label')}}</h6>
                </label>

                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="difficultQuestionsToggle"
                    @click="difficultQuestionsToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!difficultQuestionsToggle"
                    @click="difficultQuestionsToggle = false; $v.form.difficultQuestions.$model=[{number:''}];"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>

              <b-form inline class="mt-2" v-if="difficultQuestionsToggle">
                <span v-for="(dq, index) in $v.form.difficultQuestions.$each.$iter" :key="index">
                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="dq.number.$model"
                    :state="dq.number.$dirty ? !dq.number.$error : null"
                    type="text"
                    required
                    :placeholder="$t('difficultQuestionsNumber_desc')"
                  ></b-form-input>
                </span>
                <span class="mb-2 mb-auto ml-auto">
                  <b-button variant="outline-success" @click="addDifficultQuestionNumberField()">
                    <font-awesome-icon icon="plus"/>
                  </b-button>
                  <b-button
                    variant="outline-danger"
                    @click="removeDifficultQuestionNumberField()"
                    class="ml-2"
                  >
                    <font-awesome-icon icon="minus"/>
                  </b-button>
                </span>
              </b-form>

              <!-- buttons -->
              <b-button
                type="submit"
                variant="outline-success"
                class="mr-2 mb-1 mt-5"
              >{{$t('submit')}}</b-button>
              <b-button type="reset" variant="outline-danger" class="mr-2 mb-1 mt-5">{{$t('reset')}}</b-button>

              <b-button
                type="button"
                to="/choice"
                variant="outline-info"
                class="fa-button-outline mb-1 mt-5 float-right"
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
import { ADD_MESSAGE, ADD_ERROR, INSERT_CE } from "@/store/actions.type";
import { required, between, requiredIf } from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "ce",
  data() {
    return {
      form: {
        complexityOfTheQuestionnaire: null,
        difficultQuestions: [{ number: "" }]
      },
      complexityOfTheQuestionnaireOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "All questions", text: this.$t("all_questions") },
        { value: "Most questions", text: this.$t("most_questions") },
        {
          value: "Nearly half of the questions",
          text: this.$t("nearly_half_of_the_questions")
        },
        { value: "Very few questions", text: this.$t("very_few_questions") }
      ],

      difficultQuestionsToggle: false,
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
    addDifficultQuestionNumberField() {
      this.form.difficultQuestions.push({
        number: ""
      });
    },
    removeDifficultQuestionNumberField() {
      if (this.form.difficultQuestions.length > 1) {
        this.form.difficultQuestions.pop();
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
        this.$store.dispatch(INSERT_CE, this.form).then(() => {
          this.$store.dispatch(ADD_MESSAGE, "form_success");
          that.canProceed = true;
        });
      }
    },
    onReset(evt) {
      evt.preventDefault();
      //TODO reset
      this.form.complexityOfTheQuestionnaire = null;
      this.form.difficultQuestions = [{ number: "" }];
      this.difficultQuestionsToggle = false;

      this.$v.$reset();
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    proceed() {
      /*if (this.canProceed) {
        this.$router.push({ name: "home" });
      }*/
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
      complexityOfTheQuestionnaire: { required },
      difficultQuestions: {
        $each: {
          number: {
            required: requiredIf(function() {
              return this.difficultQuestionsToggle;
            }),
            between: between(1, 16)
          }
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