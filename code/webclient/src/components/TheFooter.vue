<template>
  <footer id="footer">
    <nav class="navbar fixed-bottom footer">
      <div>
        <b-link href="http://www.univaq.it/en/">&copy; {{$t('univaq')}}</b-link>-
        <b-link href="https://www.disim.univaq.it/main/index.php">DISIM</b-link>
      </div>
      <div class="d-none d-md-block">
        <b-link href="https://www.linkedin.com/in/valentina-cecchini/">Valentina Cecchini</b-link>&nbsp;-
        <b-link href="https://www.linkedin.com/in/stefano-valentini/">Stefano Valentini</b-link>
      </div>
    </nav>
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

.footer {
  line-height: 40px !important;
}
</style>
