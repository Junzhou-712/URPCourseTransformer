var curSemClassSchedule = ''; //当前学期课表
var everySemClassSchedule = ''; //历年历学期课表

var setTimeOut_ = 4000;  // 设置脚本实际运行的开始时间，网络不好建议时间稍长，1000等于1s
(function () {
    'use strict';

    console.log("Script running...");
    unsafeWindow.addEventListener("load",main);
    
})();

function main() {
    var windowURL = window.location.href;
    if(windowURL.indexOf(ClassScheduleToICSURL)) {

    }
}

class Course {
    
}