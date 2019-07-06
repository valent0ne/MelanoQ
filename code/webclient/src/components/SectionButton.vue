<template>
  <b-dropdown :id="'dropdown-'+type" class="mr-2 mb-1 mt-3" :variant="variant">
    <template slot="button-content">{{$t('section_'+type.toLowerCase())}}&nbsp;</template>
    <slot v-for="(s, key) in subsections">
      <b-dropdown-item
        :disabled="checkPermissions(type, key)"
        :to="'/insert/'+type.toLowerCase()+key"
      >{{$t(s)}}</b-dropdown-item>
    </slot>
  </b-dropdown>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "SectionButton",
  props: {
    type: String,
    subsections: Object,
    variant: String
  },
  data() {
    return {};
  },
  methods: {
    checkPermissions(type, key) {
      var item = (type + key).toLowerCase();
      if (
        (item == "a1" ||
          item == "c1" ||
          item == "c2" ||
          item == "c3" ||
          item == "ce") &&
        this.user.type != "physician"
      ) {
        return true;
      }
    }
  },
  computed: {
    ...mapState({
      isAuthenticated: state => state.auth.isAuthenticated,
      user: state => state.auth.user
    })
  }
};
</script>

<style>
</style>
