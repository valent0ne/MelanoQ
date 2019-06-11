<template>
  <b-dropdown :id="'dropdown-'+type" class="mr-2 mb-1 mt-3" :variant="variant">
    <template slot="button-content">{{$t('section_'+type.toLowerCase())}}</template>
    <slot v-for="(s, key) in subsections">
      <b-dropdown-item :to="'/insert/'+type.toLowerCase()+key">{{$t(s)}}</b-dropdown-item>
    </slot>
  </b-dropdown>
</template>

<script>
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
  methods: {}
};
</script>

<style>
</style>
