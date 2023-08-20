const cameraSelect = document.getElementById("cameras");
const microphoneSelect = document.getElementById("microphones");
const localVideo = document.getElementById("local-video");

const remoteIdInp = document.getElementById("remoteId");
const callBtn = document.getElementById("callBtn");

let localStream = null;

document.addEventListener("DOMContentLoaded", async function () {
  try {
    const devices = await navigator.mediaDevices.enumerateDevices();

    devices.forEach((device) => {
      if (device.kind === "videoinput") {
        const option = document.createElement("option");
        option.value = device.deviceId;
        option.text = device.label || "Camera " + (cameraSelect.length + 1);
        cameraSelect.appendChild(option);
      } else if (device.kind === "audioinput") {
        const option = document.createElement("option");
        option.value = device.deviceId;
        option.text =
          device.label || "Microphone " + (microphoneSelect.length + 1);
        microphoneSelect.appendChild(option);
      }
    });

    // Get media stream and display in local-video element
    localStream = await navigator.mediaDevices.getUserMedia({
      video: true,
      audio: true,
    });
    localVideo.srcObject = localStream;
  } catch (error) {
    console.error("Error getting devices:", error);
  }

  // Handle camera button click to toggle camera on/off
  const cameraBtn = document.getElementById("camera-btn");
  let isCameraOn = true;

  cameraBtn.addEventListener("click", async () => {
    if (localStream) {
      const videoTracks = localStream.getVideoTracks();
      videoTracks.forEach((track) => {
        if (isCameraOn) {
          track.enabled = false; // Turn off the camera
        } else {
          track.enabled = true; // Turn on the camera
        }
      });

      isCameraOn = !isCameraOn; // Toggle the camera status
    }
  });

  // Handle microphone button click to toggle mute/unmute
  const microphoneBtn = document.getElementById("microphone-btn");
  let isMicrophoneMuted = false;

  microphoneBtn.addEventListener("click", async () => {
    if (localStream) {
      const audioTracks = localStream.getAudioTracks();
      audioTracks.forEach((track) => {
        track.enabled = !isMicrophoneMuted; // Toggle mute/unmute
      });

      // Toggle the microphone status AFTER the track status is set
      isMicrophoneMuted = !isMicrophoneMuted;
    }
  });
});

cameraSelect.addEventListener("change", async () => {
  if (localStream) {
    localStream.getTracks().forEach((track) => track.stop()); // Stop current tracks
  }

  const selectedCamera = cameraSelect.value;
  localStream = await navigator.mediaDevices.getUserMedia({
    video: { deviceId: selectedCamera },
    audio: true,
  });
  localVideo.srcObject = localStream;
});

microphoneSelect.addEventListener("change", async () => {
  if (localStream) {
    localStream.getTracks().forEach((track) => track.stop()); // Stop current tracks
  }

  const selectedMicrophone = microphoneSelect.value;
  localStream = await navigator.mediaDevices.getUserMedia({
    video: true,
    audio: { deviceId: selectedMicrophone },
  });
  localVideo.srcObject = localStream;
});

const socket = new SockJS('/websocket-endpoint');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
  console.log('Connected: ' + frame);
  stompClient.subscribe('/topic/hello', function (greeting) {
    console.log(greeting.body)
  });
  stompClient.send('/app/hello', {}, "Test");
});
