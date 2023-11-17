<template>
    <div>
        <!-- 댓글 입력 폼 -->
        <textarea v-model="content"></textarea>
        <button @click="submitComment1">댓글 작성</button>

        <!-- 댓글 목록 -->
        <div v-if="commentList.length">
            <h3>댓글 목록</h3>
            <ul>
                <li v-for="(comment, index) in commentList" :key="index">
                    {{ comment.content }}
                    <button @click="editComment(index)">수정</button>
                    <button @click="deleteComment(comment.num)">삭제</button>
                </li>
            </ul>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useBoardStore } from "@/stores/board";
import { useUserStore } from "@/stores/user";
import { useCommentStore } from "@/stores/comment";
import axios from "axios"
import { useRouter } from "vue-router";


const userStore = useUserStore();
const boardStore = useBoardStore();
const commentStore = useCommentStore();
const router = useRouter();
const content = ref('');

const commentList = computed(()=> commentStore.comments);

console.log(commentList)
const submitComment1 = () => {
    console.log(userStore.loginUser)
    const comment = {
        board: boardStore.board,
        user: userStore.loginUser,
        content: content.value,
    };

    commentStore.submitComment(comment)
        .then(() => {
            alert("등록");
            content.value = ''; // 댓글 입력 필드 초기화
        })
        .catch((error) => {
            console.error(error.message); // 오류 메시지 출력
        });
};

const editComment = (index) => {
    // 해당 인덱스의 댓글 수정 기능 추가
    // 필요한 UI 변경 및 API 호출 등을 수행합니다.
};

const deleteComment =  (commentId) => {
    axios.delete(`http://localhost:8080/comment/${commentId}`)
        .then(async () => {
            await commentStore.deleteComment(commentId);
            alert("삭제되었습니다.");
            console.log(commentList)
        })
        .catch((error) => {
            console.error(error.message);
        });
};

</script>

<style scoped></style>