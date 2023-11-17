<template>
    <div>
      <textarea v-model="comment"></textarea>
      <button @click="submitComment">Submit</button>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        comment: "",
      };
    },
    methods: {
      submitComment() {
        // Submit the comment to the server
      },
    },
  };
  </script>