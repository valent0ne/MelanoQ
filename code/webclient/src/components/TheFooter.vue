<template>
  <footer if="footer" class="footer">
    <div class="container d-flex justify-content-between">
      <div>
        <b-link href="http://www.univaq.it/en/">&copy; {{$t('univaq')}}</b-link> -
        <b-link href="https://www.disim.univaq.it/main/index.php">DISIM</b-link>
      </div>
      <div>
        <b-link href="https://www.linkedin.com/in/valentina-cecchini/">Valentina Cecchini</b-link> -
        <b-link href="https://www.linkedin.com/in/stefano-valentini/">Stefano Valentini</b-link>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "TheFooter"
};
</script>

<style scoped>
a {
  color: rgba(255, 255, 255, 0.5) !important;
}

a:hover {
  color: rgba(255, 255, 255, 0.75) !important;
}
</style>
