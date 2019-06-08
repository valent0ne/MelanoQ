<template>
  <div class="home container my-5">
    <Message/>

    <b-card id="welcome-card">
      <div class="card-body d-flex flex-column">
        <b-card-title>{{$t('section_selection')}}</b-card-title>
        <b-card-text>{{$t('choose_section')}}</b-card-text>
        <div>
          <SectionButton type="A" v-bind:subsections="{1:'I', 2:'II'}"/>
          <SectionButton
            type="B"
            v-bind:subsections="{1:'I', 2:'II', 3:'III', 'evaluation':'evaluation'}"
          />
          <SectionButton
            type="C"
            v-bind:subsections="{1:'I', 2:'II', 3:'III', 'evaluation':'evaluation'}"
          />
          <SectionButton type="D" v-bind:subsections="{1:'I'}"/>
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
