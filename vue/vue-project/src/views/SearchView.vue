<template>
    <div class="search-bar">
      <div class="search-options">
        <button :style="{ backgroundColor: store.type === 'board' ? '#ff6961' : '' }" @click="selectType('board')">이용 후기</button>
        <button :style="{ backgroundColor: store.type === 'video' ? '#ff6961' : '' }" @click="selectType('video')">비디오</button>
      </div>
  
      <label>검색 기준 :</label>
      <select v-model="searchInfo.key" v-if="store.type === 'board'" class="select-options">
        <option value="title">제목</option>
        <option value="user" selected>닉네임</option>
        <option value="content">내용</option>
      </select>
      <select v-model="searchInfo.key" v-else class="select-options">
        <option value="title">제목</option>
        <option value="sort">부위</option>
      </select>
  
      <div class="search-input">
        <label>검색 내용 :</label>
        <input type="text" v-model="searchInfo.word" />
      </div>
      
      <div class="search-button">
        <button @click="searchList">검색</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  import { useSearchStore } from '@/stores/search';
  
  const store = useSearchStore();
  const router = useRouter();
  const searchInfo = ref({
    key: 'title',
    word: '',
  });
  
  const searchList = function () {
    if (searchInfo.value.word === '') {
      alert('검색어를 입력해주세요');
      return;
    }

    if(store.type !== 'board' && store.type !== 'video'){
        alert('검색 유형을 설정해주세요')
        return;
    }
  
    if (store.type === 'board') {
      store.searchBoardList(searchInfo.value);
    } else {
      store.searchVideoList(searchInfo.value);
    }
    searchInfo.value.word = '';
    router.push('/searchResult');
  };
  
  const selectType = function (str) {
    store.type = str;
    store.searchList = null;
  };
  </script>
  
  <style scoped>
  .search-bar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #f5f5f5;
    padding: 10px;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    width: 40vw;
  }
  
  .search-options {
    display: flex;
    align-items: center;
}

.search-options button {
    margin-right: 10px;
    background-color: #ddd;
    border: none;
    cursor: pointer;
    padding: 8px 12px;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.search-options button:hover {
    background-color: #e0e0e0;
}

.select-options {
    margin-right: 10px;
    width: 80px;
}

  .search-input input {
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-right: 10px;
    margin-left: 10px;
  }
  
  .search-button button {
    background-color: #2196F3;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .search-button button:hover {
    background-color: #1565c0;
  }
  </style>
  