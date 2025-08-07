<script setup lang="ts">

</script>

<template>
    <el-container id="el-container">
        <el-aside class="mainpage-layout">
            <div class="aside block">

            </div>
        </el-aside>
        <el-container>
            <el-header class="mainpage-header">
                <div class="header block">

                </div>
            </el-header>
            <el-main class="mainpage-layout">
                <div class="main block">

                </div>
            </el-main>
        </el-container>

    </el-container>
</template>

<style scoped lang="less">
#el-container{
    height: 100%;
}
.mainpage-header{
    margin-top:20px ;
}
.mainpage-layout{
    height: 100%;
    padding: 20px;

}

.block{
    width: 100%;
    height: 100%;
    background-color: rgba(255, 255, 255, 0.72);
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}






</style>