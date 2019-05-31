<template>
  <div class="home container my-3">
    <b-card>
      <div class="card-body d-flex flex-column">
        <b-card-title>{{$t('welcome_message_title')}}</b-card-title>
        <b-card-text>{{$t('welcome_message_body')}}</b-card-text>

        <div class="mt-4">
          <b-button href="#" variant="outline-info" class="mr-2">{{$t('case')}}</b-button>
          <b-button href="#" variant="outline-primary" class="mr-2">{{$t('control')}}</b-button>
          <b-button href="#" variant="outline-dark" class="mr-2">{{$t('physician')}}</b-button>
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>
