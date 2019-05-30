<template>
  <div class="LocaleChanger">
    <b-row>
      <b-col sm>{{ $t('language') }}</b-col>
      <b-col sm>
      <b-form-select v-model="$i18n.locale" size="sm">
        <option v-for="(lang, i) in langs" :key="`Lang${i}`" :value="lang">{{ lang }}</option>
      </b-form-select>
      </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  name: "LocaleChanger",
  data() {
    return { langs: ["en", "it"] };
  }
};
</script>