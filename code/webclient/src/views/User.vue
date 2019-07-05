<template>
  <div>
    <div class="home container my-4">
      <Message />
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>
            <span class="float-left mt-1">{{$t('add_new_user')}}</span>
          </b-card-title>
          <div>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <!-- username -->
              <label for="username" class="mt-4">
                <h6>{{$t('username_label')}}</h6>
              </label>
              <b-form-input
                id="username"
                v-model="$v.form.username.$model"
                :state="$v.form.username.$dirty ? !$v.form.username.$error : null"
                type="text"
                required
              ></b-form-input>

              <!-- password -->
              <label for="password" class="mt-4">
                <h6>{{$t('password_label')}}</h6>
              </label>
              <b-form-input name="password" v-model="form.password" type="password" required></b-form-input>

              <label for="role" class="mt-4">
                <h6>{{$t('role_label')}}</h6>
              </label>

              <b-form-select
                id="role"
                v-model="$v.form.role.$model"
                :state="$v.form.role.$dirty ? !$v.form.role.$error : null"
                :options="roleOptions"
              ></b-form-select>

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
import { ADD_MESSAGE, ADD_ERROR, INSERT_USER } from "@/store/actions.type";
import { required } from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "user",
  data() {
    return {
      form: {
        username: "",
        password: "",
        role: null
      },
      show: true,
      roleOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "physician", text: this.$t("physician") },
        { value: "nurse", text: this.$t("nurse") },
        { value: "researcher", text: this.$t("researcher") },
        { value: "admin", text: this.$t("admin") }
      ]
    };
  },
  created: function() {
    if (this.user.role !== "admin") {
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
        this.$store.dispatch(INSERT_USER, this.form).then(() => {
          this.$store.dispatch(ADD_MESSAGE, "form_success");
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
      this.form.username = "";
      this.form.password = "";
      this.form.role = null;
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
      username: { required },
      password: { required },
      role: { required }
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