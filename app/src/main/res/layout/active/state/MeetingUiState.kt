package com.ringcentral.video.quickstart.ui.active.state

import com.example.testapp.ui.active.state.AudioUiState
import com.example.testapp.ui.active.state.VideoUiState

data class MeetingUiState(
    val meetingId: String = "",
    val leaveMeeting: Boolean = false,
    val localVideoState: VideoUiState = VideoUiState(),
    val localAudioState: AudioUiState = AudioUiState(),
    val isHostOrModerator: Boolean = false
)