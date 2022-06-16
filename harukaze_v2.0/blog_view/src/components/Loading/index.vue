<template>
  <section
    class="home"
    @mousewheel.prevent
    @scroll.prevent
  >
    <figure>
      <div />
      <div />
      <div />
    </figure>
  </section>
</template>

<script lang="ts" setup>

</script>

<style scoped lang='scss'>
$X: 70deg;
$color: #1ec0f5;
$speed: 1s;

.home {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    // background: linear-gradient(#232f44, #000, .5);
    background-color: rgba(0,0,0,.5);
    z-index: 1000;
}

figure {
    width: 150px;
    height: 150px;
    transform: translate(-50%, -65%);
    margin: 0;
    position: absolute;
    left: 50%;
    top: 50%;
}
div {
    width: 100%;
    height: 100%;
    position: absolute;
}
div:after {
    content: "";
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    border-top: 25px solid $color;
    border-right: 5px solid $color;
    border-bottom: 10px solid transparent;
    border-left: 0px solid transparent;
    border-radius: 50%;
}

div:nth-child(1):after {
    animation: ring $speed linear infinite;
    transform: rotateX($X) rotateY(0deg) rotate(0deg);
}
div:nth-child(2):after {
    animation: ring2 $speed linear infinite;
    animation-delay: ($speed/-2);
    transform: rotateX($X) rotateY(60deg) rotate(0deg);
}
div:nth-child(3):after {
    animation: ring3 $speed linear infinite;
    animation-delay: ($speed/-3);
    transform: rotateX($X) rotateY(-60deg) rotate(0deg);
}

@keyframes ring {
    100% {
        transform: rotateX($X) rotateY(0deg) rotate(360deg);
    }
}
@keyframes ring2 {
    100% {
        transform: rotateX($X) rotateY(60deg) rotate(360deg);
    }
}
@keyframes ring3 {
    100% {
        transform: rotateX($X) rotateY(-60deg) rotate(360deg);
    }
}

</style>
