<template>
  <div>
    <div class="home container my-4">
      <Message />
      <b-card v-if="isAuthenticated" class="mb-4">
        <div class="card-body d-flex flex-column">
          <b-card-title>
            {{$t('questionnaire')}} {{localDbCodeNumber}}
            <b-button
              type="button"
              @click="setDepth(1)"
              variant="outline-danger"
              class="fa-button-outline float-right"
            >
              <font-awesome-icon icon="minus" />
            </b-button>
            <b-button
              type="button"
              @click="setDepth(100)"
              variant="outline-success"
              class="fa-button-outline mr-2 float-right"
            >
              <font-awesome-icon icon="plus" />
            </b-button>
          </b-card-title>
          <b-card-text class="line-break mt-2">{{$t('questionnaire_page_text')}}</b-card-text>
          <b-card bg-variant="light">
            <tree-view
              :key="treeViewComponentKey"
              :data="questionnaire"
              :options="{maxDepth: depth, rootObjectKey: 'questionnaire'}"
            ></tree-view>
          </b-card>
        </div>
      </b-card>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { ADD_ERROR, GET_QUESTIONNAIRE } from "@/store/actions.type";

import Message from "@/components/Message.vue";

export default {
  name: "questionnaire",

  data() {
    return {
      treeViewComponentKey: 0,
      questionnaire: null,
      depth: 1,
      localDbCodeNumber: this.$route.params.localDbCodeNumber
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
    this.$store
      .dispatch(GET_QUESTIONNAIRE, this.localDbCodeNumber)
      .then(data => {
        this.questionnaire = data.data;
      })
      .catch(() => {
        this.$store.dispatch(ADD_ERROR, "cannot_retrieve_questionnaire");
      });
  },
  methods: {
    setDepth(x) {
      this.depth = x;
      this.treeViewComponentKey += 1;
    }
  },
  components: {
    Message
  },
  computed: {
    ...mapState({
      user: state => state.auth.user,
      isAuthenticated: state => state.auth.isAuthenticated
    })
  }
};
</script>

