<template>
  <div class="dashboard-editor-container">
    <panel-group />

    <el-row style="background: #fff; padding: 32px 16px 0">
      <line-chart :height="lineHeight" />
    </el-row>
  </div>
</template>

<script>
import PanelGroup from "./dashboard/PanelGroup";
import LineChart from "./dashboard/LineChart";

export default {
  name: "Index",
  components: {
    PanelGroup,
    LineChart,
  },
  data() {
    return {
      // 350头部，70提示框
      // 如果设置隐藏top-tags，则还需要减去35
      lineHeight: document.documentElement.clientHeight - 350 + "px", //实时屏幕高度
    };
  },
  methods: {},
};
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: #fff;
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
