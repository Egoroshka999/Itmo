window.addEventListener("load", () => {
    let video = document.getElementById("video");
    let button = document.querySelector("input[type=submit]");
    let video_wrapper = document.getElementById("video-wrapper");
    let flag = true;
    button.addEventListener("click", event => {
        flag = !flag;
        if (flag)
            return;
        event.preventDefault();
        video.currentTime = 0;
        video_wrapper.style.display = "block";
        video.play();
    })

    video.addEventListener("ended", () => {
        button.click();
    });
}) 