$(function () {
  let mario = $("#mario");
  let enemy = $("#enemy");
  let missile = $("#missile");

  // 점프 중인지?
  let isJumping = false;
  let isGameOver = false;
  var isLaunching = false;
  let score = 0;

  $("#start").click(function () {
    // $("#gameover_screen").hide();
    $("#intro").hide();

    gameStart();
  });

  $("#restart").click(function () {
    isGameOver = false; // 게임 종료 상태 초기화
    score = 0; // 점수 초기화
    updateScore(score);
    // $("#missile").hide();
    $("#gameover_screen").hide();
    reset();

    $("#intro").show();
  });
  $("#gameover_screen").hide();
  $("#missile").hide();

  function gameStart() {
    // mario.css("bottom", "30px");
    setKeyboardEvent();
    enemyStart();

    checkGameOver();
  }

  function updateScore(score) {
    $("#score").text(score);
  }

  function isColliding(el1, el2) {
    const rect1 = el1.getBoundingClientRect();
    const rect2 = el2.getBoundingClientRect();

    return !(
      rect1.bottom < rect2.top ||
      rect1.top > rect2.bottom ||
      rect1.right < rect2.left ||
      rect1.left > rect2.right
    );
  }

  function checkGameOver() {
    setInterval(function () {
      console.log(`isGameOver:${isGameOver}`);

      if (isColliding(mario[0], enemy[0])) {
        isGameOver = true;
        // mario.stop();
        enemy.stop();
        missile.stop();

        $("#gameover_screen").show();
        $("#missile").hide();
      }

      if (isColliding(missile[0], enemy[0])) {
        enemy.css({ right: "-50px" });
        isLaunching = false;
        $("#missile").hide();
        score += 100;
        updateScore(score);
        reset();
        enemyStart();
      }
    }, 1000 / 60);
  }

  function getRandomNumber(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  function reset() {
    enemy.stop(); // 적 멈춤
    missile.stop(); // 미사일 멈춤
    enemy.css("right", "50px"); // 적 위치 초기화
    missile.css("left", "107px"); // 미사일 위치 초기화
  }

  function enemyStart() {
    // 속도 조절
    const speed = getRandomNumber(1000, 3000);
    enemy.css("right", "-50px");

    // 적이 오른쪽에서 왼쪽으로 이동
    enemy.animate({ right: "900px" }, speed, "linear", function () {
      //점수 올리자
      score += 100;
      updateScore(score);

      //적 리셋
      enemy.css("right", "-50px");
      enemyStart();
    });
  }

  function missileLaunch() {
    isLaunching = true;
    $("#missile").show();
    // 속도 조절
    const speed = 800;

    // 미사일이 왼쪽에서서 오른쪽으로 이동
    missile.animate({ left: "400px" }, speed, "linear", function () {
      //미사일 리셋
      missile.css("left", "107px").hide();
      isLaunching = false;
    });
  }

  function jump() {
    isJumping = true;
    mario
      .animate({ bottom: "+=80px" })
      .animate({ bottom: "-=80px" }, function () {
        isJumping = false;
      });
  }

  // 키보드 이벤트 정의
  function setKeyboardEvent() {
    $("html").keydown(function (e) {
      switch (e.key) {
        case "w":
          if (!isJumping) {
            jump();
          }
          break;
        case "d":
          if (!isLaunching) {
            missileLaunch(); // missileLaunch 함수 호출
          }
          break;
      }
      // console.log(e.key);
    });
  }
});
