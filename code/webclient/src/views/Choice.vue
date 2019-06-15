<template>
  <div class="home container my-5">
    <Message/>

    <b-card id="welcome-card">
      <div class="card-body d-flex flex-column">
        <b-card-title>{{$t('section_selection')}}</b-card-title>
        <b-card-text>{{$t('choose_section')}}</b-card-text>
        <div>
          <SectionButton type="A" variant="outline-info" v-bind:subsections="{1:'I', 2:'II'}"/>
          <SectionButton
            type="B"
            variant="outline-info"
            v-bind:subsections="{1:'I', 2:'II', 3:'III', 'e':'evaluation'}"
          />
          <SectionButton
            type="C"
            variant="outline-info"
            v-bind:subsections="{1:'I', 2:'II', 3:'III', 'e':'evaluation'}"
          />
          <b-button
            variant="outline-info"
            class="mr-2 mb-1 mt-3"
            :to="'/insert/d'"
          >{{$t('section_d')}}</b-button>
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
import { mapState } from "vuex";
import Message from "@/components/Message.vue";
import SectionButton from "@/components/SectionButton.vue";

export default {
  name: "choice",
  data() {
    return {};
  },
  methods: {},
  components: {
    Message,
    SectionButton
  },
  computed: {
    ...mapState({
      isAuthenticated: state => state.auth.isAuthenticated,
      user: state => state.auth.user
    })
  }
};
</script>
