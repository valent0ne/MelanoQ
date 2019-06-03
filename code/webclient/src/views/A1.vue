<template>
  <!-- https://bootstrap-vue.js.org/docs/components/form -->
  <div>
    <div class="home container my-5">
      <Message/>
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>{{$t('section_a')+'.I'}}</b-card-title>
        </div>
        <div>
          <!-- form here -->
        </div>
      </b-card>
    </div>
  </div>
</template>
<script>
import Message from "@/components/Message.vue";

export default {
  name: "a1",
  components: {
    Message
  },
  props: {
    action: String
  }
};
</script>