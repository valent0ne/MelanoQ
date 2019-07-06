<template>
  <div>
    <div class="home container my-4">
      <Message />
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>
            <span class="float-left mt-1">{{$t('section_b')+'.I'}}</span>
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
              <!-- skinType1 -->
              <label for="skinType1" class="mt-4">
                <h6>{{'1.a. '+$t('skinType1_desc')}}</h6>
              </label>
              <b-form-select
                id="skinType1"
                v-model="$v.form.skinType1.$model"
                :state="$v.form.skinType1.$dirty ? !$v.form.skinType1.$error : null"
                :options="skinType1Options"
              >{{$t('skinType1_desc')}}</b-form-select>

              <!-- skinType2 -->
              <label for="skinType2" class="mt-4">
                <h6>{{'1.b. '+$t('skinType2_desc')}}</h6>
              </label>
              <b-form-select
                id="skinType2"
                v-model="$v.form.skinType2.$model"
                :state="$v.form.skinType2.$dirty ? !$v.form.skinType2.$error : null"
                :options="skinType2Options"
              ></b-form-select>
              <b-img src="@/assets/images/jdv15208-sup-0001-figs1.png" fluid-grow class="mt-2"></b-img>
              <hr />

              <!-- eyeColor -->
              <label for="eyeColor" class="mt-4">
                <h6>{{'2. '+$t('eyeColor_label')}}</h6>
              </label>
              <b-form-select
                id="eyeColor"
                v-model="$v.form.eyeColor.$model"
                :state="$v.form.eyeColor.$dirty ? !$v.form.eyeColor.$error : null"
                :options="eyeColorOptions"
              ></b-form-select>
              <b-img src="@/assets/images/jdv15208-sup-0002-figs2.png" fluid-grow class="mt-2"></b-img>
              <b-form-text>{{$t('eyeColor_desc')}}</b-form-text>
              <hr />

              <!-- hairColor -->
              <label for="hairColor" class="mt-4">
                <h6>{{'3. '+$t('hairColor_label')}}</h6>
              </label>
              <b-form-select
                id="hairColor"
                v-model="$v.form.hairColor.$model"
                :state="$v.form.hairColor.$dirty ? !$v.form.hairColor.$error : null"
                :options="hairColorOptions"
              ></b-form-select>
              <b-img src="@/assets/images/jdv15208-sup-0003-figs3.png" fluid-grow class="mt-2"></b-img>
              <b-form-text>{{$t('hairColor_desc')}}</b-form-text>
              <hr />

              <!-- freckles -->
              <label for="freckles" class="mt-4">
                <h6>{{'4. '+$t('freckles_label')}}</h6>
              </label>
              <b-form-select
                id="freckles"
                v-model="$v.form.freckles.$model"
                :state="$v.form.freckles.$dirty ? !$v.form.freckles.$error : null"
                type="text"
                required
                :options="frecklesOptions"
              ></b-form-select>
              <b-img src="@/assets/images/jdv15208-sup-0004-figs4.png" fluid-grow class="mt-2"></b-img>
              <b-form-text>{{$t('freckles_desc')}}</b-form-text>
              <hr />

              <!-- neviInChildhoodAdolescence -->
              <label for="neviInChildhoodAdolescence" class="mt-4">
                <h6>{{'5. '+$t('neviInChildhoodAdolescence_label')}}</h6>
              </label>
              <b-form-select
                id="neviInChildhoodAdolescence"
                v-model="$v.form.neviInChildhoodAdolescence.$model"
                :state="$v.form.neviInChildhoodAdolescence.$dirty ? !$v.form.neviInChildhoodAdolescence.$error : null"
                type="text"
                required
                :options="neviInChildhoodAdolescenceOptions"
              ></b-form-select>
              <b-img src="@/assets/images/jdv15208-sup-0005-figs5.png" fluid-grow class="mt-2"></b-img>
              <b-form-text>{{$t('neviInChildhoodAdolescence_desc')}}</b-form-text>

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
import { ADD_MESSAGE, ADD_ERROR, INSERT_B1 } from "@/store/actions.type";
import { required } from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "b1",
  data() {
    return {
      form: {
        skinType1: null,
        skinType2: null,
        eyeColor: null,
        hairColor: null,
        freckles: null,
        neviInChildhoodAdolescence: null
      },
      skinType1Options: [
        { value: null, text: this.$t("please_select_option") },
        {
          value: "Not burn",
          text: this.$t("not_burn")
        },
        {
          value: "Burns lightly",
          text: this.$t("burns_lightly")
        },
        {
          value: "Burns moderately",
          text: this.$t("burns_moderately")
        },
        {
          value: "Burns severely",
          text: this.$t("burns_severely")
        }
      ],
      skinType2Options: [
        { value: null, text: this.$t("please_select_option") },
        {
          value: "No tan",
          text: this.$t("no_tan")
        },
        {
          value: "Tans lightly",
          text: this.$t("tans_lightly")
        },
        {
          value: "Tans moderately",
          text: this.$t("tans_moderately")
        },
        {
          value: "Tans deeply",
          text: this.$t("tans_deeply")
        }
      ],
      eyeColorOptions: [
        { value: null, text: this.$t("please_select_option") },
        {
          value: "Light (green, blue, gray)",
          text: this.$t("light_(green,_blue,_gray)")
        },
        {
          value: "Medium (light brown, hazel)",
          text: this.$t("medium_(light_brown,_hazel)")
        },
        {
          value: "Dark (dark brown, black)",
          text: this.$t("dark_(dark_brown,_black)")
        }
      ],
      hairColorOptions: [
        { value: null, text: this.$t("please_select_option") },
        {
          value: "Red",
          text: this.$t("red")
        },
        {
          value: "Blonde",
          text: this.$t("blonde")
        },
        {
          value: "Light brown",
          text: this.$t("light_brown")
        },
        {
          value: "Dark brown",
          text: this.$t("dark_brown")
        },
        {
          value: "Black",
          text: this.$t("black")
        }
      ],
      frecklesOptions: [
        { value: null, text: this.$t("please_select_option") },
        {
          value: "None",
          text: this.$t("none")
        },
        {
          value: "Few",
          text: this.$t("few")
        },
        {
          value: "Some",
          text: this.$t("some")
        },
        {
          value: "Many",
          text: this.$t("many")
        }
      ],
      neviInChildhoodAdolescenceOptions: [
        { value: null, text: this.$t("please_select_option") },
        {
          value: "None",
          text: this.$t("none")
        },
        {
          value: "Few",
          text: this.$t("few")
        },
        {
          value: "Some",
          text: this.$t("some")
        },
        {
          value: "Many",
          text: this.$t("many")
        }
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
        this.$store.dispatch(INSERT_B1, this.form).then(() => {
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
      this.form.skinType1 = null;
      this.form.skinType2 = null;
      this.form.eyeColor = null;
      this.form.hairColor = null;
      this.form.freckles = null;
      this.form.neviInChildhoodAdolescence = null;
      this.$v.$reset();
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    proceed() {
      if (this.canProceed) {
        this.$router.push({ name: "b2" });
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
      skinType1: { required },
      skinType2: { required },
      eyeColor: { required },
      hairColor: { required },
      freckles: { required },
      neviInChildhoodAdolescence: { required }
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